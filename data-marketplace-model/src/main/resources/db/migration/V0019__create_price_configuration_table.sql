create table price_configuration
(
    id                                                        int             not null AUTO_INCREMENT primary key,
    availability_modifier_minimum_language_data_count_entries int unsigned    not null,
    availability_modifier_minimum_word_count                  bigint unsigned not null,
    base_price                                                double          not null,
    commission_fee                                            double          not null,
    transaction_fee                                           double          not null
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
