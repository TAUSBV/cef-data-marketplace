create table if not exists translation_documents_characteristics
(
    characteristic_id int unsigned not null,
    document_id       int unsigned NOT NULL,
    UNIQUE KEY `translation_documents_characteristics_unique` (characteristic_id, document_id),
    CONSTRAINT `translation_documents_characteristics_documents_id_fk` FOREIGN KEY (document_id) REFERENCES `translation_documents` (`id`),
    CONSTRAINT `translation_documents_characteristics_characteristics_id_fk` FOREIGN KEY (characteristic_id) REFERENCES `language_data_characteristics` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
