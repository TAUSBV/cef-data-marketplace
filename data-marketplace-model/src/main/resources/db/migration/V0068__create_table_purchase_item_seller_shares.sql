CREATE TABLE `purchase_item_seller_shares`
(
    `id`                 bigint unsigned NOT NULL AUTO_INCREMENT,
    `purchase_item_id`   bigint unsigned NOT NULL,
    `seller_id`          bigint unsigned NOT NULL,
    `document_uid`       binary(16)      NOT NULL,
    `document_name`      varchar(500)    NOT NULL,
    `unit_price`         double unsigned NOT NULL,
    `total_units`        int unsigned    NOT NULL,
    `total_source_words` int unsigned    NOT NULL,
    `total_target_words` int unsigned    NOT NULL,
    `created_at`         datetime(6)     NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uq-purchase_item_seller_share` (`purchase_item_id`, `document_uid`),
    KEY `fk-purchase_item_seller_share-user_idx` (`seller_id`),
    KEY `fk-purchase_item_seller_share-purchase_item_idx` (`purchase_item_id`),
    CONSTRAINT `fk-purchase_item_seller_share-purchase_item` FOREIGN KEY (`purchase_item_id`) REFERENCES `purchase_item` (`id`),
    CONSTRAINT `fk-purchase_item_seller_share-user` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`),
    CONSTRAINT `product_seller_shares-user-fk` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
