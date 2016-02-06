-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: bazy
-- ------------------------------------------------------
-- Server version	5.7.9-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'pawel33317@gmail.com','pawel33317','haslo01k'),(2,'marian@wp.pl','marian','mariano'),(3,'xxx@xx.xx','Laluś','password'),(4,'xxx@xx.xx','Laluś','password'),(5,'xxx@xx.xx','Laluś','password'),(6,'xxx@xx.xx','Laluś','password'),(7,'xxx@xx.xx','Laluś','password'),(8,'xxx@xx.xx','Laluś','password'),(9,'xxx@xx.xx','Laluś','password'),(10,'xxx@xx.xx','Laluś','password');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-29 15:38:00
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: nbbazy
-- ------------------------------------------------------
-- Server version	5.7.9-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `autorzy`
--

LOCK TABLES `autorzy` WRITE;
/*!40000 ALTER TABLE `autorzy` DISABLE KEYS */;
INSERT INTO `autorzy` VALUES (1,'Sienkiewicz','Henryk',NULL),(2,'Kowalski','Jan',NULL),(3,'Kowalskiego','Artur',NULL);
/*!40000 ALTER TABLE `autorzy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `czytelnicy`
--

LOCK TABLES `czytelnicy` WRITE;
/*!40000 ALTER TABLE `czytelnicy` DISABLE KEYS */;
INSERT INTO `czytelnicy` VALUES (2,'Frankowski','Adam',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'Czubak','Pawel',NULL,NULL,NULL,NULL,NULL,'Labanada',NULL,NULL,NULL),(4,'Nowacki','Wiktor',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'Nowacki','Wiktor',NULL,NULL,NULL,NULL,NULL,'W-wa',NULL,NULL,NULL);
/*!40000 ALTER TABLE `czytelnicy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `egzemplarze`
--

LOCK TABLES `egzemplarze` WRITE;
/*!40000 ALTER TABLE `egzemplarze` DISABLE KEYS */;
INSERT INTO `egzemplarze` VALUES (1,1,'N'),(2,1,'N'),(3,1,'N'),(4,2,'N'),(5,2,'N'),(6,2,'N'),(7,3,'N'),(8,3,'N'),(9,3,'N');
/*!40000 ALTER TABLE `egzemplarze` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `kategorie`
--

LOCK TABLES `kategorie` WRITE;
/*!40000 ALTER TABLE `kategorie` DISABLE KEYS */;
INSERT INTO `kategorie` VALUES (1,'Proza'),(2,'Informatyka');
/*!40000 ALTER TABLE `kategorie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `publikacje`
--

LOCK TABLES `publikacje` WRITE;
/*!40000 ALTER TABLE `publikacje` DISABLE KEYS */;
INSERT INTO `publikacje` VALUES (1,1,2,'Ogniem i mieczem',2,'2010','Łódź',3,NULL),(2,2,1,'Dziady',2,'2010','Łódź',2,NULL),(3,3,1,'Bazy danych',3,'1997','Łódź',1,NULL);
/*!40000 ALTER TABLE `publikacje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `szafki`
--

LOCK TABLES `szafki` WRITE;
/*!40000 ALTER TABLE `szafki` DISABLE KEYS */;
INSERT INTO `szafki` VALUES (1,'A-c'),(2,'C-f'),(3,'G-x');
/*!40000 ALTER TABLE `szafki` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `wydawca`
--

LOCK TABLES `wydawca` WRITE;
/*!40000 ALTER TABLE `wydawca` DISABLE KEYS */;
INSERT INTO `wydawca` VALUES (1,'PWN',NULL),(2,'Fabryka słów',NULL),(3,'WNT',NULL);
/*!40000 ALTER TABLE `wydawca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `wypozyczenia`
--

LOCK TABLES `wypozyczenia` WRITE;
/*!40000 ALTER TABLE `wypozyczenia` DISABLE KEYS */;
INSERT INTO `wypozyczenia` VALUES (3,4,'2016-01-29 08:43:54',NULL,NULL,NULL,79,1);
/*!40000 ALTER TABLE `wypozyczenia` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-29 15:38:00
