package com.cy.mybatis.mapper;

import com.cy.mybatis.poj.Orders;
import com.cy.mybatis.poj.User;

import java.util.List;

public interface UserMapper {
    public User findUserById(int id) throws Exception;
    public List<User> findUserByUsername(String user) throws Exception;
    public List<Orders> findOrdersAndUser() throws Exception;
}
