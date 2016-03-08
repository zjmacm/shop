-- ----------------------------
-- 买家表
-- ----------------------------

DROP TABLE IF EXISTS `shop_user`;
CREATE TABLE `shop_user`(
  `id` VARCHAR(32) NOT NULL ,
  `user_name` VARCHAR (120) NOT NULL,

  `real_name` VARCHAR (120) DEFAULT NULL,

  `register_date` DATETIME DEFAULT NULL,
  `phone` VARCHAR(30) DEFAULT NULL,

  `mobile` VARCHAR(30) DEFAULT NULL,
  `gender` VARCHAR(1) COMMENT 'F,M',
  `email` VARCHAR(50) DEFAULT NULL ,

  `user_status` VARCHAR(1)  COMMENT '0，预启用，1启用（激活）2，首次登陆，3，资料未完善 4.正常，5.禁用，6.注销',
  `password` VARCHAR(255) DEFAULT NULL ,
  `icon` VARCHAR(100) DEFAULT NULL COMMENT '先不用做',
  `birthday` DATETIME DEFAULT NULL ,
  `school` VARCHAR (200) DEFAULT NULL,
  `department` VARCHAR(50) DEFAULT NULL ,
  `grade` VARCHAR(50) DEFAULT NULL,
  `third_account` VARCHAR(50) DEFAULT NULL ,
  `third_token` VARCHAR(50) DEFAULT NULL ,
  `third_party` VARCHAR(20),

  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

