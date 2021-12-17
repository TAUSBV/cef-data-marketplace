ALTER TABLE translation_unit
    ADD INDEX translation_unit_document_self_reference_idx (document_id, reference_id);