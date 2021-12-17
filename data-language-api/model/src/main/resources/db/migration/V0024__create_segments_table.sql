create table segments
(
    id                 SERIAL primary key auto_increment,
    hash               bigint                     not null,
    quality            varchar(50)                not null,
    status             varchar(50)  default 'NEW' null,
    sequence           int unsigned default 0     null,
    document_id        int unsigned               not null,
    source_hash        bigint                     not null,
    source_value       text                       not null,
    source_word_count  bigint       default 0     null,
    source_language_id int                        not null,
    target_hash        bigint                     not null,
    target_value       text                       not null,
    target_word_count  bigint       default 0     null,
    target_language_id int                        not null,
    created_at         datetime(6)                null,
    updated_at         datetime(6)                null,
    constraint segments_document_fk
        foreign key (document_id) references translation_documents (id),
    INDEX segments_hashes_idx (document_id, hash, source_hash, target_hash)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
    COMMENT ='This table is used by the data language received workflow to temporary
stage the segments from incoming document. The data on this table is deleted after
the document is fully processed. This table should not be used by other purposes.';
