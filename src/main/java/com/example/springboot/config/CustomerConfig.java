package com.example.springboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author lqb
 * @date 2021/12/17 15:28
 */
@Component
@Data
@ConfigurationProperties(prefix = "customer")
public class CustomerConfig implements Serializable {
    private String id;
    private String name;
    private String age;
}
