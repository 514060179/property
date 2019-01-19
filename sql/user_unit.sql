DROP TABLE `user_unit`;
CREATE TABLE `user_unit` (
  `user_unit_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `user_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '住户id',
  `unit_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '单位id',
  `owner` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否为业主',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_unit_id`),
  KEY `UNIQUE_USER_UNIT` (`user_id`,`unit_id`),
  KEY `unit_id` (`unit_id`),
  CONSTRAINT `user_unit_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `user_unit_ibfk_2` FOREIGN KEY (`unit_id`) REFERENCES `unit` (`unit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
