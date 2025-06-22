CREATE TABLE pacientes (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    logradouro VARCHAR(100) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    complemento VARCHAR(100),
    numero VARCHAR(20),
    uf CHAR(2) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    ativo BOOLEAN NOT NULL
);