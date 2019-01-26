ALTER TABLE `bms`.`charge_item`
  ADD COLUMN `community_id` VARCHAR(50) NOT NULL COMMENT '社区id' AFTER `item_id`,
  ADD FOREIGN KEY (`community_id`) REFERENCES `bms`.`community`(`community_id`);