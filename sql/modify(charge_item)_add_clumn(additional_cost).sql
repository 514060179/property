ALTER TABLE `bms`.`charge_item`
  ADD COLUMN `additional_cost` DECIMAL(12,2) NULL COMMENT '额外费用' AFTER `unit_price`;