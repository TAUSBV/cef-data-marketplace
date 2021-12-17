create table translation_document_price_suggestion
(
    `id`                     int NOT NULL AUTO_INCREMENT primary key,
    uid                      binary(16) not null,
    price_category_id        int null,
    suggested_price_per_word decimal(6, 4) default 0.0000 null,
    constraint translation_document_price_suggestion_category_fk
        foreign key (price_category_id) references language_data_availability_category (id)
) engine = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
