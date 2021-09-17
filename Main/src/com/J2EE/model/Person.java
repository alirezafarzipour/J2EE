package com.J2EE.model;

import java.io.Serializable;

public class Person implements Serializable {
    private long id;
    private String name;
    private String family;
    private String username;

    public Person() {
    }

    public Person(String name, String family, String username) {
        this.name = name;
        this.family = family;
        this.username = username;
    }

    public Person(long id, String name, String family, String username) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
