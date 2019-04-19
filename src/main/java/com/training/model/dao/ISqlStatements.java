package com.training.model.dao;

public interface ISqlStatements {
    String DB_NAME = "`test`";

    String INSERT_INTO_USER = "INSERT INTO " + DB_NAME + ".`users`" +
            " (`nickname`, `password`, `role`, `spendMoney`) VALUES (?, ?, ?, 0)";

    String READ_USER_BY_ID = "select * from " + DB_NAME + ".`users`" +
            "where `users`.`id` =(?)";
    String READ_USER_BY_NICKNAME = "select * from " + DB_NAME + ".`users`" +
            "where `users`.`nickname` =(?)";

    String INSERT_INTO_DISCOUNT = "INSERT INTO " + DB_NAME + ".`discounts`" +
            " `carClass`, `sourceStreetId`, `destinationStreetId`, " +
            "`minimalBill`, `minimalThreshold`, `discount` VALUES" +
            " (?, ?, ?, ?, ?, ?)";
    String READ_DISCOUNT_BY_ID = "select * from " + DB_NAME + ".`discounts`" +
            "where `discounts`.`id` =(?)";
    String READ_DISCOUNT = "select * from " + DB_NAME + ".`discounts`";



    //INSERT INTO `test`.`discounts`(`carClass`,`sourceStreetId`,`destinationStreetId`,`minimalBill`,`minimalThreshold`,`discount`) VALUES (0,0,0,0,0,500);
    //INSERT INTO `test`.`discounts`(`carClass`,`sourceStreetId`,`destinationStreetId`,`minimalBill`,`minimalThreshold`,`discount`) VALUES (1,2,1,300,5,500);
}
