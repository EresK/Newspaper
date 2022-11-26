CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    email TEXT NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    UNIQUE(email)
);

--CREATE TABLE IF NOT EXISTS descriptions (
--    id BIGSERIAL PRIMARY KEY,
--    publication_id BIGSERIAL FOREIGN KEY,
--    title TEXT NOT NULL,
--    issue_number BIGSERIAL NOT NULL,
--    issue_date TIMESTAMP NOT NULL,
--    image BYTEA,
--    UNIQUE(publication_id)
--);
--
--CREATE TABLE IF NOT EXISTS publications (
--    id BIGSERIAL PRIMARY KEY,
--    description_id FOREIGN KEY
--    UNIQUE(description_id)
--);
