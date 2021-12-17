create table if not exists translation_document_price_category
(
    price_category_id int          null,
    id                int unsigned not null
        primary key,
    constraint FK3sy13b2wcuqd1cueetbuf27n3
        foreign key (price_category_id) references language_data_availability_category (id),
    constraint FKn274hln198fyngowkybbchj6m
        foreign key (id) references translation_document (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

