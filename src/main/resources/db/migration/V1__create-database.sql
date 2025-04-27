CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE academia(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    endereco TEXT,
    telefone VARCHAR(30),
);

CREATE TABLE aluno(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    senha VARCHAR(30) NOT NULL,
    atividade INT NOT NULL
            CHECK (status IN (0,1))
            DEFAULT 1;
    academia_id UUID NOT NULL
    FOREIGN KEY (academia_id) REFERENCES academia(id)
);

