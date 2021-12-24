package com.example.springboot.controller;

import com.example.springboot.anno.SwitchSource;
import com.example.springboot.model.Customer;
import com.example.springboot.service.CustomerService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author lqb
 * @date 2021/12/20 19:09
 */
@RestController
public class DataSourceController {

    @Resource
    private CustomerService customerService;

    @RequestMapping("/getCustomerById1/{id}")
    public Customer getCustomerById1(@PathVariable Integer id) {
        return customerService.getCustomerById(id);
    }

    @RequestMapping("/getCustomerById2/{id}")
    @SwitchSource
    public Customer getCustomerById2(@PathVariable Integer id) {
        return customerService.getCustomerById(id);
    }

    public static void main(String[] args) {
        int[] ints = new int[]{1,2,3,4,5,6};
        String collect = Arrays.stream(ints).boxed().map(String::valueOf).collect(Collectors.joining(","));
        System.out.println(collect);
    }
}
