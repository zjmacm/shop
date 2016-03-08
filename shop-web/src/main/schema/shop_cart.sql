-- ----------------------------
-- 购物车
-- 买家加入购物车
-- ----------------------------

DROP TABLE IF EXISTS `shop_cart`;
CREATE TABLE `shop_cart`(
`id` VARCHAR(32) NOT NULL ,
`user_id` VARCHAR (32) NOT NULL,
`goods_id` VARCHAR (1000) NOT NULL,
`quantity` INTEGER DEFAULT 1,
`old_prince` DECIMAL ,
`status` VARCHAR(1) COMMENT '0，不可以，1可以购买',
`create_time` DATETIME,

PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;