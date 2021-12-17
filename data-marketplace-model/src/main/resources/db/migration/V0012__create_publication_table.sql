create table publication
(
    `id`        int unsigned NOT NULL AUTO_INCREMENT primary key,
    uid         binary(16)   not null,
    created_at  datetime(6)  null,
    status      varchar(20)  null,
    updated_at  datetime(6)  null,
    document_id int unsigned null,
    constraint publication_translation_document_fk
        foreign key (document_id) references translation_document (id),
    index publication_uid (uid)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
