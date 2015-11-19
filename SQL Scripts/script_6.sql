CREATE TABLE IF NOT EXISTS frequencia_aluno (
cod_chamada int(11) NOT NULL,
cod_alunos int(11) NOT NULL, 
presente varchar(1) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE frequencia_aluno
  ADD CONSTRAINT fk_freq_aluno_chamada FOREIGN KEY (cod_chamada) REFERENCES frequencia(cod_chamada), 
  ADD CONSTRAINT fk_freq_aluno_alunos FOREIGN KEY (cod_alunos) REFERENCES alunos(cod_alunos);