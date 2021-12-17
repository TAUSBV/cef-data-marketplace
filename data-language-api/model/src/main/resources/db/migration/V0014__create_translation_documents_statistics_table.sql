create table translation_documents_statistics
(
    `id`                                int unsigned            NOT NULL AUTO_INCREMENT primary key,
    created_at                          datetime(6)             not null,
    total_units                         bigint        default 0 null,
    total_units_original                bigint        default 0 null,
    total_units_replica                 bigint        default 0 null,
    total_units_low_quality             bigint        default 0 null,
    total_source_word_count             bigint        default 0 null,
    total_source_original_word_count    bigint        default 0 null,
    total_source_replica_word_count     bigint        default 0 null,
    total_source_low_quality_word_count bigint        default 0 null,
    total_target_original_word_count    bigint        default 0 null,
    total_target_replica_word_count     bigint        default 0 null,
    total_target_low_quality_word_count bigint        default 0 null,
    total_target_word_count             bigint        default 0 null,
    comment                             varchar(1000) default '',
    document_id                         int UNSIGNED            null,
    constraint translation_documents_statistics_document_fk
        foreign key (document_id) references translation_documents (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
