DROP TABLE IF EXISTS `shop_seller`;
CREATE TABLE `shop_seller`(
`id` VARCHAR(32) NOT NULL ,
`seller_name` VARCHAR (120) NOT NULL COMMENT '唯一',
`owner_name` VARCHAR (120) NOT NULL,
`card_number` VARCHAR (30) NOT NULL,
`front_pic` VARCHAR (100)  COMMENT' 有像素要求',
`back_pic` VARCHAR (100)  COMMENT' 有像素要求',
`QQ` VARCHAR (50) NOT NULL,
`owner_photos` VARCHAR(255),
`phone` VARCHAR(30) DEFAULT NULL,
`mobile` VARCHAR(30) DEFAULT NULL,
`gender` VARCHAR(1) COMMENT 'F,M',
`email` VARCHAR(50) DEFAULT NULL ,

`seller_status` VARCHAR(1)  COMMENT '0，预启用，1启用（激活）2，首次登陆，3，资料未完善4.正常，5.禁用，6.注销',
`password` VARCHAR(255) DEFAULT NULL ,
`icon` VARCHAR(255) DEFAULT NULL COMMENT '现在先不用',

`birthday` DATETIME DEFAULT NULL ,
`birth_place` VARCHAR  (300) DEFAULT NULL COMMENT '省+市+县（区）',
`register_date` DATETIME DEFAULT NULL,
`living` VARCHAR(300) DEFAULT NULL COMMENT '省+市+县（区）',

`school` VARCHAR(200) DEFAULT NULL ,
`department` varchar (50) default null ,
`grade` varchar (50) default null ,


PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into shop_seller(id,seller_name, owner_name,card_number,QQ, email, password)values (
                      "1","donahue","ldz" ,"1234567890","123456","ldz2012yn@gmail.com","123456"
);

insert into shop_seller(id,seller_name, owner_name,card_number,QQ, email, password)values (
                      "2","sven","ldz" ,"0987654321","654321","admin@gmail.com","123456"
);
