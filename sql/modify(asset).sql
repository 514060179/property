 ALTER TABLE `bms`.`asset`
  ADD COLUMN `asset_maintain` TINYINT(1) DEFAULT 0 NULL COMMENT '是否需要保养0否1是' AFTER `asset_english_describe`,
  ADD COLUMN `asset_maintain_remind_cycle` INT(11) DEFAULT 0 NULL COMMENT '提醒周期' AFTER `asset_maintain`;

ALTER TABLE `bms`.`asset`
  ADD COLUMN `asset_no` VARCHAR(50) NULL COMMENT '资产编号' AFTER `community_id`,
  ADD  UNIQUE INDEX `UNIQUE_ASSET_NO` (`community_id`, `asset_no`);

ALTER TABLE `bms`.`asset`
  ADD COLUMN `asset_buy_date` DATE NULL COMMENT '购买日期' AFTER `asset_maintain_remind_cycle`;

ALTER TABLE `bms`.`asset`
  ADD COLUMN `asset_overdue_date` DATE NULL COMMENT '过期日期' AFTER `asset_buy_date`,
  ADD COLUMN `asset_status` INT DEFAULT 0 NULL COMMENT '状态0使用中1货存2损坏3弃置' AFTER `asset_type`;