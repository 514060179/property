ALTER TABLE `bms`.`place`
  ADD COLUMN `place_charge` NUMERIC (12, 2) NULL COMMENT '每小时费用' AFTER `place_icon_type`,
  ADD COLUMN `place_attach_charge` NUMERIC (12, 2) NULL COMMENT '附加费用' AFTER `place_charge`;