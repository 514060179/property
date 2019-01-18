CREATE TABLE `bms`.`charge_item_record`(
  `record_id` VARCHAR(50) NOT NULL COMMENT 'id',
  `user_id` VARCHAR(50) NOT NULL COMMENT '住户id',
  `unit_item_id` VARCHAR(50) NOT NULL COMMENT '单位收费项目id',
  `record_date` VARCHAR(20) NOT NULL COMMENT '收费记录年月份(2019-01)',
  `record_status` INT NOT NULL COMMENT '状态0欠费1已付2预支付',
  `record_time` DATETIME COMMENT '收费时间',
  `record_actual_amount` DECIMAL(12,2) COMMENT '实际收取金额',
  `record_amount` DECIMAL(12,2) COMMENT '收费金额',
  `record_late_fee` DECIMAL COMMENT '滞纳金额',
  `record_late_date` INT COMMENT '滞纳金天数',
  `record_remark` VARCHAR(200) COMMENT '备注',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`record_id`),
  UNIQUE INDEX `UNIQUE_USER_ITEM_RECORD` (`user_id`, `unit_item_id`, `record_date`),
  FOREIGN KEY (`user_id`) REFERENCES `bms`.`user`(`user_id`),
  FOREIGN KEY (`unit_item_id`) REFERENCES `bms`.`unit_charge_item`(`unit_item_id`)
) COMMENT '收费记录' ENGINE=INNODB CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;