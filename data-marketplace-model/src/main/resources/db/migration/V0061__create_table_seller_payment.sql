CREATE TABLE `seller_payment`
(
    `id`             BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `uid`            BINARY(16)      NOT NULL,
    `purchase_id`    BIGINT UNSIGNED NOT NULL,
    `user_id`        BIGINT UNSIGNED NOT NULL,
    `commission_fee` DOUBLE UNSIGNED NOT NULL DEFAULT 0,
    `sent_at`        DATETIME(6)     NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `uid_UNIQUE` (`uid` ASC) VISIBLE,
    INDEX `seller_payment-purchase-fk_idx` (`purchase_id` ASC) VISIBLE,
    INDEX `seller_payment-user-fk_idx` (`user_id` ASC) VISIBLE,
    UNIQUE INDEX `purchase-user-UNIQUE` (`purchase_id` ASC, `user_id` ASC) VISIBLE,
    CONSTRAINT `seller_payment-purchase-fk`
        FOREIGN KEY (`purchase_id`)
            REFERENCES `purchase` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `seller_payment-user-fk`
        FOREIGN KEY (`user_id`)
            REFERENCES `user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
