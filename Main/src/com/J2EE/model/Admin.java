package com.J2EE.model;

import java.io.Serializable;

public class Admin implements Serializable {
    private long id;
    private String Name;
    private String family;
    private String username;

    public Admin() {
    }

    public Admin(String name, String family, String username) {
        Name = name;
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
        return Name;
    }

    public void setName(String name) {
        Name = name;
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
