create table notification
(
    id         int unsigned not null auto_increment primary key,
    recipient  varchar(255) not null,
    type       varchar(100) not null,
    status     varchar(20)  not null,
    created_at datetime(6)  not null,
    updated_at datetime(6)  not null
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;