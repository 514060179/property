ALTER TABLE `bms`.`building_child`
ADD COLUMN `name` varchar(50) NOT NULL COMMENT '名称' AFTER `building_id`,
ADD COLUMN `amount` int(0) NOT NULL COMMENT '数量' AFTER `name`,
ADD COLUMN `area` decimal(12, 2) NOT NULL COMMENT '面积' AFTER `amount`,
ADD COLUMN `purpose` varchar(100) NOT NULL COMMENT '用途' AFTER `area`,
ADD COLUMN `perthousand` decimal(12, 2) NULL COMMENT '千分比之相对价值' AFTER `purpose`,
MODIFY COLUMN `tips` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '子部分之提示:数量' AFTER `perthousand`,
MODIFY COLUMN `value` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '子部分之值:50' AFTER `tips`;