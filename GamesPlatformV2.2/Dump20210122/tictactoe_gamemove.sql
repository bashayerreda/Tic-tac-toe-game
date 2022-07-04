-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: tictactoe
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `gamemove`
--

DROP TABLE IF EXISTS `gamemove`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gamemove` (
  `idGame` int NOT NULL,
  `cellOrder` int NOT NULL,
  `cellNumber` int NOT NULL,
  `cellType` char(1) DEFAULT NULL,
  PRIMARY KEY (`idGame`,`cellOrder`,`cellNumber`),
  CONSTRAINT `gamemove_ibfk_1` FOREIGN KEY (`idGame`) REFERENCES `gamearchive` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gamemove`
--

LOCK TABLES `gamemove` WRITE;
/*!40000 ALTER TABLE `gamemove` DISABLE KEYS */;
INSERT INTO `gamemove` VALUES (154,0,5,'X'),(154,1,1,'O'),(154,2,3,'X'),(154,3,6,'O'),(154,4,9,'X'),(154,5,8,'O'),(154,6,7,'X'),(155,0,5,'X'),(155,1,3,'O'),(155,2,9,'X'),(155,3,1,'O'),(155,4,7,'X'),(155,5,6,'O'),(155,6,2,'X'),(155,7,4,'O'),(155,8,8,'X'),(156,0,1,'X'),(156,1,5,'O'),(156,2,3,'X'),(156,3,9,'O'),(156,4,8,'X'),(156,5,7,'O'),(156,6,2,'X'),(157,0,9,'X'),(158,0,1,'X'),(158,1,5,'O'),(158,2,9,'X'),(158,3,7,'O'),(158,4,3,'X'),(159,0,5,'X'),(159,1,2,'O'),(159,2,1,'X'),(159,3,7,'O'),(159,4,6,'X'),(159,5,9,'O'),(159,6,8,'X'),(159,7,3,'O'),(159,8,4,'X'),(160,0,2,'X'),(160,1,5,'O'),(161,0,2,'X'),(162,0,2,'X'),(162,1,3,'X'),(163,0,2,'X'),(163,1,3,'X'),(163,2,1,'X'),(164,0,1,'X'),(165,1,1,'X'),(165,2,3,'O'),(165,3,5,'X'),(166,4,9,'X'),(167,0,3,'X'),(168,1,1,'X'),(169,0,4,'X'),(169,1,1,'O'),(169,2,3,'X'),(169,3,5,'O'),(169,4,9,'X'),(169,5,6,'O'),(170,0,1,'X'),(170,1,5,'O');
/*!40000 ALTER TABLE `gamemove` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-22 18:02:25
