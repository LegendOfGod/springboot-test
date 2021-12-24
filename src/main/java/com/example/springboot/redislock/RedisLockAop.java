package com.example.springboot.redislock;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * @author lqb
 * @date 2021/12/23 19:01
 */
@Aspect
@Component
@Slf4j
public class RedisLockAop {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final ConcurrentLinkedQueue<RedisLockDefinitionHolder> holderList = new ConcurrentLinkedQueue<>();
    private static final ScheduledExecutorService SCHEDULER = new ScheduledThreadPoolExecutor(1,new ThreadFactoryBuilder().setNameFormat("延时").build());

    @Pointcut("@annotation(com.example.springboot.redislock.RedisLockAnnotation)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        RedisLockAnnotation lockAnnotation = method.getAnnotation(RedisLockAnnotation.class);
        BusinessLockTypeEnum businessLockTypeEnum = lockAnnotation.businessType();
        Object[] args = joinPoint.getArgs();
        String key = args[lockAnnotation.defaultField()].toString();
        //redis加锁的key
        String businessKey = businessLockTypeEnum.getUniqueKey(key);
        String keyValue = UUID.randomUUID().toString();
        Object result = null;

        try {
            Boolean isSuccess = stringRedisTemplate.opsForValue().setIfAbsent(businessKey, keyValue);
            if (Boolean.FALSE.equals(isSuccess)) {
                throw new Exception("获取锁失败，加锁的key为:" + businessKey);
            }
            Thread currentThread = Thread.currentThread();
            stringRedisTemplate.expire(businessKey, lockAnnotation.lockTime(), TimeUnit.SECONDS);
            holderList.add(new RedisLockDefinitionHolder(businessKey, lockAnnotation.lockTime(), System.currentTimeMillis(), lockAnnotation.tryCount(), currentThread));
            joinPoint.proceed();
            // 两秒执行一次「续时」操作
            SCHEDULER.scheduleAtFixedRate(() -> {
                // 这里记得加 try-catch，否者报错后定时任务将不会再执行=-=
                Iterator<RedisLockDefinitionHolder> iterator = holderList.iterator();
                while (iterator.hasNext()) {
                    RedisLockDefinitionHolder holder = iterator.next();
                    // 判空
                    if (holder == null) {
                        iterator.remove();
                        continue;
                    }
                    // 判断 key 是否还有效，无效的话进行移除
                    if (stringRedisTemplate.opsForValue().get(holder.getBusinessKey()) == null) {
                        iterator.remove();
                        continue;
                    }
                    // 超时重试次数，超过时给线程设定中断
                    if (holder.getCurrentTryCount() > holder.getTotalTryCount()) {
                        holder.getThread().interrupt();
                        iterator.remove();
                        continue;
                    }
                    // 判断是否进入最后三分之一时间
                    long curTime = System.currentTimeMillis();
                    boolean shouldExtend = (holder.getLastModifyTime() + holder.getModifyPeriod()) <= curTime;
                    if (shouldExtend) {
                        holder.setLastModifyTime(curTime);
                        stringRedisTemplate.expire(holder.getBusinessKey(), holder.getLockTime(), TimeUnit.SECONDS);
                        log.info("businessKey : [" + holder.getBusinessKey() + "], try count : " + holder.getCurrentTryCount());
                        holder.setCurrentTryCount(holder.getCurrentTryCount() + 1);
                    }
                }
            }, 0, 2, TimeUnit.SECONDS);
            if (currentThread.isInterrupted()) {
                throw new InterruptedException("当前线程被强制终止");
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            //最终要释放锁
            stringRedisTemplate.delete(businessKey);
            log.info("释放锁：key:{}", businessKey);
        }
        return result;
    }


}
