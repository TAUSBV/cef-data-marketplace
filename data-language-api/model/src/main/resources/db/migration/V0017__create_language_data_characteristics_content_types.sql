CREATE TABLE `language_data_characteristics_content_types`
(
    characteristic_id int UNSIGNED NOT NULL,
    content_type_id   int          NOT NULL,
    UNIQUE KEY `language_data_characteristics_content_types_unique` (characteristic_id, content_type_id),
    CONSTRAINT `characteristics_content_types_id_fk` FOREIGN KEY (content_type_id) REFERENCES `content_types` (`id`),
    CONSTRAINT `characteristics_content_types_characteristics_id_fk` FOREIGN KEY (characteristic_id) REFERENCES `language_data_characteristics` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
