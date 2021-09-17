package com.J2EE.service;

import com.J2EE.model.Admin;
import com.J2EE.model.Person;
import com.J2EE.model.User;
import com.J2EE.repository.AdminDA;
import com.J2EE.repository.PersonDA;
import com.J2EE.repository.UserDA;

import javax.ejb.Stateful;
import java.util.List;

@Stateful
public class CRUDService {
    private AdminDA adminDA = new AdminDA();
    private PersonDA personDA = new PersonDA();
    private static List<Person> personList;
    private static List<Admin> adminList;

    public List<Person> findAllUser(){
        personList = personDA.selectAll();
        return personList;
    }

    public Person findUserById(Person person){
        return personDA.selectById(person);
    }

    public Person findUserByUsername(Person person){
        return personDA.selectByUsername(person);
    }

    public void addUser(Person person, User user){
        UserDA userDA = new UserDA();
        userDA.insert(user);

        personDA.insert(person);
        personList = personDA.selectAll();
    }

    public void updateUser(Person person){
        personDA.update(person);
        personList = personDA.selectAll();
    }

    public void deleteUser(Person person){
        personDA.delete(person);
        personList = personDA.selectAll();
    }

    public void addAdmin(Admin admin, User user){
        UserDA userDA = new UserDA();
        userDA.insert(user);

        adminDA.insert(admin);
        adminList = adminDA.selectAll();
    }

    public List<Admin> findAllAdmin(){
        adminList = adminDA.selectAll();
        return adminList;
    }

}
