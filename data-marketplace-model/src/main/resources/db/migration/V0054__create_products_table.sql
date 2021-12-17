create table products
(
    id                 bigint unsigned auto_increment primary key,
    sku                varchar(1000) null,
    name               varchar(500)  null,
    description        varchar(5000) null,
    amount_variable    bit           not null,
    created_at         datetime(6)   not null,
    price_max          double        null,
    price_min          double        null,
    price              double        not null,
    total_units        int           not null,
    total_source_words int           not null,
    total_target_words int           not null
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
