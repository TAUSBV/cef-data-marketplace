create table product_sellers
(
    virtual_document_product_id bigint unsigned not null,
    sellers_id                  bigint unsigned not null,
    constraint unique_sellers_id
        unique (sellers_id),
    constraint product_sellers_virtual_document_fk
        foreign key (virtual_document_product_id) references product (id),
    constraint product_sellers_seller_fk
        foreign key (sellers_id) references user (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
