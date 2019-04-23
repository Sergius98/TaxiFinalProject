DROP DATABASE `taxiDB`;
CREATE SCHEMA `taxiDB` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `taxiDB`.`streets`(
   `id`   INT                 NOT NULL  AUTO_INCREMENT,
   `name_en` VARCHAR (20)     NOT NULL,
   `name_ua` VARCHAR (20)     NOT NULL,
   `x`    DOUBLE              NOT NULL,
   `y`    DOUBLE              NOT NULL,
   PRIMARY KEY (`id`),
   UNIQUE (`id`)
);

CREATE TABLE `taxiDB`.`users`(
   `id`         INT              NOT NULL  AUTO_INCREMENT,
   `nickname`   VARCHAR (20)     NOT NULL,
   `password`   VARCHAR (20)     NOT NULL,
   `role`       INT    			     NOT NULL,
   `spendMoney` BIGINT     		   NOT NULL,
   PRIMARY KEY (`id`),
   UNIQUE (`id`),
   UNIQUE (`nickname`)
);

CREATE TABLE `taxiDB`.`car`(
    `id`      INT              NOT NULL  AUTO_INCREMENT,
    `name_en` VARCHAR (20)     NOT NULL,
    `name_ua` VARCHAR (20)     NOT NULL,
    `price`   BIGINT           NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE (`id`)
);

CREATE TABLE `taxiDB`.`taxies`(
   `id`         INT              NOT NULL  AUTO_INCREMENT,
   `name`       VARCHAR (20)     NOT NULL,
   `carClass`   INT    			     NOT NULL,
   `streetId`   INT    			     NOT NULL,
   PRIMARY KEY (`id`),
   UNIQUE (`id`),
   FOREIGN KEY (`carClass`)
   REFERENCES `car`(`id`)
);

CREATE TABLE `taxiDB`.`loyaltyThresholds`(
   `id`         INT              NOT NULL  AUTO_INCREMENT,
   `threshold`  BIGINT    			     NOT NULL,
   `discount`   FLOAT   		     NOT NULL,
   PRIMARY KEY (`id`),
   UNIQUE (`id`)
);

CREATE TABLE `taxiDB`.`discounts`(
   `id`                    INT      NOT NULL  AUTO_INCREMENT,
   `carClass`              INT                       			  ,
   `sourceStreetId`        INT           		       			    ,
   `destinationStreetId`   INT       		     	     		      ,
   `minimalBill`        BIGINT   	                			  	,
   `minimalThreshold`   BIGINT   		       			            ,
   `discount`           BIGINT   		         			          ,
   PRIMARY KEY (`id`),
   UNIQUE (`id`),
   FOREIGN KEY (`carClass`)
     REFERENCES `car`(`id`),
   FOREIGN KEY (`sourceStreetId`)
     REFERENCES `streets`(`id`),
   FOREIGN KEY (`destinationStreetId`)
     REFERENCES `streets`(`id`)
);

INSERT INTO `taxiDB`.`users`(nickname, password, role, spendMoney) VALUES (    "admin",     "admin", 1,  100500);
INSERT INTO `taxiDB`.`users`(nickname, password, role, spendMoney) VALUES (     "root",      "1111", 1,  201000);
INSERT INTO `taxiDB`.`users`(nickname, password, role, spendMoney) VALUES ("moderator", "moderator", 1,  402000);
INSERT INTO `taxiDB`.`users`(nickname, password, role, spendMoney) VALUES (        "a",         "a", 1,  804000);
INSERT INTO `taxiDB`.`users`(nickname, password, role, spendMoney) VALUES (      "aaa",       "aaa", 1, 1608000);
INSERT INTO `taxiDB`.`users`(nickname, password, role, spendMoney) VALUES (      "god",       "god", 1, 3216000);
INSERT INTO `taxiDB`.`users`(nickname, password, role, spendMoney) VALUES (     "user",      "user", 0, 6432000);
INSERT INTO `taxiDB`.`users`(nickname, password, role, spendMoney) VALUES (        "u",         "u", 0,12864000);


INSERT INTO `taxiDB`.`streets`(`name_en`,`name_ua`,`x`,`y`) VALUES ("First","Перша",1,5);
INSERT INTO `taxiDB`.`streets`(`name_en`,`name_ua`,`x`,`y`) VALUES ("Second","Друга",5,15);
INSERT INTO `taxiDB`.`streets`(`name_en`,`name_ua`,`x`,`y`) VALUES ("Third","Третя",2,10);
INSERT INTO `taxiDB`.`streets`(`name_en`,`name_ua`,`x`,`y`) VALUES ("Fourth","Четверта",6,10);
INSERT INTO `taxiDB`.`streets`(`name_en`,`name_ua`,`x`,`y`) VALUES ("Fifth","Пята",1,15);
INSERT INTO `taxiDB`.`streets`(`name_en`,`name_ua`,`x`,`y`) VALUES ("Sixth","Шоста",6,5);
INSERT INTO `taxiDB`.`streets`(`name_en`,`name_ua`,`x`,`y`) VALUES ("Tenth","Десята",10,10);
INSERT INTO `taxiDB`.`streets`(`name_en`,`name_ua`,`x`,`y`) VALUES ("Dark","Темна",0,0);
INSERT INTO `taxiDB`.`streets`(`name_en`,`name_ua`,`x`,`y`) VALUES ("Light","Світла",20,20);
INSERT INTO `taxiDB`.`streets`(`name_en`,`name_ua`,`x`,`y`) VALUES ("Immortal","Безсмертна",0,20);
INSERT INTO `taxiDB`.`streets`(`name_en`,`name_ua`,`x`,`y`) VALUES ("Mortal","Смертна",20,0);

Insert into `taxiDB`.`car`(`name_en`,`name_ua`,`price`) VALUES ("Econom","Економний",100);
Insert into `taxiDB`.`car`(`name_en`,`name_ua`,`price`) VALUES ("Normal","Нормальний",150);
Insert into `taxiDB`.`car`(`name_en`,`name_ua`,`price`) VALUES ("Business","Бізнес",225);
Insert into `taxiDB`.`car`(`name_en`,`name_ua`,`price`) VALUES ("VIP","ВІП",500);
Insert into `taxiDB`.`car`(`name_en`,`name_ua`,`price`) VALUES ("LUX","ЛЮКС",2000);
INSERT INTO `taxiDB`.`taxies`(name,`carClass`,`streetId`) VALUES ("Immortal88",1,1);
INSERT INTO `taxiDB`.`taxies`(name,`carClass`,`streetId`) VALUES ("ImmortalWorrior",1,7);
INSERT INTO `taxiDB`.`taxies`(name,`carClass`,`streetId`) VALUES ("Mortal67",2,5);
INSERT INTO `taxiDB`.`taxies`(name,`carClass`,`streetId`) VALUES ("MortalMage",2,10);
INSERT INTO `taxiDB`.`taxies`(name,`carClass`,`streetId`) VALUES ("Flash13",3,3);
INSERT INTO `taxiDB`.`taxies`(name,`carClass`,`streetId`) VALUES ("LordOfTaxi",4,1);

INSERT INTO `taxiDB`.`discounts`
  (`carClass`,`sourceStreetId`,`destinationStreetId`,`minimalBill`,`minimalThreshold`,`discount`) VALUES (   1,   1,  11,  100,   1, 10);
INSERT INTO `taxiDB`.`discounts`
  (`carClass`,`sourceStreetId`,`destinationStreetId`,`minimalBill`,`minimalThreshold`,`discount`) VALUES (   2,   2,  10,  300, 100, 50);
INSERT INTO `taxiDB`.`discounts`
  (`carClass`,`sourceStreetId`,`destinationStreetId`,`minimalBill`,`minimalThreshold`,`discount`) VALUES (NULL,   3,   9,  500, 300,100);
INSERT INTO `taxiDB`.`discounts`
  (`carClass`,`sourceStreetId`,`destinationStreetId`,`minimalBill`,`minimalThreshold`,`discount`) VALUES (   3,NULL,   8,  800, 500,200);
INSERT INTO `taxiDB`.`discounts`
  (`carClass`,`sourceStreetId`,`destinationStreetId`,`minimalBill`,`minimalThreshold`,`discount`) VALUES (   4,   4,NULL, 1200, 800,500);
INSERT INTO `taxiDB`.`discounts`
  (`carClass`,`sourceStreetId`,`destinationStreetId`,`minimalBill`,`minimalThreshold`,`discount`) VALUES (   5,   5,   7, NULL,1200, 10);
INSERT INTO `taxiDB`.`discounts`
  (`carClass`,`sourceStreetId`,`destinationStreetId`,`minimalBill`,`minimalThreshold`,`discount`) VALUES (   1,   6,   1, NULL,NULL,49);
INSERT INTO `taxiDB`.`discounts`
  (`carClass`,`sourceStreetId`,`destinationStreetId`,`minimalBill`,`minimalThreshold`,`discount`) VALUES (   1,   7,NULL, NULL,NULL,40);
INSERT INTO `taxiDB`.`discounts`
  (`carClass`,`sourceStreetId`,`destinationStreetId`,`minimalBill`,`minimalThreshold`,`discount`) VALUES (   1,NULL,NULL, NULL,NULL,35);
INSERT INTO `taxiDB`.`discounts`
  (`carClass`,`sourceStreetId`,`destinationStreetId`,`minimalBill`,`minimalThreshold`,`discount`) VALUES (NULL,NULL,NULL, NULL,1000,21);
INSERT INTO `taxiDB`.`discounts`
  (`carClass`,`sourceStreetId`,`destinationStreetId`,`minimalBill`,`minimalThreshold`,`discount`) VALUES (NULL,NULL,NULL,  100, 500,14);
INSERT INTO `taxiDB`.`discounts`
  (`carClass`,`sourceStreetId`,`destinationStreetId`,`minimalBill`,`minimalThreshold`,`discount`) VALUES (NULL,NULL,   8,   50,2000,10);
INSERT INTO `taxiDB`.`discounts`
  (`carClass`,`sourceStreetId`,`destinationStreetId`,`minimalBill`,`minimalThreshold`,`discount`) VALUES (NULL,   9,   8,   20, 500,19);


INSERT INTO `taxiDB`.loyaltyThresholds (`threshold`, `discount`) VALUES (    100, 0.01);
INSERT INTO `taxiDB`.loyaltyThresholds (`threshold`, `discount`) VALUES (    200, 0.03);
INSERT INTO `taxiDB`.loyaltyThresholds (`threshold`, `discount`) VALUES (   1000, 0.25);
INSERT INTO `taxiDB`.loyaltyThresholds (`threshold`, `discount`) VALUES (   3000, 0.33);
INSERT INTO `taxiDB`.loyaltyThresholds (`threshold`, `discount`) VALUES (  25000,    1);
INSERT INTO `taxiDB`.loyaltyThresholds (`threshold`, `discount`) VALUES ( 100000, 1.33);
INSERT INTO `taxiDB`.loyaltyThresholds (`threshold`, `discount`) VALUES ( 333333, 1.55);
INSERT INTO `taxiDB`.loyaltyThresholds (`threshold`, `discount`) VALUES ( 777777, 1.77);
INSERT INTO `taxiDB`.loyaltyThresholds (`threshold`, `discount`) VALUES (3333333,    2);
INSERT INTO `taxiDB`.loyaltyThresholds (`threshold`, `discount`) VALUES (7777777, 2.33);
INSERT INTO `taxiDB`.loyaltyThresholds (`threshold`, `discount`) VALUES (9999999, 2.77);
