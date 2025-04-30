CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE academia(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(80) NOT NULL,
    telefone VARCHAR(30),
    endereco_id UUID NOT NULL,
    FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);

CREATE TABLE endereco (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    cep VARCHAR(9) NOT NULL,
    logradouro VARCHAR(100),
    numero VARCHAR(10) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    estado VARCHAR(2) NOT NULL
);

CREATE TABLE cliente(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(100),
    email VARCHAR(100) UNIQUE NOT NULL,
    status VARCHAR(10) NOT NULL
        CHECK (status IN ('ATIVO','INATIVO'))
        DEFAULT 'ATIVO',
    academia_id UUID NOT NULL,
    FOREIGN KEY (academia_id) REFERENCES academia(id)
);

