CREATE TABLE companies
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255)   NOT NULL,
    website     VARCHAR(255)   NOT NULL,
    email       VARCHAR(255)   NOT NULL,
    created_at  TIMESTAMP      NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP      NOT NULL DEFAULT NOW(),
    UNIQUE (name)
);