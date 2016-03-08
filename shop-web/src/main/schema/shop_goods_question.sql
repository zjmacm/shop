-- ----------------------------
-- 商品询问表
-- 商品询问表就是可以在下面询问，这样所有的人都能看见呢
-- ----------------------------

DROP TABLE IF EXISTS `shop_goods_question`;
CREATE TABLE `shop_goods_question`(
`id` VARCHAR(32) NOT NULL ,
`goods_id` VARCHAR (32) NOT NULL,
`ask_comment` VARCHAR (1000) NOT NULL,
`user_id`  VARCHAR (32) NOT NULL ,
`question_times` INTEGER DEFAULT 1,
`status` VARCHAR(1) COMMENT '0，等待卖家审核，1，正常状态，2审核不通过',
`create_time` DATETIME,
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;