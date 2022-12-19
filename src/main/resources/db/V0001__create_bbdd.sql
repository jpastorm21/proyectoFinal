DROP TABLE IF EXISTS "proyecto_final";

CREATE TABLE "data_bolsa" (
    id BIGSERIAL NOT NULL,
    name VARCHAR(255) NOT NULL,
    price FLOAT8 NOT NULL,
    email VARCHAR(255) NOT NULL,
    notify boolean NOT NULL DEFAULT false,
    min FLOAT8 NOT NULL,
    max FLOAT8 NOT NULL,
    create_date TIMESTAMP NOT NULL,
    update_date TIMESTAMP NULL,
    CONSTRAINT data_bolsa_pkey PRIMARY KEY (id)
);
