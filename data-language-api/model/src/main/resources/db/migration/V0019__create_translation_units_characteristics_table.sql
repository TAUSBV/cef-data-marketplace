create table if not exists translation_units_characteristics
(
    unit_id SERIAL       NOT NULL primary key,
    characteristic_id  int unsigned not null,
    UNIQUE KEY `translation_units_characteristics_unique` (`characteristic_id`, `unit_id`),
    CONSTRAINT `translation_units_characteristics_documents_id_fk` FOREIGN KEY (`unit_id`) REFERENCES `translation_units` (`id`),
    CONSTRAINT `translation_units_characteristics_characteristics_id_fk` FOREIGN KEY (`characteristic_id`) REFERENCES `language_data_characteristics` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

