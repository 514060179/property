ALTER TABLE `bms`.`place_record`
  ADD COLUMN `total_hour` INT (11) NULL COMMENT '总小时数' AFTER `record_status`,
  ADD COLUMN `average_charge` NUMERIC (12, 2) NULL COMMENT '费用/小时' AFTER `total_hour`,
  ADD COLUMN `attach_charge` NUMERIC (12, 2) NULL COMMENT '附加费用' AFTER `average_charge`,
  ADD COLUMN `total_charge` NUMERIC (12, 2) NULL COMMENT '总费用' AFTER `attach_charge`;