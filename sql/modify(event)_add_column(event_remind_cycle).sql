ALTER TABLE `bms`.`event`
  ADD COLUMN `event_remind_cycle` INT(11) DEFAULT 15 NULL COMMENT '事件提醒周期（单位为天）' AFTER `event_type`;