ALTER TABLE `bms`.`asset_storage_record`
  ADD COLUMN `storage_unitprice` DECIMAL(12,2) NULL COMMENT '单价' AFTER `storage_amount`;