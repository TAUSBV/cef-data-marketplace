create table translation_document_unit_samples
(
    id                            SERIAL primary key auto_increment,
    owner_id                      bigint unsigned                 null COMMENT 'TAUS Corporate ID',
    document_id                   int unsigned                    not null,
    document_uid                  binary(16)                      not null,
    unit_id                       bigint unsigned                 not null,
    unit_uid                      binary(16)                      not null,
    unit_hash                     bigint                          not null,
    unit_sequence                 int unsigned                    not null,
    unit_domain_id                int                             not null,
    unit_domain_description       varchar(255)                    not null,
    unit_content_type_id          int                             not null,
    unit_content_type_description varchar(255)                    not null,
    source_id                     bigint unsigned                 not null,
    source_hash                   bigint                          not null,
    source_value                  text                            not null,
    source_word_count             bigint      default 0           null,
    source_language_id            int                             not null,
    target_id                     bigint unsigned                 not null,
    target_hash                   bigint                          not null,
    target_value                  text                            not null,
    target_word_count             bigint      default 0           null,
    target_language_id            int                             not null,
    created_at                    datetime(6)                     null,
    constraint document_samples_document_id_fk
        foreign key (document_id) references translation_documents (id),
    constraint document_samples_unit_id_fk
        foreign key (unit_id) references translation_units (id),
    constraint document_samples_source_id_fk
        foreign key (source_id) references translation_variants (id),
    constraint document_samples_target_id_fk
        foreign key (target_id) references translation_variants (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
    COMMENT ='This table hold unit samples for published documents.';
