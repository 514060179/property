ALTER TABLE `bms`.`notice`
  ADD COLUMN `building_id` VARCHAR(50) NULL COMMENT '楼宇id' AFTER `community_id`,
  DROP FOREIGN KEY `FK_NOTICE_REFERENCE_COMMUNIT`;

ALTER TABLE `bms`.`notice`
  CHANGE `notice_type` `notice_type` INT (11) NULL COMMENT '公告类型0通告1节日提醒2注意事项3政府文件4外判公司须知5工程6办理手续',
  ADD COLUMN `start_time` DATE NULL COMMENT '开始日期' AFTER `notice_english_details`,
  ADD COLUMN `end_time` DATE NULL COMMENT '结束日期' AFTER `start_time`;
