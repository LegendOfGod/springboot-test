package com.example.springboot.controller;

import com.example.springboot.dto.UserDTO;
import com.example.springboot.validationGroups.AddUserGroup;
import com.example.springboot.validationGroups.UpdateUserGroup;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lqb
 * @date 2021/12/21 9:55
 */
@RestController
public class ValidationController {

    @Resource
    private ObjectMapper objectMapper;

    @RequestMapping("/validateUser")
    public String validateUser(@Validated(UpdateUserGroup.class) UserDTO userDTO, BindingResult result) throws JsonProcessingException {
        if (result.hasErrors()) {
            Map<String, String> errorMsg = new HashMap<>();
            result.getFieldErrors().forEach(
                    item -> errorMsg.put(item.getField(), item.getDefaultMessage())
            );
            return objectMapper.writeValueAsString(errorMsg);
        }
        return "success";
    }
}
