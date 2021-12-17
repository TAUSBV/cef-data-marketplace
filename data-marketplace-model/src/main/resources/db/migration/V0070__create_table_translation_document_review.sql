create table if not exists translation_document_review
(
  id               bigint unsigned auto_increment primary key,
  anonymous        bit             not null,
  score            double          not null,
  comment          varchar(5000)   null,
  status           varchar(20) default 'PENDING',
  moderation_code  binary(16)      not null,
  document_uid     binary(16)      not null,
  created_at       datetime(6)     null,
  purchase_item_id bigint unsigned not null,
  reviewer_id      bigint unsigned not null,
  constraint translation_document_review_purchase_item_fk
    foreign key (purchase_item_id) references purchase_item (id),
  constraint translation_document_review_reviewer_fk
    foreign key (reviewer_id) references buyer (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
