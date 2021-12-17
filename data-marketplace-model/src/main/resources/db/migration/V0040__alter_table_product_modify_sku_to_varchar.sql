alter table `product` RENAME COLUMN `sku` to `sku_old`;
ALTER TABLE `product` ADD COLUMN `sku` VARCHAR(300);
update `product` set `sku` = BIN_TO_UUID(`sku_old`);
alter table `product` drop column `sku_old`;
ALTER TABLE `product` MODIFY COLUMN `sku` VARCHAR(300) not null;
ALTER TABLE `product` ADD INDEX `product_sku_index` (`sku`)