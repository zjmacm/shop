-- ----------------------------
-- 系统管理员
-- ----------------------------

DROP TABLE IF EXISTS `shop_admin`;
CREATE TABLE `shop_admin`(
`id` VARCHAR(32) NOT NULL ,
`admin_name` VARCHAR (120) NOT NULL,
`real_name` VARCHAR (120) NOT NULL,

`register_date` DATETIME DEFAULT NULL,
`phone` VARCHAR(30) DEFAULT NULL,

`mobile` VARCHAR(30) DEFAULT NULL,
`gender` VARCHAR(1) COMMENT 'F,M',
`email` VARCHAR(50) DEFAULT NULL ,

`admin_status` VARCHAR(1)  COMMENT '1.正常，2.禁用，3.注销',
`password` VARCHAR(255) DEFAULT NULL ,
`icon` VARCHAR(100) DEFAULT NULL COMMENT '先不用做',
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
