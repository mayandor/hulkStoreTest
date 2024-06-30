CREATE TABLE IF NOT EXISTS movement_detail(
    id bigint DEFAULT nextval('my_sequence') NOT NULL PRIMARY KEY,
    quantity INTEGER,
    product_id bigint,
    movement_id bigint,
    CONSTRAINT product_id_fk FOREIGN KEY (product_id) REFERENCES product(id),
    CONSTRAINT movement_id_fk FOREIGN KEY (movement_id) REFERENCES movement(id)
)