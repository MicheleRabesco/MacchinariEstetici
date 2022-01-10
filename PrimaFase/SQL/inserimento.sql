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
-- Dumping data for table `accessoria`
--

LOCK TABLES `accessoria` WRITE;
/*!40000 ALTER TABLE `accessoria` DISABLE KEYS */;
INSERT INTO `accessoria` VALUES ('Cromo-light','professionale',0),('Facial Tip','professionale',1),('Lampada LED al collagene','professionale',1),('Manico Rullo Vacuum a infrarossi RF','professionale',0),('Manico Vacuum Facciale RF','professionale',0);
/*!40000 ALTER TABLE `accessoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `affido`
--

LOCK TABLES `affido` WRITE;
/*!40000 ALTER TABLE `affido` DISABLE KEYS */;
INSERT INTO `affido` VALUES (2,'AAAAAA00A01A000A'),(8,'AAAAAA00A01A000A'),(10,'AAAAAA00A01A000A'),(11,'AAAAAA00A01A000A'),(5,'AAAAAB00A01A000A'),(12,'AAAAAB00A01A000A'),(13,'AAAAAB00A01A000A'),(1,'non affidato'),(3,'non affidato'),(4,'non affidato'),(6,'non affidato'),(7,'non affidato'),(9,'non affidato');
/*!40000 ALTER TABLE `affido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `associazione`
--

LOCK TABLES `associazione` WRITE;
/*!40000 ALTER TABLE `associazione` DISABLE KEYS */;
INSERT INTO `associazione` VALUES ('Be active Spa','Cromo-light'),('Be active Spa','Lampada LED al collagene'),('Laser Diodo Portatile','Facial Tip'),('V-Shape','Manico Rullo Vacuum a infrarossi RF'),('V-Shape','Manico Vacuum Facciale RF');
/*!40000 ALTER TABLE `associazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `base`
--

LOCK TABLES `base` WRITE;
/*!40000 ALTER TABLE `base` DISABLE KEYS */;
INSERT INTO `base` VALUES ('Be active Spa','professionale',2000,740,500,'cute'),('EM-shape','professionale',545,486,1040,'grasso'),('Laser Diodo Portatile','professionale',400,530,440,'cute'),('V-Shape','professionale',532,537,1130,'grasso');
/*!40000 ALTER TABLE `base` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('Alex','Bove','BVOLXA00S19F839V',2,'3392671067','bove1900@gmail.com'),('Andrea','Aceto','CTANDR00E25H703O',3,'3342002177','a.aceto6@studenti.unisa.it'),('Rossella ','Ferrara','FRRRSL03C68A944Q',4,'3662231176','r.ferrara03@gmail.com'),('Francesca','Parente','PRNFNC03C71H703K',2,'3283888330','francescaparente03@gmail.com'),('Edoardo','Privato','PRVDRD00B33H703K',1,'3331698776','eddy.privato@libero.it'),('Michele','Rabesco','RBSMHL98R16A509S',2,'3338651333','m.rabesco@studenti.unisa.it');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `coinvolgimento`
--

LOCK TABLES `coinvolgimento` WRITE;
/*!40000 ALTER TABLE `coinvolgimento` DISABLE KEYS */;
INSERT INTO `coinvolgimento` VALUES ('0001',2,7,'2020-12-08','2020-12-10',16),('0001',3,9,'2020-12-09',NULL,NULL),('0001',10,6,'2022-01-08',NULL,NULL),('0002',1,3,'2020-12-08','2020-12-08',3),('0002',3,9,'2020-12-09',NULL,NULL),('0003',1,9,'2020-12-09',NULL,NULL),('0003',2,9,'2020-12-09',NULL,NULL),('0003',3,9,'2020-12-09',NULL,NULL),('0004',1,3,'2020-12-08','2020-12-08',3),('0004',2,7,'2020-12-08','2020-12-10',16),('0004',3,9,'2020-12-09',NULL,NULL),('1001',4,1,'2020-12-10',NULL,NULL);
/*!40000 ALTER TABLE `coinvolgimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `corriere`
--

LOCK TABLES `corriere` WRITE;
/*!40000 ALTER TABLE `corriere` DISABLE KEYS */;
INSERT INTO `corriere` VALUES ('AAAAAA00A01A000A','AA000BB','DHL','2018-01-01','Mario','Rossi','m.rossi@gmail.com','0000000000'),('AAAAAB00A01A000A','AA000AB','Bartolini','2020-06-30','Bruno','Bruni','b.bruni@gmail.com','0000000001');
/*!40000 ALTER TABLE `corriere` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dipendente`
--

LOCK TABLES `dipendente` WRITE;
/*!40000 ALTER TABLE `dipendente` DISABLE KEYS */;
INSERT INTO `dipendente` VALUES ('0001','determinato',NULL,16,NULL),('0002','indeterminato',NULL,3,NULL),('0003','part-time',NULL,0,NULL),('0004','indeterminato',NULL,19,NULL),('1001','indeterminato','Ordine Ingegneri Provincia Napoli',NULL,'Meccanica'),('1002','indeterminato','Ordine Ingegneri Provincia Napoli',NULL,'Elettronica');
/*!40000 ALTER TABLE `dipendente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `intervento`
--

LOCK TABLES `intervento` WRITE;
/*!40000 ALTER TABLE `intervento` DISABLE KEYS */;
INSERT INTO `intervento` VALUES (1,3,0,'Ccompletato','2020-12-08','2020-12-08'),(2,7,0,'Completato','2020-12-08','2020-12-10'),(3,9,0,'In lavorazione','2020-12-09',NULL),(4,1,1,'Valutato','2020-12-10',NULL),(5,4,0,'Completato','2020-12-10','2022-01-11'),(10,6,0,'Richiesto','2022-01-08',NULL);
/*!40000 ALTER TABLE `intervento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `macchinario`
--

LOCK TABLES `macchinario` WRITE;
/*!40000 ALTER TABLE `macchinario` DISABLE KEYS */;
INSERT INTO `macchinario` VALUES (1,1280,2,101221,90,'guasto batteria','Combina 5 diverse tecnologie','CTANDR00E25H703O','V-Shape',NULL),(2,265,NULL,101221,100,'nessuna','Usato per la rimozione della cellulite','CTANDR00E25H703O',NULL,'Manico Rullo Vacuum a infrarossi RF'),(3,200,NULL,101221,100,'malfunzionamento','Trattamento occhiaie, rimozione rughe','CTANDR00E25H703O',NULL,'Manico Vacuum Facciale RF'),(4,899.98,1,101221,85,'bruciatura resistenza','Effettua decomposizione fototermica selettiva','RBSMHL98R16A509S','Laser Diodo Portatile',NULL),(5,39.99,NULL,101221,100,'nessuna','Per aree difficili da raggiungere','RBSMHL98R16A509S',NULL,'Facial Tip'),(6,1799,0,101221,80,'malfunzionamento','Riduce grasso localizzato','FRRRSL03C68A944Q','EM-shape',NULL),(7,2459,2,101221,80,'malfunzionamento','Agisce su inestetismi cutanei','FRRRSL03C68A944Q','Be active Spa',NULL),(8,120,NULL,101221,100,'nessuna','Migliora il metabolismo','PRNFNC03C71H703K',NULL,'Cromo-light'),(9,99.98,NULL,101221,60,'rottura','Per trattamenti Anti-Age','PRVDRD00B33H703K',NULL,'Lampada LED al collagene'),(10,265,NULL,101221,100,'nessuna','Usato per la rimozione della cellulite','BVOLXA00S19F839V',NULL,'Manico Rullo Vacuum a infrarossi RF'),(11,1280,2,50122,100,'nessuna','Combina 5 diverse tecnologie','PRNFNC03C71H703K','V-Shape',NULL),(12,265,NULL,50122,100,'nessuna','Usato per la rimozione della cellulite','FRRRSL03C68A944Q',NULL,'Manico Rullo Vacuum a infrarossi RF'),(13,2459,2,50122,100,'nessuna','Agisce su inestetismi cutanei','FRRRSL03C68A944Q','Be active Spa',NULL),(14,39.99,NULL,101221,100,'nessuna','Per aree difficili da raggiungere','BVOLXA00S19F839V',NULL,'Facial Tip');
/*!40000 ALTER TABLE `macchinario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `partecipazione`
--

LOCK TABLES `partecipazione` WRITE;
/*!40000 ALTER TABLE `partecipazione` DISABLE KEYS */;
INSERT INTO `partecipazione` VALUES (1,'0001'),(2,'0002'),(1,'0003'),(3,'0004'),(1,'1001'),(2,'1001'),(3,'1001');
/*!40000 ALTER TABLE `partecipazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `progetto`
--

LOCK TABLES `progetto` WRITE;
/*!40000 ALTER TABLE `progetto` DISABLE KEYS */;
INSERT INTO `progetto` VALUES (1,'commercializzato',1),(2,'commercializzato',2),(3,'commercializzato',3),(4,'commercializzato',4),(5,'commercializzato',5),(6,'commercializzato',6),(7,'prototipale',7),(8,'commercializzato',8),(9,'commercializzato',9),(10,'collaudato',10),(11,'commercializzato',11),(12,'commercializzato',12),(13,'commercializzato',13),(14,'commercializzato',14);
/*!40000 ALTER TABLE `progetto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `schedadipendente`
--

LOCK TABLES `schedadipendente` WRITE;
/*!40000 ALTER TABLE `schedadipendente` DISABLE KEYS */;
INSERT INTO `schedadipendente` VALUES ('AAAAAA00A01A000B','Giuseppe','Raiola','g.raiola@gmail.com','1000000000','0001'),('AAAAAA00A01A000C','Maria','Ferrari','m.ferrari@gmail.com','2000000000','0002'),('AAAAAA00A01A000D','Marco','Bianchi','m.bianchi@gmail.com','3000000000','0003'),('AAAAAA00A01A000E','Andrea','Romano','a.romano@gmail.com','4000000000','0004'),('AAAAAA00A01A000F','Mario','Citarella','m.citarella@gmail.com','5000000000','1001'),('AAAAAA00A01A000G','Paolo','Caruso','p.caruso@gmail.com','6000000000','1002');
/*!40000 ALTER TABLE `schedadipendente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-09 19:09:01
