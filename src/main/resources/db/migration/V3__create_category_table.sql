CREATE TABLE IF NOT EXISTS category(
    id bigint DEFAULT nextval('my_sequence') NOT NULL PRIMARY KEY,
    "name" VARCHAR(30)
)