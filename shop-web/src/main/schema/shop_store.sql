DROP TABLE IF EXISTS `shop_store`;
CREATE TABLE `shop_store`(

   `id` VARCHAR(32) NOT NULL,
   `seller_id` VARCHAR(32) NOT NULL,
   `store_name` VARCHAR(120) NOT NULL,
   `longitude`  DECIMAL (18,12) NOT NULL,
   `latitude` DECIMAL (18,12) NOT NULL,
   `address` VARCHAR (200) NOT  NULL ,
   `zip` VARCHAR (12),
   `register_date` DATETIME,
   `audit_pass` VARCHAR (1),
   `audit_time` DATETIME ,
   `level` INTEGER ,
   `sort_order` integer,
   `store_status` VARCHAR (1),
   `start_time` TIME ,
   `end_time` TIME ,
   `transfer_fee` INTEGER ,
   `transfer_limit` integer,
   `invoice` INTEGER,
   `pay_way` VARCHAR (1),
   `province` VARCHAR (60),
   `city` VARCHAR (60),
   `country` VARCHAR (60),
    PRIMARY KEY (`id`)

)ENGINE=InnoDB DEFAULT CHARSET=utf8;