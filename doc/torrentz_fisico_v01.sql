--
-- bdtorrentz01_20-11-02
--
--

DROP TABLE IF EXISTS
	Usuarios,
	Planos,
	Categorias,
	Filmes,
	Visualizacoes,
	Contratos;

CREATE TABLE Usuarios
(
	usu_id SERIAL PRIMARY KEY,
	usu_nome VARCHAR(100) NOT NULL
		CONSTRAINT usu_nome_curto CHECK (length(usu_nome) >= 2)
		CONSTRAINT usu_nome_longo CHECK (length(usu_nome) <= 100),
	usu_cpf CHAR(11) UNIQUE NOT NULL
		CONSTRAINT usu_cpf_invalido CHECK (length(usu_cpf) = 11),
	usu_email VARCHAR(100) NOT NULL
		CONSTRAINT usu_email_curto CHECK (length(usu_email) >= 5) -- a@b.c
		CONSTRAINT usu_email_longo CHECK (length(usu_email) <= 100),
	usu_senha CHAR(64) NOT NULL
		CONSTRAINT usu_senha_sha256_invalida CHECK (length(usu_senha) = 64), -- SHA256
	usu_cup_porcentagem NUMERIC(4,2) NOT NULL
		CONSTRAINT usu_cup_porcentagem_invalida CHECK (usu_cup_porcentagem >= 1.0 AND usu_cup_porcentagem <= 60.0),
	usu_cup_data_geracao DATE NOT NULL
		CONSTRAINT usu_cup_data_geracao_invalida CHECK (usu_cup_data_geracao <= CURRENT_DATE)
);

CREATE TABLE Planos
(
	pla_id SERIAL PRIMARY KEY,
	pla_acesso_simultaneo INTEGER NOT NULL
		CONSTRAINT pla_acesso_simultaneo_invalido CHECK (pla_acesso_simultaneo >=1 AND pla_acesso_simultaneo <= 100),
	pla_nome VARCHAR(50) NOT NULL
		CONSTRAINT pla_nome_curto CHECK (length(pla_nome) >= 2)
		CONSTRAINT pla_nome_longo CHECK (length(pla_nome) <= 50),
	pla_preco NUMERIC(8,2) NOT NULL
		CONSTRAINT pla_preco_invalido CHECK (pla_preco >= 0.01 AND pla_preco <= 999999.99)
);

CREATE TABLE Categorias
(
	cat_id SERIAL PRIMARY KEY,
	cat_nome VARCHAR(50) UNIQUE NOT NULL
		CONSTRAINT cat_nome_curto CHECK (length(cat_nome) >= 2)
		CONSTRAINT cat_nome_longo CHECK (length(cat_nome) <= 50)
);

CREATE TABLE Filmes
(
	fil_id SERIAL PRIMARY KEY,
	fil_sinopse VARCHAR(500) UNIQUE NOT NULL
		CONSTRAINT fil_sinopse_curto CHECK (length(fil_sinopse) >= 10)
		CONSTRAINT fil_sinopse_longo CHECK (length(fil_sinopse) <= 500),
	fil_titulo VARCHAR(50) UNIQUE NOT NULL
		CONSTRAINT fil_titulo_curto CHECK (length(fil_sinopse) >= 2)
		CONSTRAINT fil_titulo_longo CHECK (length(fil_sinopse) <= 50),
	fil_ano INTEGER NOT NULL
		CONSTRAINT fil_ano_invalido CHECK (fil_ano >= 1890)
		CONSTRAINT fil_ano_futuro CHECK (fil_ano <= date_part('year', CURRENT_DATE)::int),
	-- Em 1890, Edison projeta diversos filmes de seu estúdio, aos quais encontra-se “Black Maria”,
	-- considerado o primeiro filme da história do cinema.
	-- FONTES: https://www.infoescola.com/cinema/historia-do-cinema/
	--         https://stackoverflow.com/questions/35704412/postgresql-query-current-year
	
	fil_cat_id INTEGER,
	CONSTRAINT FK_filme_categoria FOREIGN KEY (fil_cat_id) REFERENCES Categorias (cat_id)
);

CREATE TABLE Visualizacoes
(
	vis_data DATE NOT NULL
		CONSTRAINT fil_data_futura CHECK (vis_data <= CURRENT_DATE),
	vis_completo BOOLEAN NOT NULL,
	vis_usu_id INTEGER,
	vis_fil_id INTEGER,
	CONSTRAINT FK_usuario_visualizacao FOREIGN KEY (vis_usu_id) REFERENCES Usuarios (usu_id),
	CONSTRAINT FK_filme_visualizacao FOREIGN KEY (vis_fil_id) REFERENCES Filmes (fil_id)
);

CREATE TABLE Contratos
(
	con_status CHAR(1) NOT NULL
		CONSTRAINT con_status_invalido CHECK (con_status IN('A', 'I', 'S')),
	con_inicio DATE NOT NULL
		CONSTRAINT con_inicio_data_futura CHECK (con_inicio <= CURRENT_DATE),
	con_fim DATE NOT NULL
		CONSTRAINT con_fim_invalido CHECK (con_fim > con_inicio),
	con_usu_id INTEGER NOT NULL,
	con_pla_id INTEGER NOT NULL,
	CONSTRAINT FK_usuario_contrato FOREIGN KEY (con_usu_id) REFERENCES Usuarios (usu_id),
	CONSTRAINT FK_plano_contrato FOREIGN KEY (con_pla_id) REFERENCES Planos (pla_id)
);
