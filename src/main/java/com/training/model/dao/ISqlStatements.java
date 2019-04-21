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
    String GET_ORDER = "\n" +
            "SELECT src.X as srcX,\n" +
            "       src.Y as srcY,\n" +
            "       dst.X as dstX,\n" +
            "       dst.Y as dstY,\n" +
            "       car.price as taxiCarClassPrice,\n" +
            "\n" +
            "       (ABS(src.X-dst.X) + ABS(src.Y-dst.Y)) as distance,\n" +
            "       (SELECT SUM(discount) as userThresholdsDiscount\n" +
            "           from test.loyaltyThresholds as l\n" +
            "           where u.spendMoney >= l.threshold\n" +
            "       ) as userThresholdsDiscount,\n" +
            "       ((ABS(src.X-dst.X) + ABS(src.Y-dst.Y)) * car.price) as bill,\n" +
            "       (SELECT SUM(d.discount)\n" +
            "        from test.discounts as d\n" +
            "        WHERE (((src.id = d.sourceStreetId) or (d.sourceStreetId is Null)) and\n" +
            "              ((dst.id = d.destinationStreetId) or (d.destinationStreetId is Null)) and\n" +
            "              ((u.spendMoney >= d.minimalThreshold) or (d.minimalThreshold is Null)) and\n" +
            "              ((bill >= d.minimalBill) or (d.minimalBill is Null)) and\n" +
            "              ((car.id = d.carClass) or (d.carClass is Null)))\n" +
            "       ) as orderDiscountsSum\n" +
            "from test.users as u\n" +
            "  join test.streets as src\n" +
            "    join test.streets as dst\n" +
            "      join test.car as car\n" +
            "where src.id=(?) and\n" +
            "      dst.id=(?) and\n" +
            "      u.id=(?) and\n" +
            "      car.id=(?);";


    //SELECT * FROM `test`.`discounts` WHERE (carClass = 0 or carClass is NULL) and minimalThreshold>=0;




    //INSERT INTO `test`.`loyaltyThresholds`(`threshold`,`discount`) VALUES (500, 0.01);
}
