package com.J2EE.repository;

import com.J2EE.model.User;
import com.J2EE.util.SessionProvider;
import org.apache.ibatis.session.SqlSession;

public class UserDA {

    public void insert(User user) {
        SqlSession session = SessionProvider.openSession();
        session.insert("UserMapper.insertUser", user);
        session.insert("UserMapper.insertRole", user);
        session.commit();
        session.close();
    }

}
