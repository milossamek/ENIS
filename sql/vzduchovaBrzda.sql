-- --------------------------------------------------------
-- Host:                         enis.folpc.cz
-- Server version:               5.5.57-0+deb8u1 - (Raspbian)
-- Server OS:                    debian-linux-gnu
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping data for table enisapp.ciselnik_vzduchova_brzda: ~6 rows (approximately)
/*!40000 ALTER TABLE `ciselnik_vzduchova_brzda` DISABLE KEYS */;
REPLACE INTO `ciselnik_vzduchova_brzda` (`id`, `druh`) VALUES
	(0, 'Prubežné brzdové potrubí'),
	(1, 'Systém G (pouze nákladní)'),
	(2, 'Systém P (pouze osobní)'),
	(3, 'Smíšený systém G / P'),
	(8, 'Bez brzdy a bez brzdového potrubí'),
	(9, 'Nekódovaný systém');
/*!40000 ALTER TABLE `ciselnik_vzduchova_brzda` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
