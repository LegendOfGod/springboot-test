package com.example.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author lqb
 * @date 2022/1/11 19:16
 */
@RestController
@Slf4j
public class ModelAttributeController {

    @ModelAttribute
    public void addString(ModelAndView model) {
        model.getModel().put("user","user");
    }

    @GetMapping("/getUser")
    @CrossOrigin
    public String getUser(@ModelAttribute String user, ModelAndView modelAndView){
        Object user1 = modelAndView.getModel().get("user");
        log.info("======================test====================");
        return (String) user1;
    }

    @RequestMapping("/initBinder")
    public String initBinder(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        return format;
    }

    @InitBinder
    public void init(WebDataBinder webDataBinder){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor customDateEditor = new CustomDateEditor(simpleDateFormat, true);
        webDataBinder.registerCustomEditor(Date.class,customDateEditor);
    }
}
