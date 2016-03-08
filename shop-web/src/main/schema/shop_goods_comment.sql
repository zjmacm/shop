-- ----------------------------
-- 商品评论表
-- ----------------------------

DROP TABLE IF EXISTS `shop_goods_comment`;
CREATE TABLE `shop_goods_comment`(
`id` VARCHAR(32) NOT NULL ,
`goods_id` VARCHAR (32) NOT NULL,
`user_id` VARCHAR (32) NOT NULL,
`comment_score` INTEGER DEFAULT 5 COMMENT '默认是5分',
`create_time` DATETIME,

PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;