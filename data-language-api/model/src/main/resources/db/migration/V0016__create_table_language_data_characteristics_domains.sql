CREATE TABLE `language_data_characteristics_domains`
(
    characteristic_id int UNSIGNED NOT NULL,
    domain_id         int          NOT NULL,
    KEY `language_data_characteristics_domains_id_fk` (characteristic_id),
    CONSTRAINT `characteristics_domains_id_fk` FOREIGN KEY (domain_id) REFERENCES `domains` (`id`),
    CONSTRAINT `characteristics_characteristics_id_fk` FOREIGN KEY (characteristic_id) REFERENCES `language_data_characteristics` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
