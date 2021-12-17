create table if not exists buyer
(
    id                       bigint unsigned auto_increment
        primary key,
    uid                      binary(16)   null,
    email                    varchar(255) not null,
    first_name               varchar(255) not null,
    last_name                varchar(255) not null,
    country                  varchar(255) not null,
    address                  VARCHAR(255) not null,
    city                     VARCHAR(255) not null,
    postal_code              VARCHAR(50)  not null,
    state                    VARCHAR(100) null,
    entity_type              VARCHAR(20)  not null,
    company                  VARCHAR(255) null,
    vat                      VARCHAR(20)  null,
    privacy_policy_consent   bit          not null,
    terms_of_service_consent bit          not null,
    created_at               datetime(6)  null,
    updated_at               datetime(6)  null,
    constraint buyer_uid UNIQUE KEY (uid)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
