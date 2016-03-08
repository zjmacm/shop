-- ----------------------------
-- 网站公告
-- ----------------------------

DROP TABLE IF EXISTS `shop_article`;
CREATE TABLE `shop_article`(
`id` VARCHAR(32) NOT NULL ,
`title` VARCHAR (120) NOT NULL,
`key_word` VARCHAR (200) NOT NULL COMMENT'逗号隔开，方便搜索',

`content` LONGTEXT DEFAULT NULL,
`last_update` DATETIME DEFAULT NULL,

`create_time` DATETIME DEFAULT NULL,
 primary key (id)

)ENGINE=InnoDB DEFAULT CHARSET=utf8;
