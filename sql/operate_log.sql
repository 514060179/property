CREATE TABLE `bms`.`operate_log` (
  `log_id` BIGINT NOT NULL AUTO_INCREMENT,
  `manager_id` VARCHAR (50) NOT NULL COMMENT '操作人id',
  `operate_type` ENUM ('query', 'modify', 'add', 'del') DEFAULT 'add' COMMENT '操作类型',
  `content` VARCHAR (150) COMMENT '操作内容',
  `param` VARCHAR (500) COMMENT '参数内容',
  `request_url` VARCHAR (150) COMMENT '请求路径',
  `package` VARCHAR (150) COMMENT '请求包名',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`log_id`)
) ENGINE = INNODB COMMENT '操作日志' CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;