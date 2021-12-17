create table if not exists user
(
    id                       bigint unsigned auto_increment
        primary key,
    uid                      binary(16)   null,
    corporate_id             bigint       null,
    email                    varchar(255) null,
    prefix                   varchar(10) null,
    first_name                varchar(255) null,
    last_name                 varchar(255) null,
    country                  varchar(255) null,
    payment_term_consent     bit          not null,
    privacy_policy_consent   bit          not null,
    request_terms_agreement  bit          not null,
    terms_of_service_consent bit          not null,
    verified                 bit          not null,
    created_at               datetime(6)  null,
    updated_at               datetime(6)  null,
    constraint user_uid UNIQUE KEY (uid)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
