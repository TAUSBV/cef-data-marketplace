create table if not exists download
(
    id          bigint unsigned auto_increment
        primary key,
    uid         binary(16)      null,
    status      varchar(20)     null,
    item_id    bigint unsigned null,
    created_at  datetime(6)     null,
    updated_at  datetime(6)     null,
    constraint download_purchase_item_fk
        foreign key (item_id) references purchase_item (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
