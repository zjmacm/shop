-- ----------------------------
-- 授权表
-- ----------------------------
DROP TABLE IF EXISTS `shop_authority`;
CREATE TABLE `shop_authority`(
`id` VARCHAR(32) NOT NULL ,
`user_id` VARCHAR (32) NOT NULL,
`role_id` VARCHAR (32) NOT NULL,
`authority_level` INTEGER ,
`create_time` DATETIME,

PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
