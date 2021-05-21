-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 21, 2021 at 02:28 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_pharmacy`
--

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `iddoctor` int(11) NOT NULL,
  `Person` int(11) NOT NULL,
  `INAMInbr` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Table structure for table `medicine`
--

CREATE TABLE `medicine` (
  `idmedicine` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Company` varchar(255) DEFAULT NULL,
  `Activesubstance` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `idpatient` int(11) NOT NULL,
  `Person` int(11) NOT NULL,
  `Agegroup` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `idperson` int(11) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `Lastname` varchar(255) NOT NULL,
  `BirthDay` date DEFAULT NULL,
  `Adress` varchar(255) DEFAULT NULL,
  `PhoneNumber` varchar(255) DEFAULT NULL,
  `EmailAdd` varchar(255) DEFAULT NULL,
  `NISS` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `person`
--

-- --------------------------------------------------------

--
-- Table structure for table `pharmacist`
--

CREATE TABLE `pharmacist` (
  `idpharmacist` int(11) NOT NULL,
  `Person` int(11) NOT NULL,
  `INAMInbr` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `prescription`
--

CREATE TABLE `prescription` (
  `idprescription` int(11) NOT NULL,
  `Patient` int(11) NOT NULL,
  `Doctor` int(11) NOT NULL,
  `Medicine` int(11) NOT NULL,
  `signature` text DEFAULT NULL,
  `DatePresc` date DEFAULT NULL,
  `DateDelivery` date DEFAULT NULL,
  `Dosage` int(255) DEFAULT NULL,
  `FreqAdministration` varchar(255) DEFAULT NULL,
  `Duration_of_Treatment` varchar(255) DEFAULT NULL,
  `Form_of_Administration` varchar(255) DEFAULT NULL,
  `Renewable` binary(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `iduser` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `last_login` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`iddoctor`),
  ADD KEY `Doctor_fk0` (`Person`);

--
-- Indexes for table `medicine`
--
ALTER TABLE `medicine`
  ADD PRIMARY KEY (`idmedicine`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`idpatient`),
  ADD KEY `Patient_fk0` (`Person`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`idperson`);

--
-- Indexes for table `pharmacist`
--
ALTER TABLE `pharmacist`
  ADD PRIMARY KEY (`idpharmacist`),
  ADD KEY `Pharmacist_fk0` (`Person`);

--
-- Indexes for table `prescription`
--
ALTER TABLE `prescription`
  ADD PRIMARY KEY (`idprescription`),
  ADD KEY `Prescription_fk0` (`Patient`),
  ADD KEY `Prescription_fk1` (`Doctor`),
  ADD KEY `Prescription_fk2` (`Medicine`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`iduser`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `iddoctor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `medicine`
--
ALTER TABLE `medicine`
  MODIFY `idmedicine` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `idpatient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `person`
--
ALTER TABLE `person`
  MODIFY `idperson` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `pharmacist`
--
ALTER TABLE `pharmacist`
  MODIFY `idpharmacist` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `prescription`
--
ALTER TABLE `prescription`
  MODIFY `idprescription` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `iduser` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `doctor`
--
ALTER TABLE `doctor`
  ADD CONSTRAINT `Doctor_fk0` FOREIGN KEY (`Person`) REFERENCES `person` (`idperson`);

--
-- Constraints for table `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `Patient_fk0` FOREIGN KEY (`Person`) REFERENCES `person` (`idperson`);

--
-- Constraints for table `pharmacist`
--
ALTER TABLE `pharmacist`
  ADD CONSTRAINT `Pharmacist_fk0` FOREIGN KEY (`Person`) REFERENCES `person` (`idperson`);

--
-- Constraints for table `prescription`
--
ALTER TABLE `prescription`
  ADD CONSTRAINT `Prescription_fk0` FOREIGN KEY (`Patient`) REFERENCES `patient` (`idpatient`),
  ADD CONSTRAINT `Prescription_fk1` FOREIGN KEY (`Doctor`) REFERENCES `doctor` (`iddoctor`),
  ADD CONSTRAINT `Prescription_fk2` FOREIGN KEY (`Medicine`) REFERENCES `medicine` (`idmedicine`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
