ALTER TABLE `translation_documents_statistics`
    ADD COLUMN `total_units_annotated_pii`             BIGINT DEFAULT 0 AFTER `total_units_low_quality`,
    ADD COLUMN `total_source_word_count_annotated_pii` BIGINT DEFAULT 0 AFTER `total_source_low_quality_word_count`,
    ADD COLUMN `total_target_word_count_annotated_pii` BIGINT DEFAULT 0 AFTER `total_target_low_quality_word_count`;
