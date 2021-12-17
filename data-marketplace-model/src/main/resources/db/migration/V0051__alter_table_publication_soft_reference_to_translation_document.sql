ALTER TABLE publication
    DROP CONSTRAINT publication_translation_document_fk,
    ADD COLUMN document_uid binary(16) null,
    MODIFY document_id int unsigned null;
