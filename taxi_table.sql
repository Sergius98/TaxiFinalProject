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

CREATE TABLE `test`.`discount`(
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
