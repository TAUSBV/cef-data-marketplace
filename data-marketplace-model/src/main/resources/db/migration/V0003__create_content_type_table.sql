CREATE TABLE `content_type`
(
    `id`          int NOT NULL AUTO_INCREMENT primary key,
    `description` varchar(255) DEFAULT NULL,
    `origin`      varchar(20)  DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

