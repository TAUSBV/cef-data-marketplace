ALTER TABLE `seller_payment`
    DROP FOREIGN KEY `seller_id_fk`;

ALTER TABLE `seller_payment` CHANGE COLUMN `seller_id` `seller_id` BIGINT UNSIGNED NOT NULL;

ALTER TABLE `seller_payment`
    ADD CONSTRAINT `seller_payment_seller_id_fk`
        FOREIGN KEY (`seller_id`)
            REFERENCES `user` (`id`)
            ON DELETE RESTRICT
            ON UPDATE CASCADE;

ALTER TABLE `seller_payment`
    CHANGE COLUMN `created_at` `created_at` DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ;

ALTER TABLE `seller_payment`
    CHANGE COLUMN `updated_at` `updated_at` DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) on update CURRENT_TIMESTAMP(6);



