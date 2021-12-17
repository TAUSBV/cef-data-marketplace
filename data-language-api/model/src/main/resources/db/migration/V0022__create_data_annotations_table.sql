CREATE TABLE `data_annotations`
(
    `id`        int unsigned NOT NULL AUTO_INCREMENT primary key,
    category_id int unsigned NOT NULL,
    offset_start int default 0,
    offset_end   int default 0,
    created_At  datetime(6)  not null,
    constraint data_annotations_category_fk
        foreign key (category_id) references data_annotations_categories (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

