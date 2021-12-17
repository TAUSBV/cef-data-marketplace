create table translation_units
(
    id           SERIAL primary key,
    uid          binary(16)                 not null,
    hash         bigint                     not null,
    quality      varchar(50)                not null,
    status       varchar(50)  default 'NEW' null,
    updated_at   datetime(6)                null,
    is_virtual   bit          default 0     null,
    sequence     int unsigned default 0     null,
    created_at   datetime(6)                null,
    document_id  int unsigned               not null,
    reference_id bigint unsigned            null,
    source_id    bigint unsigned            not null,
    target_id    bigint unsigned            not null,
    owner_uid    binary(16)                 null COMMENT 'TAUS Corporate UID',
    owner_id     bigint unsigned            null COMMENT 'TAUS Corporate ID',
    constraint translation_unit_translation_document_fk
        foreign key (document_id) references translation_documents (id),
    constraint translation_unit_self_reference_fk
        foreign key (reference_id) references translation_units (id),
    constraint translation_unit_source_fk
        foreign key (source_id) references translation_variants (id),
    constraint translation_unit_target_fk
        foreign key (target_id) references translation_variants (id),
    INDEX translation_unit_document_self_reference_idx (document_id, reference_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
