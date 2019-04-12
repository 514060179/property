ALTER TABLE `bms`.`advertisement`
  ADD COLUMN `purpose` INT DEFAULT 0 NULL COMMENT '用途:0通告1节日提醒2注意事项3政府文件4外判公司须知5工程6办理手续' AFTER `used`,
  ADD COLUMN `start_time` DATE NULL COMMENT '开始时间' AFTER `purpose`,
  ADD COLUMN `end_time` DATE NULL COMMENT '结束时间' AFTER `start_time`;