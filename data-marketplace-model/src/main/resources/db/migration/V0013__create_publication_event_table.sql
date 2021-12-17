create table publication_event
(
    `id`           int unsigned  NOT NULL AUTO_INCREMENT primary key,
    uid            binary(16)    not null,
    created_at     datetime(6)   null,
    description    varchar(1000) null,
    status         varchar(20)   null,
    type           varchar(20)   null,
    publication_id int unsigned  null,
    constraint publication_event_publication_fk
        foreign key (publication_id) references publication (id),
    index publication_event_uid (uid)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

