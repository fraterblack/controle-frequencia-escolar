
CREATE TABLE IF NOT EXISTS alunos (
  cod_alunos int(11) NOT NULL AUTO_INCREMENT,
  nome_aluno varchar(200) NOT NULL,
  data_nascimento date NOT NULL,
  doc_id varchar(200) NOT NULL,
  PRIMARY KEY (cod_alunos)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1  ;


CREATE TABLE IF NOT EXISTS materias (
  cod_materias int(11) NOT NULL AUTO_INCREMENT,
  nome_materia varchar(200) NOT NULL,
  PRIMARY KEY (cod_materias)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 ;



CREATE TABLE IF NOT EXISTS mat_turmas (
  cod_mat_turmas int(11) NOT NULL AUTO_INCREMENT,
  cod_turmas int(11) NOT NULL,
  cod_materias int(11) NOT NULL,
  PRIMARY KEY (cod_mat_turmas),
  KEY fk_mat_turmas_turmas (cod_turmas),
  KEY fk_mat_turmas_mat (cod_materias)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 ;


CREATE TABLE IF NOT EXISTS turmas (
  cod_turmas int(15) NOT NULL AUTO_INCREMENT,
  nome_turma varchar(30) NOT NULL,
  ano_turma varchar(30) NOT NULL,
  PRIMARY KEY (cod_turmas)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS turmas_alunos (
  cod_alunos int(11) NOT NULL,
  cod_turmas int(11) NOT NULL,
  PRIMARY KEY (cod_alunos,cod_turmas),
  KEY fk_turmas_alu_turm (cod_turmas)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS usuarios (
  cod_usuarios varchar(20) NOT NULL,
  senha varchar(20) NOT NULL,
  nome varchar(50) NOT NULL,
  tipo varchar(1) NOT NULL,
  PRIMARY KEY (cod_usuarios)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Limitadores para a tabela mat_turmas
--
ALTER TABLE mat_turmas
  ADD CONSTRAINT fk_mat_turmas_mat FOREIGN KEY (cod_materias) REFERENCES materias (cod_materias),
  ADD CONSTRAINT fk_mat_turmas_turmas FOREIGN KEY (cod_turmas) REFERENCES turmas (cod_turmas);

--
-- Limitadores para a tabela turmas_alunos
--
ALTER TABLE turmas_alunos
  ADD CONSTRAINT fk_turmas_alu_alu FOREIGN KEY (cod_alunos) REFERENCES alunos (cod_alunos),
  ADD CONSTRAINT fk_turmas_alu_turm FOREIGN KEY (cod_turmas) REFERENCES turmas (cod_turmas);


