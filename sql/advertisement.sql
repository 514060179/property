CREATE TABLE `bms`.`advertisement`(
  `adv_id` VARCHAR(50) NOT NULL COMMENT 'id',
  `community_id` VARCHAR(50) COMMENT '社区id',
  `building_id` VARCHAR(50) COMMENT '楼宇id',
  `title` VARCHAR(200) NOT NULL COMMENT '广告名字',
  `type` INT DEFAULT 0 COMMENT '广告类型0普通图片1视频',
  `url` VARCHAR(200) NOT NULL COMMENT 'url',
  `residence_time` INT COMMENT '播放时间（单位秒）',
  `describe` VARCHAR(300) COMMENT '描述',
  `used` TINYINT(1) DEFAULT 0 NULL COMMENT '是否使用0否1是' ,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`adv_id`)
) COMMENT '广告' ENGINE=INNODB CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;