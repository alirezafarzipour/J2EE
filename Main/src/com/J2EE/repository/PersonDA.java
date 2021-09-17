package com.J2EE.repository;

import com.J2EE.model.Person;
import com.J2EE.util.SessionProvider;
import org.apache.ibatis.session.SqlSession;

import java.util.List;


public class PersonDA {

    public List<Person> selectAll() {
        SqlSession session = SessionProvider.openSession();
        try {
            List<Person> list = session.selectList("PersonMapper.getAll");
            return list;
        } finally {
            session.close();
        }
    }

    public Person selectById(Person person) {
        SqlSession session = SessionProvider.openSession();
        try {
            Person output = session.selectOne("PersonMapper.getById", person);
            return output;
        } finally {
            session.close();
        }
    }

    public Person selectByUsername(Person person) {
        SqlSession session = SessionProvider.openSession();
        try {
            Person output = session.selectOne("PersonMapper.getByUsername", person);
            return output;
        } finally {
            session.close();
        }
    }

    public void update(Person person) {
        SqlSession session = SessionProvider.openSession();
        session.update("PersonMapper.update", person);
        session.commit();
        session.close();
    }

    public void insert(Person person) {

        SqlSession session = SessionProvider.openSession();
        session.insert("PersonMapper.insert", person);
        session.commit();
        session.close();

    }

    public void delete(Person person) {
        SqlSession session = SessionProvider.openSession();
        session.update("PersonMapper.deleteById", person); //update isDeleted column
        session.commit();
        session.close();
    }

}