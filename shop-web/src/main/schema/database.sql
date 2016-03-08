-- MySQL dump 10.13  Distrib 5.6.19, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: shop
-- ------------------------------------------------------
-- Server version	5.6.19-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_admin`
--

DROP TABLE IF EXISTS `shop_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_admin` (
  `id` varchar(32) NOT NULL,
  `admin_name` varchar(120) NOT NULL,
  `real_name` varchar(120) NOT NULL,
  `register_date` datetime DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `mobile` varchar(30) DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL COMMENT 'F,M',
  `email` varchar(50) DEFAULT NULL,
  `admin_status` varchar(1) DEFAULT NULL COMMENT '1.正常，2.禁用，3.注销',
  `password` varchar(255) DEFAULT NULL,
  `icon` varchar(100) DEFAULT NULL COMMENT '先不用做',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_admin`
--

LOCK TABLES `shop_admin` WRITE;
/*!40000 ALTER TABLE `shop_admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_article`
--

DROP TABLE IF EXISTS `shop_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_article` (
  `id` varchar(32) NOT NULL,
  `title` varchar(120) NOT NULL,
  `key_word` varchar(200) NOT NULL COMMENT '逗号隔开，方便搜索',
  `content` longtext,
  `last_update` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_article`
--

LOCK TABLES `shop_article` WRITE;
/*!40000 ALTER TABLE `shop_article` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_authority`
--

DROP TABLE IF EXISTS `shop_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_authority` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  `authority_level` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_authority`
--

LOCK TABLES `shop_authority` WRITE;
/*!40000 ALTER TABLE `shop_authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_cart`
--

DROP TABLE IF EXISTS `shop_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_cart` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `goods_id` varchar(1000) NOT NULL,
  `quantity` int(11) DEFAULT '1',
  `old_prince` decimal(10,0) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL COMMENT '0，不可以，1可以购买',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_cart`
--

LOCK TABLES `shop_cart` WRITE;
/*!40000 ALTER TABLE `shop_cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_category`
--

DROP TABLE IF EXISTS `shop_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_category` (
  `id` varchar(32) NOT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `category` varchar(60) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  CONSTRAINT `shop_category_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `shop_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_category`
--

LOCK TABLES `shop_category` WRITE;
/*!40000 ALTER TABLE `shop_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_favoriate`
--

DROP TABLE IF EXISTS `shop_favoriate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_favoriate` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `goods_id` varchar(1000) NOT NULL,
  `quantity` int(11) DEFAULT '1',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_favoriate`
--

LOCK TABLES `shop_favoriate` WRITE;
/*!40000 ALTER TABLE `shop_favoriate` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_favoriate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_goods`
--

DROP TABLE IF EXISTS `shop_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_goods` (
  `id` varchar(32) NOT NULL,
  `name` varchar(256) NOT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `code` varchar(100) DEFAULT NULL,
  `description` longtext,
  `cost` decimal(10,0) DEFAULT NULL,
  `sale_price` decimal(10,0) DEFAULT NULL,
  `discount_price` decimal(10,0) DEFAULT NULL,
  `credit` int(11) DEFAULT NULL,
  `brand` varchar(200) DEFAULT NULL,
  `category_id` varchar(32) DEFAULT NULL,
  `photo` longtext,
  `store_id` varchar(32) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `audit` varchar(1) DEFAULT NULL COMMENT '0未审核 1审核通过 2审核不通过',
  `status` varchar(1) DEFAULT NULL COMMENT '0下架 1上架 2缺货',
  `sale_quantity` int(11) DEFAULT NULL,
  `scan_time` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_goods`
--

LOCK TABLES `shop_goods` WRITE;
/*!40000 ALTER TABLE `shop_goods` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_goods_answer`
--

DROP TABLE IF EXISTS `shop_goods_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_goods_answer` (
  `id` varchar(32) NOT NULL,
  `question_id` varchar(32) NOT NULL,
  `ans_comment` varchar(1000) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_goods_answer`
--

LOCK TABLES `shop_goods_answer` WRITE;
/*!40000 ALTER TABLE `shop_goods_answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_goods_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_goods_comment`
--

DROP TABLE IF EXISTS `shop_goods_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_goods_comment` (
  `id` varchar(32) NOT NULL,
  `goods_id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `comment_score` int(11) DEFAULT '5' COMMENT '默认是5分',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_goods_comment`
--

LOCK TABLES `shop_goods_comment` WRITE;
/*!40000 ALTER TABLE `shop_goods_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_goods_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_goods_question`
--

DROP TABLE IF EXISTS `shop_goods_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_goods_question` (
  `id` varchar(32) NOT NULL,
  `goods_id` varchar(32) NOT NULL,
  `ask_comment` varchar(1000) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `question_times` int(11) DEFAULT '1',
  `status` varchar(1) DEFAULT NULL COMMENT '0，等待卖家审核，1，正常状态，2审核不通过',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_goods_question`
--

LOCK TABLES `shop_goods_question` WRITE;
/*!40000 ALTER TABLE `shop_goods_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_goods_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_offline_order`
--

DROP TABLE IF EXISTS `shop_offline_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_offline_order` (
  `id` varchar(32) NOT NULL,
  `seller_mobile` varchar(30) NOT NULL,
  `seller_phone` varchar(30) DEFAULT NULL,
  `seller_email` varchar(50) DEFAULT NULL,
  `seller_message` longtext,
  `user_mobile` varchar(30) NOT NULL,
  `user_phone` varchar(30) DEFAULT NULL,
  `user_email` varchar(50) DEFAULT NULL,
  `user_message` longtext,
  `seller_id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `goods_id` varchar(32) NOT NULL,
  `quantity` int(11) DEFAULT '1',
  `status` varchar(1) DEFAULT NULL COMMENT '0.取消   1，成功',
  `cancel_reason` longtext,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_offline_order`
--

LOCK TABLES `shop_offline_order` WRITE;
/*!40000 ALTER TABLE `shop_offline_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_offline_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_role`
--

DROP TABLE IF EXISTS `shop_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_role` (
  `id` varchar(32) NOT NULL,
  `role_name` varchar(120) NOT NULL,
  `default_level` varchar(120) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_role`
--

LOCK TABLES `shop_role` WRITE;
/*!40000 ALTER TABLE `shop_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_seller`
--

DROP TABLE IF EXISTS `shop_seller`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_seller` (
  `id` varchar(32) NOT NULL,
  `seller_name` varchar(120) NOT NULL COMMENT '唯一',
  `owner_name` varchar(120) NOT NULL,
  `card_number` varchar(30) NOT NULL,
  `front_pic` varchar(100) DEFAULT NULL COMMENT ' 有像素要求',
  `back_pic` varchar(100) DEFAULT NULL COMMENT ' 有像素要求',
  `QQ` varchar(50) NOT NULL,
  `owner_photos` varchar(255) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `mobile` varchar(30) DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL COMMENT 'F,M',
  `email` varchar(50) DEFAULT NULL,
  `seller_status` varchar(1) DEFAULT NULL COMMENT '0，预启用，1启用（激活）2，首次登陆，3，资料未完善4.正常，5.禁用，6.注销',
  `password` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL COMMENT '现在先不用',
  `birthday` datetime DEFAULT NULL,
  `birth_place` varchar(300) DEFAULT NULL COMMENT '省+市+县（区）',
  `register_date` datetime DEFAULT NULL,
  `living` varchar(300) DEFAULT NULL COMMENT '省+市+县（区）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_seller`
--

LOCK TABLES `shop_seller` WRITE;
/*!40000 ALTER TABLE `shop_seller` DISABLE KEYS */;
INSERT INTO `shop_seller` VALUES ('1','donahue','ldz','1234567890',NULL,NULL,'123456',NULL,NULL,NULL,NULL,'ldz2012yn@gmail.com',NULL,'123456',NULL,NULL,NULL,NULL,NULL),('2','sven','ldz','0987654321',NULL,NULL,'654321',NULL,NULL,NULL,NULL,'admin@gmail.com',NULL,'123456',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `shop_seller` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_store`
--

DROP TABLE IF EXISTS `shop_store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_store` (
  `id` varchar(32) NOT NULL,
  `seller_id` varchar(32) NOT NULL,
  `store_name` varchar(120) NOT NULL,
  `longitude` decimal(18,12) NOT NULL,
  `latitude` decimal(18,12) NOT NULL,
  `address` varchar(200) NOT NULL,
  `zip` varchar(12) DEFAULT NULL,
  `register_date` datetime DEFAULT NULL,
  `audit_pass` varchar(1) DEFAULT NULL,
  `audit_time` datetime DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `sort_order` int(11) DEFAULT NULL,
  `store_status` varchar(1) DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `transfer_fee` int(11) DEFAULT NULL,
  `transfer_limit` int(11) DEFAULT NULL,
  `invoice` int(11) DEFAULT NULL,
  `pay_way` varchar(1) DEFAULT NULL,
  `province` varchar(60) DEFAULT NULL,
  `city` varchar(60) DEFAULT NULL,
  `country` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_store`
--

LOCK TABLES `shop_store` WRITE;
/*!40000 ALTER TABLE `shop_store` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_user`
--

DROP TABLE IF EXISTS `shop_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_user` (
  `id` varchar(32) NOT NULL,
  `user_name` varchar(120) NOT NULL,
  `real_name` varchar(120) DEFAULT NULL,
  `register_date` datetime DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `mobile` varchar(30) DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL COMMENT 'F,M',
  `email` varchar(50) DEFAULT NULL,
  `user_status` varchar(1) DEFAULT NULL COMMENT '0，预启用，1启用（激活）2，首次登陆，3，资料未完善 4.正常，5.禁用，6.注销',
  `password` varchar(255) DEFAULT NULL,
  `icon` varchar(100) DEFAULT NULL COMMENT '先不用做',
  `birthday` datetime DEFAULT NULL,
  `school` varchar(200) DEFAULT NULL,
  `department` varchar(50) DEFAULT NULL,
  `grade` varchar(50) DEFAULT NULL,
  `third_account` varchar(50) DEFAULT NULL,
  `third_token` varchar(50) DEFAULT NULL,
  `third_party` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_user`
--

LOCK TABLES `shop_user` WRITE;
/*!40000 ALTER TABLE `shop_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-02-01 18:05:41
