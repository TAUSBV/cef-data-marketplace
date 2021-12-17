ALTER TABLE `translation_documents`
  ADD COLUMN `anonymize` TINYINT DEFAULT 0 AFTER `size`;
