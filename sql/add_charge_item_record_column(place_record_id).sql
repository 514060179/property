ALTER TABLE `bms`.`charge_item_record`
ADD COLUMN `place_record_id` varchar(50) NULL COMMENT '定场记录id' AFTER `record_late_date`;


ALTER TABLE `bms`.`charge_item_record`
ADD COLUMN `unit_no` varchar(50) NULL AFTER `unit_id`;



ALTER TABLE `bms`.`charge_item_record`
ADD COLUMN `unit_id` varchar(50) NULL AFTER `user_id`;


ALTER TABLE `bms`.`charge_item_record` DROP FOREIGN KEY `charge_item_record_ibfk_1`;

ALTER TABLE `bms`.`charge_item_record` DROP FOREIGN KEY `charge_item_record_ibfk_2`;


ALTER TABLE `bms`.`charge_item_record`
ADD COLUMN `community_id` varchar(50) NULL COMMENT '社区id' AFTER `record_id`;


update charge_item_record c inner join unit u on c.unit_id=u.unit_id
set c.unit_no=u.unit_no,c.community_id=u.community_id


ALTER TABLE `bms`.`charge_item_record`
MODIFY COLUMN `unit_item_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '单位收费项目id' AFTER `unit_no`;


ALTER TABLE `bms`.`charge_item_record`
  ADD COLUMN `unit_no` VARCHAR(50) NULL COMMENT '单元编号' AFTER `unit_id`;


ALTER TABLE `bms`.`charge_item_record`
ADD COLUMN `record_type` int(3) NULL DEFAULT 0 COMMENT '记录类型0物业费1基金收费2订场收费3其他收费' AFTER `unit_item_id`;



  ALTER TABLE `bms`.`charge_item_record`
  ADD COLUMN `record_type` INT(10) DEFAULT 0 NULL COMMENT '记录类型0物业费1基金收费2订场收费3其他收费' AFTER `place_record_id`;

