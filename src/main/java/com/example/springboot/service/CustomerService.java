package com.example.springboot.service;

import com.example.springboot.model.Customer;

/**
 * @author lqb
 * @date 2021/12/20 19:10
 */
public interface CustomerService {
    public Customer getCustomerById(Integer id);
}
