ALTER TABLE `bms`.`asset`
MODIFY COLUMN `community_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '社区ID' AFTER `asset_id`,
ADD COLUMN `building_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '建筑ID' AFTER `community_id`;