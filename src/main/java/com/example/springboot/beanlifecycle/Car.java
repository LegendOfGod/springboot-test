package com.example.springboot.beanlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author lqb
 * @date 2022/1/12 10:24
 */
public class Car implements InitializingBean, DisposableBean, BeanPostProcessor {
    public Car() {
        System.out.println("Car 创建");
    }

    public void initTest() {
        System.out.println(" .. init-method .. ");
    }

    public void destroyTest() {
        System.out.println(" .. destroyTest-method .. ");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(" .. InitializingBean .. ");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println(" .. DisposableBean .. ");
    }




    @PostConstruct
    public void postConstructTest() {
        System.out.println(" .. @PostConstruct .. ");
    }

    @PreDestroy
    public void preDestroyTest() {
        System.out.println(" .. @PreDestroy .. ");
    }

}
