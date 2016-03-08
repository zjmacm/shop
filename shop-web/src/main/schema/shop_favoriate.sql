-- ----------------------------
-- 收藏夹
-- 买家收藏自己发现的东西
-- ----------------------------

DROP TABLE IF EXISTS `shop_favoriate`;
CREATE TABLE `shop_favoriate`(
`id` VARCHAR(32) NOT NULL ,
`user_id` VARCHAR (32) NOT NULL,
`goods_id` VARCHAR (1000) NOT NULL,
`quantity` INTEGER DEFAULT 1,
`create_time` DATETIME,

PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;