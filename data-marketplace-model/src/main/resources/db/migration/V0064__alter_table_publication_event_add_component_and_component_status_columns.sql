ALTER TABLE publication_event
  ADD COLUMN component       VARCHAR(50) NOT NULL after created_at,
  ADD COLUMN component_status VARCHAR(50) NOT NULL after component;