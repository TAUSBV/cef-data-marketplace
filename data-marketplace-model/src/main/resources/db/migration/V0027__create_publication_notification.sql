create table publication_notification
(
    notification_id int unsigned not null,
    publication_id int unsigned not null,
    primary key (notification_id, publication_id),
    constraint publication_notification_notification
        foreign key (notification_id) references notification (id),
    constraint publication_notification_publication
        foreign key (publication_id) references publication (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;