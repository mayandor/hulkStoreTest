CREATE TABLE IF NOT EXISTS movement(
    id bigint DEFAULT nextval('my_sequence') NOT NULL PRIMARY KEY,
    movement_date DATE,
    type VARCHAR,
    user_id bigint,
    CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES users(id)
)