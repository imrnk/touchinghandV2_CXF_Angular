CREATE DATABASE  IF NOT EXISTS `ocsa` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `ocsa`;
-- MySQL dump 10.16  Distrib 10.1.26-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: ocsa
-- ------------------------------------------------------
-- Server version	10.1.26-MariaDB-0+deb9u1

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
-- Table structure for table `reference_data`
--

DROP TABLE IF EXISTS `reference_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reference_data` (
  `reference_type_id` int(11) NOT NULL,
  `reference_data_key` varchar(45) DEFAULT NULL,
  `reference_data_value` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reference_data`
--

LOCK TABLES `reference_data` WRITE;
/*!40000 ALTER TABLE `reference_data` DISABLE KEYS */;
INSERT INTO `reference_data` VALUES (1,'neat','neat'),(1,'disheveled','disheveled'),(1,'inappropriate','inappropriate'),(1,'bizarre','bizarre'),(1,'other','other'),(2,'normal','normal'),(2,'tangential','tangential'),(2,'pressured','pressured'),(2,'impoverished','impoverished'),(2,'other','other'),(3,'normal','normal'),(3,'intense','intense'),(3,'avoidant','avoidant'),(3,'other','other'),(4,'normal','normal'),(4,'restless','restless'),(4,'tic','tic'),(4,'slow','slow'),(4,'other','other'),(5,'full','full'),(5,'constricted','constricted'),(5,'flat','flat'),(5,'labile','labile'),(5,'other','other'),(6,'euthymic','euthymic'),(6,'anxious','anxious'),(6,'angry','angry'),(6,'depressed','depressed'),(6,'euphoric','euphoric'),(6,'irritable','irritable'),(6,'other','other'),(7,'none','none'),(7,'place','place'),(7,'object','object'),(7,'person','person'),(7,'time','time'),(8,'none','none'),(8,'short-term','short-term'),(8,'long-term','long-term'),(8,'other','other'),(9,'normal','normal'),(9,'distracted','distracted'),(9,'other','other'),(10,'none','none'),(10,'auditory','auditory'),(10,'visual','visual'),(10,'other','other'),(11,'none','none'),(11,'derealization','derealization'),(11,'depersonalization','depersonalization'),(12,'none','none'),(12,'ideation','ideation'),(12,'plan','plan'),(12,'intent','intent'),(12,'self-harm','self-harm'),(13,'none','none'),(13,'aggressive','aggressive'),(13,'intent','intent'),(13,'plan','plan'),(14,'none','none'),(14,'grandiose','grandiose'),(14,'paranoid','paranoid'),(14,'religious','religious'),(14,'other','other'),(15,'cooperative','cooperative'),(15,'guarded','guarded'),(15,'hyperactive','hyperactive'),(15,'agitated','agitated'),(15,'paranoid','paranoid'),(15,'stereotyped','stereotyped'),(15,'aggressive','aggressive'),(15,'bizarre','bizarre'),(15,'withdrawn','withdrawn'),(15,'other','other'),(16,'good','good'),(16,'fair','fair'),(16,'poor','poor'),(16,'other','other'),(17,'good','good'),(17,'fair','fair'),(17,'poor','poor'),(17,'other','other'),(18,'rebt','REBT'),(18,'cbt','CBT'),(18,'psychoanalysis','Psychoanalysis'),(18,'existentiatherapy','Existential Therapy'),(18,'playtherapy','Play Therapy'),(18,'dmt','Dance Movement Therapy'),(18,'behaviortherapy','Behavior Therapy');
/*!40000 ALTER TABLE `reference_data` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-28 14:21:13
