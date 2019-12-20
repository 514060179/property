CREATE TABLE `bms`.`community_child`(
  `community_child_id` VARCHAR(50) NOT NULL,
  `community_id` VARCHAR(50) NOT NULL COMMENT ''社区id'',
  `name` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT ''名称'',
  `amount` INT(11) NOT NULL COMMENT ''数量'',
  `area` DECIMAL(12,2) NOT NULL COMMENT ''面积'',
  `purpose` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT ''用途'',
  `perthousand` DECIMAL(12,2) DEFAULT NULL COMMENT ''千分比之相对价值'',
  `tips` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT ''子部分之提示:数量'',
  `value` VARCHAR(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT ''子部分之值:50'',
  `sort` INT(11) NOT NULL DEFAULT ''999'' COMMENT ''排序'',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT ''创建时间'',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT ''更新时间'',
  PRIMARY KEY (`community_child_id`),
  FOREIGN KEY (`community_id`) REFERENCES `bms`.`community`(`community_id`)
) ENGINE=INNODB COMMENT ''社区之子部分'' CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;