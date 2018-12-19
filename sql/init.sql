/*
SQLyog  v12.2.6 (64 bit)
MySQL - 8.0.13 : Database - bms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bms` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `bms`;

/*Table structure for table `EVENT` */

DROP TABLE IF EXISTS `EVENT`;

CREATE TABLE `EVENT` (
  `event_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `community_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '主键',
  `event_content` text COLLATE utf8mb4_general_ci COMMENT '事件内容',
  `event_cause` text COLLATE utf8mb4_general_ci COMMENT '事件原因',
  `event_solve` text COLLATE utf8mb4_general_ci COMMENT '解决方案',
  `event_status` int(11) DEFAULT NULL COMMENT '事件进度0开始1待定2完成',
  `event_remark` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `event_date` date DEFAULT NULL COMMENT '事件日期',
  `event_type` int(11) DEFAULT NULL COMMENT '事件类型1采购2保养',
  `event_finish_date` date DEFAULT NULL COMMENT '完成时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`event_id`),
  KEY `FK_EVENT_REFERENCE_COMMUNIT` (`community_id`),
  CONSTRAINT `FK_EVENT_REFERENCE_COMMUNIT` FOREIGN KEY (`community_id`) REFERENCES `community` (`community_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='事件';

/*Data for the table `EVENT` */

/*Table structure for table `asset` */

DROP TABLE IF EXISTS `asset`;

CREATE TABLE `asset` (
  `asset_id` varchar(50) NOT NULL COMMENT '主键',
  `community_id` varchar(50) DEFAULT NULL COMMENT '主键',
  `asset_type` varchar(50) DEFAULT NULL COMMENT '资产类型',
  `asset_name` varchar(200) DEFAULT NULL COMMENT '资产名字',
  `asset_traditional_name` varchar(200) DEFAULT NULL COMMENT '资产名字(繁体)',
  `asset_english_name` varchar(200) DEFAULT NULL COMMENT '资产名字(英文)',
  `asset_position` varchar(200) DEFAULT NULL COMMENT '位置',
  `asset_traditional_position` varchar(200) DEFAULT NULL COMMENT '位置(繁体)',
  `asset_english_position` varchar(200) DEFAULT NULL COMMENT '位置(英文)',
  `asset_describe` text COMMENT '描述',
  `asset_traditional_describe` text COMMENT '描述(繁体)',
  `asset_english_describe` text COMMENT '描述(英文)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`asset_id`),
  KEY `FK_ASSET_REFERENCE_COMMUNIT` (`community_id`),
  CONSTRAINT `FK_ASSET_REFERENCE_COMMUNIT` FOREIGN KEY (`community_id`) REFERENCES `community` (`community_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='资产';

/*Data for the table `asset` */

/*Table structure for table `asset_storage_record` */

DROP TABLE IF EXISTS `asset_storage_record`;

CREATE TABLE `asset_storage_record` (
  `storage_record_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `asset_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '资产id',
  `storage_type` int(11) DEFAULT NULL COMMENT '出入类型 1入库2出库',
  `storage_amount` int(11) DEFAULT NULL COMMENT '数量',
  `storage_remark` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`storage_record_id`),
  KEY `FK_ASSET_ST_REFERENCE_ASSET` (`asset_id`),
  CONSTRAINT `FK_ASSET_ST_REFERENCE_ASSET` FOREIGN KEY (`asset_id`) REFERENCES `asset` (`asset_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='资产库存记录';

/*Data for the table `asset_storage_record` */

/*Table structure for table `building` */

DROP TABLE IF EXISTS `building`;

CREATE TABLE `building` (
  `building_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `community_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '主键',
  `building_name` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名字',
  `building_no` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '编号',
  `full_address` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '全地址',
  `building_struct` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '建筑结构',
  `building_direction` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '建筑方向',
  `floor_up_num` int(11) DEFAULT NULL COMMENT '楼上几层',
  `floor_low_num` int(11) DEFAULT NULL COMMENT '楼下几层',
  `deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`building_id`),
  UNIQUE KEY `building_no_unique` (`building_no`),
  KEY `FK_BUILDING_REFERENCE_COMMUNIT` (`community_id`),
  CONSTRAINT `FK_BUILDING_REFERENCE_COMMUNIT` FOREIGN KEY (`community_id`) REFERENCES `community` (`community_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='建筑,大厦';

/*Data for the table `building` */

/*Table structure for table `community` */

DROP TABLE IF EXISTS `community`;

CREATE TABLE `community` (
  `community_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `community_no` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '社区编号',
  `community_name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名字',
  `community_loc_x` decimal(20,10) DEFAULT NULL COMMENT '纬度',
  `community_loc_y` decimal(20,10) DEFAULT NULL COMMENT '经度',
  `community_address` varchar(300) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '位置,地址',
  `community_area` decimal(12,2) DEFAULT NULL COMMENT '总面积(平方米)',
  `community_building_area` decimal(12,2) DEFAULT NULL COMMENT '建筑面积',
  `community_road_area` decimal(12,2) DEFAULT NULL COMMENT '道路面积',
  `community_greenarea` decimal(12,2) DEFAULT NULL COMMENT '绿化面积(平方米)',
  `community_common_area` decimal(12,2) DEFAULT NULL COMMENT '公用面积',
  `community_garage_area` decimal(12,2) DEFAULT NULL COMMENT '车库面积',
  `community_garage_count` int(11) DEFAULT NULL COMMENT '车位数量',
  `community_room_count` int(11) DEFAULT NULL COMMENT '房间总数',
  `community_management_type` tinyint(4) DEFAULT NULL COMMENT '管理类型(0普通管理1综合管理)',
  `community_contacts` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系人',
  `community_contacts_phone` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系人号码',
  `community_remark` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `community_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`community_id`),
  UNIQUE KEY `unique_community_no` (`community_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='社区';

/*Data for the table `community` */

/*Table structure for table `complain` */

DROP TABLE IF EXISTS `complain`;

CREATE TABLE `complain` (
  `complain_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户id',
  `community_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '主键',
  `complain_position` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '位置(XX大厦)',
  `complain_specific_position` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '具体位置(停车场,商铺...)',
  `complain_type` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类型',
  `complain_class_type` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类型分类',
  `complain_describe` text COLLATE utf8mb4_general_ci COMMENT '描述',
  `complain_voice` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '声音',
  `complain_images` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图片',
  `complain_liaisons_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联络人',
  `complain_liaisons_sex` tinyint(1) DEFAULT NULL COMMENT '性别0女1男',
  `complain_liaisons_email` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮件',
  `complain_status` int(11) DEFAULT NULL COMMENT '状态0发起1收到2处理中3处理完成',
  `complain_finish_time` timestamp NULL DEFAULT NULL COMMENT '完成时间',
  `complain_handler` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '处理人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`complain_id`),
  KEY `FK_COMPLAIN_REFERENCE_USER` (`user_id`),
  KEY `FK_COMPLAIN_REFERENCE_COMMUNIT` (`community_id`),
  CONSTRAINT `FK_COMPLAIN_REFERENCE_COMMUNIT` FOREIGN KEY (`community_id`) REFERENCES `community` (`community_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_COMPLAIN_REFERENCE_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='投诉';

/*Data for the table `complain` */

/*Table structure for table `manager` */

DROP TABLE IF EXISTS `manager`;

CREATE TABLE `manager` (
  `manager_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `community_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '主键',
  `NAME` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名字',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `email` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '电邮',
  `username` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名',
  `PASSWORD` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`manager_id`),
  KEY `FK_MANAGER_REFERENCE_COMMUNIT` (`community_id`),
  CONSTRAINT `FK_MANAGER_REFERENCE_COMMUNIT` FOREIGN KEY (`community_id`) REFERENCES `community` (`community_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='管理员';

/*Data for the table `manager` */

/*Table structure for table `notice` */

DROP TABLE IF EXISTS `notice`;

CREATE TABLE `notice` (
  `notice_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `community_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '社区id',
  `notice_type` int(11) DEFAULT NULL COMMENT '通知类型',
  `notice_title` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '场所标题(简体)',
  `notice_traditional_title` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '场所标题(繁体)',
  `notice_english_title` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '场所标题(英文)',
  `notice_image` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图片',
  `notice_details` text COLLATE utf8mb4_general_ci COMMENT '详情',
  `notice_traditional_details` text COLLATE utf8mb4_general_ci COMMENT '详情',
  `notice_english_details` text COLLATE utf8mb4_general_ci COMMENT '详情',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`notice_id`),
  KEY `FK_NOTICE_REFERENCE_COMMUNIT` (`community_id`),
  CONSTRAINT `FK_NOTICE_REFERENCE_COMMUNIT` FOREIGN KEY (`community_id`) REFERENCES `community` (`community_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='通告';

/*Data for the table `notice` */

/*Table structure for table `place` */

DROP TABLE IF EXISTS `place`;

CREATE TABLE `place` (
  `place_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `community_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '社区id',
  `place_name` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '场所名字(简体)',
  `place_traditional_name` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '场所名字(繁体)',
  `place_english_name` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '场所名字(英文)',
  `place_introduction` text COLLATE utf8mb4_general_ci COMMENT '简介',
  `place_traditional_introduction` text COLLATE utf8mb4_general_ci COMMENT '繁体简介',
  `place_english_introduction` text COLLATE utf8mb4_general_ci COMMENT '英文简介',
  `place_image` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图片url',
  `place_start_time` time DEFAULT NULL COMMENT '起始时间',
  `place_end_time` time DEFAULT NULL COMMENT '结束时间',
  `place_need_order` int(11) DEFAULT NULL COMMENT '需要预定(0否1是)',
  `place_upper_limit` int(11) DEFAULT NULL COMMENT '预约时间上限',
  `place_advance_order_day` int(11) DEFAULT NULL COMMENT '提前天数',
  `place_farthest_order_day` int(11) DEFAULT NULL COMMENT '最大天数',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`place_id`),
  KEY `FK_PLACE_REFERENCE_COMMUNIT` (`community_id`),
  CONSTRAINT `FK_PLACE_REFERENCE_COMMUNIT` FOREIGN KEY (`community_id`) REFERENCES `community` (`community_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='场所';

/*Data for the table `place` */

/*Table structure for table `place_record` */

DROP TABLE IF EXISTS `place_record`;

CREATE TABLE `place_record` (
  `record_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户id',
  `community_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '主键',
  `place_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '场所id',
  `order_date` date DEFAULT NULL COMMENT '预约日期',
  `order_start_time` time DEFAULT NULL COMMENT '预定开始时间',
  `order_end_time` time DEFAULT NULL COMMENT '预定结束时间',
  `record_status` int(11) DEFAULT NULL COMMENT '预定状态(-1预约取消0开始发起1预约成功2预约失败)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`record_id`),
  KEY `FK_PLACE_RE_REFERENCE_USER` (`user_id`),
  KEY `FK_PLACE_RE_REFERENCE_COMMUNIT` (`community_id`),
  CONSTRAINT `FK_PLACE_RE_REFERENCE_COMMUNIT` FOREIGN KEY (`community_id`) REFERENCES `community` (`community_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_PLACE_RE_REFERENCE_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='预定记录';

/*Data for the table `place_record` */

/*Table structure for table `unit` */

DROP TABLE IF EXISTS `unit`;

CREATE TABLE `unit` (
  `unit_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `building_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '建筑id',
  `unit_name` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名字',
  `unit_no` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '单元编号',
  `unit_covered_area` decimal(10,2) DEFAULT NULL COMMENT '覆盖面积大小(单位平方米)',
  `unit_relative_proportion` decimal(5,3) DEFAULT NULL COMMENT '分层建筑物相对比(千分之一)',
  `unit_child_relative_proportion` decimal(5,3) DEFAULT NULL COMMENT '分层建筑物之子部分相对比(千分之一)',
  `unit_purpose` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用途',
  `unit_position` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '位置(地下,一楼,二楼,三楼...)',
  `unit_type` int(11) DEFAULT NULL COMMENT '单位类型1商铺2住宅3停车场',
  `unit_full_address` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '地址',
  `unit_status` int(11) DEFAULT NULL COMMENT '单元状态0空置1租赁2装修中3入住',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`unit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='单元';

/*Data for the table `unit` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `community_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '主键',
  `NAME` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名字',
  `english_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '英文名字',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别(0女1男)',
  `country_code` varchar(5) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '区号',
  `tel` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `email` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '电邮',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `id_card` varchar(18) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '身份证',
  `username` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录账号',
  `PASSWORD` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录密码',
  `portrait` varchar(300) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `marriage_system` varchar(3) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '婚姻制度',
  `mate_name` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '配偶名字',
  `deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `unique_username` (`username`),
  KEY `FK_USER_REFERENCE_COMMUNIT` (`community_id`),
  CONSTRAINT `FK_USER_REFERENCE_COMMUNIT` FOREIGN KEY (`community_id`) REFERENCES `community` (`community_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户';

/*Data for the table `user` */

/*Table structure for table `user_unit` */

DROP TABLE IF EXISTS `user_unit`;

CREATE TABLE `user_unit` (
  `user_unit_id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '业主id',
  `unit_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '单元id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_unit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='业主与单元关系';

/*Data for the table `user_unit` */

/*Table structure for table `visitor` */

DROP TABLE IF EXISTS `visitor`;

CREATE TABLE `visitor` (
  `visitor_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `community_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '主键',
  `visitor_name` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '访客名字',
  `visitor_sex` int(11) DEFAULT NULL COMMENT '性别0女1男',
  `visitor_phone` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号码',
  `visitor_cause` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '访客来由',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`visitor_id`),
  KEY `FK_VISITOR_REFERENCE_COMMUNIT` (`community_id`),
  CONSTRAINT `FK_VISITOR_REFERENCE_COMMUNIT` FOREIGN KEY (`community_id`) REFERENCES `community` (`community_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='访客';

/*Data for the table `visitor` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

ALTER TABLE `bms`.`manager`
  ADD  UNIQUE INDEX `UNIQUE_MANAGER_USERNAME` (`username`);