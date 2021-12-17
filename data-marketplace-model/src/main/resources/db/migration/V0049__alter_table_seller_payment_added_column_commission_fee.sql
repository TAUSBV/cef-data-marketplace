ALTER TABLE `seller_payment`
    ADD COLUMN `commission_fee` DOUBLE NOT NULL DEFAULT 0 AFTER `seller_id`;
