ALTER TABLE `bms`.`unit`
  ADD COLUMN `community_id` VARCHAR(50) NOT NULL COMMENT '社区id' AFTER `unit_id`,
  ADD FOREIGN KEY (`community_id`) REFERENCES `bms`.`community`(`community_id`);


 ALTER TABLE `bms`.`unit`
  CHANGE `unit_type` `unit_type` INT(11) DEFAULT 1 NULL COMMENT '单位用途1商业2轻型汽车/电单车3住宅';
  ADD COLUMN `unit_title` INT(11) NULL COMMENT '单位业权' AFTER `unit_status`;