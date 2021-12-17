create table translation_documents
(
    `id`                        int unsigned                      NOT NULL AUTO_INCREMENT primary key,
    uid                         binary(16)                        not null,
    created_at                  datetime(6)                       not null,
    updated_at                  datetime(6)                       not null,
    format                      varchar(50)   default 'TMX'       null,
    name                        varchar(255)  default 'undefined' null,
    description                 varchar(600)  default 'undefined' null,
    status                      varchar(50)   default 'NEW'       not null,
    size                        int           default 0           null,
    suggested_price_per_word    decimal(6, 4) default 0.0000      null,
    availability_category_label varchar(20)   default ''          null,
    source_language_id          int                               null,
    target_language_id          int                               null,
    owner_uid                   binary(16)                        null COMMENT 'TAUS Corporate UID',
    owner_id                    bigint unsigned                   null COMMENT 'TAUS Corporate ID',
    constraint translation_documents_source_language_id_fk
        foreign key (source_language_id) references languages (id),
    constraint translation_documents_target_language_id_fk
        foreign key (target_language_id) references languages (id),
    constraint translation_document_uid UNIQUE KEY (uid)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
