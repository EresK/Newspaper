CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    email TEXT NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    UNIQUE(email)
);
--CREATE TABLE IF NOT EXISTS comments (
--    id BIGSERIAL PRIMARY KEY,
--    text TEXT NOT NULL,
--    publicationDate TIMESTAMP NOT NULL,
--    ownerId BIGSERIAL NOT NULL,
--    publicationId BIGSERIAL NOT NULL,
--);