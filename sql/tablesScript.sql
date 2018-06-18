-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.1.26-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win32
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table enis.historie
DROP TABLE IF EXISTS `historie`;
CREATE TABLE IF NOT EXISTS `historie` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UZIVATEL` int(11) DEFAULT NULL,
  `OPERACE` varchar(50) COLLATE utf8_czech_ci DEFAULT NULL,
  `DATUMZMENY` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

-- Data exporting was unselected.
-- Dumping structure for table enis.loznerozmery
DROP TABLE IF EXISTS `loznerozmery`;
CREATE TABLE IF NOT EXISTS `loznerozmery` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOZNADELKA` int(11) DEFAULT NULL,
  `LOZNAPLOCHA` int(11) DEFAULT NULL,
  `LOZNYPROSTOR` int(11) DEFAULT NULL,
  `VYSKALOZNEPLOCHYPRVOZU` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

-- Data exporting was unselected.
-- Dumping structure for table enis.podvozek
DROP TABLE IF EXISTS `podvozek`;
CREATE TABLE IF NOT EXISTS `podvozek` (
  `ID` int(11) NOT NULL,
  `VZDALOTOCCEPUPODVOZKU` int(11) DEFAULT NULL,
  `ROZVORPODVOZKU` int(11) DEFAULT NULL,
  `UNOSNOSTKONSTRUKCNI` int(11) DEFAULT NULL,
  `POCETPODVOZU` int(11) DEFAULT NULL,
  `POCETCLANKU` int(11) DEFAULT NULL,
  `POCETNAPRAV` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

-- Data exporting was unselected.
-- Dumping structure for table enis.revize
DROP TABLE IF EXISTS `revize`;
CREATE TABLE IF NOT EXISTS `revize` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATUMPOSREVIZE` date DEFAULT NULL,
  `REVIZLHUTA` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

-- Data exporting was unselected.
-- Dumping structure for table enis.role
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAZEV` varchar(50) COLLATE utf8_czech_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

-- Data exporting was unselected.
-- Dumping structure for table enis.rozmery
DROP TABLE IF EXISTS `rozmery`;
CREATE TABLE IF NOT EXISTS `rozmery` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DELKAPRESNARAZNIKY` int(11) DEFAULT NULL,
  `ID_LOZNEROZMERY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ROZMERY_LOZNEROZMERY` (`ID_LOZNEROZMERY`),
  CONSTRAINT `FK_ROZMERY_LOZNEROZMERY` FOREIGN KEY (`ID_LOZNEROZMERY`) REFERENCES `loznerozmery` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

-- Data exporting was unselected.
-- Dumping structure for table enis.rucnibrzda
DROP TABLE IF EXISTS `rucnibrzda`;
CREATE TABLE IF NOT EXISTS `rucnibrzda` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DRUHRUCNIBRZDY` int(11) DEFAULT NULL,
  `BRZDICIHMOTNOSTRUCNIBRZDY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

-- Data exporting was unselected.
-- Dumping structure for table enis.stavkm
DROP TABLE IF EXISTS `stavkm`;
CREATE TABLE IF NOT EXISTS `stavkm` (
  `ID` int(11) NOT NULL,
  `NAJETKM` int(11) DEFAULT NULL,
  `ZBYVAJICIKM` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

-- Data exporting was unselected.
-- Dumping structure for table enis.uzivatel
DROP TABLE IF EXISTS `uzivatel`;
CREATE TABLE IF NOT EXISTS `uzivatel` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `JMENO` int(11) DEFAULT NULL,
  `HESLO` int(11) DEFAULT NULL,
  `ID_ROLE` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `FK_UZIVATEL_ROLE` (`ID_ROLE`),
  CONSTRAINT `FK_UZIVATEL_ROLE` FOREIGN KEY (`ID_ROLE`) REFERENCES `role` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

-- Data exporting was unselected.
-- Dumping structure for table enis.vuz
CREATE TABLE `vuz` (
	`ID` INT(11) NOT NULL AUTO_INCREMENT,
	`VUZCIS` INT(11) NOT NULL DEFAULT '0',
	`MAXRYCHLOST` INT(11) NOT NULL DEFAULT '0',
	`VLASTNIHMOTNOST` INT(11) NOT NULL DEFAULT '0',
	`ID_PODVOZKU` INT(11) NOT NULL DEFAULT '0',
	`ID_REVIZE` INT(11) NOT NULL DEFAULT '0',
	`ID_ROZMERY` INT(11) NOT NULL DEFAULT '0',
	`ID_RUCNIBRZDA` INT(11) NOT NULL DEFAULT '0',
	`ID_VZDUCHOVABRZDA` INT(11) NOT NULL DEFAULT '0',
	PRIMARY KEY (`ID`),
	INDEX `FK_VUZ_PODVOZEK` (`ID_PODVOZKU`),
	INDEX `FK_VUZ_REVIZE` (`ID_REVIZE`),
	INDEX `FK_VUZ_ROZMERY` (`ID_ROZMERY`),
	INDEX `FK_VUZ_RUCNIBRZDA` (`ID_RUCNIBRZDA`),
	INDEX `FK_VUZ_VZDUCHOVABRZDA` (`ID_VZDUCHOVABRZDA`),
	CONSTRAINT `FK_VUZ_PODVOZEK` FOREIGN KEY (`ID_PODVOZKU`) REFERENCES `podvozek` (`ID`),
	CONSTRAINT `FK_VUZ_REVIZE` FOREIGN KEY (`ID_REVIZE`) REFERENCES `revize` (`ID`),
	CONSTRAINT `FK_VUZ_ROZMERY` FOREIGN KEY (`ID_ROZMERY`) REFERENCES `rozmery` (`ID`),
	CONSTRAINT `FK_VUZ_RUCNIBRZDA` FOREIGN KEY (`ID_RUCNIBRZDA`) REFERENCES `rucnibrzda` (`ID`),
	CONSTRAINT `FK_VUZ_VZDUCHOVABRZDA` FOREIGN KEY (`ID_VZDUCHOVABRZDA`) REFERENCES `vzduchovabrzda` (`ID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

-- Data exporting was unselected.
-- Dumping structure for table enis.vzduchovabrzda
DROP TABLE IF EXISTS `vzduchovabrzda`;
CREATE TABLE IF NOT EXISTS `vzduchovabrzda` (
  `ID` int(11) NOT NULL,
  `DRUHVZDUCHOVEBRZDY` int(11) DEFAULT NULL,
  `BRZDICIHMOTNOSTVZDUCHBRZDY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
