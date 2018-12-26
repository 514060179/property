ALTER TABLE `bms`.`user_role`
	ADD COLUMN `manager` VARCHAR(50) NOT NULL COMMENT '管理员id' AFTER `user_id`;