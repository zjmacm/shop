/*商品分类表*/
DROP TABLE IF EXISTS `shop_category`;
CREATE TABLE `shop_category`(
  `id`varchar (32) not null,
  `parent_id` varchar (32) default null ,
  `category` varchar (60) not null ,
  `create_time` datetime ,
  primary key (id),
  foreign key (parent_id) references shop_category(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
