/*Table structure for table `version` */

DROP TABLE IF EXISTS `version`;

CREATE TABLE `version` (
  `version_id` varchar(50) NOT NULL COMMENT '主键',
  `version_number` varchar(20) DEFAULT NULL COMMENT '版本号',
  `version_describe` varchar(255) DEFAULT NULL COMMENT '版本内容',
  `forced_update` tinyint(4) DEFAULT NULL COMMENT '是否强制更新',
  `device_type` char(1) DEFAULT NULL COMMENT '设备类型（1.ios，2.android）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`version_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT="版本信息表";


