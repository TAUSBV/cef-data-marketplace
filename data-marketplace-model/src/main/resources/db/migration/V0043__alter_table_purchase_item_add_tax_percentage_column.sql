ALTER TABLE purchase_item
    ADD COLUMN tax_percentage DOUBLE NOT NULL after price;
