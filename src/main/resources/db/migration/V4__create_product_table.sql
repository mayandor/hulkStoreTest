CREATE TABLE IF NOT EXISTS product(
    id bigint DEFAULT nextval('my_sequence') NOT NULL PRIMARY KEY,
    "name" VARCHAR(50),
    price real,
    image VARCHAR,
    stock integer,
    category_id bigint,
    CONSTRAINT cat_id_fk FOREIGN KEY (category_id) REFERENCES category(id)
)