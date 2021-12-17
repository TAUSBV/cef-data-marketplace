create table language_data_availability_category
(
    `id`                    int                    NOT NULL AUTO_INCREMENT primary key,
    label                   varchar(50)            not null,
    higher_bound_percentile int unsigned default 0 not null,
    higher_bound            double       default 0 not null,
    availability_modifier   double       default 0 not null,
    sort                    tinyint unsigned       not null
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
