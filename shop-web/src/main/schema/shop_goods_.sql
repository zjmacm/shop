-- ----------------------------
-- 商品表
-- ----------------------------
DROP TABLE IF EXISTS `shop_goods`;
create table `shop_goods`(
  `id` varchar (32) not null,
  `name` varchar (256) not null,
  `price` decimal ,
  `code` varchar(100),
  `description` longtext ,
  `seller_id` varchar (32) not null,
  `cost` decimal ,
  `sale_price` decimal ,
  `discount_price` decimal ,
  `credit` integer ,
  `brand` varchar (200),
  `category_id` varchar(32) ,
  `photo` longtext ,
  `store_id` varchar (32),
  `quantity` integer ,
  `audit` varchar (1) comment '0未审核 1审核通过 2审核不通过',
  `status` varchar (1) comment '0下架 1上架 2缺货',
  `sale_quantity` integer,
  `scan_time` integer ,
  `create_time` datetime ,

  `images` varchar (1000) comment '数据格式如下:uri|uri|uri,多图路径用|符号分割,第一个uri是主图',
  primary key (`id`)
/*  foreign key (`category_id`) references `shop_category` (`id`)
*/
)ENGINE=InnoDB DEFAULT CHARSET=utf8;