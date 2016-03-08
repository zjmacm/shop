-- ----------------------------
-- 线下交易订单表
-- ----------------------------

DROP TABLE IF EXISTS `shop_offline_order`;
CREATE TABLE `shop_offline_order`(
`id` VARCHAR(32) NOT NULL ,

`seller_mobile` VARCHAR(30) NOT NULL ,
`seller_phone` VARCHAR(30),
`seller_email` VARCHAR(50),
`seller_message` LONGTEXT,

`user_mobile` VARCHAR(30) NOT NULL ,
`user_phone` VARCHAR(30),
`user_email` VARCHAR(50),
`user_message` LONGTEXT,

`seller_id` VARCHAR (32) NOT NULL ,
`user_id`  VARCHAR (32) NOT NULL ,
`goods_id` VARCHAR (32) NOT NULL ,
`quantity` INTEGER DEFAULT 1,
`status`  VARCHAR (1) COMMENT '0.取消   1，成功',
`cancel_reason` LONGTEXT ,
`create_time` DATETIME,
PRIMARY KEY (`id`)

)ENGINE=InnoDB DEFAULT CHARSET=utf8;