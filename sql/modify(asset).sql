 ALTER TABLE `bms`.`asset`
  ADD COLUMN `asset_maintain` TINYINT(1) DEFAULT 0 NULL COMMENT '是否需要保养0否1是' AFTER `asset_english_describe`,
  ADD COLUMN `asset_maintain_remind_cycle` INT(11) DEFAULT 0 NULL COMMENT '提醒周期' AFTER `asset_maintain`;

ALTER TABLE `bms`.`asset`
  ADD COLUMN `asset_no` VARCHAR(50) NULL COMMENT '资产编号' AFTER `community_id`,
  ADD  UNIQUE INDEX `UNIQUE_ASSET_NO` (`community_id`, `asset_no`);
