CREATE TABLE IF NOT EXISTS users(
    id bigint DEFAULT nextval('my_sequence') NOT NULL PRIMARY KEY,
    "name" VARCHAR(30) NOT NULL,
    lastname VARCHAR(30),
    username VARCHAR(30) NOT NULL,
    role VARCHAR(30)
)