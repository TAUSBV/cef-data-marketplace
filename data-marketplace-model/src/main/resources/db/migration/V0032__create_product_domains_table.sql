create table product_domains
(
    virtual_document_product_id bigint unsigned not null,
    domains_id                  bigint          not null,
    constraint unique_domains_id
        unique (domains_id),
    constraint product_domains_virtual_document_fk
        foreign key (virtual_document_product_id) references product (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
