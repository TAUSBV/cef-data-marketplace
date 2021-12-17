CREATE TABLE `translation_variants_annotations`
(
    `translation_variant_id` bigint unsigned NOT NULL,
    `annotations_id` int UNSIGNED NOT NULL,
    CONSTRAINT `translation_variants_annotations_translation_variant_id_fk` FOREIGN KEY (`translation_variant_id`) REFERENCES `translation_variants` (`id`),
    CONSTRAINT `translation_variants_annotations_annotation_id_fk` FOREIGN KEY (`annotations_id`) REFERENCES `data_annotations` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

