ALTER TABLE `bms`.`images`
  ADD COLUMN `image_thumbnail` VARCHAR(300) NULL COMMENT '缩略图' AFTER `image_url`;