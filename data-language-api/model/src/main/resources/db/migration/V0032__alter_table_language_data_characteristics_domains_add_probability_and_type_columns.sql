ALTER TABLE `language_data_characteristics_domains`
  RENAME COLUMN `characteristic_id` TO `characteristics_id`,
  ADD COLUMN `probability` DECIMAL(5, 4) DEFAULT 1.0 AFTER `domain_id`,
  ADD COLUMN `type`        VARCHAR(100)  DEFAULT 'DEFINED' AFTER `probability`,
  ADD COLUMN `created_at`  DATETIME(6) AFTER `type`;
