ALTER TABLE `bms`.`event`
  CHANGE `event_type` `event_type` INT (11) NULL COMMENT '事件类型1采购2保养3投诉4损坏',
  CHANGE `event_status` `event_status` INT(11) NULL COMMENT '事件进度0跟进中1报价中2接获投诉3与管理机关讨论中4待定5工程进行中6待开大会表决7完成',
  ADD COLUMN `asset_no` VARCHAR (50) NULL COMMENT '资产编号' AFTER `community_id`,
  ADD COLUMN `user_id` VARCHAR(50) NULL COMMENT '业主id' AFTER `asset_no`,
  ADD COLUMN `complain_position` VARCHAR (50) NULL COMMENT '投诉位置(XX大厦)' AFTER `event_remind_cycle`,
  ADD COLUMN `complain_specific_position` VARCHAR (50) NULL COMMENT '具体位置(停车场,商铺...)' AFTER `complain_position`,
  ADD COLUMN `complain_type` VARCHAR (50) NULL COMMENT '投诉类型' AFTER `complain_specific_position`,
  ADD COLUMN `complain_class_type` VARCHAR (50) NULL COMMENT '投诉类型分类' AFTER `complain_type`,
  ADD COLUMN `complain_voice` VARCHAR (50) NULL COMMENT '投诉声音' AFTER `complain_class_type`,
  ADD COLUMN `complain_reply` VARCHAR (200) NULL COMMENT '投诉进度回复' AFTER `complain_voice`,
  ADD COLUMN `event_channel` INT (11) DEFAULT 0 NULL COMMENT '接收渠道:0公司1app2业主口头投诉' AFTER `complain_reply`;