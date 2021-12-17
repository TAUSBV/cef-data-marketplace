create table product_parts
(
    id          bigint unsigned auto_increment primary key,
    description varchar(5000)   null,
    type_id     varchar(100)    not null,
    type        varchar(50)     not null,
    created_at  datetime(6)     not null,
    product_id  bigint unsigned null,
    CONSTRAINT `product_parts_product_id_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
