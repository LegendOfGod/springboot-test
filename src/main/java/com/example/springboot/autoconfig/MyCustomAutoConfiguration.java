package com.example.springboot.autoconfig;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lqb
 * @date 2021/12/23 14:08
 */
@Component
@ConditionalOnProperty(prefix = "my.auto", name = "enabled", havingValue = "true", matchIfMissing = false)
@ConfigurationProperties(prefix = "my.auto")
@Data
public class MyCustomAutoConfiguration {
    private String name;
    private Integer age;
}
