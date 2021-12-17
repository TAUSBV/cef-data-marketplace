insert into products (id, sku, name, description, amount_variable, created_at, price_max, price_min, price, total_units,
                      total_source_words, total_target_words)
select p.id,
       p.sku,
       p.description,
       p.description,
       1,
       p.created_at,
       p.price_max,
       p.price_min,
       (select td.price_per_word * (tds.total_source_original_word_count + tds.total_source_replica_word_count)
        from translation_document td
                 inner join translation_document_statistics tds on td.id = tds.document_id
        where td.id = p.document_id and tds.id = (select max(tds2.id) from translation_document_statistics tds2 where tds2.document_id = p.document_id)),
       (select tds.total_units_original + tds.total_units_replica
        from translation_document_statistics tds
        where tds.document_id = p.document_id and tds.id = (select max(tds2.id) from translation_document_statistics tds2 where tds2.document_id = p.document_id)),
       (select tds.total_source_original_word_count + tds.total_source_replica_word_count
        from translation_document_statistics tds
        where tds.document_id = p.document_id and tds.id = (select max(tds2.id) from translation_document_statistics tds2 where tds2.document_id = p.document_id)),
       (select tds.total_target_original_word_count + tds.total_target_replica_word_count
        from translation_document_statistics tds
        where tds.document_id = p.document_id and tds.id = (select max(tds2.id) from translation_document_statistics tds2 where tds2.document_id = p.document_id))
from product p;


insert into product_seller_shares (seller_id, product_id, price, total_units, total_source_words, total_target_words,
                                   created_at)
SELECT td.owner_id,
       p.id,
       td.price_per_word * (tds.total_source_original_word_count + tds.total_source_replica_word_count),
       tds.total_units_original + tds.total_units_replica,
       tds.total_source_original_word_count + tds.total_source_replica_word_count,
       tds.total_target_original_word_count + tds.total_target_replica_word_count,
       p.created_at
from translation_document td
         inner join translation_document_statistics tds on td.id = tds.document_id
         inner join product p on td.id = p.document_id and tds.id = (select max(tds2.id) from translation_document_statistics tds2 where tds2.document_id = p.document_id);

insert into product_parts (description, type_id, type, created_at, product_id)
SELECT l.tag, l.id, 'SOURCE_LANGUAGE', p.created_at, p.id  from translation_document td
                                                                    inner join product p on td.id = p.document_id
                                                                    inner join language l on td.source_language_id = l.id;

insert into product_parts (description, type_id, type, created_at, product_id)
SELECT l.tag, l.id, 'TARGET_LANGUAGE', p.created_at, p.id  from translation_document td
                                                                    inner join product p on td.id = p.document_id
                                                                    inner join language l on td.target_language_id = l.id;

insert into product_parts (description, type_id, type, created_at, product_id)
SELECT d.description, d.id, 'DOMAIN', p.created_at, p.id  from translation_document td
                                                                   inner join product p on td.id = p.document_id
                                                                   inner join domain d on td.domain_id = d.id;

insert into product_parts (description, type_id, type, created_at, product_id)
SELECT c.description, c.id, 'CONTENT_TYPE', p.created_at, p.id  from translation_document td
                                                                         inner join product p on td.id = p.document_id
                                                                         inner join content_type c on td.content_type_id = c.id;

insert into product_parts (description, type_id, type, created_at, product_id)
SELECT td.name, td.id, 'DOCUMENT', p.created_at, p.id  from translation_document td
                                                                inner join product p on td.id = p.document_id;
