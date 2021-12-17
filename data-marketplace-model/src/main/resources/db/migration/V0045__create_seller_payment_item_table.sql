
CREATE TABLE `seller_payment_item` (
   `payment_id` bigint unsigned NOT NULL,
   `item_id` bigint unsigned NOT NULL,
   UNIQUE KEY `item_id_UNIQUE` (`item_id`),
   KEY `payment_id_fk_idx` (`payment_id`),
   CONSTRAINT `item_id_fk` FOREIGN KEY (`item_id`) REFERENCES `purchase_item` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
   CONSTRAINT `payment_id_fk` FOREIGN KEY (`payment_id`) REFERENCES `seller_payment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
