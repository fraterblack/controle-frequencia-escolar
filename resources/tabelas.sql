
--
-- Table structure for table `alunos`
--

CREATE TABLE `alunos` (
  `cod_alunos` int(11) NOT NULL,
  `nome_aluno` varchar(200) NOT NULL,
  `data_nascimento` date NOT NULL,
  `doc_id` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `alunos`
--

INSERT INTO `alunos` (`cod_alunos`, `nome_aluno`, `data_nascimento`, `doc_id`) VALUES
(1, 'João Pedro', '1990-06-18', '12345'),
(2, 'Pedro Henrique', '1991-03-01', '122345'),
(3, 'Henrique Augusto', '1989-12-05', '12345'),
(4, 'Maria Clara', '1989-12-14', '122345'),
(5, 'Ana Cláudia', '1990-03-07', '121345'),
(6, 'Antônio Carlos', '1990-06-18', '12345'),
(7, 'Igor', '1991-03-01', '122345'),
(8, 'Marcelo', '1989-12-05', '12345'),
(9, 'Regiane', '1989-12-14', '122345'),
(10, 'Daiane', '1990-03-07', '121345'),
(11, 'Maria Eugênia', '1990-06-18', '12345'),
(12, 'Ricardo', '1991-03-01', '122345'),
(13, 'Antônio', '1989-12-05', '12345'),
(14, 'Madalena', '1989-12-14', '122345'),
(15, 'Maria de Lourdes', '1990-03-07', '121345'),
(16, 'Albertina', '1990-06-18', '12345'),
(17, 'Kelly', '1991-03-01', '122345'),
(18, 'Augusto', '1989-12-05', '12345'),
(19, 'Mario Sérgio', '1989-12-14', '122345'),
(20, 'Edvaldo', '1990-03-07', '121345'),
(21, 'Anderson', '1990-03-07', '121345'),
(22, 'Lourdes Maria', '1990-03-07', '121345'),
(23, 'Jenifer', '1990-03-07', '121345'),
(24, 'Ágata', '1990-03-07', '121345'),
(25, 'Arthur', '1991-03-01', '122345'),
(26, 'Maria Júlia', '1989-12-14', '122345'),
(27, 'Simone', '1991-03-01', '122345'),
(28, 'Andrio', '1991-03-01', '122345'),
(29, 'André', '1989-12-14', '122345'),
(30, 'Gustavo', '1989-12-14', '122345'),
(31, 'Devair', '1991-03-01', '122345'),
(32, 'Ronei', '1989-12-14', '122345'),
(33, 'Osvaldo', '1989-12-05', '12345'),
(34, 'Juliana', '1990-06-18', '12345'),
(35, 'Rutiéli', '1989-12-05', '12345'),
(36, 'Romer', '1990-06-18', '12345'),
(37, 'Barth', '1989-12-05', '12345'),
(38, 'Lisa', '1990-06-18', '12345'),
(39, 'Margie', '1989-12-05', '12345'),
(40, 'Meg', '1990-06-18', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `disciplinas`
--

CREATE TABLE `disciplinas` (
  `cod_disciplinas` int(11) NOT NULL,
  `nome_disciplina` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `disciplinas`
--

INSERT INTO `disciplinas` (`cod_disciplinas`, `nome_disciplina`) VALUES
(1, 'Matemática'),
(2, 'Português'),
(3, 'História'),
(4, 'Geografia'),
(5, 'Educação Física');

-- --------------------------------------------------------

--
-- Table structure for table `disc_turmas`
--

CREATE TABLE `disc_turmas` (
  `cod_disc_turmas` int(11) NOT NULL,
  `cod_turmas` int(11) NOT NULL,
  `cod_disciplinas` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `disc_turmas`
--

INSERT INTO `disc_turmas` (`cod_disc_turmas`, `cod_turmas`, `cod_disciplinas`) VALUES
(1, 3, 5),
(2, 3, 4),
(3, 3, 3),
(4, 3, 1),
(5, 3, 2),
(6, 1, 3),
(7, 1, 1),
(8, 1, 2),
(9, 2, 3),
(10, 2, 1),
(11, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `frequencia`
--

CREATE TABLE `frequencia` (
  `cod_chamada` int(11) NOT NULL,
  `data_aula` date NOT NULL,
  `data_chamada` date NOT NULL,
  `cod_disc_turmas` int(11) NOT NULL,
  `cod_usuarios` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `frequencia`
--

INSERT INTO `frequencia` (`cod_chamada`, `data_aula`, `data_chamada`, `cod_disc_turmas`, `cod_usuarios`) VALUES
(1, '2017-03-15', '2017-03-20', 1, 1),
(2, '2017-03-16', '2017-03-20', 1, 1),
(3, '2017-03-17', '2017-03-20', 1, 1),
(4, '2017-03-20', '2017-03-20', 1, 1),
(5, '2017-03-15', '2017-03-20', 2, 1),
(6, '2017-03-16', '2017-03-20', 2, 1),
(7, '2017-03-17', '2017-03-20', 2, 1),
(8, '2017-03-15', '2017-03-20', 3, 1),
(9, '2017-03-17', '2017-03-20', 3, 2),
(10, '2017-03-20', '2017-03-20', 3, 2),
(11, '2017-03-15', '2017-03-20', 4, 2),
(12, '2017-03-16', '2017-03-20', 4, 2),
(13, '2017-03-17', '2017-03-20', 4, 2),
(14, '2017-03-20', '2017-03-20', 4, 2),
(15, '2017-03-15', '2017-03-20', 5, 2),
(16, '2017-03-16', '2017-03-20', 5, 2),
(17, '2017-03-17', '2017-03-20', 5, 2);

-- --------------------------------------------------------

--
-- Table structure for table `frequencia_aluno`
--

CREATE TABLE `frequencia_aluno` (
  `cod_chamada` int(11) NOT NULL,
  `cod_alunos` int(11) NOT NULL,
  `presente` varchar(1) NOT NULL,
  `falta_justificada` varchar(1) NOT NULL,
  `observacao` varchar(100) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `frequencia_aluno`
--

INSERT INTO `frequencia_aluno` (`cod_chamada`, `cod_alunos`, `presente`, `falta_justificada`, `observacao`) VALUES
(1, 24, 's', 'n', ''),
(1, 16, 's', 'n', ''),
(1, 5, 's', 'n', ''),
(1, 21, 's', 'n', ''),
(1, 29, 's', 'n', ''),
(1, 28, 's', 'n', ''),
(1, 13, 's', 'n', ''),
(1, 6, 's', 'n', ''),
(1, 25, 's', 'n', ''),
(1, 18, 's', 'n', ''),
(2, 24, 's', 'n', ''),
(2, 16, 's', 'n', ''),
(2, 5, 's', 'n', ''),
(2, 21, 's', 'n', ''),
(2, 29, 's', 'n', ''),
(2, 28, 's', 'n', ''),
(2, 13, 's', 'n', ''),
(2, 6, 's', 'n', ''),
(2, 25, 's', 'n', ''),
(2, 18, 's', 'n', ''),
(3, 24, 's', 'n', ''),
(3, 16, 's', 'n', ''),
(3, 5, 'n', 'n', ''),
(3, 21, 'n', 's', 'Teve um mal estar durante a aula'),
(3, 29, 's', 'n', ''),
(3, 28, 's', 'n', ''),
(3, 13, 's', 'n', ''),
(3, 6, 's', 'n', ''),
(3, 25, 's', 'n', ''),
(3, 18, 's', 'n', ''),
(4, 24, 's', 'n', ''),
(4, 16, 's', 'n', ''),
(4, 5, 's', 'n', ''),
(4, 21, 'n', 'n', ''),
(4, 29, 's', 'n', ''),
(4, 28, 's', 'n', ''),
(4, 13, 's', 'n', ''),
(4, 6, 's', 'n', ''),
(4, 25, 's', 'n', ''),
(4, 18, 's', 'n', ''),
(5, 24, 's', 'n', ''),
(5, 16, 's', 'n', ''),
(5, 5, 's', 'n', ''),
(5, 21, 's', 'n', ''),
(5, 29, 's', 'n', ''),
(5, 28, 's', 'n', ''),
(5, 13, 's', 'n', ''),
(5, 6, 's', 'n', ''),
(5, 25, 's', 'n', ''),
(5, 18, 's', 'n', ''),
(6, 24, 's', 'n', ''),
(6, 16, 's', 'n', ''),
(6, 5, 's', 'n', ''),
(6, 21, 's', 'n', ''),
(6, 29, 's', 'n', ''),
(6, 28, 's', 'n', ''),
(6, 13, 's', 'n', ''),
(6, 6, 's', 'n', ''),
(6, 25, 's', 'n', ''),
(6, 18, 's', 'n', ''),
(7, 24, 's', 'n', ''),
(7, 16, 's', 'n', ''),
(7, 5, 's', 'n', ''),
(7, 21, 'n', 's', ''),
(7, 29, 's', 'n', ''),
(7, 28, 's', 'n', ''),
(7, 13, 's', 'n', ''),
(7, 6, 's', 'n', ''),
(7, 25, 's', 'n', ''),
(7, 18, 's', 'n', ''),
(8, 24, 's', 'n', ''),
(8, 16, 's', 'n', ''),
(8, 5, 's', 'n', ''),
(8, 21, 's', 'n', ''),
(8, 29, 's', 'n', ''),
(8, 28, 's', 'n', ''),
(8, 13, 's', 'n', ''),
(8, 6, 's', 'n', ''),
(8, 25, 's', 'n', ''),
(8, 18, 's', 'n', ''),
(9, 24, 's', 'n', ''),
(9, 16, 's', 'n', ''),
(9, 5, 's', 'n', ''),
(9, 21, 'n', 'n', ''),
(9, 29, 's', 'n', ''),
(9, 28, 's', 'n', ''),
(9, 13, 's', 'n', ''),
(9, 6, 's', 'n', ''),
(9, 25, 's', 'n', ''),
(9, 18, 's', 'n', ''),
(10, 24, 's', 'n', ''),
(10, 16, 's', 'n', ''),
(10, 5, 's', 'n', ''),
(10, 21, 'n', 's', 'Apresentou atestado'),
(10, 29, 's', 'n', ''),
(10, 28, 's', 'n', ''),
(10, 13, 's', 'n', ''),
(10, 6, 's', 'n', ''),
(10, 25, 's', 'n', ''),
(10, 18, 's', 'n', ''),
(11, 24, 's', 'n', ''),
(11, 16, 's', 'n', ''),
(11, 5, 's', 'n', ''),
(11, 21, 's', 'n', ''),
(11, 29, 's', 'n', ''),
(11, 28, 's', 'n', ''),
(11, 13, 's', 'n', ''),
(11, 6, 's', 'n', ''),
(11, 25, 's', 'n', ''),
(11, 18, 's', 'n', ''),
(12, 24, 's', 'n', ''),
(12, 16, 's', 'n', ''),
(12, 5, 's', 'n', ''),
(12, 21, 's', 'n', ''),
(12, 29, 's', 'n', ''),
(12, 28, 's', 'n', ''),
(12, 13, 's', 'n', ''),
(12, 6, 's', 'n', ''),
(12, 25, 's', 'n', ''),
(12, 18, 's', 'n', ''),
(13, 24, 's', 'n', ''),
(13, 16, 's', 'n', ''),
(13, 5, 's', 'n', ''),
(13, 21, 's', 'n', ''),
(13, 29, 's', 'n', ''),
(13, 28, 's', 'n', ''),
(13, 13, 's', 'n', ''),
(13, 6, 's', 'n', ''),
(13, 25, 's', 'n', ''),
(13, 18, 's', 'n', ''),
(14, 24, 's', 'n', ''),
(14, 16, 's', 'n', ''),
(14, 5, 's', 'n', ''),
(14, 21, 's', 'n', ''),
(14, 29, 's', 'n', ''),
(14, 28, 's', 'n', ''),
(14, 13, 's', 'n', ''),
(14, 6, 's', 'n', ''),
(14, 25, 's', 'n', ''),
(14, 18, 's', 'n', ''),
(15, 24, 's', 'n', ''),
(15, 16, 's', 'n', ''),
(15, 5, 's', 'n', ''),
(15, 21, 's', 'n', ''),
(15, 29, 's', 'n', ''),
(15, 28, 's', 'n', ''),
(15, 13, 's', 'n', ''),
(15, 6, 's', 'n', ''),
(15, 25, 's', 'n', ''),
(15, 18, 's', 'n', ''),
(16, 24, 's', 'n', ''),
(16, 16, 's', 'n', ''),
(16, 5, 's', 'n', ''),
(16, 21, 's', 'n', ''),
(16, 29, 's', 'n', ''),
(16, 28, 's', 'n', ''),
(16, 13, 's', 'n', ''),
(16, 6, 's', 'n', ''),
(16, 25, 's', 'n', ''),
(16, 18, 's', 'n', ''),
(17, 24, 's', 'n', ''),
(17, 16, 's', 'n', ''),
(17, 5, 's', 'n', ''),
(17, 21, 's', 'n', ''),
(17, 29, 's', 'n', ''),
(17, 28, 's', 'n', ''),
(17, 13, 's', 'n', ''),
(17, 6, 's', 'n', ''),
(17, 25, 's', 'n', ''),
(17, 18, 's', 'n', '');

-- --------------------------------------------------------

--
-- Table structure for table `matriculas`
--

CREATE TABLE `matriculas` (
  `cod_matriculas` int(11) NOT NULL,
  `cod_matricula_externa` varchar(100) DEFAULT NULL,
  `cod_turmas` int(11) NOT NULL,
  `cod_alunos` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `matriculas`
--

INSERT INTO `matriculas` (`cod_matriculas`, `cod_matricula_externa`, `cod_turmas`, `cod_alunos`) VALUES
(1, NULL, 3, 24),
(2, NULL, 3, 16),
(3, NULL, 3, 5),
(4, NULL, 3, 21),
(5, NULL, 3, 29),
(6, NULL, 3, 28),
(7, NULL, 3, 13),
(8, NULL, 3, 6),
(9, NULL, 3, 25),
(10, NULL, 3, 18),
(11, NULL, 1, 37),
(12, NULL, 1, 10),
(13, NULL, 1, 31),
(14, NULL, 1, 20),
(15, NULL, 1, 30),
(16, NULL, 1, 3),
(17, NULL, 1, 7),
(18, NULL, 1, 23),
(19, NULL, 1, 1),
(20, NULL, 1, 34),
(21, NULL, 2, 17),
(22, NULL, 2, 23),
(23, NULL, 2, 1),
(24, NULL, 2, 34),
(25, NULL, 2, 38),
(26, NULL, 2, 22),
(27, NULL, 2, 14),
(28, NULL, 2, 8),
(29, NULL, 2, 39),
(30, NULL, 2, 4);

-- --------------------------------------------------------

--
-- Table structure for table `turmas`
--

CREATE TABLE `turmas` (
  `cod_turmas` int(15) NOT NULL,
  `nome_turma` varchar(50) NOT NULL,
  `ano_turma` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `turmas`
--

INSERT INTO `turmas` (`cod_turmas`, `nome_turma`, `ano_turma`) VALUES
(1, '7º Ano Matutino', '2017'),
(2, '7º Ano Vespertino', '2017'),
(3, '6º Ano Matutino', '2017');

-- --------------------------------------------------------

--
-- Table structure for table `usuarios`
--

CREATE TABLE `usuarios` (
  `cod_usuarios` int(15) NOT NULL,
  `login` varchar(80) NOT NULL,
  `senha` varchar(200) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `tipo` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuarios`
--

INSERT INTO `usuarios` (`cod_usuarios`, `login`, `senha`, `nome`, `tipo`) VALUES
(1, 'admin', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'Administrador', 'a'),
(2, 'professor', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'Professor', 'p'),
(3, 'supervisor', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'Supervisor', 's');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `alunos`
--
ALTER TABLE `alunos`
  ADD PRIMARY KEY (`cod_alunos`);

--
-- Indexes for table `disciplinas`
--
ALTER TABLE `disciplinas`
  ADD PRIMARY KEY (`cod_disciplinas`);

--
-- Indexes for table `disc_turmas`
--
ALTER TABLE `disc_turmas`
  ADD PRIMARY KEY (`cod_disc_turmas`),
  ADD KEY `fk_disc_turmas_turmas` (`cod_turmas`),
  ADD KEY `fk_disc_turmas_disc` (`cod_disciplinas`);

--
-- Indexes for table `frequencia`
--
ALTER TABLE `frequencia`
  ADD PRIMARY KEY (`cod_chamada`),
  ADD KEY `fk_frequencia_usuarios` (`cod_usuarios`),
  ADD KEY `fk_freq_disc_turmas` (`cod_disc_turmas`);

--
-- Indexes for table `frequencia_aluno`
--
ALTER TABLE `frequencia_aluno`
  ADD KEY `fk_freq_aluno_chamada` (`cod_chamada`),
  ADD KEY `fk_freq_aluno_alunos` (`cod_alunos`);

--
-- Indexes for table `matriculas`
--
ALTER TABLE `matriculas`
  ADD PRIMARY KEY (`cod_matriculas`),
  ADD KEY `fk_matriculas_turmas` (`cod_turmas`),
  ADD KEY `fk_matriculas_alunos` (`cod_alunos`);

--
-- Indexes for table `turmas`
--
ALTER TABLE `turmas`
  ADD PRIMARY KEY (`cod_turmas`);

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`cod_usuarios`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `alunos`
--
ALTER TABLE `alunos`
  MODIFY `cod_alunos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;
--
-- AUTO_INCREMENT for table `disciplinas`
--
ALTER TABLE `disciplinas`
  MODIFY `cod_disciplinas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `disc_turmas`
--
ALTER TABLE `disc_turmas`
  MODIFY `cod_disc_turmas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `frequencia`
--
ALTER TABLE `frequencia`
  MODIFY `cod_chamada` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `matriculas`
--
ALTER TABLE `matriculas`
  MODIFY `cod_matriculas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT for table `turmas`
--
ALTER TABLE `turmas`
  MODIFY `cod_turmas` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `cod_usuarios` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `disc_turmas`
--
ALTER TABLE `disc_turmas`
  ADD CONSTRAINT `fk_disc_turmas_disc` FOREIGN KEY (`cod_disciplinas`) REFERENCES `disciplinas` (`cod_disciplinas`),
  ADD CONSTRAINT `fk_disc_turmas_turmas` FOREIGN KEY (`cod_turmas`) REFERENCES `turmas` (`cod_turmas`);

--
-- Constraints for table `frequencia`
--
ALTER TABLE `frequencia`
  ADD CONSTRAINT `fk_freq_disc_turmas` FOREIGN KEY (`cod_disc_turmas`) REFERENCES `disc_turmas` (`cod_disc_turmas`),
  ADD CONSTRAINT `fk_frequencia_usuarios` FOREIGN KEY (`cod_usuarios`) REFERENCES `usuarios` (`cod_usuarios`);

--
-- Constraints for table `frequencia_aluno`
--
ALTER TABLE `frequencia_aluno`
  ADD CONSTRAINT `fk_freq_aluno_alunos` FOREIGN KEY (`cod_alunos`) REFERENCES `alunos` (`cod_alunos`),
  ADD CONSTRAINT `fk_freq_aluno_chamada` FOREIGN KEY (`cod_chamada`) REFERENCES `frequencia` (`cod_chamada`);

--
-- Constraints for table `matriculas`
--
ALTER TABLE `matriculas`
  ADD CONSTRAINT `fk_matriculas_alunos` FOREIGN KEY (`cod_alunos`) REFERENCES `alunos` (`cod_alunos`),
  ADD CONSTRAINT `fk_matriculas_turmas` FOREIGN KEY (`cod_turmas`) REFERENCES `turmas` (`cod_turmas`);
