CREATE TABLE `matching_data_request`
(
    `id`                 int unsigned                                         NOT NULL AUTO_INCREMENT,
    `uid`                binary(16)                                           NOT NULL,
    `status`             enum ('RECEIVED','ANALYZING','MATCHING','FAILED','COMPLETED') NOT NULL,
    `type`               enum ('MONOLINGUAL','BILINGUAL')                     NOT NULL,
    `source_language_id` int                                                  NOT NULL,
    `target_language_id` int                                                  NOT NULL,
    `email`              varchar(255)                                         NOT NULL,
    `file_name`          varchar(255)                                         NOT NULL,
    `file_size`          int unsigned                                         NOT NULL,
    `total_units`        int unsigned                                         NOT NULL,
    `sample_language`    enum ('SOURCE','TARGET') DEFAULT NULL,
    `domain_id`          int                      DEFAULT NULL,
    `analysis_result`    json                     DEFAULT NULL,
    `created_at`         datetime(6)                                          NOT NULL,
    `updated_at`         datetime(6)                                          NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uid_UNIQUE` (`uid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
