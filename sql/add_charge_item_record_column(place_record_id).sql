ALTER TABLE `bms`.`charge_item_record`
ADD COLUMN `place_record_id` varchar(50) NULL COMMENT '定场记录id' AFTER `record_late_date`;