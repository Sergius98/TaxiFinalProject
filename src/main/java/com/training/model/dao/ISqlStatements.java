package com.training.model.dao;

public interface ISqlStatements {
    String DB_NAME = "`test`";
    String INSERT_INTO_USER = "INSERT INTO " + DB_NAME + ".`users`" +
            " (`nickname`, `password`, `role`, `spendMoney`) VALUES (?, ?, ?, 0)";

    String READ_USER_BY_ID = "select * from " + DB_NAME + ".`users`" +
            "where users.id =(?)";
    String READ_USER_BY_NICKNAME = "select * from " + DB_NAME + ".`users`" +
            "where users.nickname =(?)";
}
