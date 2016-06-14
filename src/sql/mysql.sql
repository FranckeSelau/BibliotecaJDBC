
-- phpMyAdmin SQL Dump
-- version 4.2.10
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Jun 09, 2016 at 11:50 AM
-- Server version: 5.5.38
-- PHP Version: 5.6.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `biblioteca`
--

-- --------------------------------------------------------

--
-- Table structure for table `cliente`
--

CREATE TABLE `cliente` (
`matricula` bigint(20) unsigned NOT NULL,
  `nome` varchar(40) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cliente`
--

INSERT INTO `cliente` (`matricula`, `nome`, `telefone`) VALUES
(1, 'Saulo Vieira', '5130242428'),
(2, 'Francke Selau', '5120203030'),
(3, 'Luis Ries', '5130303030');

-- --------------------------------------------------------

--
-- Table structure for table `devolucao`
--

CREATE TABLE `devolucao` (
`id` int(11) NOT NULL,
  `retirada` int(11) NOT NULL,
  `devolvido` date NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `devolucao`
--

INSERT INTO `devolucao` (`id`, `retirada`, `devolvido`) VALUES
(1, 1, '2016-06-09'),
(2, 4, '2016-06-09'),
(3, 2, '2016-06-09');

-- --------------------------------------------------------

--
-- Table structure for table `livro`
--

CREATE TABLE `livro` (
`isbn` int(11) NOT NULL,
  `nome` varchar(40) DEFAULT NULL,
  `autor` varchar(40) DEFAULT NULL,
  `editora` varchar(40) DEFAULT NULL,
  `ano` date DEFAULT NULL,
  `retiradas` varchar(15) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `livro`
--

INSERT INTO `livro` (`isbn`, `nome`, `autor`, `editora`, `ano`, `retiradas`) VALUES
(1, 'Harry Potter e a Pedra Filosofal', 'J.k. Rowling', 'Colecionador', '1991-01-01', NULL),
(2, 'Cronicas de Gelo e Fogo', 'J.R.R. Martin', 'Artica', '1992-01-01', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `retirada`
--

CREATE TABLE `retirada` (
`id` bigint(20) unsigned NOT NULL,
  `retirada` date DEFAULT NULL,
  `devolvido` date DEFAULT NULL,
  `entrega` date DEFAULT NULL,
  `matricula` int(11) NOT NULL,
  `isbn` int(11) NOT NULL,
  `livroDevolvido` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `retirada`
--

INSERT INTO `retirada` (`id`, `retirada`, `devolvido`, `entrega`, `matricula`, `isbn`, `livroDevolvido`) VALUES
(1, '2016-06-09', '2016-06-16', '2016-06-16', 1, 1, NULL),
(2, '2016-06-09', '2016-06-16', '2016-06-16', 1, 1, NULL),
(3, '2016-06-09', '2016-06-16', '2016-06-16', 1, 2, NULL),
(4, '2016-06-09', '2016-06-16', '2016-06-16', 2, 2, NULL);

-- --------------------------------------------------------

--
-- Stand-in structure for view `view_livros_devolvidos`
--
CREATE TABLE `view_livros_devolvidos` (
`id` bigint(20) unsigned
,`retirada` date
,`devolvido` date
,`entrega` date
,`matricula` int(11)
,`isbn` int(11)
,`livroDevolvido` tinyint(1)
,`data_devolucao` date
);
-- --------------------------------------------------------

--
-- Stand-in structure for view `view_livros_disponiveis`
--
CREATE TABLE `view_livros_disponiveis` (
`diferenca` bigint(22)
,`isbn` int(11)
,`nome` varchar(40)
,`autor` varchar(40)
,`editora` varchar(40)
,`ano` date
,`retiradas` varchar(15)
);
-- --------------------------------------------------------

--
-- Stand-in structure for view `view_mais_emprestados`
--
CREATE TABLE `view_mais_emprestados` (
`livro` bigint(21)
,`isbn` int(11)
,`nome` varchar(40)
);
-- --------------------------------------------------------

--
-- Structure for view `view_livros_devolvidos`
--
DROP TABLE IF EXISTS `view_livros_devolvidos`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_livros_devolvidos` AS select `r`.`id` AS `id`,`r`.`retirada` AS `retirada`,`r`.`devolvido` AS `devolvido`,`r`.`entrega` AS `entrega`,`r`.`matricula` AS `matricula`,`r`.`isbn` AS `isbn`,`r`.`livroDevolvido` AS `livroDevolvido`,`d`.`devolvido` AS `data_devolucao` from (`retirada` `r` join `devolucao` `d` on((`d`.`retirada` = `r`.`id`)));

-- --------------------------------------------------------

--
-- Structure for view `view_livros_disponiveis`
--
DROP TABLE IF EXISTS `view_livros_disponiveis`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_livros_disponiveis` AS (select ((select count(0) from `retirada` where (`retirada`.`isbn` = `l`.`isbn`)) - (select count(0) from (`devolucao` `d` join `retirada` `r` on((`r`.`id` = `d`.`retirada`))) where (`r`.`isbn` = `l`.`isbn`))) AS `diferenca`,`l`.`isbn` AS `isbn`,`l`.`nome` AS `nome`,`l`.`autor` AS `autor`,`l`.`editora` AS `editora`,`l`.`ano` AS `ano`,`l`.`retiradas` AS `retiradas` from `livro` `l`);

-- --------------------------------------------------------

--
-- Structure for view `view_mais_emprestados`
--
DROP TABLE IF EXISTS `view_mais_emprestados`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_mais_emprestados` AS select count(`r`.`isbn`) AS `livro`,`r`.`isbn` AS `isbn`,`l`.`nome` AS `nome` from (`retirada` `r` join `livro` `l` on((`l`.`isbn` = `r`.`isbn`))) group by `r`.`isbn` order by count(`r`.`isbn`) desc;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
 ADD PRIMARY KEY (`matricula`), ADD UNIQUE KEY `matricula` (`matricula`);

--
-- Indexes for table `devolucao`
--
ALTER TABLE `devolucao`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `livro`
--
ALTER TABLE `livro`
 ADD PRIMARY KEY (`isbn`);

--
-- Indexes for table `retirada`
--
ALTER TABLE `retirada`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `id` (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
MODIFY `matricula` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `devolucao`
--
ALTER TABLE `devolucao`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `livro`
--
ALTER TABLE `livro`
MODIFY `isbn` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `retirada`
--
ALTER TABLE `retirada`
MODIFY `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;