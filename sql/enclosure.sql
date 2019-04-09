CREATE TABLE `bms`.`enclosure`(
  `enclosure_id` VARCHAR(50) NOT NULL,
  `object_id` VARCHAR(50) NOT NULL COMMENT '对象id',
  `enclosure_url` VARCHAR(100) NOT NULL COMMENT '地址url',
  `enclosure_object_type` INT NOT NULL DEFAULT 0 COMMENT '附件对象类型0社区1事件2楼宇',
  `enclosure_type` INT NOT NULL DEFAULT 0 COMMENT '附件类型0 pdf文件；1其他文件',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`enclosure_id`)
) ENGINE=INNODB CHARSET=utf8mb4 COMMENT '附件' COLLATE=utf8mb4_general_ci;