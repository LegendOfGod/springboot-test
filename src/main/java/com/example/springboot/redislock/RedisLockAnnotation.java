package com.example.springboot.redislock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lqb
 * @date 2021/12/23 18:57
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisLockAnnotation {
    /**
     * 加锁参数
     */
    int defaultField() default 0;

    /**
     * 超时重试次数 默认3
     */
    int tryCount() default 3;

    /**
     * 加锁时间 秒
     */
    long lockTime() default 30;

    /**
     * 业务类型
     */
    BusinessLockTypeEnum businessType();
}
