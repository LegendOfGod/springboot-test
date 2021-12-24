package com.example.springboot.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lqb
 * @date 2021/12/17 14:46
 */
@Component
@Data
@ConfigurationProperties(prefix = "userinfo")
public class UserInfoConfig {

    private String userName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date birthDay;
    private List<String> hobbies;
    private Map<String,Object> maps;
}
