CREATE TABLE `bms`.`advance_money`(
  `advance_id` VARCHAR(50) NOT NULL COMMENT 'id',
  `user_id` VARCHAR(50) NOT NULL COMMENT '用户id',
  `advance_amount` DECIMAL(12,2) NOT NULL COMMENT '预收金额',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`advance_id`),
  UNIQUE INDEX `UNIQUE_USER` (`user_id`),
  FOREIGN KEY (`user_id`) REFERENCES `bms`.`user`(`user_id`)
)COMMENT '预收费用账户'  ENGINE=INNODB CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;