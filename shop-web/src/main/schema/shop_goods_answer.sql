-- ----------------------------
-- 商品询问回复表
-- 卖家针对卖家提问的回复
-- ----------------------------

DROP TABLE IF EXISTS `shop_goods_answer`;
CREATE TABLE `shop_goods_answer`(
`id` VARCHAR(32) NOT NULL ,
`question_id` VARCHAR (32) NOT NULL,
`ans_comment` VARCHAR (1000) NOT NULL,
`create_time` DATETIME,

PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;