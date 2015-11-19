ALTER TABLE usuarios CHANGE cod_usuarios cod_usuarios INT(15) NOT NULL AUTO_INCREMENT;
ALTER TABLE usuarios CHANGE COLUMN senha senha VARCHAR(200) NOT NULL;
ALTER TABLE usuarios ADD COLUMN login VARCHAR(80) NOT NULL AFTER cod_usuarios;

INSERT INTO usuarios (cod_usuarios, login, senha, nome, tipo) VALUES (NULL, 'administrador', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Administrador', 'a');