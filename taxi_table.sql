DROP DATABASE `test`;
CREATE SCHEMA `test` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `test`.`streets`(
   `id`   INT              NOT NULL  AUTO_INCREMENT,
   `name_en` VARCHAR (20)     NOT NULL,
   `name_ua` VARCHAR (20)     NOT NULL,
   `x`    FLOAT            NOT NULL,
   `y`    FLOAT            NOT NULL,   
   PRIMARY KEY (`id`),
   UNIQUE (`id`)
);

CREATE TABLE `test`.`users`(
   `id`         INT              NOT NULL  AUTO_INCREMENT,
   `nickname`   VARCHAR (20)     NOT NULL,
   `password`   VARCHAR (20)     NOT NULL,
   `role`       INT    			 NOT NULL,
   `spendMoney` INT     		 NOT NULL,
   PRIMARY KEY (`id`),
   UNIQUE (`id`),
   UNIQUE (`nickname`)
);

CREATE TABLE `test`.`taxies`(
   `id`         INT              NOT NULL  AUTO_INCREMENT,
   `name`       VARCHAR (20)     NOT NULL,
   `carClass`   INT    			 NOT NULL,
   `streetId`   INT    			 NOT NULL,
   PRIMARY KEY (`id`),
   UNIQUE (`id`)
);

CREATE TABLE `test`.`loyaltyThresholds`(
   `id`         INT              NOT NULL  AUTO_INCREMENT,
   `threshold`  INT    			 NOT NULL,
   `discount`   FLOAT   		 NOT NULL,
   PRIMARY KEY (`id`),
   UNIQUE (`id`)
);

CREATE TABLE `test`.`discounts`(
   `id`                    INT              NOT NULL  AUTO_INCREMENT,
   `carClass`              INT    			 ,
   `sourceStreetId`        INT      		 ,
   `destinationStreetId`   INT     		     ,
   `minimalBill`           INT   	    	 ,
   `minimalThreshold`      INT   		     ,
   `discount`              INT   		     ,
   PRIMARY KEY (`id`),
   UNIQUE (`id`)
);

CREATE TABLE `test`.`car`(
   `id`      INT              NOT NULL  AUTO_INCREMENT,
   `name_en` VARCHAR (20)     NOT NULL,
   `name_ua` VARCHAR (20)     NOT NULL,
   `price`  BIGINT  NOT NULL,
   PRIMARY KEY (`id`),
   UNIQUE (`id`)
);


Insert into `test`.`car`(`name_en`,`name_ua`,`price`) VALUES ("Econom","Економний",100);
Insert into `test`.`car`(`name_en`,`name_ua`,`price`) VALUES ("Normal","Нормальний",150);
Insert into `test`.`car`(`name_en`,`name_ua`,`price`) VALUES ("Business","Бізнес",225);
Insert into `test`.`car`(`name_en`,`name_ua`,`price`) VALUES ("VIP","ВІП",500);
Insert into `test`.`car`(`name_en`,`name_ua`,`price`) VALUES ("LUX","ЛЮКС",2000);

INSERT INTO `test`.`discounts`(`carClass`,`sourceStreetId`,`destinationStreetId`,`minimalBill`,`minimalThreshold`,`discount`) VALUES (2,1,1,0,0,500);
INSERT INTO `test`.`discounts`(`carClass`,`sourceStreetId`,`destinationStreetId`,`minimalBill`,`minimalThreshold`,`discount`) VALUES (1,2,1,300,5,500);
INSERT INTO `test`.`streets`(`name_en`,`name_ua`,`x`,`y`) VALUES ("First","Перша",1,5);
INSERT INTO `test`.`streets`(`name_en`,`name_ua`,`x`,`y`) VALUES ("Second","Друга",5,15);
INSERT INTO `test`.`streets`(`name_en`,`name_ua`,`x`,`y`) VALUES ("Third","Третя",2,10);
INSERT INTO `test`.`streets`(`name_en`,`name_ua`,`x`,`y`) VALUES ("Fourth","Четверта",6,10);
INSERT INTO `test`.`streets`(`name_en`,`name_ua`,`x`,`y`) VALUES ("Fifth","Пята",1,15);
INSERT INTO `test`.`streets`(`name_en`,`name_ua`,`x`,`y`) VALUES ("Sixth","Шоста",6,5);


/* TODO: 4/21/19 change name to nickname in taxi db */

INSERT INTO `test`.`taxies`(name,`carClass`,`streetId`) VALUES ("APOTO",1,1);
INSERT INTO `test`.`taxies`(name,`carClass`,`streetId`) VALUES ("KOKO",2,5);
INSERT INTO `test`.`taxies`(name,`carClass`,`streetId`) VALUES ("EIKIE",3,3);