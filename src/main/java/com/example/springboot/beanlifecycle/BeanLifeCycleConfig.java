package com.example.springboot.beanlifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lqb
 * @date 2022/1/12 10:06
 */
@Configuration
public class BeanLifeCycleConfig {

    @Bean(initMethod = "initTest",destroyMethod = "destroyTest")
    public Car car(){
        return new Car();
    }
}
