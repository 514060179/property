/*Table structure for table `images` */

DROP TABLE IF EXISTS `images`;

CREATE TABLE `images` (
  `image_id` VARCHAR(50) NOT NULL COMMENT '主键',
  `image_url` VARCHAR(300) DEFAULT NULL COMMENT '图片url',
  `object_id` VARCHAR(50) DEFAULT NULL COMMENT '对象id如:场所 place_id',
  `image_type` INT NOT NULL DEFAULT 0 COMMENT '图片类型0投诉/保修1场所2公告3其他',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`image_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='图片表';
