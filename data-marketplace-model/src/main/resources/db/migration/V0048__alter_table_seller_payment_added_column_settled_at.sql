ALTER TABLE `seller_payment`
    ADD COLUMN `settled_at` DATETIME(6) NULL DEFAULT NULL AFTER `updated_at`;
