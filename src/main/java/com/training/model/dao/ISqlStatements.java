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


    String READ_STREET_BY_ID = "select * from " + DB_NAME + ".`streets`" +
            "where `streets`.`id` =(?)";
    String READ_STREET = "select * from " + DB_NAME + ".`streets`";

    //SELECT * FROM `test`.`discounts` WHERE (carClass = 0 or carClass is NULL) and minimalThreshold>=0;
    //INSERT INTO `test`.`discounts`(`carClass`,`sourceStreetId`,`destinationStreetId`,`minimalBill`,`minimalThreshold`,`discount`) VALUES (2,1,1,0,0,500);
    //INSERT INTO `test`.`discounts`(`carClass`,`sourceStreetId`,`destinationStreetId`,`minimalBill`,`minimalThreshold`,`discount`) VALUES (1,2,1,300,5,500);
    //INSERT INTO `test`.`streets`(`name_en`,`name_ua`,`x`,`y`) VALUES ("First","Перша",1,5);
    //INSERT INTO `test`.`streets`(`name_en`,`name_ua`,`x`,`y`) VALUES ("Second","Друга",5,15);
    //INSERT INTO `test`.`streets`(`name_en`,`name_ua`,`x`,`y`) VALUES ("Third","Третя",2,10);
    //INSERT INTO `test`.`streets`(`name_en`,`name_ua`,`x`,`y`) VALUES ("Fourth","Четверта",6,10);
    //INSERT INTO `test`.`streets`(`name_en`,`name_ua`,`x`,`y`) VALUES ("Fifth","Пята",1,15);
    //INSERT INTO `test`.`streets`(`name_en`,`name_ua`,`x`,`y`) VALUES ("Sixth","Шоста",6,5);
}
