ALTER TABLE `bms`.`building`
  ADD COLUMN `house_num` INT NULL COMMENT '住宅数量' AFTER `building_direction`,
  ADD COLUMN `parking_num` INT NULL COMMENT '车位数量' AFTER `house_num`;

ALTER TABLE `bms`.`building`
  ADD COLUMN `community_child_id` VARCHAR(50) NULL COMMENT '社区子部分id' AFTER `community_id`;
