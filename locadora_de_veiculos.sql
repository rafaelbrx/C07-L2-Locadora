-- Banco de Dados: Locadora de Veículos

DROP DATABASE IF EXISTS LOCADORA;
CREATE DATABASE LOCADORA;
USE LOCADORA;

-- Criação das Tabelas

CREATE TABLE Cliente(
    id_cliente INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    telefone VARCHAR(15) NOT NULL,
    email VARCHAR(50) NOT NULL
);

CREATE TABLE Veiculo(
    id_veiculo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(30) NOT NULL,
    placa VARCHAR(20) NOT NULL UNIQUE,
    ano INT NOT NULL,
    disponibilidade BOOLEAN NOT NULL
);

CREATE TABLE Categoria(
    id_categoria INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(30) NOT NULL,
    descricao VARCHAR(100) NOT NULL
);

CREATE TABLE Reserva(
    id_reserva INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    data_inicio DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_fim DATETIME,
    duracao_reserva TINYINT NOT NULL CHECK(duracao_reserva IN (3,5,7)), 
    
    idR_cliente INT NOT NULL,
    idR_veiculo INT NOT NULL,
    FOREIGN KEY (idR_cliente) REFERENCES Cliente(id_cliente),
    FOREIGN KEY (idR_veiculo) REFERENCES Veiculo(id_veiculo)
);

CREATE TABLE Pagamento(
    id_pagamento INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    valor DOUBLE NOT NULL,
    forma_pagamento ENUM('Cartão de Crédito', 'Cartão de Débito', 'Depósito Bancário') NOT NULL,
    status BOOLEAN NOT NULL,
    
    idP_reserva INT NOT NULL UNIQUE,
    FOREIGN KEY (idP_reserva) REFERENCES Reserva(id_reserva)
);

CREATE TABLE Veiculo_Categoria(
    id_veiculo INT NOT NULL,
    id_categoria INT NOT NULL,
    
    PRIMARY KEY(id_veiculo, id_categoria),
    FOREIGN KEY(id_veiculo) REFERENCES Veiculo(id_veiculo),
    FOREIGN KEY(id_categoria) REFERENCES Categoria(id_categoria)
);

-- Inserção de instâncias

INSERT INTO Cliente(nome, cpf, telefone, email) VALUES
('Maria da Cunha', '123.456.789-00', '(35) 99999-8888', 'maria.cunha@email.com'),
('João Roberto', '123.895.779-21', '(31) 98755-8238', 'joaorr@email.com'),
('Marta Fagundes', '334.745.203-29', '(11) 99823-7451', 'marta.fag@email.com');

INSERT INTO Veiculo(modelo, placa, ano, disponibilidade) VALUES
('Fiat Argo 1.3', 'QTK5H32', 2023, TRUE),
('Chevrolet Onix LT', 'GBR1A57', 2023, TRUE),
('Toyota Corolla XEI', 'LZN8K04', 2021, FALSE);

INSERT INTO Categoria(nome, descricao) VALUES
('Econômico', 'Carros com baixo consumo de combustível'),
('SUV', 'Veículos utilitários esportivos'),
('Executivo', 'Carros confortáveis para clientes corporativos');

INSERT INTO Reserva(duracao_reserva, idR_cliente, idR_veiculo) VALUES
(3, 1, 1),
(5, 2, 2),
(7, 3, 3);

INSERT INTO Pagamento(valor, forma_pagamento, status, idP_reserva) VALUES
(550.00, 'Cartão de Crédito', TRUE, 1),
(700.00, 'Cartão de Débito', TRUE, 2),
(1800.00, 'Depósito Bancário', FALSE, 3);

INSERT INTO Veiculo_Categoria(id_veiculo, id_categoria) VALUES
(1, 1),
(2, 2),
(3, 3);


-- Update: alterar disponibilidade do veículo 3
UPDATE Veiculo SET disponibilidade = TRUE WHERE id_veiculo = 3;


-- Delete: remover novo cliente
INSERT INTO Cliente (nome, cpf, telefone, email) values ('Teste', '123.123.123-12', '(00) 00000-0000', 'email.com');
DELETE FROM Cliente WHERE id_cliente = 4;


-- Criação de usuários
CREATE USER IF NOT EXISTS 'locadora_func'@'localhost' IDENTIFIED BY 'funcionario123';
CREATE USER IF NOT EXISTS 'locadora_cliente'@'localhost' IDENTIFIED BY 'cliente123';

-- DROP: deleta usuário Cliente
DROP USER 'locadora_cliente'@'localhost';

-- ALTER: modifica nome da coluna ano para ano_fabricacao, da tabela veiculo
ALTER TABLE Veiculo RENAME COLUMN ano TO ano_fabricacao;

-- Conceder privilégios
GRANT SELECT, INSERT, UPDATE, DELETE ON LOCADORA.* TO 'locadora_func';
FLUSH PRIVILEGES; 

-- TRIGGER: calcular data_fim automaticamente na Reserva
CREATE TRIGGER set_data_fim
BEFORE INSERT ON Reserva
FOR EACH ROW
SET NEW.data_fim = DATE_ADD(NEW.data_inicio, INTERVAL NEW.duracao_reserva DAY);

-- FUNCTION: total de reservas de um cliente
DELIMITER $$
CREATE FUNCTION total_reservas_cliente(cliente_id INT) RETURNS INT
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total FROM Reserva WHERE idR_cliente = cliente_id;
    RETURN total;
END$$
DELIMITER ;

-- VIEW: reservas com dados de cliente e veículo
CREATE VIEW vw_reservas_completas AS
SELECT r.id_reserva, c.nome AS cliente, v.modelo AS veiculo, r.data_inicio, r.data_fim, r.duracao_reserva
FROM Reserva r
JOIN Cliente c ON r.idR_cliente = c.id_cliente
JOIN Veiculo v ON r.idR_veiculo = v.id_veiculo;
