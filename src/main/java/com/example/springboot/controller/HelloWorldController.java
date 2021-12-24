package com.example.springboot.controller;

import com.example.springboot.anno.RepeatSubmit;
import com.example.springboot.config.CustomerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lqb
 * @date 2021/12/17 14:25
 */
@RestController
@Slf4j
@RepeatSubmit
public class HelloWorldController {

    private CustomerConfig customerConfig;

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setCustomerConfig(CustomerConfig customerConfig) {
        this.customerConfig = customerConfig;
    }

    @RequestMapping("/helloWorld/{name}")
    @RepeatSubmit(seconds = 60)
    public String helloWorld(@PathVariable("name") String name) {
        return name;
    }

    @RequestMapping("/redisTemplate")
    public CustomerConfig redisTemplate(){
        redisTemplate.opsForValue().set("user",customerConfig);
        CustomerConfig user = (CustomerConfig) redisTemplate.opsForValue().get("user");
        return user;
    }
}
