-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: progetto
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accessoria`
--

DROP TABLE IF EXISTS `accessoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accessoria` (
  `nomeCat` varchar(45) NOT NULL,
  `destinazioneUso` varchar(45) NOT NULL,
  `applicabilitàMul` tinyint(1) NOT NULL,
  PRIMARY KEY (`nomeCat`),
  UNIQUE KEY `nomeCat_UNIQUE` (`nomeCat`),
  CONSTRAINT `accessoria_chk_1` CHECK (((`applicabilitàMul` >= 0) and (`applicabilitàMul` <= 1)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `affido`
--

DROP TABLE IF EXISTS `affido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `affido` (
  `seriale_macch_corr` int NOT NULL,
  `cf_corr` varchar(45) NOT NULL DEFAULT 'non affidato',
  PRIMARY KEY (`seriale_macch_corr`,`cf_corr`),
  KEY `cf_corr_idx` (`cf_corr`),
  CONSTRAINT `seriale_macch_corr` FOREIGN KEY (`seriale_macch_corr`) REFERENCES `macchinario` (`seriale`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `associazione`
--

DROP TABLE IF EXISTS `associazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `associazione` (
  `nomeCat_base` varchar(45) NOT NULL DEFAULT 'base',
  `nomeCat_acc` varchar(45) NOT NULL DEFAULT 'accessoria',
  PRIMARY KEY (`nomeCat_base`,`nomeCat_acc`),
  KEY `nomeCat_base_idx` (`nomeCat_base`),
  KEY `nomeCat_acc_idx` (`nomeCat_acc`),
  CONSTRAINT `nomeCat_acc` FOREIGN KEY (`nomeCat_acc`) REFERENCES `accessoria` (`nomeCat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `nomeCat_base` FOREIGN KEY (`nomeCat_base`) REFERENCES `base` (`nomeCat`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `base`
--

DROP TABLE IF EXISTS `base`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `base` (
  `nomeCat` varchar(45) NOT NULL DEFAULT 'base',
  `destinazioneUso` varchar(45) NOT NULL,
  `lunghezza` int NOT NULL,
  `larghezza` int NOT NULL,
  `altezza` int NOT NULL,
  `trattamenti` varchar(45) NOT NULL,
  PRIMARY KEY (`nomeCat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  `cf` varchar(45) NOT NULL,
  `numAcquisti` int NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`cf`),
  UNIQUE KEY `telefono_UNIQUE` (`telefono`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  CONSTRAINT `cliente_chk_1` CHECK ((`email` like _utf8mb4'%_@_%._%')),
  CONSTRAINT `cliente_chk_2` CHECK (regexp_like(`cf`,_utf8mb4'[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]')),
  CONSTRAINT `cliente_chk_3` CHECK (regexp_like(`telefono`,_utf8mb4'[0-9]{10}'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `coinvolgimento`
--

DROP TABLE IF EXISTS `coinvolgimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coinvolgimento` (
  `matricola_dip_coinv` varchar(4) NOT NULL,
  `num_intervento` int NOT NULL,
  `seriale_macc_coinv` int NOT NULL,
  `dataInizio` date NOT NULL,
  `dataFine` date DEFAULT NULL,
  `ore` int DEFAULT NULL,
  PRIMARY KEY (`matricola_dip_coinv`,`num_intervento`,`seriale_macc_coinv`),
  KEY `seriale_macc_coinv_idx` (`seriale_macc_coinv`),
  KEY `matricola_dip_coinv_idx` (`matricola_dip_coinv`),
  KEY `num_intervento_idx` (`num_intervento`),
  CONSTRAINT `matricola_dip_coinv` FOREIGN KEY (`matricola_dip_coinv`) REFERENCES `dipendente` (`matricola`) ON UPDATE CASCADE,
  CONSTRAINT `num_intervento` FOREIGN KEY (`num_intervento`) REFERENCES `intervento` (`progressivo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `seriale_macc_coinv` FOREIGN KEY (`seriale_macc_coinv`) REFERENCES `macchinario` (`seriale`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `coinvolgimento_chk_1` CHECK ((`dataFine` >= `dataInizio`))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `corriere`
--

DROP TABLE IF EXISTS `corriere`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `corriere` (
  `cf` varchar(45) NOT NULL,
  `targa` varchar(7) NOT NULL,
  `società` varchar(45) NOT NULL,
  `dataPrimoImp` date NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  PRIMARY KEY (`cf`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `telefono_UNIQUE` (`telefono`),
  CONSTRAINT `corriere_chk_1` CHECK (regexp_like(`cf`,_utf8mb4'[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]')),
  CONSTRAINT `corriere_chk_2` CHECK (regexp_like(`telefono`,_utf8mb4'[0-9]{10}')),
  CONSTRAINT `corriere_chk_3` CHECK (regexp_like(`targa`,_utf8mb4'[A-Z]{2}[0-9]{3}[A-Z]{2}')),
  CONSTRAINT `corriere_chk_4` CHECK ((`email` like _utf8mb4'%_@_%._%'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dipendente`
--

DROP TABLE IF EXISTS `dipendente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dipendente` (
  `matricola` varchar(4) NOT NULL,
  `tipoContratto` varchar(45) NOT NULL,
  `alboProfessionale` varchar(45) DEFAULT NULL,
  `oreManutenzione` int DEFAULT NULL,
  `specializzazione` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`matricola`),
  CONSTRAINT `dipendente_chk_2` CHECK (regexp_like(`matricola`,_utf8mb4'[0-9]{4}'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `intervento`
--

DROP TABLE IF EXISTS `intervento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `intervento` (
  `progressivo` int NOT NULL AUTO_INCREMENT,
  `seriale_macchinario` int NOT NULL,
  `sostituzione` tinyint(1) NOT NULL DEFAULT '0',
  `stato` varchar(45) DEFAULT NULL,
  `dataArrivo` date NOT NULL,
  `dataFine` date DEFAULT NULL,
  PRIMARY KEY (`progressivo`),
  KEY `seriale_macchinario_idx` (`seriale_macchinario`),
  CONSTRAINT `seriale_macchinario` FOREIGN KEY (`seriale_macchinario`) REFERENCES `macchinario` (`seriale`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `intervento_chk_1` CHECK ((`dataFine` >= `dataArrivo`))
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `macchinario`
--

DROP TABLE IF EXISTS `macchinario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `macchinario` (
  `seriale` int NOT NULL,
  `prezzo` float NOT NULL,
  `numAccessori` int DEFAULT NULL,
  `lotto` int NOT NULL,
  `valutazione` int NOT NULL DEFAULT '100',
  `problematiche` varchar(45) NOT NULL DEFAULT 'nessuna',
  `descrizione` varchar(45) NOT NULL,
  `cf_cliente` varchar(45) DEFAULT NULL,
  `isBase` varchar(45) DEFAULT NULL,
  `isAccessoria` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`seriale`),
  KEY `cf_cliente_idx` (`cf_cliente`),
  KEY `isBase_idx` (`isBase`),
  KEY `isAccessoria_idx` (`isAccessoria`),
  CONSTRAINT `cf_cliente` FOREIGN KEY (`cf_cliente`) REFERENCES `cliente` (`cf`),
  CONSTRAINT `isAccessoria` FOREIGN KEY (`isAccessoria`) REFERENCES `accessoria` (`nomeCat`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `isBase` FOREIGN KEY (`isBase`) REFERENCES `base` (`nomeCat`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `macchinario_chk_1` CHECK (((`valutazione` >= 1) and (`valutazione` <= 100)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `partecipazione`
--

DROP TABLE IF EXISTS `partecipazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partecipazione` (
  `id_prog` int NOT NULL,
  `matricola_dip_part` varchar(4) NOT NULL,
  PRIMARY KEY (`id_prog`,`matricola_dip_part`),
  KEY `matricola_dip_part_idx` (`matricola_dip_part`),
  CONSTRAINT `id_prog` FOREIGN KEY (`id_prog`) REFERENCES `progetto` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `matricola_dip_part` FOREIGN KEY (`matricola_dip_part`) REFERENCES `dipendente` (`matricola`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `progetto`
--

DROP TABLE IF EXISTS `progetto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `progetto` (
  `ID` int NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `seriale_macc_prog` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `seriale_macc_prog_idx` (`seriale_macc_prog`),
  CONSTRAINT `seriale_macc_prog` FOREIGN KEY (`seriale_macc_prog`) REFERENCES `macchinario` (`seriale`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `schedadipendente`
--

DROP TABLE IF EXISTS `schedadipendente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedadipendente` (
  `cf` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `matricola_dip` varchar(4) NOT NULL,
  PRIMARY KEY (`matricola_dip`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `telefono_UNIQUE` (`telefono`),
  CONSTRAINT `matricola_dip` FOREIGN KEY (`matricola_dip`) REFERENCES `dipendente` (`matricola`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `schedadipendente_chk_1` CHECK (regexp_like(`cf`,_utf8mb4'[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]')),
  CONSTRAINT `schedadipendente_chk_2` CHECK (regexp_like(`telefono`,_utf8mb4'[0-9]{10}')),
  CONSTRAINT `schedadipendente_chk_3` CHECK ((`email` like _utf8mb4'%_@_%._%'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-09 19:08:17
