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
-- Current Database: `bazy`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `bazy` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bazy`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(80) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password` char(41) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'pawel33317@gmail.com','pawel33317','haslo01k'),(2,'marian@wp.pl','marian','mariano'),(3,'xxx@xx.xx','Laluś','password'),(4,'xxx@xx.xx','Laluś','password'),(5,'xxx@xx.xx','Laluś','password'),(6,'xxx@xx.xx','Laluś','password'),(7,'xxx@xx.xx','Laluś','password'),(8,'xxx@xx.xx','Laluś','password'),(9,'xxx@xx.xx','Laluś','password'),(10,'xxx@xx.xx','Laluś','password');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `nbbazy`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `nbbazy` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `nbbazy`;

--
-- Table structure for table `autorzy`
--

DROP TABLE IF EXISTS `autorzy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `autorzy` (
  `idautora` int(11) NOT NULL AUTO_INCREMENT,
  `nazwisko` varchar(50) NOT NULL,
  `imie` varchar(30) NOT NULL,
  `uwagi` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idautora`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autorzy`
--

LOCK TABLES `autorzy` WRITE;
/*!40000 ALTER TABLE `autorzy` DISABLE KEYS */;
INSERT INTO `autorzy` VALUES (1,'Sienkiewicz','Henryk',NULL),(2,'Kowalski','Jan',NULL),(3,'Kowalskiego','Artur',NULL);
/*!40000 ALTER TABLE `autorzy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `czytelnicy`
--

DROP TABLE IF EXISTS `czytelnicy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `czytelnicy` (
  `NRkarty` int(11) NOT NULL AUTO_INCREMENT,
  `Nazwisko` varchar(50) NOT NULL,
  `Imie` varchar(25) NOT NULL,
  `Iddokumentu` varchar(30) DEFAULT NULL,
  `NRdokumentu` varchar(15) DEFAULT NULL,
  `DataUr` varchar(15) DEFAULT NULL,
  `MiejsceUr` varchar(15) DEFAULT NULL,
  `Kod` varchar(15) DEFAULT NULL,
  `Miasto` varchar(15) DEFAULT NULL,
  `IDulicy` varchar(15) DEFAULT NULL,
  `Ulica` varchar(70) DEFAULT NULL,
  `telefon` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`NRkarty`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `czytelnicy`
--

LOCK TABLES `czytelnicy` WRITE;
/*!40000 ALTER TABLE `czytelnicy` DISABLE KEYS */;
INSERT INTO `czytelnicy` VALUES (2,'Frankowski','Adam',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'Czubak','Pawel',NULL,NULL,NULL,NULL,NULL,'Labanada',NULL,NULL,NULL),(4,'Nowacki','Wiktor',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'Nowacki','Wiktor',NULL,NULL,NULL,NULL,NULL,'W-wa',NULL,NULL,NULL);
/*!40000 ALTER TABLE `czytelnicy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `egzemplarze`
--

DROP TABLE IF EXISTS `egzemplarze`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `egzemplarze` (
  `IDegzemplarza` int(11) NOT NULL AUTO_INCREMENT,
  `IDpublikacji` int(11) NOT NULL,
  `Ubytki` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`IDegzemplarza`),
  KEY `FK_egzemp_publ` (`IDpublikacji`),
  CONSTRAINT `FK_egzemp_publ` FOREIGN KEY (`IDpublikacji`) REFERENCES `publikacje` (`idpublikacji`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `egzemplarze`
--

LOCK TABLES `egzemplarze` WRITE;
/*!40000 ALTER TABLE `egzemplarze` DISABLE KEYS */;
INSERT INTO `egzemplarze` VALUES (1,1,'N'),(2,1,'N'),(3,1,'N'),(4,2,'N'),(5,2,'N'),(6,2,'N'),(7,3,'N'),(8,3,'N'),(9,3,'N');
/*!40000 ALTER TABLE `egzemplarze` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kategorie`
--

DROP TABLE IF EXISTS `kategorie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kategorie` (
  `idkategorii` int(11) NOT NULL AUTO_INCREMENT,
  `kategoria` varchar(50) NOT NULL,
  PRIMARY KEY (`idkategorii`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kategorie`
--

LOCK TABLES `kategorie` WRITE;
/*!40000 ALTER TABLE `kategorie` DISABLE KEYS */;
INSERT INTO `kategorie` VALUES (1,'Proza'),(2,'Informatyka');
/*!40000 ALTER TABLE `kategorie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publikacje`
--

DROP TABLE IF EXISTS `publikacje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publikacje` (
  `idpublikacji` int(11) NOT NULL AUTO_INCREMENT,
  `idautora` int(11) NOT NULL,
  `idkategorii` int(11) NOT NULL,
  `tytul` varchar(250) NOT NULL,
  `idwydawcy` int(11) NOT NULL,
  `rok` char(4) DEFAULT NULL,
  `miejsce` varchar(50) DEFAULT NULL,
  `idszafki` int(11) NOT NULL,
  `slowa_kluczowe` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idpublikacji`),
  KEY `kl_obcy_publ_aut` (`idautora`),
  KEY `kl_obcy_publ_szaf` (`idszafki`),
  KEY `kl_obcy_publ_kat` (`idwydawcy`),
  KEY `kl_for_pub_kat` (`idkategorii`),
  CONSTRAINT `kl_for_pub_kat` FOREIGN KEY (`idkategorii`) REFERENCES `kategorie` (`idkategorii`),
  CONSTRAINT `kl_obcy_publ_aut` FOREIGN KEY (`idautora`) REFERENCES `autorzy` (`idautora`),
  CONSTRAINT `kl_obcy_publ_kat` FOREIGN KEY (`idwydawcy`) REFERENCES `wydawca` (`idwydawcy`),
  CONSTRAINT `kl_obcy_publ_szaf` FOREIGN KEY (`idszafki`) REFERENCES `szafki` (`idszafki`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publikacje`
--

LOCK TABLES `publikacje` WRITE;
/*!40000 ALTER TABLE `publikacje` DISABLE KEYS */;
INSERT INTO `publikacje` VALUES (1,1,2,'Ogniem i mieczem',2,'2010','Łódź',3,NULL),(2,2,1,'Dziady',2,'2010','Łódź',2,NULL),(3,3,1,'Bazy danych',3,'1997','Łódź',1,NULL);
/*!40000 ALTER TABLE `publikacje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `szafki`
--

DROP TABLE IF EXISTS `szafki`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `szafki` (
  `idszafki` int(11) NOT NULL AUTO_INCREMENT,
  `szafka` varchar(50) NOT NULL,
  PRIMARY KEY (`idszafki`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `szafki`
--

LOCK TABLES `szafki` WRITE;
/*!40000 ALTER TABLE `szafki` DISABLE KEYS */;
INSERT INTO `szafki` VALUES (1,'A-c'),(2,'C-f'),(3,'G-x');
/*!40000 ALTER TABLE `szafki` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wydawca`
--

DROP TABLE IF EXISTS `wydawca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wydawca` (
  `idwydawcy` int(11) NOT NULL AUTO_INCREMENT,
  `wydawca` varchar(50) NOT NULL,
  `nazwa` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idwydawcy`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wydawca`
--

LOCK TABLES `wydawca` WRITE;
/*!40000 ALTER TABLE `wydawca` DISABLE KEYS */;
INSERT INTO `wydawca` VALUES (1,'PWN',NULL),(2,'Fabryka słów',NULL),(3,'WNT',NULL);
/*!40000 ALTER TABLE `wydawca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wypozyczenia`
--

DROP TABLE IF EXISTS `wypozyczenia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wypozyczenia` (
  `NRkarty` int(11) NOT NULL,
  `IDegzemplarza` int(11) NOT NULL,
  `Data_Wyp` datetime NOT NULL,
  `Data_Przed` datetime DEFAULT NULL,
  `Dara_Zwrot` datetime DEFAULT NULL,
  `IDwypozyczenia` int(11) DEFAULT NULL,
  `wypozyczeniacol` int(11) NOT NULL AUTO_INCREMENT,
  `odebrano` int(2) DEFAULT NULL,
  PRIMARY KEY (`wypozyczeniacol`),
  KEY `FK_Wyp_Egzemp` (`IDegzemplarza`),
  KEY `kl_obcy_wyp_czyt` (`NRkarty`),
  CONSTRAINT `FK_Wyp_Egzemp` FOREIGN KEY (`IDegzemplarza`) REFERENCES `egzemplarze` (`IDegzemplarza`),
  CONSTRAINT `kl_obcy_wyp_czyt` FOREIGN KEY (`NRkarty`) REFERENCES `czytelnicy` (`NRkarty`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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

-- Dump completed on 2016-01-29 15:39:34
