-- -------------
-- 创建数据库和相关的账户,取得相关的权限
--
-- -------------
drop database if exists shop;

create database shop default character set utf8 collate utf8_general_ci;

grant all on *.* to 'shop'@'localhost' identified by 'shop123456' with grant option;




