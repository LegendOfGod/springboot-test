package com.example.springboot.aspect;

import com.example.springboot.anno.SwitchSource;
import com.example.springboot.datasource.DataSourceHolder;
import com.example.springboot.datasource.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author lqb
 * @date 2021/12/20 17:21
 */
@Aspect
@Slf4j
@Component
public class DataSourceAop {

    @Pointcut("@annotation(com.example.springboot.anno.SwitchSource)")
    public void pointcut(){
    }

    @Before("pointcut()")
    public void preOpt(JoinPoint joinPoint){
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        SwitchSource annotation = method.getAnnotation(SwitchSource.class);
        log.info("=======要切换的datasource是：{}",annotation.value());
        DataSourceHolder.setDataSources(annotation.value());
    }

    @After("pointcut()")
    public void afterOpt(){
        DataSourceHolder.clearDataSource();
        log.info("======还原到默认的数据源");
    }
}
