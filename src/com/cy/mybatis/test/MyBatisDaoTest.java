package com.cy.mybatis.test;

import com.cy.mybatis.dao.UserDao;
import com.cy.mybatis.dao.UserDaoImpl;
import com.cy.mybatis.poj.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

public class MyBatisDaoTest {

    private SqlSessionFactory sessionFactory;

    @Before
    public void init() throws Exception{
        SqlSessionFactoryBuilder builder= new SqlSessionFactoryBuilder();
        InputStream inputStream= Resources.getResourceAsStream("sqlMapConfig.xml");
        sessionFactory = builder.build(inputStream);
    }

    @Test
    public void testGetUserById() throws Exception{
        UserDao userDao = new UserDaoImpl(sessionFactory);
        User user = userDao.getUserById(22);
        System.out.println(user);
    }

}
