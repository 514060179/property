DROP TABLE IF EXISTS `jurisdiction`;

CREATE TABLE `jurisdiction` (
  `jn_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `jn_name` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '权限名称',
  `jn_url` VARCHAR(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '具体操作权限',
  `jn_pid` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '父级id',
  `jn_path` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所有父级id集合',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`jn_id`)
) ENGINE=INNODB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色权限表';

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `role_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色名称',
  `role_description` VARCHAR(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色描述',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`role_id`)
) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户角色表';

/*Table structure for table `role_jn` */

DROP TABLE IF EXISTS `role_jn`;

CREATE TABLE `role_jn` (
  `role_jn_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` BIGINT(20) NOT NULL COMMENT '角色id',
  `jn_id` BIGINT(20) NOT NULL COMMENT '权限id',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`role_jn_id`),
  KEY `FK_Reference_13` (`jn_id`),
  KEY `FK_Reference_14` (`role_id`),
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`jn_id`) REFERENCES `jurisdiction` (`jn_id`),
  CONSTRAINT `FK_Reference_14` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=INNODB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色权限关系';

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `user_role_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` BIGINT(20) NOT NULL COMMENT '角色id',
  `user_id` VARCHAR(50) NOT NULL COMMENT '用户id',
  `manager_id` VARCHAR(50) NOT NULL COMMENT '管理员id',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_role_id`),
  KEY `FK_Reference_11` (`role_id`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=INNODB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户角色表';