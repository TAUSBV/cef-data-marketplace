create table product_content_types
(
    virtual_document_product_id bigint unsigned not null,
    content_types_id            bigint          not null,
    constraint unique_content_types_id
        unique (content_types_id),
    constraint product_content_types_virtual_document_fk
        foreign key (virtual_document_product_id) references product (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
