create table translation_variants
(
    id          SERIAL primary key,
    uid         binary(16)       not null,
    hash        bigint           not null,
    created_at  datetime(6)      not null,
    updated_at  datetime(6)      not null,
    value       text             not null,
    word_count  bigint default 0 null,
    language_id int              not null,
    constraint translation_variants_language_hash_unique
        unique (language_id, hash),
    constraint translation_variants_languages_fk
        foreign key (language_id) references languages (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
