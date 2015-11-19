SET FOREIGN_KEY_CHECKS=0;
ALTER TABLE frequencia DROP FOREIGN KEY fk_freq_turmas;

TRUNCATE frequencia;
TRUNCATE frequencia_aluno;

ALTER TABLE frequencia CHANGE cod_turmas cod_disc_turmas INT(11) NOT NULL;

SET FOREIGN_KEY_CHECKS=1;

ALTER TABLE frequencia
  ADD CONSTRAINT fk_freq_disc_turmas FOREIGN KEY (cod_disc_turmas) REFERENCES disc_turmas(cod_disc_turmas);