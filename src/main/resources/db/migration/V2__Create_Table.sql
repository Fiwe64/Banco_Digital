CREATE TABLE clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(255) NOT NULL
);
CREATE TABLE contas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT,
    saldo DOUBLE DEFAULT 0,
    tipo_conta VARCHAR(255) NOT NULL,  -- Define o tipo de conta (CORRENTE, POUPANCA, SALARIO)
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);
CREATE TABLE pagamentos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    conta_id BIGINT,
    valor DOUBLE NOT NULL,
    FOREIGN KEY (conta_id) REFERENCES contas(id)
);
