create table notification_metadata
(
    id              int unsigned not null auto_increment primary key,
    notification_id int unsigned null,
    name            varchar(255) not null,
    value           varchar(255) not null,
    constraint notification_metadata_notification_fk
        foreign key (notification_id) references notification (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;