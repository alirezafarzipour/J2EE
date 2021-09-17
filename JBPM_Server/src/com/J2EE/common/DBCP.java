package com.J2EE.common;

import org.apache.commons.dbcp2.BasicDataSource;


import java.sql.Connection;

public class DBCP {
    private static BasicDataSource basicDataSource = new BasicDataSource();
    static
    {
        basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
        basicDataSource.setUsername("bpms2");
        basicDataSource.setPassword("myjava123");
        basicDataSource.setMaxTotal(10);
    }
    public static Connection getConnection()throws Exception
    {
        return basicDataSource.getConnection();
    }

}
