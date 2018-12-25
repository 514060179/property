/*
SQLyog Ultimate v12.3.1 (64 bit)
MySQL - 5.7.23 : Database - bms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bms` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `bms`;

/*Table structure for table `image` */

DROP TABLE IF EXISTS `image`;

CREATE TABLE `image` (
  `image_id` varchar(50) NOT NULL COMMENT '主键',
  `image_url` varchar(300) DEFAULT NULL COMMENT '图片url',
  `complain_id` varchar(50) DEFAULT NULL COMMENT '投诉/保修id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`image_id`),
  KEY `FK_IMAGE_REFERENCE_COMPLAIN` (`complain_id`),
  CONSTRAINT `FK_IMAGE_REFERENCE_COMPLAIN` FOREIGN KEY (`complain_id`) REFERENCES `complain` (`complain_id`) ON DELETE RESTRICT ON 
  UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='图片表';
