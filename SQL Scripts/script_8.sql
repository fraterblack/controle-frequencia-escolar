ALTER TABLE frequencia ADD COLUMN cod_usuarios int(11) NOT NULL AFTER cod_turmas;
ALTER TABLE frequencia ADD COLUMN data_chamada date NOT NULL AFTER data_aula;

#Re-index os códigos dos usuários
set @ROW = 0;
UPDATE usuarios SET cod_usuarios= @ROW := @ROW+1 ORDER BY cod_usuarios ASC;

#Adiciona codigo do usuário 1 em chamadas já realizadas
UPDATE frequencia SET cod_usuarios=1;

#Atribui data
UPDATE frequencia SET data_chamada=frequencia.data_aula;

#Adiciona foreign keys
ALTER TABLE frequencia
  ADD CONSTRAINT fk_frequencia_usuarios FOREIGN KEY (cod_usuarios) REFERENCES usuarios(cod_usuarios);

