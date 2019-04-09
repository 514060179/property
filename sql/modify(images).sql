ALTER TABLE `bms`.`images`
  CHANGE `image_type` `image_type` INT(11) DEFAULT 0 NOT NULL COMMENT '图片类型0投诉/保修1场所2公告3物业资产4事件';