INSERT INTO usuario (id_usuario, LOGIN, NOME, SENHA, TELEFONE) VALUES (1, 'admin', 'Administrador', 'admin', '4199653639');
INSERT INTO usuario (id_usuario, LOGIN, NOME, SENHA, TELEFONE) VALUES (2, 'mairalima', 'Maira Lima', 'mairalima', '419999999');
INSERT INTO usuario (id_usuario, LOGIN, NOME, SENHA, TELEFONE) VALUES (3, 'willianganzert', 'Willian Ganzert', 'willianganzert', '419999999');
INSERT INTO usuario (id_usuario, LOGIN, NOME, SENHA, TELEFONE) VALUES (4, 'visitante', 'Visitante', 'visitante', '419999999');

INSERT INTO perfil(id_perfil, descricao, nome) VALUES (1, 'Administrador do Sistema', 'Administrador');
INSERT INTO perfil(id_perfil, descricao, nome) VALUES (2, 'Usuario do Sistema', 'Usuario');
INSERT INTO perfil(id_perfil, descricao, nome) VALUES (3, 'Visitante', 'Visitante');


INSERT INTO usuario_perfil(id_usuario_perfil, dataatribuicao, id_perfil, id_usuario)
    VALUES (1, current_timestamp, 1, 1);
INSERT INTO usuario_perfil(id_usuario_perfil, dataatribuicao, id_perfil, id_usuario)
    VALUES (2, current_timestamp, 2, 1);
INSERT INTO usuario_perfil(id_usuario_perfil, dataatribuicao, id_perfil, id_usuario)
    VALUES (3, current_timestamp, 2, 2);
INSERT INTO usuario_perfil(id_usuario_perfil, dataatribuicao, id_perfil, id_usuario)
    VALUES (4, current_timestamp, 2, 3);
INSERT INTO usuario_perfil(id_usuario_perfil, dataatribuicao, id_perfil, id_usuario)
    VALUES (5, current_timestamp, 3, 4);
    
INSERT INTO perfil_acesso(
            id_perfil_acesso, dataatribuicao, id_modelo_dispositivo, id_modelo_predefinicao, 
            id_perfil)
    VALUES (1, current_timestamp, 1, 0,3);
            
            


--Exemplo
INSERT INTO dispositivo VALUES (1, 'Lampada');
ALTER SEQUENCE dispositivo_id_dispositivo_seq RESTART WITH 2;

INSERT INTO modelo_dispositivo VALUES (1, 'Lampada Sala', NULL, 1);
ALTER SEQUENCE modelo_dispositivo_id_modelo_dispositivo_seq RESTART WITH 2;

INSERT INTO modelo_acao VALUES (1, 'Acender a luz', 'Ligar', 1);
INSERT INTO modelo_acao VALUES (2, 'Apagar a luz', 'Desligar', 1);
ALTER SEQUENCE modelo_acao_id_modelo_acao_seq RESTART WITH 3;

INSERT INTO parametro VALUES (1, 'Ligado', 2, '1', '0', 1);
ALTER SEQUENCE parametro_id_parametro_seq RESTART WITH 2;

INSERT INTO modelo_parametro VALUES (1, '1', 1, 1);
INSERT INTO modelo_parametro VALUES (2, '0', 2, 1);
ALTER SEQUENCE modelo_parametro_id_modelo_parametro_seq RESTART WITH 3;

