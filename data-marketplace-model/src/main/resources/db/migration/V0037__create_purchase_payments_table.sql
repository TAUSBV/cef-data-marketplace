create table if not exists purchase_payments
(
    purchase_id bigint unsigned not null,
    payments_id  bigint unsigned not null,
    constraint payment_id
        unique (payments_id),
    constraint purchase_payment_purchase_fk
        foreign key (purchase_id) references purchase (id),
    constraint purchase_payment_payment_fk
        foreign key (payments_id) references payment (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
