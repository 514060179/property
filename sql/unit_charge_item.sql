CREATE TABLE `bms`.`unit_charge_item`(
  `unit_item_id` VARCHAR(50) NOT NULL COMMENT 'id',
  `item_id` VARCHAR(50) NOT NULL COMMENT '项目id',
  `unit_id` VARCHAR(100) NOT NULL COMMENT '单元id',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`unit_item_id`),
  FOREIGN KEY (`item_id`) REFERENCES `charge_item`(`item_id`),
  FOREIGN KEY (`unit_id`) REFERENCES `unit`(`unit_id`),
  UNIQUE INDEX `UNIQUE_UNIT_ITEM` (`item_id`, `unit_id`)
) COMMENT '单位收费项目' ENGINE=INNODB CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;