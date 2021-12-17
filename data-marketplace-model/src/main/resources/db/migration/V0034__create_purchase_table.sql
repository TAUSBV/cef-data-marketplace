create table if not exists purchase
(
    id          bigint unsigned auto_increment
        primary key,
    uid         binary(16)      null,
    status      varchar(20)     null,
    service_fee double          not null,
    buyer_id    bigint unsigned null,
    created_at  datetime(6)     null,
    updated_at  datetime(6)     null,
    constraint purchase_buyer_fk
        foreign key (buyer_id) references buyer (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
