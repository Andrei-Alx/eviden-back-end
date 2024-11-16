CREATE DATABASE IF NOT EXISTS gameappbff_db;
USE gameappbff_db;

-- MySQL dump 10.13  Distrib 9.0.1, for Linux (x86_64)
--
-- Host: localhost    Database: gameappbff_db
-- ------------------------------------------------------
-- Server version	9.0.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card` (
  `is_active` bit(1) NOT NULL,
  `id` binary(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card_set`
--

DROP TABLE IF EXISTS `card_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card_set` (
  `is_active` bit(1) NOT NULL,
  `id` binary(16) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card_set`
--

LOCK TABLES `card_set` WRITE;
/*!40000 ALTER TABLE `card_set` DISABLE KEYS */;
/*!40000 ALTER TABLE `card_set` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card_set_cards`
--

DROP TABLE IF EXISTS `card_set_cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card_set_cards` (
  `card_set_id` binary(16) NOT NULL,
  `cards_id` binary(16) NOT NULL,
  KEY `FK7q7799dj5edu76mebb22ssgv5` (`cards_id`),
  KEY `FKt9my81qpppyipxj8yq8s7dia` (`card_set_id`),
  CONSTRAINT `FK7q7799dj5edu76mebb22ssgv5` FOREIGN KEY (`cards_id`) REFERENCES `card` (`id`),
  CONSTRAINT `FKt9my81qpppyipxj8yq8s7dia` FOREIGN KEY (`card_set_id`) REFERENCES `card_set` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card_set_cards`
--

LOCK TABLES `card_set_cards` WRITE;
/*!40000 ALTER TABLE `card_set_cards` DISABLE KEYS */;
/*!40000 ALTER TABLE `card_set_cards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card_set_tags`
--

DROP TABLE IF EXISTS `card_set_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card_set_tags` (
  `tag_key` tinyint DEFAULT NULL,
  `card_set_id` binary(16) NOT NULL,
  `tag_value` varchar(255) DEFAULT NULL,
  KEY `FKcufsggh7d4mdk566cpkc8k5ke` (`card_set_id`),
  CONSTRAINT `FKcufsggh7d4mdk566cpkc8k5ke` FOREIGN KEY (`card_set_id`) REFERENCES `card_set` (`id`),
  CONSTRAINT `card_set_tags_chk_1` CHECK ((`tag_key` between 0 and 4))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card_set_tags`
--

LOCK TABLES `card_set_tags` WRITE;
/*!40000 ALTER TABLE `card_set_tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `card_set_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card_tags`
--

DROP TABLE IF EXISTS `card_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card_tags` (
  `tag_key` tinyint DEFAULT NULL,
  `card_id` binary(16) NOT NULL,
  `tag_value` varchar(255) DEFAULT NULL,
  KEY `FK3d8m6ot8d661jw391k45btejs` (`card_id`),
  CONSTRAINT `FK3d8m6ot8d661jw391k45btejs` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`),
  CONSTRAINT `card_tags_chk_1` CHECK ((`tag_key` between 0 and 4))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card_tags`
--

LOCK TABLES `card_tags` WRITE;
/*!40000 ALTER TABLE `card_tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `card_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card_translations`
--

DROP TABLE IF EXISTS `card_translations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card_translations` (
  `card_id` binary(16) NOT NULL,
  `language` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  KEY `FKcay5qmpa36pxwp8979frbppcq` (`card_id`),
  CONSTRAINT `FKcay5qmpa36pxwp8979frbppcq` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card_translations`
--

LOCK TABLES `card_translations` WRITE;
/*!40000 ALTER TABLE `card_translations` DISABLE KEYS */;
/*!40000 ALTER TABLE `card_translations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `final_result`
--

DROP TABLE IF EXISTS `final_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `final_result` (
  `game_id` binary(16) DEFAULT NULL,
  `id` binary(16) NOT NULL,
  `player_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `final_result`
--

LOCK TABLES `final_result` WRITE;
/*!40000 ALTER TABLE `final_result` DISABLE KEYS */;
/*!40000 ALTER TABLE `final_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `final_result_results`
--

DROP TABLE IF EXISTS `final_result_results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `final_result_results` (
  `final_result_id` binary(16) NOT NULL,
  `results_id` binary(16) NOT NULL,
  UNIQUE KEY `UK_3vdw1dlo2t6p5tqhw6w1bkb5r` (`results_id`),
  KEY `FKj2alpx5j4qgp9byb6l2xil4st` (`final_result_id`),
  CONSTRAINT `FK1en4a14umlj4u1ex245plyqm1` FOREIGN KEY (`results_id`) REFERENCES `result` (`id`),
  CONSTRAINT `FKj2alpx5j4qgp9byb6l2xil4st` FOREIGN KEY (`final_result_id`) REFERENCES `final_result` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `final_result_results`
--

LOCK TABLES `final_result_results` WRITE;
/*!40000 ALTER TABLE `final_result_results` DISABLE KEYS */;
/*!40000 ALTER TABLE `final_result_results` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game` (
  `status` tinyint DEFAULT NULL,
  `id` binary(16) NOT NULL,
  `lobby_id` binary(16) DEFAULT NULL,
  `company_type` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rrjespy3jm988k8tuipp8jkus` (`lobby_id`),
  CONSTRAINT `FKoxjim81ppwnik0r3f4tcgmd6` FOREIGN KEY (`lobby_id`) REFERENCES `lobby` (`id`),
  CONSTRAINT `game_chk_1` CHECK ((`status` between 0 and 2))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game`
--

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game_rounds`
--

DROP TABLE IF EXISTS `game_rounds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game_rounds` (
  `game_id` binary(16) NOT NULL,
  `rounds_id` binary(16) NOT NULL,
  UNIQUE KEY `UK_gwn3wpw21acccak4odbxhpola` (`rounds_id`),
  KEY `FKhwg9acgvm267aqpi5wj3wm54h` (`game_id`),
  CONSTRAINT `FK378y0jg9i07kroelcx5qif7ww` FOREIGN KEY (`rounds_id`) REFERENCES `round` (`id`),
  CONSTRAINT `FKhwg9acgvm267aqpi5wj3wm54h` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_rounds`
--

LOCK TABLES `game_rounds` WRITE;
/*!40000 ALTER TABLE `game_rounds` DISABLE KEYS */;
/*!40000 ALTER TABLE `game_rounds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lobby`
--

DROP TABLE IF EXISTS `lobby`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lobby` (
  `id` binary(16) NOT NULL,
  `lobby_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lobby`
--

LOCK TABLES `lobby` WRITE;
/*!40000 ALTER TABLE `lobby` DISABLE KEYS */;
/*!40000 ALTER TABLE `lobby` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lobby_players`
--

DROP TABLE IF EXISTS `lobby_players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lobby_players` (
  `id` binary(16) DEFAULT NULL,
  `lobby_id` binary(16) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  KEY `FKn5vts70bkgke2lr6poe34dann` (`lobby_id`),
  CONSTRAINT `FKn5vts70bkgke2lr6poe34dann` FOREIGN KEY (`lobby_id`) REFERENCES `lobby` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lobby_players`
--

LOCK TABLES `lobby_players` WRITE;
/*!40000 ALTER TABLE `lobby_players` DISABLE KEYS */;
/*!40000 ALTER TABLE `lobby_players` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player_round`
--

DROP TABLE IF EXISTS `player_round`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player_round` (
  `id` binary(16) NOT NULL,
  `player_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player_round`
--

LOCK TABLES `player_round` WRITE;
/*!40000 ALTER TABLE `player_round` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_round` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player_round_disliked_cards`
--

DROP TABLE IF EXISTS `player_round_disliked_cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player_round_disliked_cards` (
  `disliked_cards_id` binary(16) NOT NULL,
  `player_round_id` binary(16) NOT NULL,
  KEY `FK9phy0fv4a0ofaia6jpirscexx` (`disliked_cards_id`),
  KEY `FK2rs8yw98ditv149ioapfod8hm` (`player_round_id`),
  CONSTRAINT `FK2rs8yw98ditv149ioapfod8hm` FOREIGN KEY (`player_round_id`) REFERENCES `player_round` (`id`),
  CONSTRAINT `FK9phy0fv4a0ofaia6jpirscexx` FOREIGN KEY (`disliked_cards_id`) REFERENCES `card` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player_round_disliked_cards`
--

LOCK TABLES `player_round_disliked_cards` WRITE;
/*!40000 ALTER TABLE `player_round_disliked_cards` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_round_disliked_cards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player_round_distributed_cards`
--

DROP TABLE IF EXISTS `player_round_distributed_cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player_round_distributed_cards` (
  `distributed_cards_id` binary(16) NOT NULL,
  `player_round_id` binary(16) NOT NULL,
  KEY `FK18efpyd1ebm9ahi7bdyptp11m` (`distributed_cards_id`),
  KEY `FKnovxt88h8ggb3jnhnwohbg6wr` (`player_round_id`),
  CONSTRAINT `FK18efpyd1ebm9ahi7bdyptp11m` FOREIGN KEY (`distributed_cards_id`) REFERENCES `card` (`id`),
  CONSTRAINT `FKnovxt88h8ggb3jnhnwohbg6wr` FOREIGN KEY (`player_round_id`) REFERENCES `player_round` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player_round_distributed_cards`
--

LOCK TABLES `player_round_distributed_cards` WRITE;
/*!40000 ALTER TABLE `player_round_distributed_cards` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_round_distributed_cards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player_round_liked_cards`
--

DROP TABLE IF EXISTS `player_round_liked_cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player_round_liked_cards` (
  `liked_cards_id` binary(16) NOT NULL,
  `player_round_id` binary(16) NOT NULL,
  KEY `FKq8tbr4mhb6ye8sw2medagbem1` (`liked_cards_id`),
  KEY `FKaw043ycg6dh7vql746nxsmlb0` (`player_round_id`),
  CONSTRAINT `FKaw043ycg6dh7vql746nxsmlb0` FOREIGN KEY (`player_round_id`) REFERENCES `player_round` (`id`),
  CONSTRAINT `FKq8tbr4mhb6ye8sw2medagbem1` FOREIGN KEY (`liked_cards_id`) REFERENCES `card` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player_round_liked_cards`
--

LOCK TABLES `player_round_liked_cards` WRITE;
/*!40000 ALTER TABLE `player_round_liked_cards` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_round_liked_cards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player_round_result`
--

DROP TABLE IF EXISTS `player_round_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player_round_result` (
  `game_id` binary(16) DEFAULT NULL,
  `id` binary(16) NOT NULL,
  `player_id` binary(16) DEFAULT NULL,
  `result_id` binary(16) DEFAULT NULL,
  `round_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3mugahnlcui71ye2swv062n7k` (`result_id`),
  CONSTRAINT `FKa6cy6m15rcvkovhglul3gm4b3` FOREIGN KEY (`result_id`) REFERENCES `result` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player_round_result`
--

LOCK TABLES `player_round_result` WRITE;
/*!40000 ALTER TABLE `player_round_result` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_round_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player_round_selected_cards`
--

DROP TABLE IF EXISTS `player_round_selected_cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player_round_selected_cards` (
  `player_round_id` binary(16) NOT NULL,
  `selected_cards_id` binary(16) NOT NULL,
  KEY `FK9e9udfq8w0c6vkykvo9aufv0q` (`selected_cards_id`),
  KEY `FK863yh6fkbpndgm9s5q69fj6k3` (`player_round_id`),
  CONSTRAINT `FK863yh6fkbpndgm9s5q69fj6k3` FOREIGN KEY (`player_round_id`) REFERENCES `player_round` (`id`),
  CONSTRAINT `FK9e9udfq8w0c6vkykvo9aufv0q` FOREIGN KEY (`selected_cards_id`) REFERENCES `card` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player_round_selected_cards`
--

LOCK TABLES `player_round_selected_cards` WRITE;
/*!40000 ALTER TABLE `player_round_selected_cards` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_round_selected_cards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `result` (
  `type` tinyint DEFAULT NULL,
  `id` binary(16) NOT NULL,
  `player_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `result_chk_1` CHECK ((`type` between 0 and 2))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result`
--

LOCK TABLES `result` WRITE;
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
/*!40000 ALTER TABLE `result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result_advice_cards`
--

DROP TABLE IF EXISTS `result_advice_cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `result_advice_cards` (
  `advice_cards_id` binary(16) NOT NULL,
  `result_id` binary(16) NOT NULL,
  KEY `FKgjdok53f9w8t1oyb64pvgfrag` (`advice_cards_id`),
  KEY `FKsrdig48h371b01r7g9bbh2jgx` (`result_id`),
  CONSTRAINT `FKgjdok53f9w8t1oyb64pvgfrag` FOREIGN KEY (`advice_cards_id`) REFERENCES `card` (`id`),
  CONSTRAINT `FKsrdig48h371b01r7g9bbh2jgx` FOREIGN KEY (`result_id`) REFERENCES `result` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result_advice_cards`
--

LOCK TABLES `result_advice_cards` WRITE;
/*!40000 ALTER TABLE `result_advice_cards` DISABLE KEYS */;
/*!40000 ALTER TABLE `result_advice_cards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result_chosen_cards`
--

DROP TABLE IF EXISTS `result_chosen_cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `result_chosen_cards` (
  `chosen_cards_id` binary(16) NOT NULL,
  `result_id` binary(16) NOT NULL,
  KEY `FK1tmtl39e2t1ep4d1svgnxrc4b` (`chosen_cards_id`),
  KEY `FKjlhx8uis3jxtuk9qfsa7ck13x` (`result_id`),
  CONSTRAINT `FK1tmtl39e2t1ep4d1svgnxrc4b` FOREIGN KEY (`chosen_cards_id`) REFERENCES `card` (`id`),
  CONSTRAINT `FKjlhx8uis3jxtuk9qfsa7ck13x` FOREIGN KEY (`result_id`) REFERENCES `result` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result_chosen_cards`
--

LOCK TABLES `result_chosen_cards` WRITE;
/*!40000 ALTER TABLE `result_chosen_cards` DISABLE KEYS */;
/*!40000 ALTER TABLE `result_chosen_cards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result_result`
--

DROP TABLE IF EXISTS `result_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `result_result` (
  `result_id` binary(16) NOT NULL,
  `result` varchar(255) DEFAULT NULL,
  KEY `FKh8htha2bllyofmepse6n1c9ag` (`result_id`),
  CONSTRAINT `FKh8htha2bllyofmepse6n1c9ag` FOREIGN KEY (`result_id`) REFERENCES `result` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result_result`
--

LOCK TABLES `result_result` WRITE;
/*!40000 ALTER TABLE `result_result` DISABLE KEYS */;
/*!40000 ALTER TABLE `result_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `round`
--

DROP TABLE IF EXISTS `round`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `round` (
  `nr_of_liked_cards` int DEFAULT NULL,
  `nr_of_selected_cards` int DEFAULT NULL,
  `show_personal_or_group_results` tinyint DEFAULT NULL,
  `show_same_card_order` bit(1) DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `card_set_id` binary(16) DEFAULT NULL,
  `id` binary(16) NOT NULL,
  `shuffle_method` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2pc9jtr2rgw1fw1gn8m2rcihm` (`card_set_id`),
  CONSTRAINT `FK2pc9jtr2rgw1fw1gn8m2rcihm` FOREIGN KEY (`card_set_id`) REFERENCES `card_set` (`id`),
  CONSTRAINT `round_chk_1` CHECK ((`show_personal_or_group_results` between 0 and 2)),
  CONSTRAINT `round_chk_2` CHECK ((`status` between 0 and 2))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `round`
--

LOCK TABLES `round` WRITE;
/*!40000 ALTER TABLE `round` DISABLE KEYS */;
/*!40000 ALTER TABLE `round` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `round_player_rounds`
--

DROP TABLE IF EXISTS `round_player_rounds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `round_player_rounds` (
  `player_rounds_id` binary(16) NOT NULL,
  `round_id` binary(16) NOT NULL,
  UNIQUE KEY `UK_je2esj85bfutpr09n0vfq1tx9` (`player_rounds_id`),
  KEY `FK7bl05j52gk2ek2so8ycv2pikv` (`round_id`),
  CONSTRAINT `FK7bl05j52gk2ek2so8ycv2pikv` FOREIGN KEY (`round_id`) REFERENCES `round` (`id`),
  CONSTRAINT `FKck85l7qrf9j45rs9waa8ralci` FOREIGN KEY (`player_rounds_id`) REFERENCES `player_round` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `round_player_rounds`
--

LOCK TABLES `round_player_rounds` WRITE;
/*!40000 ALTER TABLE `round_player_rounds` DISABLE KEYS */;
/*!40000 ALTER TABLE `round_player_rounds` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-12 17:25:00
