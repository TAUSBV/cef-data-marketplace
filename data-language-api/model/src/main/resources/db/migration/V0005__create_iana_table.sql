CREATE TABLE IF NOT EXISTS `iana`
(
    `id`              int unsigned NOT NULL AUTO_INCREMENT primary key,
    `type`            varchar(45)  NOT NULL,
    `subtag`          varchar(45)  DEFAULT NULL,
    `description`     varchar(150) NOT NULL,
    `script`          varchar(45)  DEFAULT NULL,
    `scope`           varchar(45)  DEFAULT NULL,
    `added`           datetime(6)  not null,
    `comments`        varchar(150) DEFAULT NULL,
    `deprecated`      datetime(6)  DEFAULT NULL,
    `macrolanguage`   varchar(45)  DEFAULT NULL,
    `preferred_value` varchar(45)  DEFAULT NULL,
    `prefix`          varchar(45)  DEFAULT NULL,
    `tag`             varchar(45)  DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
