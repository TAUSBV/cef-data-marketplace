ALTER TABLE user
    ADD COLUMN address     VARCHAR(255) NULL after last_name,
    ADD COLUMN city        VARCHAR(255) NULL after address,
    ADD COLUMN postal_code VARCHAR(50)  NULL after city,
    ADD COLUMN state       VARCHAR(100) NULL after postal_code,
    ADD COLUMN entity_type VARCHAR(20)  NULL after country,
    ADD COLUMN company     VARCHAR(255) NULL after entity_type,
    ADD COLUMN vat         VARCHAR(20)  NULL after company;