ALTER TABLE `bms`.`unit_charge_item`
  ADD COLUMN `type` INT DEFAULT 0 NULL COMMENT '0周期性1临时性2已处理' AFTER `unit_id`;