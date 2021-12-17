ALTER TABLE `segments`
  ADD COLUMN `quality_flags` VARCHAR(1000) DEFAULT 'UNDEFINED' AFTER `quality`;
ALTER TABLE `translation_units`
  ADD COLUMN `quality_flags` VARCHAR(1000) DEFAULT 'UNDEFINED' AFTER `quality`;
