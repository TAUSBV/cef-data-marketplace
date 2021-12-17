CREATE TABLE `product_seller_shares`
(
    `id`                 bigint unsigned auto_increment primary key,
    `seller_id`          bigint unsigned NULL,
    `product_id`         bigint unsigned NULL,
    `price`              double          not null,
    `total_units`        int             not null,
    `total_source_words` int             not null,
    `total_target_words` int             not null,
    `created_at`         datetime(6)     not null,
    UNIQUE KEY `product_seller_shares_seller_product_id_unique` (`product_id`, `seller_id`),
    CONSTRAINT `product_seller_shares_product_id_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `product_seller_shares_seller_id_fk` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
