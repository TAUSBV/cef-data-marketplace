ALTER TABLE `products`
    ADD COLUMN `matching_data_request_id` INT UNSIGNED AFTER `total_target_words`;

ALTER TABLE `products`
    ADD CONSTRAINT `fk-product-matching_data_request`
        FOREIGN KEY (`matching_data_request_id`)
            REFERENCES `matching_data_request` (`id`)
            ON DELETE SET NULL
            ON UPDATE NO ACTION;