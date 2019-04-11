CREATE TABLE `bms`.`building_child`(
  `child_id` VARCHAR(50) NOT NULL,
  `building_id` VARCHAR(50) NOT NULL COMMENT '楼宇id',
  `tips` VARCHAR(50) NOT NULL COMMENT '子部分之提示:数量',
  `value` VARCHAR(150) NOT NULL COMMENT '子部分之值:50',
  `sort` INT NOT NULL DEFAULT 999 COMMENT '排序',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`child_id`),
  FOREIGN KEY (`building_id`) REFERENCES `bms`.`building`(`building_id`)
) ENGINE=INNODB COMMENT '楼宇之子部分' CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;