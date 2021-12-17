CREATE TABLE `language_data_characteristics`
(
    `id`             int UNSIGNED NOT NULL AUTO_INCREMENT primary key,
    `created_at`     datetime(6)   DEFAULT NULL,
    `price_per_word` decimal(6, 4) DEFAULT '0.0000'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
