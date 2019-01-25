  ALTER TABLE `bms`.`visitor`
  ADD COLUMN `building_id` VARCHAR(50) NULL COMMENT '楼宇id' AFTER `community_id`;