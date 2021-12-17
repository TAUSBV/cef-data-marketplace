create table translation_metadata
(
    id    serial primary key,
    name  varchar(100) null,
    value text         null
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
