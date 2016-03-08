-- ----------------------------
-- 角色表
-- ----------------------------

DROP TABLE IF EXISTS `shop_role`;
CREATE TABLE `shop_role`(
`id` VARCHAR(32) NOT NULL ,
`role_name` VARCHAR (120) NOT NULL,
`default_level` VARCHAR (120) NOT NULL,
`create_time` DATETIME,

PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;