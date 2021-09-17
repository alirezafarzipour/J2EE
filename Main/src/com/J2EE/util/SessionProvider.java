package com.J2EE.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class SessionProvider {
    private static SqlSessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("connection.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void reInit()throws Exception
    {
        sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("connection.xml"));

    }

    public static SqlSession openSession() {
        return sessionFactory.openSession();
    }
}