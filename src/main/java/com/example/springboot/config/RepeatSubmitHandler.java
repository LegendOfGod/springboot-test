package com.example.springboot.config;

import com.example.springboot.anno.RepeatSubmit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lqb
 * @date 2021/12/20 10:18
 */
@Component
@Slf4j
public class RepeatSubmitHandler implements HandlerInterceptor {


    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            RepeatSubmit methodRepeatAnnotation = AnnotationUtils.findAnnotation(method.getMethod(), RepeatSubmit.class);
            RepeatSubmit controllerRepeatAnnotation = AnnotationUtils.findAnnotation(method.getMethod().getDeclaringClass(), RepeatSubmit.class);
            if (methodRepeatAnnotation == null && controllerRepeatAnnotation == null) {
                return true;
            }
            String uri = request.getRequestURI();
            StringBuffer url = request.getRequestURL();
            log.info("===========>拦截到请求uri:{}", uri);
            log.info("===========>拦截到请求url:{}", url.toString());
            Boolean absent = stringRedisTemplate.opsForValue().setIfAbsent(uri, uri, methodRepeatAnnotation != null ? methodRepeatAnnotation.seconds() : controllerRepeatAnnotation.seconds(), TimeUnit.SECONDS);
            if (absent != null && !absent) {
                throw new Exception("重复提交请稍后重试");
            }else {
                return true;
            }
        }
        return true;
    }
}
