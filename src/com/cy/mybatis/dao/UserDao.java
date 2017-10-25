package com.cy.mybatis.dao;

import com.cy.mybatis.poj.User;

public interface UserDao {
    public User getUserById(int id) throws Exception;
    public void insertUser(User user) throws Exception;

}
