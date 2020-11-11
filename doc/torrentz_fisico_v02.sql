--
-- torrentz_fisico_v02.sql
-- 
--

DROP TABLE IF EXISTS
	Visualizacoes,
	Contratos,
	Usuarios,
	Planos,
	Categorias,
	Filmes
;
	
CREATE TABLE Usuarios
(
	usu_id SERIAL PRIMARY KEY,
	usu_nome VARCHAR(100) NOT NULL
		CONSTRAINT usu_nome_curto CHECK (length(usu_nome) >= 2)
		CONSTRAINT usu_nome_longo CHECK (length(usu_nome) <= 100),
        usu_perfil CHAR(1) NOT NULL
                CONSTRAINT usu_perfil_invalido CHECK(usu_perfil IN('A', 'U')),
	usu_cpf CHAR(11) UNIQUE NOT NULL
		CONSTRAINT usu_cpf_invalido CHECK (length(usu_cpf) = 11),
	usu_email VARCHAR(100) UNIQUE NOT NULL
		CONSTRAINT usu_email_curto CHECK (length(usu_email) >= 5) -- a@b.c
		CONSTRAINT usu_email_longo CHECK (length(usu_email) <= 100),
	usu_senha CHAR(64) NOT NULL
		CONSTRAINT usu_senha_sha256_invalida CHECK (length(usu_senha) = 64), -- SHA256
	usu_cup_porcentagem NUMERIC(4,2) NOT NULL
		CONSTRAINT usu_cup_porcentagem_invalida CHECK (usu_cup_porcentagem >= 1.0 AND usu_cup_porcentagem <= 60.0),
	usu_cup_data_geracao DATE NOT NULL
		CONSTRAINT usu_cup_data_geracao_invalida CHECK (usu_cup_data_geracao <= CURRENT_DATE)
);

INSERT INTO Usuarios
(
	usu_nome,
	usu_perfil,
	usu_cpf,
	usu_email,
	usu_senha,
	usu_cup_porcentagem, 
	usu_cup_data_geracao
) VALUES
	(
		'Marcos Paulo',
		'A',
		'27981108071',
		'contato.marcospaulo1@gmail.com',
		'8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92',
		10.00,
		TO_DATE('DD/MM/YYYY','07/11/2020')
	),

	(
		'Vinicius Araujo Lopes',
		'A',
		'93830651090',
		'suporte@viniciusalopes.com.br',
		'8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92',
		12.00,
		TO_DATE('07/11/2020', 'DD/MM/YYYY')
	),
	(
		'Lucas Araujo',
		'A',
		'53869888083',
		'lucasgoias1@gmail.com',
		'8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92',
		04.00,
		TO_DATE('07/11/2020', 'DD/MM/YYYY')
	),
	(
		'Calebe de Costa',
		'A',
		'41164832069',
		'calebaum@gmail.com',
		'8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92',
		20.00,
		TO_DATE('07/11/2020', 'DD/MM/YYYY')
	)
;

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

INSERT INTO Planos (pla_acesso_simultaneo, pla_nome, pla_preco) VALUES
	(2, 'Básico', 9.99)
;

CREATE TABLE Categorias
(
	cat_id SERIAL PRIMARY KEY,
	cat_nome VARCHAR(50) UNIQUE NOT NULL
		CONSTRAINT cat_nome_curto CHECK (length(cat_nome) >= 2)
		CONSTRAINT cat_nome_longo CHECK (length(cat_nome) <= 50)
);

INSERT INTO Categorias(cat_nome) VALUES
	('Ação'),
    ('Animação'),
    ('Aventura'),
    ('Cinema de arte'),
    ('Chanchada'),
    ('Comédia'),
    ('Comédia de ação'),
    ('Comédia de terror'),
    ('Comédia dramática'),
    ('Comédia romântica'),
    ('Dança'),
    ('Documentário'),
    ('Docuficção'),
    ('Drama'),
    ('Espionagem'),
    ('Faroeste'),
    ('Fantasia científica'),
    ('Ficção científica'),
    ('Filmes com truques'),
    ('Filmes de guerra'),
    ('Musical'),
    ('Filme policial'),
    ('Romance'),
    ('Seriado'),
    ('Suspense'),
    ('Terror'),
    ('Thriller');


CREATE TABLE Filmes
(
	fil_id SERIAL PRIMARY KEY,
	fil_sinopse VARCHAR(500) UNIQUE NOT NULL
		CONSTRAINT fil_sinopse_curto CHECK (length(fil_sinopse) >= 10)
		CONSTRAINT fil_sinopse_longo CHECK (length(fil_sinopse) <= 500),
	fil_titulo VARCHAR(50) UNIQUE NOT NULL
		CONSTRAINT fil_titulo_curto CHECK (length(fil_titulo) >= 2)
		CONSTRAINT fil_titulo_longo CHECK (length(fil_titulo) <= 50),
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

INSERT INTO filmes 
(
	fil_titulo, 
	fil_sinopse, 
	fil_ano, 
	fil_cat_id
) VALUES
(
	'Vingadores: Ultimato',
	'Em Vingadores: Ultimato, após Thanos eliminar metade das criaturas vivas em Vingadores: Guerra Infinita, os heróis precisam lidar com a dor da perda de amigos e seus entes queridos. Com Tony Stark (Robert Downey Jr.) vagando perdido no espaço sem água nem comida, o Capitão América/Steve Rogers (Chris Evans) e a Viúva Negra/Natasha Romanov (Scarlett Johansson) precisam liderar a resistência contra o titã louco.',
	2019,
	1
),
(
	'Velozes & Furiosos 7',
	'Ian Shaw (Jason Statham), um assassino profissional, quer vingança pela morte de seu irmão. Com a tranqüilidade destruída, o grupo precisa se reunir novamente para impedir que o pior aconteça. Mas, desta vez, a questão não é só ser veloz. A luta é também pela sobrevivência.',
	2015,
	1
),
(
	'Matrix',
	'The Matrix acompanha a aventura de Neo, um jovem hacker que é chamado para o movimento de resistência liderado por Morpheus, na luta contra a dominação dos humanos pelas máquinas. ... Neo descobre que a resistência acredita que ele é o Escolhido, um messias que virá libertar a humanidade da escravidão da Matrix.',
	1999,
	18
),
(
	'Um Sonho de Liberdade',
	'Resumo do Filme O filme conta a história da grande reviravolta na vida de Andy Durfresne, jovem bem-sucedido que trabalhava como contador em um banco, quando é condenado à prisão perpétua na penitenciaria de Shawshank pelo assassinato de sua esposa e o amante.',
	1995,
	14
),
(
	'O Poderoso Chefão ',
	'O filme conta a primeira parte da saga da famiglia Corleone. Comandada pelo respeitado Don Vito Corleone (Marlon Brando), a família mafiosa controla os negócios ilegais na Nova York dos anos 40 e 50, em constantes conflitos com outros grupos e dons',
	1972,
	22
),
(
	'Batman - O Cavaleiro das Trevas',
	'Com a ajuda do tenente James Gordon (Gary Oldman) e do promotor público Harvey Dent (Aaron Eckhart), Batman luta contra o crime organizado. Acuados com o combate, os chefes do crime aceitam a proposta feita pelo Coringa (Heath Ledger) e o contratam para combater o Homem-Morcego.',
	2008,
	25
),
(
	'12 Homens e uma Sentença',
	'Você é membro de um júri. O réu é acusado de assassinato, e se for considerado culpado, será executado. ... Depois de ver o filme 12 homens e uma sentença, você desejará fervorosamente que um homem como “o jurado de número 8” (interpretado por Henry Fonda) faça parte do júri do seu julgamento',
	1958,
	14
),
(
	'Cidade de Deus',
	'Buscapé é um jovem pobre, negro e sensível, que cresce em um universo de muita violência. Ele vive na Cidade de Deus, favela carioca conhecida por ser um dos locais mais violentos do Rio. Amedrontado com a possibilidade de se tornar um bandido, Buscapé é salvo de seu destino por causa de seu talento como fotógrafo, o qual permite que siga carreira na profissão. É por meio de seu olhar atrás da câmera que ele analisa o dia a dia da favela em que vive, onde a violência aparenta ser infinita.',
	2002,
	22
),
(
	'À Espera de um Milagre',
	'Um carcereiro tem um relacionamento incomum com um preso que se encontra no corredor na morte: Coffey, condenado por ter matado brutalmente duas gêmeas de nove anos. Ele tem tamanho e força para ter cometido o crime, mas seu comportamento é completamente oposto à sua aparência.',
	1999,
	17
),
(
	'Star Wars: Episódio V - O Império Contra-Ataca ',
	'O filme fala dos conflitos contínuos da Aliança Rebelde contra o Império Galáctico, depois da destruição total da primeira Estrela da Morte, que mesmo assim as rebeliões continuam fugindo da ameaça do Império, que manda sondas espiãs por toda a Galáxia em busca dos rebeldes.',--sinopse
	1980,
	3
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