CREATE TABLE area (
 id_area NUMERIC(10) NOT NULL,
 nome VARCHAR(50) NOT NULL
);

ALTER TABLE area ADD CONSTRAINT PK_area PRIMARY KEY (id_area);


CREATE TABLE configuracoes (
 chave VARCHAR(36) NOT NULL,
 valor VARCHAR(36) NOT NULL
);

ALTER TABLE configuracoes ADD CONSTRAINT PK_configuracoes PRIMARY KEY (chave);


CREATE TABLE dispositivo (
 id_dispositivo NUMERIC(10) NOT NULL,
 nome VARCHAR(100) NOT NULL
);

ALTER TABLE dispositivo ADD CONSTRAINT PK_dispositivo PRIMARY KEY (id_dispositivo);


CREATE TABLE log_status (
 id_log_status NUMERIC(10) NOT NULL,
 nome VARCHAR(50)
);

ALTER TABLE log_status ADD CONSTRAINT PK_log_status PRIMARY KEY (id_log_status);


CREATE TABLE modelo_dispositivo (
 id_modelo_dispositivo NUMERIC(10) NOT NULL,
 id_dispositivo NUMERIC(10) NOT NULL,
 id_area NUMERIC(10),
 nome VARCHAR(50) NOT NULL
);

ALTER TABLE modelo_dispositivo ADD CONSTRAINT PK_modelo_dispositivo PRIMARY KEY (id_modelo_dispositivo);


CREATE TABLE modelo_predefinicao (
 id_modelo_predefinicao CHAR(10) NOT NULL,
 nome VARCHAR(50) NOT NULL,
 descricao VARCHAR(200)
);

ALTER TABLE modelo_predefinicao ADD CONSTRAINT PK_modelo_predefinicao PRIMARY KEY (id_modelo_predefinicao);


CREATE TABLE parametro (
 id_parametro NUMERIC(10) NOT NULL,
 id_dispositivo NUMERIC(10) NOT NULL,
 nome VARCHAR(50) NOT NULL,
 tipo NUMERIC(10) NOT NULL,
 tipo_valor VARCHAR(20) NOT NULL,
 tipo_valor2 VARCHAR(20)
);

ALTER TABLE parametro ADD CONSTRAINT PK_parametro PRIMARY KEY (id_parametro);


CREATE TABLE perfil (
 id_perfil NUMERIC(10) NOT NULL,
 nome VARCHAR(50) NOT NULL,
 descricao VARCHAR(200)
);

ALTER TABLE perfil ADD CONSTRAINT PK_perfil PRIMARY KEY (id_perfil);


CREATE TABLE perfil_acesso (
 id_perfil_acesso NUMERIC(10) NOT NULL,
 id_perfil NUMERIC(10) NOT NULL,
 id_modelo_predefinicao CHAR(10) NOT NULL,
 id_modelo_dispositivo NUMERIC(10),
 data_atribuicao DATE NOT NULL
);

ALTER TABLE perfil_acesso ADD CONSTRAINT PK_perfil_acesso PRIMARY KEY (id_perfil_acesso);


CREATE TABLE status_execucao (
 id_status_execucao NUMERIC(10) NOT NULL,
 nome VARCHAR(50) NOT NULL
);

ALTER TABLE status_execucao ADD CONSTRAINT PK_status_execucao PRIMARY KEY (id_status_execucao);


CREATE TABLE usuario (
 id_usuario NUMERIC(10) NOT NULL,
 id_perfil NUMERIC(10) NOT NULL,
 login VARCHAR(30) NOT NULL,
 senha VARCHAR(100) NOT NULL
);

ALTER TABLE usuario ADD CONSTRAINT PK_usuario PRIMARY KEY (id_usuario);


CREATE TABLE usuario_perfil (
 id_perfil NUMERIC(10) NOT NULL,
 id_usuario NUMERIC(10) NOT NULL
);

ALTER TABLE usuario_perfil ADD CONSTRAINT PK_usuario_perfil PRIMARY KEY (id_perfil,id_usuario);


CREATE TABLE modelo_acao (
 id_modelo_acao NUMERIC(10) NOT NULL,
 id_modelo_dispositivo NUMERIC(10),
 nome VARCHAR(50) NOT NULL,
 descricao VARCHAR(200)
);

ALTER TABLE modelo_acao ADD CONSTRAINT PK_modelo_acao PRIMARY KEY (id_modelo_acao);


CREATE TABLE modelo_parametro (
 id_modelo_parametro NUMERIC(10) NOT NULL,
 id_parametro NUMERIC(10),
 id_modelo_acao NUMERIC(10),
 valor_parametro_acao VARCHAR(20) NOT NULL
);

ALTER TABLE modelo_parametro ADD CONSTRAINT PK_modelo_parametro PRIMARY KEY (id_modelo_parametro);


CREATE TABLE acao_predefinicao (
 id_acao_predefinicao NUMERIC(10) NOT NULL,
 id_modelo_predefinicao CHAR(10),
 id_modelo_acao NUMERIC(10)
);

ALTER TABLE acao_predefinicao ADD CONSTRAINT PK_acao_predefinicao PRIMARY KEY (id_acao_predefinicao);


CREATE TABLE fila_evento_executar (
 id_fila_evento NUMERIC(10) NOT NULL,
 id_modelo_acao NUMERIC(10),
 id_status_execucao NUMERIC(10),
 hora_insercao_fila TIMESTAMP WITH TIME ZONE(10) NOT NULL,
 hora_execucao_evento TIMESTAMP WITH TIME ZONE(10),
 numero_tentativa INT DEFAULT 0 NOT NULL
);

ALTER TABLE fila_evento_executar ADD CONSTRAINT PK_fila_evento_executar PRIMARY KEY (id_fila_evento);


CREATE TABLE log_execucao_parametro (
 id_log_execucao_parametro NUMERIC(10) NOT NULL,
 id_fila_evento NUMERIC(10) NOT NULL,
 id_parametro NUMERIC(10) NOT NULL,
 id_log_status NUMERIC(10),
 valor_parametro CHAR(10) NOT NULL,
 descricao_erro VARCHAR(500),
 hora_criacao_log TIMESTAMP WITH TIME ZONE(10) NOT NULL
);

ALTER TABLE log_execucao_parametro ADD CONSTRAINT PK_log_execucao_parametro PRIMARY KEY (id_log_execucao_parametro);


ALTER TABLE modelo_dispositivo ADD CONSTRAINT FK_modelo_dispositivo_0 FOREIGN KEY (id_dispositivo) REFERENCES dispositivo (id_dispositivo);
ALTER TABLE modelo_dispositivo ADD CONSTRAINT FK_modelo_dispositivo_1 FOREIGN KEY (id_area) REFERENCES area (id_area);


ALTER TABLE parametro ADD CONSTRAINT FK_parametro_0 FOREIGN KEY (id_dispositivo) REFERENCES dispositivo (id_dispositivo);


ALTER TABLE perfil_acesso ADD CONSTRAINT FK_perfil_acesso_0 FOREIGN KEY (id_perfil) REFERENCES perfil (id_perfil);
ALTER TABLE perfil_acesso ADD CONSTRAINT FK_perfil_acesso_1 FOREIGN KEY (id_modelo_predefinicao) REFERENCES modelo_predefinicao (id_modelo_predefinicao);
ALTER TABLE perfil_acesso ADD CONSTRAINT FK_perfil_acesso_2 FOREIGN KEY (id_modelo_dispositivo) REFERENCES modelo_dispositivo (id_modelo_dispositivo);


ALTER TABLE usuario ADD CONSTRAINT FK_usuario_0 FOREIGN KEY (id_perfil) REFERENCES perfil (id_perfil);


ALTER TABLE usuario_perfil ADD CONSTRAINT FK_usuario_perfil_0 FOREIGN KEY (id_perfil) REFERENCES perfil (id_perfil);
ALTER TABLE usuario_perfil ADD CONSTRAINT FK_usuario_perfil_1 FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario);


ALTER TABLE modelo_acao ADD CONSTRAINT FK_modelo_acao_0 FOREIGN KEY (id_modelo_dispositivo) REFERENCES modelo_dispositivo (id_modelo_dispositivo);


ALTER TABLE modelo_parametro ADD CONSTRAINT FK_modelo_parametro_0 FOREIGN KEY (id_parametro) REFERENCES parametro (id_parametro);
ALTER TABLE modelo_parametro ADD CONSTRAINT FK_modelo_parametro_1 FOREIGN KEY (id_modelo_acao) REFERENCES modelo_acao (id_modelo_acao);


ALTER TABLE acao_predefinicao ADD CONSTRAINT FK_acao_predefinicao_0 FOREIGN KEY (id_modelo_predefinicao) REFERENCES modelo_predefinicao (id_modelo_predefinicao);
ALTER TABLE acao_predefinicao ADD CONSTRAINT FK_acao_predefinicao_1 FOREIGN KEY (id_modelo_acao) REFERENCES modelo_acao (id_modelo_acao);


ALTER TABLE fila_evento_executar ADD CONSTRAINT FK_fila_evento_executar_0 FOREIGN KEY (id_modelo_acao) REFERENCES modelo_acao (id_modelo_acao);
ALTER TABLE fila_evento_executar ADD CONSTRAINT FK_fila_evento_executar_1 FOREIGN KEY (id_status_execucao) REFERENCES status_execucao (id_status_execucao);


ALTER TABLE log_execucao_parametro ADD CONSTRAINT FK_log_execucao_parametro_0 FOREIGN KEY (id_fila_evento) REFERENCES fila_evento_executar (id_fila_evento);
ALTER TABLE log_execucao_parametro ADD CONSTRAINT FK_log_execucao_parametro_1 FOREIGN KEY (id_log_status) REFERENCES log_status (id_log_status);


