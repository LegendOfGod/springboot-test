package com.example.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lqb
 * @date 2021/12/20 10:49
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private RepeatSubmitHandler repeatSubmitHandler;

    @Autowired
    public void setRepeatSubmitHandler(RepeatSubmitHandler repeatSubmitHandler) {
        this.repeatSubmitHandler = repeatSubmitHandler;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(repeatSubmitHandler);
    }
}
