  ALTER TABLE `bms`.`role`
  ADD  UNIQUE INDEX `UNIQUE_ROLE_NAME` (`role_name`);

ALTER TABLE `bms`.`role`
  ADD COLUMN `role_default` TINYINT(1) DEFAULT 0 NULL COMMENT '是否默认0否1是' AFTER `role_description`;
