CREATE TABLE `bms`.`advance_record`(
  `advance_record_id` VARCHAR(50) NOT NULL COMMENT 'id',
  `advance_id` VARCHAR(50) COMMENT '预收账户id',
  `advance_amount` DECIMAL(12,2) NOT NULL COMMENT '费用',
  `advance_type` INT NOT NULL DEFAULT 0 COMMENT '出入账0出账1入账',
  `advance_describe` VARCHAR(200) COMMENT '描述',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`advance_record_id`),
  FOREIGN KEY (`advance_id`) REFERENCES `bms`.`advance_money`(`advance_id`)
) ENGINE=INNODB CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
