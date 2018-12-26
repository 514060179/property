ALTER TABLE `bms`.`user_role`   
  CHANGE `user_id` `manager_id` VARCHAR(50) CHARSET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户id';
