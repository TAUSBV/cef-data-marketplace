create table product
(
    dtype              varchar(31)  not null,
    id                 bigint unsigned auto_increment
        primary key,
    created_at         datetime(6)  null,
    updated_at         datetime(6)  null,
    sku                binary(16)   null,
    description        varchar(400) null,
    price_max          double       null,
    price_min          double       null,
    document_id        bigint       null,
    source_language_id bigint       null,
    target_language_id bigint       null
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
