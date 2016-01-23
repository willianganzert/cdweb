INSERT INTO usuario (id_usuario, LOGIN, NOME, SENHA, TELEFONE) VALUES (nextval('usuario_id_usuario_seq'), 'admin', 'Administrador', 'admin', '4199653639');


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

