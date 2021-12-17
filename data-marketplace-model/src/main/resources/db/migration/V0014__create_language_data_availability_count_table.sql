create table language_data_availability_count
(
    `id`               int    NOT NULL AUTO_INCREMENT primary key,
    source_language_id int    not null,
    target_language_id int    not null,
    content_type_id    int    not null,
    domain_id          int    not null,
    word_count         bigint not null,
    constraint language_data_availability_count_content_type_id_fk
        foreign key (content_type_id) references content_type (id),
    constraint language_data_availability_count_domain_id_fk
        foreign key (domain_id) references domain (id),
    constraint language_data_availability_count_language_id_fk
        foreign key (source_language_id) references language (id),
    constraint language_data_availability_count_language_id_fk_2
        foreign key (target_language_id) references language (id)
)
    engine = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci;

create unique index language_data_availability_count_u_index
    on language_data_availability_count (source_language_id, target_language_id, domain_id, content_type_id)