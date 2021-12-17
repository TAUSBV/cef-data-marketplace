CREATE TABLE `data_annotations_categories`
(
    `id`        int unsigned              NOT NULL AUTO_INCREMENT primary key,
    code        varchar(50)               not null,
    description varchar(100)              not null,
    kind        varchar(50) default 'PII' not null,
    updated_at  datetime(6)               not null
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

