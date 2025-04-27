CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE academia(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(80) NOT NULL,
    endereco TEXT,
    telefone VARCHAR(30)
);

CREATE TABLE cliente(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(100),
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(80) NOT NULL,
    status VARCHAR(10) NOT NULL
        CHECK (status IN ('ATIVO','INATIVO'))
        DEFAULT 'ATIVO',
    academia_id UUID NOT NULL,
    FOREIGN KEY (academia_id) REFERENCES academia(id)
);

