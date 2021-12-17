create table translation_units_metadata
(
    translation_unit_id bigint unsigned not null,
    metadata_id         bigint unsigned not null,
    constraint unique_metadata_id
        unique (metadata_id),
    constraint translation_units_metadata_translation_unit_fk
        foreign key (translation_unit_id) references translation_units (id),
    constraint translation_units_metadata_metadata_fk
        foreign key (metadata_id) references translation_metadata (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
