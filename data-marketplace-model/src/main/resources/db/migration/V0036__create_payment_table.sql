create table if not exists payment
(
    id                    bigint unsigned auto_increment
        primary key,
    method                varchar(20)     null,
    status                varchar(20)     null,
    broker_transaction_id varchar(200)    null,
    created_at            datetime(6)     null,
    updated_at            datetime(6)     null
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
