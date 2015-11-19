CREATE TABLE IF NOT EXISTS frequencia (
cod_chamada int(11) NOT NULL AUTO_INCREMENT,
data_aula date NOT NULL,
cod_turmas int(11) NOT NULL,
PRIMARY KEY (cod_chamada)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE frequencia
  ADD CONSTRAINT fk_freq_turmas FOREIGN KEY (cod_turmas) REFERENCES turmas(cod_turmas);