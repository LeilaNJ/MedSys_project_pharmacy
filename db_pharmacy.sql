-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 15 mai 2021 à 17:53
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `db_pharmacy`
--

-- --------------------------------------------------------

--
-- Structure de la table `doctor`
--

CREATE TABLE `doctor` (
  `iddoctor` int(11) NOT NULL,
  `Person` int(11) NOT NULL,
  `INAMInbr` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `medicine`
--

CREATE TABLE `medicine` (
  `idmedicine` int(11) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `Company` varchar(255) NOT NULL,
  `Activesubstance` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

CREATE TABLE `patient` (
  `idpatient` int(11) NOT NULL,
  `Person` int(11) NOT NULL,
  `Agegroup` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `person`
--

CREATE TABLE `person` (
  `idperson` int(11) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `Lastname` varchar(255) NOT NULL,
  `BirthDay` date NOT NULL,
  `Adress` varchar(255) NOT NULL,
  `PhoneNumber` varchar(255) NOT NULL,
  `EmailAdd` varchar(255) NOT NULL,
  `NISS` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `pharmacist`
--

CREATE TABLE `pharmacist` (
  `idpharmacist` int(11) NOT NULL,
  `Person` int(11) NOT NULL,
  `INAMInbr` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `prescription`
--

CREATE TABLE `prescription` (
  `idprescription` int(11) NOT NULL,
  `Patient` int(11) NOT NULL,
  `Doctor` int(11) NOT NULL,
  `Medicine` int(11) NOT NULL,
  `signature` text NOT NULL,
  `DatePresc` date NOT NULL,
  `DateDelivery` date NOT NULL,
  `Dosage` int(255) NOT NULL,
  `FreqAdministration` varchar(255) NOT NULL,
  `Duration of Treatment` varchar(255) NOT NULL,
  `Form of Administration` varchar(255) NOT NULL,
  `Renewable` binary(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `iduser` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `last_login` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`iddoctor`),
  ADD KEY `Doctor_fk0` (`Person`);

--
-- Index pour la table `medicine`
--
ALTER TABLE `medicine`
  ADD PRIMARY KEY (`idmedicine`);

--
-- Index pour la table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`idpatient`),
  ADD KEY `Patient_fk0` (`Person`);

--
-- Index pour la table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`idperson`);

--
-- Index pour la table `pharmacist`
--
ALTER TABLE `pharmacist`
  ADD PRIMARY KEY (`idpharmacist`),
  ADD KEY `Pharmacist_fk0` (`Person`);

--
-- Index pour la table `prescription`
--
ALTER TABLE `prescription`
  ADD PRIMARY KEY (`idprescription`),
  ADD KEY `Prescription_fk0` (`Patient`),
  ADD KEY `Prescription_fk1` (`Doctor`),
  ADD KEY `Prescription_fk2` (`Medicine`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`iduser`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `iddoctor` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `medicine`
--
ALTER TABLE `medicine`
  MODIFY `idmedicine` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `patient`
--
ALTER TABLE `patient`
  MODIFY `idpatient` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `person`
--
ALTER TABLE `person`
  MODIFY `idperson` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `pharmacist`
--
ALTER TABLE `pharmacist`
  MODIFY `idpharmacist` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `prescription`
--
ALTER TABLE `prescription`
  MODIFY `idprescription` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `iduser` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `doctor`
--
ALTER TABLE `doctor`
  ADD CONSTRAINT `Doctor_fk0` FOREIGN KEY (`Person`) REFERENCES `person` (`idperson`);

--
-- Contraintes pour la table `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `Patient_fk0` FOREIGN KEY (`Person`) REFERENCES `person` (`idperson`);

--
-- Contraintes pour la table `pharmacist`
--
ALTER TABLE `pharmacist`
  ADD CONSTRAINT `Pharmacist_fk0` FOREIGN KEY (`Person`) REFERENCES `person` (`idperson`);

--
-- Contraintes pour la table `prescription`
--
ALTER TABLE `prescription`
  ADD CONSTRAINT `Prescription_fk0` FOREIGN KEY (`Patient`) REFERENCES `patient` (`idpatient`),
  ADD CONSTRAINT `Prescription_fk1` FOREIGN KEY (`Doctor`) REFERENCES `doctor` (`iddoctor`),
  ADD CONSTRAINT `Prescription_fk2` FOREIGN KEY (`Medicine`) REFERENCES `medicine` (`idmedicine`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
