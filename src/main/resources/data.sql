INSERT INTO endereco (cep, logradouro, complemento, bairro, localidade, uf, ibge, gia, ddd, siafi) VALUES
('01001000', 'Praça da Sé', '', 'Sé', 'São Paulo', 'SP', '3550308', '1004', '11', '7107'),
('30140071', 'Avenida Afonso Pena', '', 'Centro', 'Belo Horizonte', 'MG', '3106200', '', '31', '4123'),
('70040900', 'Esplanada dos Ministérios', '', 'Asa Sul', 'Brasília', 'DF', '5300108', '', '61', '9701');

INSERT INTO cliente (id, nome, endereco_cep) VALUES
(1, 'João Silva', '01001000'),
(2, 'Maria Souza', '30140071'),
(3, 'Carlos Pereira', '70040900');
