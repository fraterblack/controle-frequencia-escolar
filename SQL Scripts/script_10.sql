DROP TABLE IF EXISTS mat_turmas;
DROP TABLE IF EXISTS materias;

CREATE TABLE IF NOT EXISTS disciplinas (
  cod_disciplinas int(11) NOT NULL AUTO_INCREMENT,
  nome_disciplina varchar(200) NOT NULL,
  PRIMARY KEY (cod_disciplinas)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 ;


CREATE TABLE IF NOT EXISTS disc_turmas (
  cod_disc_turmas int(11) NOT NULL AUTO_INCREMENT,
  cod_turmas int(11) NOT NULL,
  cod_disciplinas int(11) NOT NULL,
  PRIMARY KEY (cod_disc_turmas),
  KEY fk_disc_turmas_turmas (cod_turmas),
  KEY fk_disc_turmas_disc (cod_disciplinas)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

ALTER TABLE disc_turmas
  ADD CONSTRAINT fk_disc_turmas_disc FOREIGN KEY (cod_disciplinas) REFERENCES disciplinas(cod_disciplinas),
  ADD CONSTRAINT fk_disc_turmas_turmas FOREIGN KEY (cod_turmas) REFERENCES turmas(cod_turmas);