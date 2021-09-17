package com.J2EE.model;

import java.io.Serializable;

public class Process implements Serializable {
    private String pid;
    private String pname;
    private String uname;

    public String getPid() {
        return pid;
    }

    public Process setPid(String pid) {
        this.pid = pid;
        return this;
    }

    public String getPname() {
        return pname;
    }

    public Process setPname(String pname) {
        this.pname = pname;
        return this;
    }

    public String getUname() {
        return uname;
    }

    public Process setUname(String uname) {
        this.uname = uname;
        return this;
    }
}
