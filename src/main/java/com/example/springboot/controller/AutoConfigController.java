package com.example.springboot.controller;

import com.example.springboot.autoconfig.MyCustomAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lqb
 * @date 2021/12/23 14:37
 */
@RestController
public class AutoConfigController {

    @Resource
    private MyCustomAutoConfiguration myCustomAutoConfiguration;

    @RequestMapping("/config")
    public MyCustomAutoConfiguration config(){
        return myCustomAutoConfiguration;
    }
}
