create table purchase_item
(
    id                               bigint unsigned auto_increment
        primary key,
    uid                              binary(16)       null,
    total_units             bigint default 0 null,
    total_source_word_count bigint default 0 null,
    total_target_word_count bigint default 0 null,
    amount                           double           not null,
    created_at                       datetime(6)      null,
    updated_at                       datetime(6)      null,
    price                            double           not null,
    product_id                       bigint unsigned  null,
    purchase_id                      bigint unsigned  null,
    constraint purchase_item_purchase_fk
        foreign key (purchase_id) references purchase (id),
    constraint purchase_item_product_fk
        foreign key (product_id) references product (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

