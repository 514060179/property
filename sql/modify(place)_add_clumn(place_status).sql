ALTER TABLE `simon`.`place`   
  ADD COLUMN `place_status` CHAR(1) DEFAULT '0' NOT NULL COMMENT '场地开放状态' AFTER `place_english_introduction`;