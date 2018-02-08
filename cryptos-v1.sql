-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Mar 06 Février 2018 à 13:00
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `cryptos`
--

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `name` varchar(128) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Vider la table avant d'insérer `user`
--

TRUNCATE TABLE `user`;
--
-- Contenu de la table `user`
--

INSERT INTO `user` (`name`, `id`) VALUES
('Kenza', 1),
('Julie', 2),
('Woodson', 3),
('Alix', 4),
('Christophe', 5),
('Asmaâ', 6),
('Ayoub', 7),
('Zoé', 8),
('Marie', 9),
('Benoit', 10),
('Arnaud', 11),
('Julien', 12),
('Nicolas', 13);

-- --------------------------------------------------------

--
-- Structure de la table `wallet`
--

DROP TABLE IF EXISTS `wallet`;
CREATE TABLE `wallet` (
  `id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Vider la table avant d'insérer `wallet`
--

TRUNCATE TABLE `wallet`;
--
-- Contenu de la table `wallet`
--

INSERT INTO `wallet` (`id`, `name`, `user_id`) VALUES
(1, 'Yolo', 1),
(2, 'Serious', 1),
(3, 'Funny', 2),
(4, 'Young', 3),
(5, 'Old', 4),
(9, 'Great', 4),
(10, 'Nice', 5),
(11, 'Playful', 5),
(12, 'Nice', 6),
(13, 'Disgusted', 6),
(14, 'Purple', 6),
(15, 'Brown', 7),
(16, 'Blue', 8),
(17, 'Green', 9),
(18, 'Red', 10),
(19, 'Supportive ', 10),
(20, 'Involved', 11),
(21, 'Strong', 12),
(22, 'Yellow', 13),
(23, 'Enthusiastic', 13);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx` (`id`);

--
-- Index pour la table `wallet`
--
ALTER TABLE `wallet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `wallet`
--
ALTER TABLE `wallet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `wallet`
--
ALTER TABLE `wallet`
  ADD CONSTRAINT `wallet_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
SET FOREIGN_KEY_CHECKS=1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
