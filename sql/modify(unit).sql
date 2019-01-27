ALTER TABLE `bms`.`unit`
  ADD COLUMN `community_id` VARCHAR(50) NOT NULL COMMENT '社区id' AFTER `unit_id`,
  ADD FOREIGN KEY (`community_id`) REFERENCES `bms`.`community`(`community_id`);
