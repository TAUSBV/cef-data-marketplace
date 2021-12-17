create table price_index
(
    `id`                                   int                          NOT NULL AUTO_INCREMENT primary key,
    suggested_price_per_word               decimal(6, 4) default 0.0000 null,
    updated_at                             datetime(6)                  null,
    language_data_availability_category_id int                          null,
    language_data_availability_count_id    int                          null,
    constraint price_index_language_data_availability_category_id_fk
        foreign key (language_data_availability_category_id) references language_data_availability_category (id),
    constraint price_index_language_data_availability_count_id_fk
        foreign key (language_data_availability_count_id) references language_data_availability_count (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
