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

-- Dumping data for table enisapp.uzivatel: ~4 rows (approximately)
/*!40000 ALTER TABLE `uzivatel` DISABLE KEYS */;
REPLACE INTO `uzivatel` (`id`, `heslo`, `jmeno`, `role_id`) VALUES
	(1, '$2a$10$dCxG/RVc1DzSuqNzrrANSucKzNoGrSXOC8Cx7ZVrX7gMKA7pZOhKa', 'test', 1),
	(2, '$2a$10$aBHXQ7yL0KCJ.FjrLWZ5W.z1lF.E7VmEELwJV7wJaTlTR1IW.h5AK', 'test1', 2),
	(3, '$2a$10$FWb2oZklZXA40WGgICD4Buy8xrR9gklVMBip4.Abeb.S0o4eaBmt6', 'admin', 1),
	(4, '$2a$10$X357Fwwh3tjvF3MeD8dw6ueuM2R2l2wDCTGAJWnVrLhMbmDhQ.FMe', 'gestor', 2);
/*!40000 ALTER TABLE `uzivatel` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
