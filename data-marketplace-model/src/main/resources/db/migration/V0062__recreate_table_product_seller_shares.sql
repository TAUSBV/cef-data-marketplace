DROP TABLE `product_seller_shares`;

CREATE TABLE `product_seller_shares`
(
    `id`                 bigint unsigned NOT NULL AUTO_INCREMENT,
    `seller_id`          bigint unsigned NOT NULL,
    `product_id`         bigint unsigned NOT NULL,
    `document_uid`       binary(16)      NOT NULL,
    `document_name`      varchar(500)    NOT NULL,
    `unit_price`         double unsigned NOT NULL,
    `total_units`        int unsigned    NOT NULL,
    `total_source_words` int unsigned    NOT NULL,
    `total_target_words` int unsigned    NOT NULL,
    `created_at`         datetime(6)     NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `product_seller_shares-uq` (`seller_id`, `product_id`, `document_uid`),
    KEY `product_seller_shares-user-fk_idx` (`seller_id`),
    KEY `product_seller_shares-product-fk_idx` (`product_id`),
    CONSTRAINT `product_seller_shares-product-fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
    CONSTRAINT `product_seller_shares-user-fk` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 40
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
