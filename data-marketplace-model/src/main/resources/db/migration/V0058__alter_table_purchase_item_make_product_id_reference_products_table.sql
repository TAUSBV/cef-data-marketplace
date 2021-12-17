ALTER TABLE purchase_item
    DROP CONSTRAINT purchase_item_product_fk,
    ADD CONSTRAINT purchase_item_products_fk
        foreign key (product_id) references products (id);
