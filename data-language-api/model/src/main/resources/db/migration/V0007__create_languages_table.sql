create table languages
(
    `id`            int          NOT NULL AUTO_INCREMENT primary key,
    name            varchar(100) not null,
    display_name    varchar(255) null,
    country         varchar(100) null,
    display_country varchar(255) null,
    script          varchar(100) null,
    variant         varchar(100) null,
    tag             varchar(255) not null,
    enabled_pii     tinyint(1) default 0
) engine = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
