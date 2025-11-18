-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: car_system
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `commercial_vehicles`
--

DROP TABLE IF EXISTS `commercial_vehicles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commercial_vehicles` (
  `vehicle_id` bigint NOT NULL,
  `load_capacity` decimal(10,2) NOT NULL,
  `cargo_volume` decimal(10,2) NOT NULL,
  PRIMARY KEY (`vehicle_id`),
  CONSTRAINT `commercial_vehicles_ibfk_1` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`vehicle_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commercial_vehicles`
--

LOCK TABLES `commercial_vehicles` WRITE;
/*!40000 ALTER TABLE `commercial_vehicles` DISABLE KEYS */;
INSERT INTO `commercial_vehicles` VALUES (26,5.00,12.50),(27,4.50,11.80),(28,6.00,13.20),(29,5.50,12.80),(30,4.00,10.50),(31,7.00,14.50),(32,5.20,12.10),(33,5.80,13.00),(34,3.50,9.80),(35,3.80,10.20),(36,2.50,8.50),(37,3.00,9.20),(38,2.80,8.80),(39,2.20,7.50),(40,2.00,7.20),(41,3.20,9.50),(42,4.80,11.50),(43,5.30,12.30),(44,4.20,10.80),(45,10.00,25.50),(46,15.00,38.20),(47,12.00,32.80),(48,18.00,45.50),(49,20.00,50.20),(50,16.00,42.80);
/*!40000 ALTER TABLE `commercial_vehicles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_service`
--

DROP TABLE IF EXISTS `customer_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_service` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `type` enum('consultation','complaint','feedback') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `subject` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` enum('open','processing','resolved','closed') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'open',
  `priority` enum('low','medium','high') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'medium',
  `create_time` bigint NOT NULL,
  `update_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  CONSTRAINT `customer_service_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_service`
--

LOCK TABLES `customer_service` WRITE;
/*!40000 ALTER TABLE `customer_service` DISABLE KEYS */;
INSERT INTO `customer_service` VALUES (1,5,'consultation','1','1','processing','low',1763189246791,1763189366702);
/*!40000 ALTER TABLE `customer_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `vehicle_id` bigint NOT NULL,
  `buy_count` int NOT NULL,
  `total_price` decimal(10,2) DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `user_id` (`user_id`),
  KEY `vehicle_id` (`vehicle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (27,1,1,1,129800.00,1718000000000),(28,1,26,1,115800.00,1718100000000),(29,2,5,2,283600.00,1718200000000),(30,2,30,1,98800.00,1718300000000),(31,3,9,1,313900.00,1718400000000),(32,3,35,1,89800.00,1718500000000),(33,4,12,1,231900.00,1718600000000),(34,4,40,2,103800.00,1718700000000),(35,5,15,1,339800.00,1718800000000),(36,5,45,1,235800.00,1718900000000),(37,6,3,1,112900.00,1719000000000),(38,6,27,1,108900.00,1719100000000),(39,7,7,2,169800.00,1719200000000),(40,7,32,1,128900.00,1719300000000),(41,8,10,1,325200.00,1719400000000),(42,8,37,2,139800.00,1719500000000),(43,9,18,1,108800.00,1719600000000),(44,9,42,1,92900.00,1719700000000),(45,10,22,3,263700.00,1719800000000),(46,10,47,1,356800.00,1719900000000),(47,11,4,1,119900.00,1720000000000),(48,11,29,1,125900.00,1720100000000),(49,12,8,1,115900.00,1720200000000),(50,12,34,1,138900.00,1720300000000),(51,13,13,1,289800.00,1720400000000),(52,13,39,1,52900.00,1720500000000),(53,14,17,1,109900.00,1720600000000),(54,14,44,1,105900.00,1720700000000),(55,15,21,1,87900.00,1720800000000),(56,15,49,1,368800.00,1720900000000),(57,16,25,2,65600.00,1721000000000),(58,16,31,1,142900.00,1721100000000),(59,17,2,1,145900.00,1721200000000),(60,17,36,1,89800.00,1721300000000),(61,18,6,3,209700.00,1721400000000),(62,18,41,1,65800.00,1721500000000),(63,19,11,1,321800.00,1721600000000),(64,19,46,1,389800.00,1721700000000),(65,20,14,1,219900.00,1721800000000),(66,20,50,1,329800.00,1721900000000),(67,21,16,1,115900.00,1722000000000),(68,21,28,1,132800.00,1722100000000),(69,22,19,2,199600.00,1722200000000),(70,22,33,1,145800.00,1722300000000),(71,23,20,1,109800.00,1722400000000),(72,23,38,1,59800.00,1722500000000),(73,24,23,2,139800.00,1722600000000),(74,24,43,1,112800.00,1722700000000),(75,25,24,1,75800.00,1722800000000),(76,25,48,1,342800.00,1722900000000),(77,1,1,1,129800.00,1718000000000),(78,1,26,1,115800.00,1718100000000),(79,2,5,2,283600.00,1718200000000),(80,2,30,1,98800.00,1718300000000),(81,3,9,1,313900.00,1718400000000),(82,3,35,1,89800.00,1718500000000),(83,4,12,1,231900.00,1718600000000),(84,4,40,2,103800.00,1718700000000),(85,5,15,1,339800.00,1718800000000),(86,5,45,1,235800.00,1718900000000),(87,6,3,1,112900.00,1719000000000),(88,6,27,1,108900.00,1719100000000),(89,7,7,2,169800.00,1719200000000),(90,7,32,1,128900.00,1719300000000),(91,8,10,1,325200.00,1719400000000),(92,8,37,2,139800.00,1719500000000),(93,9,18,1,108800.00,1719600000000),(94,9,42,1,92900.00,1719700000000),(95,10,22,3,263700.00,1719800000000),(96,10,47,1,356800.00,1719900000000),(97,11,4,1,119900.00,1720000000000),(98,11,29,1,125900.00,1720100000000),(99,12,8,1,115900.00,1720200000000),(100,12,34,1,138900.00,1720300000000),(101,13,13,1,289800.00,1720400000000),(102,13,39,1,52900.00,1720500000000),(103,14,17,1,109900.00,1720600000000),(104,14,44,1,105900.00,1720700000000),(105,15,21,1,87900.00,1720800000000),(106,15,49,1,368800.00,1720900000000),(107,16,25,2,65600.00,1721000000000),(108,16,31,1,142900.00,1721100000000),(109,17,2,1,145900.00,1721200000000),(110,17,36,1,89800.00,1721300000000),(111,18,6,3,209700.00,1721400000000),(112,18,41,1,65800.00,1721500000000),(113,19,11,1,321800.00,1721600000000),(114,19,46,1,389800.00,1721700000000),(115,20,14,1,219900.00,1721800000000),(116,20,50,1,329800.00,1721900000000),(117,21,16,1,115900.00,1722000000000),(118,21,28,1,132800.00,1722100000000),(119,5,50,2,659600.00,1763005530710),(120,5,50,1,329800.00,1763005736409),(121,5,49,1,368800.00,1763192564670);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passenger_vehicles`
--

DROP TABLE IF EXISTS `passenger_vehicles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passenger_vehicles` (
  `vehicle_id` bigint NOT NULL,
  `seat_count` int NOT NULL,
  `fuel_type` varchar(50) NOT NULL,
  PRIMARY KEY (`vehicle_id`),
  CONSTRAINT `passenger_vehicles_ibfk_1` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`vehicle_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passenger_vehicles`
--

LOCK TABLES `passenger_vehicles` WRITE;
/*!40000 ALTER TABLE `passenger_vehicles` DISABLE KEYS */;
INSERT INTO `passenger_vehicles` VALUES (1,5,'汽油'),(2,5,'汽油'),(3,5,'汽油'),(4,5,'汽油'),(5,5,'新能源'),(6,5,'汽油'),(7,5,'汽油'),(8,5,'汽油'),(9,5,'汽油'),(10,5,'汽油'),(11,5,'汽油'),(12,5,'新能源'),(13,5,'新能源'),(14,5,'新能源'),(15,6,'新能源'),(16,5,'汽油'),(17,5,'汽油'),(18,5,'汽油'),(19,5,'汽油'),(20,5,'汽油'),(21,5,'汽油'),(22,5,'汽油'),(23,5,'汽油'),(24,5,'汽油'),(25,4,'新能源');
/*!40000 ALTER TABLE `passenger_vehicles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_ratings`
--

DROP TABLE IF EXISTS `service_ratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_ratings` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `service_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `rating` int NOT NULL,
  `comment` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `create_time` bigint NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `service_id` (`service_id`) USING BTREE,
  CONSTRAINT `service_ratings_ibfk_1` FOREIGN KEY (`service_id`) REFERENCES `customer_service` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `service_ratings_chk_1` CHECK ((`rating` between 1 and 5))
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_ratings`
--

LOCK TABLES `service_ratings` WRITE;
/*!40000 ALTER TABLE `service_ratings` DISABLE KEYS */;
INSERT INTO `service_ratings` VALUES (1,1,5,1,'差评',1763189310760);
/*!40000 ALTER TABLE `service_ratings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopping_cart`
--

DROP TABLE IF EXISTS `shopping_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shopping_cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `vehicle_id` bigint NOT NULL,
  `quantity` int NOT NULL DEFAULT '1',
  `create_time` bigint NOT NULL,
  `update_time` bigint NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `vehicle_id` (`vehicle_id`) USING BTREE,
  CONSTRAINT `shopping_cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `shopping_cart_ibfk_2` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`vehicle_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopping_cart`
--

LOCK TABLES `shopping_cart` WRITE;
/*!40000 ALTER TABLE `shopping_cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `shopping_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `user_email` varchar(255) NOT NULL,
  `user_name` varchar(225) NOT NULL,
  `password` varchar(255) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_email` (`user_email`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'test@test.com','测试','test',1),(2,'admin@javat.cn','admin','admin',1),(3,'2@2','22222','111',1),(4,'1','1','222222',1),(5,'test','test','test',1),(6,'zhangwei@example.com','张伟','password123',1),(7,'liuna@example.com','李娜','mypassword',1),(8,'wangqiang@example.com','王强','securepass',1),(9,'chenjing@example.com','陈静','userpass456',1),(10,'liuming@example.com','刘明','login1234',1),(11,'yangfang@example.com','杨芳','pass7890',1),(12,'zhaojun@example.com','赵军','mysecret1',1),(13,'huangli@example.com','黄丽','password2',1),(14,'zhoupeng@example.com','周鹏','user12345',1),(15,'wuqian@example.com','吴倩','secure567',1),(16,'xugang@example.com','徐刚','loginpass',1),(17,'sunyan@example.com','孙艳','mypwd789',1),(18,'maofei@example.com','毛飞','passkey12',1),(19,'linlin@example.com','林琳','secretpw3',1),(20,'hecheng@example.com','何成','userkey45',1),(21,'gaojie@example.com','高洁','loginkey6',1),(22,'luoxin@example.com','罗欣','mypassword7',1),(23,'xiejun@example.com','谢军','securekey8',1),(24,'songli@example.com','宋丽','passkey99',1),(25,'denghui@example.com','邓辉','userpass00',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicles`
--

DROP TABLE IF EXISTS `vehicles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicles` (
  `vehicle_id` bigint NOT NULL AUTO_INCREMENT,
  `vehicle_type` varchar(50) NOT NULL,
  `vehicle_brand` varchar(255) DEFAULT NULL,
  `vehicle_model` varchar(255) DEFAULT NULL,
  `vehicle_price` decimal(10,2) NOT NULL,
  `vehicle_stock` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`vehicle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicles`
--

LOCK TABLES `vehicles` WRITE;
/*!40000 ALTER TABLE `vehicles` DISABLE KEYS */;
INSERT INTO `vehicles` VALUES (1,'passenger','丰田','卡罗拉',129800.00,30),(2,'passenger','本田','思域',145900.00,25),(3,'passenger','大众','朗逸',112900.00,40),(4,'passenger','别克','英朗',119900.00,28),(5,'passenger','比亚迪','秦PLUS',141800.00,35),(6,'passenger','吉利','帝豪',69900.00,50),(7,'passenger','长安','逸动',84900.00,32),(8,'passenger','哈弗','H6',115900.00,22),(9,'passenger','宝马','3系',313900.00,15),(10,'passenger','奔驰','C级',325200.00,12),(11,'passenger','奥迪','A4L',321800.00,18),(12,'passenger','特斯拉','Model 3',231900.00,20),(13,'passenger','蔚来','ET5',289800.00,10),(14,'passenger','小鹏','P7',219900.00,16),(15,'passenger','理想','L7',339800.00,8),(16,'passenger','马自达','昂克赛拉',115900.00,24),(17,'passenger','雪佛兰','科鲁兹',109900.00,26),(18,'passenger','福特','福克斯',108800.00,23),(19,'passenger','现代','伊兰特',99800.00,36),(20,'passenger','起亚','K3',109800.00,31),(21,'passenger','荣威','i5',87900.00,42),(22,'passenger','名爵','MG5',87900.00,38),(23,'passenger','奇瑞','艾瑞泽5',69900.00,45),(24,'passenger','传祺','GA4',75800.00,33),(25,'passenger','五菱','宏光MINI',32800.00,60),(26,'commercial','福田','奥铃CTS',115800.00,18),(27,'commercial','东风','多利卡',108900.00,20),(28,'commercial','解放','J6F',132800.00,15),(29,'commercial','江淮','帅铃',125900.00,16),(30,'commercial','江铃','顺达',98800.00,22),(31,'commercial','庆铃','五十铃',142900.00,10),(32,'commercial','重汽','HOWO轻卡',119900.00,17),(33,'commercial','陕汽','德龙K3000',128900.00,14),(34,'commercial','上汽大通','V80',145800.00,12),(35,'commercial','依维柯','得意',138900.00,9),(36,'commercial','金杯','海狮',89800.00,25),(37,'commercial','长安','跨越王',69900.00,30),(38,'commercial','五菱','荣光新卡',59800.00,35),(39,'commercial','北汽昌河','福瑞达K22',52900.00,28),(40,'commercial','东风小康','C32',51900.00,32),(41,'commercial','福田','祥菱V',65800.00,24),(42,'commercial','江淮','康铃',92900.00,19),(43,'commercial','江铃','凯运',112800.00,13),(44,'commercial','解放','虎VN',105900.00,21),(45,'commercial','东风','天锦',235800.00,8),(46,'commercial','重汽','汕德卡',389800.00,5),(47,'commercial','陕汽','德龙X3000',356800.00,6),(48,'commercial','红岩','杰狮',342800.00,4),(49,'commercial','北奔','V3ET',368800.00,2),(50,'commercial','联合卡车','U+',329800.00,4);
/*!40000 ALTER TABLE `vehicles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-18 20:40:30
