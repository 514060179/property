ALTER TABLE `bms`.`asset_storage_record`
  CHANGE `storage_type` `storage_type` INT(11) DEFAULT 0 NULL COMMENT '出入类型 0入库1出库';