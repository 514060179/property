CREATE TABLE `bms`.`floor`(
  `floor_id` VARCHAR(50) NOT NULL,
  `building_id` VARCHAR(50) NOT NULL COMMENT '楼宇id',
  `floor_name` VARCHAR(50) NOT NULL COMMENT '楼层名称',
  `floor_purpose` VARCHAR(50) COMMENT '楼层用途',
  `floor_ichnography` VARCHAR(50) COMMENT '楼层平面图',
  `floor_sort` INT DEFAULT 999 COMMENT '楼层排序.从小至大',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`floor_id`),
  FOREIGN KEY (`building_id`) REFERENCES `bms`.`building`(`building_id`)
) ENGINE=INNODB COMMENT '楼层' CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;