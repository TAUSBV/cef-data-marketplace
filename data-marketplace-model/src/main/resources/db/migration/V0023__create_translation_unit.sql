create table translation_unit
(
    id              SERIAL primary key,
    created_at      datetime(6)                  null,
    hash            bigint                       not null,
    price_per_word  decimal(6, 4) default 0.0000 null,
    quality         varchar(50)                  not null,
    status          varchar(50)   default 'NEW'  not null,
    updated_at      datetime(6)                  null,
    is_virtual      bit                          null,
    sequence        int unsigned  default 0      null,
    document_id     int unsigned                 not null,
    content_type_id int                          null,
    domain_id       int                          null,
    reference_id    bigint unsigned              null,
    source_id       bigint unsigned              not null,
    target_id       bigint unsigned              not null,
    owner_id        bigint unsigned              null,
    constraint translation_unit_domain_fk
        foreign key (domain_id) references domain (id),
    constraint translation_unit_content_type_fk
        foreign key (content_type_id) references content_type (id),
    constraint translation_unit_translation_document_fk
        foreign key (document_id) references translation_document (id),
    constraint translation_unit_self_reference_fk
        foreign key (reference_id) references translation_unit (id),
    constraint translation_unit_source_fk
        foreign key (source_id) references translation_variant (id),
    constraint translation_unit_target_fk
        foreign key (target_id) references translation_variant (id),
    constraint translation_unit_user_fk
        foreign key (owner_id) references user (id),
    index translation_unit_original_replica (hash, reference_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
