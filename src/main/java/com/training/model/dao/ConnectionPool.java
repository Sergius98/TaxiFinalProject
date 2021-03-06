package com.training.model.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static final String DRIVER__CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";
    private static volatile BasicDataSource dataSource;

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private ConnectionPool(){ }


    public static BasicDataSource getDataSource(){

        if (dataSource == null){
            synchronized (ConnectionPool.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setDriverClassName(DRIVER__CLASS_NAME);
                    ds.setUrl(URL);
                    ds.setUsername(USERNAME);
                    ds.setPassword(PASSWORD);
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxTotal(50);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;

    }

}