package com.training.model.dao;

public interface ISqlStatements {
    String DB_NAME = "`test`";

    String INSERT_INTO_USER = "INSERT INTO " + DB_NAME + ".`users`" +
            " (`nickname`, `password`, `role`, `spendMoney`) VALUES (?,?,?,0)";

    String READ_USER_BY_ID = "select * from " + DB_NAME + ".`users`" +
            "where `users`.`id` =(?)";
    String READ_USER_BY_NICKNAME = "select * from " + DB_NAME + ".`users`" +
            "where `users`.`nickname` =(?)";

    String INSERT_INTO_DISCOUNT = "INSERT INTO " + DB_NAME + ".`discounts` " +
            " (`carClass`, `sourceStreetId`, `destinationStreetId`, " +
            "`minimalBill`, `minimalThreshold`, `discount`) VALUES" +
            " (?, ?, ?, ?, ?, ?)";
    String READ_DISCOUNT_BY_ID = "select * from " + DB_NAME + ".`discounts`" +
            "where `discounts`.`id` =(?)";
    String READ_DISCOUNT = "select * from " + DB_NAME + ".`discounts`";
    String DELETE_DISCOUNT = "delete from " + DB_NAME +
            ".`discounts` where `id`=(?)";


    String READ_STREET_BY_ID = "select * from " + DB_NAME + ".`streets`" +
            "where `streets`.`id` =(?)";
    String READ_STREET = "select * from " + DB_NAME + ".`streets`";

    String READ_CAR_BY_ID = "select * from " + DB_NAME + ".`car`" +
            "where `car`.`id` =(?)";
    String READ_CAR = "select * from " + DB_NAME + ".`car`";

    String INSERT_INTO_LOYALTY_THRESHOLD = "INSERT INTO " + DB_NAME +
            ".`loyaltyThresholds`(`threshold`, `discount`) VALUES (?, ?)";
    String READ_LOYALTY_THRESHOLD_BY_ID = "select * from " + DB_NAME +
            ".`loyaltyThresholds`" +
            "where `loyaltyThresholds`.`id` =(?)";
    // TODO: 4/21/19 sort results
    String READ_LOYALTY_THRESHOLD = "select * from " + DB_NAME +
            ".`loyaltyThresholds`";
    String DELETE_LOYALTY_THRESHOLD = "delete from " + DB_NAME +
            ".`loyaltyThresholds` where `id`=(?)";






    /*

SELECT src.id as srcId, src.X as srcX, src.Y as srcY, dst.id as dstId,
dst.X as dstX, dst.Y as dstY, ABS(src.X-dst.X) as Xdst from test.streets as src,
test.streets as dst where src.id=1 and dst.id=2;


    */





    //SELECT * FROM `test`.`discounts` WHERE (carClass = 0 or carClass is NULL) and minimalThreshold>=0;




    //INSERT INTO `test`.`loyaltyThresholds`(`threshold`,`discount`) VALUES (500, 0.01);
}
