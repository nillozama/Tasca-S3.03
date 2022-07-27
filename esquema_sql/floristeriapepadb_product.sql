-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: floristeriapepadb
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `idproduct` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `type_idtype` int NOT NULL,
  `price` float NOT NULL,
  `characteristic` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `shop_idshop` int DEFAULT NULL,
  `ticket_idticket` int DEFAULT NULL,
  PRIMARY KEY (`idproduct`),
  KEY `fk_product_type_idx` (`type_idtype`),
  KEY `fk_product_shop1_idx` (`shop_idshop`),
  KEY `fk_product_ticket1_idx` (`ticket_idticket`),
  CONSTRAINT `fk_product_shop1` FOREIGN KEY (`shop_idshop`) REFERENCES `shop` (`idshop`),
  CONSTRAINT `fk_product_ticket1` FOREIGN KEY (`ticket_idticket`) REFERENCES `ticket` (`idticket`),
  CONSTRAINT `fk_product_type` FOREIGN KEY (`type_idtype`) REFERENCES `type` (`idtype`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (5,'pino',1,34,'',1,NULL),(6,'abeto',1,32,'',1,NULL),(15,'roble',1,221,'2,34m',2,NULL),(16,'violeta',2,6,'rojo',NULL,5),(20,'rosa',2,2,'rosa',NULL,4),(21,'rosa',2,3,'negro',NULL,5),(22,'pino',1,23,'2,8m',2,NULL),(23,'maceta',3,9,'plastico',2,NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-06  0:02:03
