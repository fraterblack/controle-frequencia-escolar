DROP TABLE IF EXISTS turmas_alunos;

CREATE TABLE IF NOT EXISTS matriculas (
  cod_matricula int(11) NOT NULL AUTO_INCREMENT,
  cod_matricula_externa varchar(100) NULL,
  cod_turmas int(11) NOT NULL, 
  cod_alunos int(11) NOT NULL, 
  PRIMARY KEY (cod_matricula), 
  KEY fk_matriculas_turmas (cod_turmas), 
  KEY fk_matriculas_alunos (cod_alunos)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

ALTER TABLE matriculas
  ADD CONSTRAINT fk_matriculas_turmas FOREIGN KEY (cod_turmas) REFERENCES turmas(cod_turmas),
  ADD CONSTRAINT fk_matriculas_alunos FOREIGN KEY (cod_alunos) REFERENCES alunos(cod_alunos);
