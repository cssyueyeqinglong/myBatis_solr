package com.cy.mybatis.dao;

import com.cy.mybatis.poj.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserDaoImpl implements UserDao {
    private SqlSessionFactory sqlSessionFactory;

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    //注入SqlSessionFactory
    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.setSqlSessionFactory(sqlSessionFactory);
    }


    @Override
    public User getUserById(int id) throws Exception {
        User user = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            user = session.selectOne("test.findUserById", 1);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public void insertUser(User user) throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            session.insert("insertUser", user);
            session.commit();
        } finally{
            session.close();
        }

    }
}
