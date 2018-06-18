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

-- Dumping data for table enisapp.ciselnik_rucni_brzda: ~3 rows (approximately)
/*!40000 ALTER TABLE `ciselnik_rucni_brzda` DISABLE KEYS */;
REPLACE INTO `ciselnik_rucni_brzda` (`id`, `druh`) VALUES
	(0, 'bez rucní brzdy nebo vadná rucní brzda'),
	(1, 'rucní brzda obsluhovatelná ze zeme\r\n'),
	(2, 'rucní brzda obsluhovatelná z brzdarského stanoviste\r\n');
/*!40000 ALTER TABLE `ciselnik_rucni_brzda` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
