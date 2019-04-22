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

    String READ_LOYALTY_THRESHOLD = "select * from " + DB_NAME +
            ".`loyaltyThresholds` ORDER BY threshold ASC";
    String DELETE_LOYALTY_THRESHOLD = "delete from " + DB_NAME +
            ".`loyaltyThresholds` where `id`=(?)";

    String GET_ORDER = "SELECT src.X as srcX, " +
            "       src.Y as srcY, " +
            "       dst.X as dstX, " +
            "       dst.Y as dstY, " +
            "       car.price as taxiCarClassPrice," +
            "       (ABS(src.X-dst.X) + ABS(src.Y-dst.Y)) as distance, " +
            "       (SELECT SUM(discount) as userThresholdsDiscount " +
            "           from " + DB_NAME +".loyaltyThresholds as l " +
            "           where u.spendMoney >= l.threshold " +
            "       ) as userThresholdsDiscount, " +
            "       ((ABS(src.X-dst.X) + ABS(src.Y-dst.Y)) * car.price) as bill, " +
            "       (SELECT SUM(d.discount) " +
            "        from " + DB_NAME +".discounts as d " +
            "        WHERE (((src.id = d.sourceStreetId) or (d.sourceStreetId is Null)) and " +
            "              ((dst.id = d.destinationStreetId) or (d.destinationStreetId is Null)) and " +
            "              ((u.spendMoney >= d.minimalThreshold) or (d.minimalThreshold is Null)) and " +
            "              ((bill >= d.minimalBill) or (d.minimalBill is Null)) and " +
            "              ((car.id = d.carClass) or (d.carClass is Null))) " +
            "       ) as orderDiscountsSum " +
            "from " + DB_NAME + ".users as u " +
            "  join " + DB_NAME + ".streets as src " +
            "    join " + DB_NAME + ".streets as dst " +
            "      join " + DB_NAME + ".car as car " +
            "where src.id=(?) and " +
            "      dst.id=(?) and " +
            "      u.id=(?) and " +
            "      car.id=(?) " +
            "limit 1";

    String FINT_TAXY_ID_AND_DELAY_BY_CAR_CLASS_AND_SRC_STREET = "SELECT t.id as taxiId, " +
            "   (ABS(l.x - s.x)+ABS(l.y - s.y)) as delay " +
            "from " + DB_NAME + ".taxies as t " +
            "   left join " + DB_NAME + ".streets as s on s.id = (?) " +
            "       left join " + DB_NAME + ".streets as l on l.id = t.streetId " +
            "where t.carClass = (?) " +
            "ORDER BY delay ASC";
    String UPDATE_TAXI_LOCATION = "update " + DB_NAME + ".taxies as t " +
            "set t.streetId = (?) where t.id = (?)";


    //SELECT * FROM `test`.`discounts` WHERE (carClass = 0 or carClass is NULL) and minimalThreshold>=0;




    //INSERT INTO `test`.`loyaltyThresholds`(`threshold`,`discount`) VALUES (500, 0.01);
}
