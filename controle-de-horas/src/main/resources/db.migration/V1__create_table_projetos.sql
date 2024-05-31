CREATE TABLE projetos(
    id SERIAL PRIMARY KEY,
    nome_projeto VARCHAR(100) NOT NULL,
    data_de_inicio TIMESTAMP,
    data_de_fim TIMESTAMP,
    horas_trabalhadas VARCHAR(50)
);
