package com.example.springboot.mapper;

import com.example.springboot.model.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author lqb
 * @date 2021/12/20 19:12
 */
@Mapper
@Repository
public interface CustomerMapper {
    /**
     * 根据id获取客户信息
     * @param id id
     * @return 客户信息
     */
    public Customer getCustomerById(Integer id);
}
