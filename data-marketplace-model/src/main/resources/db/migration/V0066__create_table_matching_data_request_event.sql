CREATE TABLE `matching_data_request_event`
(
    `id`                       int unsigned                                         NOT NULL AUTO_INCREMENT,
    `matching_data_request_id` int unsigned                                         NOT NULL,
    `status`                   enum ('RECEIVED','ANALYZING','MATCHING','FAILED','COMPLETED') NOT NULL,
    `type`                     enum ('INFO','WARNING','ERROR')                      NOT NULL,
    `description`              varchar(1000)                                        NOT NULL,
    `created_at`               datetime(6)                                          NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk-matching_data_request_event-parent_idx` (`matching_data_request_id`),
    CONSTRAINT `fk-matching_data_request_event-parent` FOREIGN KEY (`matching_data_request_id`) REFERENCES `matching_data_request` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
