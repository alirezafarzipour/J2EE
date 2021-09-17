package com.J2EE.repository;

import com.J2EE.model.Admin;
import com.J2EE.util.SessionProvider;
import org.apache.ibatis.session.SqlSession;
import java.util.List;


public class AdminDA {
    public List<Admin> selectAll() {
        SqlSession session = SessionProvider.openSession();
        try {
            return session.selectList("AdminMapper.getAll");
        } finally {
            session.close();
        }
    }

    public void insert(Admin admin) {

        SqlSession session = SessionProvider.openSession();
        session.insert("AdminMapper.insert", admin);
        session.commit();
        session.close();

    }

}
