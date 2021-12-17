create table translation_variant
(
    id          SERIAL primary key,
    hash        bigint           not null,
    created_at  datetime(6)      not null,
    updated_at  datetime(6)      not null,
    value       text             not null,
    word_count  bigint default 0 null,
    language_id int              not null,
    constraint translation_variant_language_hash_unique
        unique (language_id, hash),
    constraint translation_variant_language_fk
        foreign key (language_id) references language (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
