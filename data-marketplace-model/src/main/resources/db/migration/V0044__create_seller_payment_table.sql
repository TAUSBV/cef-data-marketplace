
CREATE TABLE `seller_payment` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `uid` binary(16) DEFAULT NULL,
  `seller_id` bigint unsigned DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `created_at` timestamp(6) NULL DEFAULT NULL,
  `updated_at` timestamp(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `seller_id_fk` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

