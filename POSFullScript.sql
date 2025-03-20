CREATE SCHEMA `POS`;
USE `POS`;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) DEFAULT NULL,
                        `password` varchar(100) DEFAULT NULL,
                        `phone` varchar(255) DEFAULT NULL,
                        `role` int DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `index2` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'احمد علي محمد','$2a$12$wI4AxjFGkLcyIAmk8wuxJuuzx9R5U0Xd.OqKLK2sgfiMggVNM7x7i','01277437142',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;


--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `storeName` varchar(20) NOT NULL,
                         `storeItems` int DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `name` varchar(50) NOT NULL,
                         `code` varchar(50) NOT NULL,
                         `price` double NOT NULL,
                         `priceforcustomer` double NOT NULL,
                         `amount` int NOT NULL,
                         `storeCode` int DEFAULT NULL,
                         `lastUpdatedDate` varchar(45) DEFAULT NULL,
                         PRIMARY KEY (`id`,`code`),
                         UNIQUE KEY `code_UNIQUE` (`code`),
                         KEY `store_id_fk_idx` (`storeCode`)
) ENGINE=InnoDB AUTO_INCREMENT=2309 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `companes`
--

DROP TABLE IF EXISTS `companes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `companes` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(100) NOT NULL,
                            `phone` varchar(100) NOT NULL,
                            `email` varchar(40) NOT NULL,
                            `address` varchar(100) NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `name` varchar(100) NOT NULL,
                             `phone` varchar(100) NOT NULL,
                             `email` varchar(40) NOT NULL,
                             `address` varchar(100) NOT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `customersales`
--

DROP TABLE IF EXISTS `customersales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customersales` (
                                 `id` int NOT NULL AUTO_INCREMENT,
                                 `item` varchar(400) NOT NULL,
                                 `total` double NOT NULL,
                                 `paid` double NOT NULL,
                                 `remain` double NOT NULL,
                                 `date` varchar(50) NOT NULL,
                                 `cus_id` int NOT NULL,
                                 `link` varchar(400) NOT NULL,
                                 `user_id` bigint DEFAULT NULL,
                                 PRIMARY KEY (`id`),
                                 KEY `company_id_iddx` (`cus_id`),
                                 KEY `user_id_fk_iddx` (`user_id`),
                                 CONSTRAINT `customer_idd` FOREIGN KEY (`cus_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE,
                                 CONSTRAINT `user_id_ffkk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `companysales`
--

DROP TABLE IF EXISTS `companysales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `companysales` (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `item` varchar(400) NOT NULL,
                                `total` double NOT NULL,
                                `paid` double NOT NULL,
                                `remain` double NOT NULL,
                                `date` varchar(50) NOT NULL,
                                `cus_id` int NOT NULL,
                                `link` varchar(400) NOT NULL,
                                `user_id` bigint DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                KEY `company_id_idx` (`cus_id`),
                                KEY `user_id_fk_idx` (`user_id`),
                                CONSTRAINT `company_id` FOREIGN KEY (`cus_id`) REFERENCES `companes` (`id`),
                                CONSTRAINT `user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `inv_code` varchar(100) NOT NULL,
                         `date` varchar(10) NOT NULL,
                         `price` double NOT NULL,
                         `cus_id` int NOT NULL,
                         `user_id` int NOT NULL,
                         `profit` double DEFAULT NULL,
                         `paid_type` varchar(45) DEFAULT NULL,
                         `due_date` varchar(10) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `inv_code_UNIQUE` (`inv_code`),
                         KEY `customerId_idx` (`cus_id`),
                         CONSTRAINT `customerId` FOREIGN KEY (`cus_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=176 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `sales_details`
--

DROP TABLE IF EXISTS `sales_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_details` (
                                 `id` int NOT NULL AUTO_INCREMENT,
                                 `inv_code` varchar(100) NOT NULL,
                                 `item_code` varchar(45) DEFAULT NULL,
                                 `amount` double NOT NULL,
                                 `price` double NOT NULL,
                                 `confirm` int DEFAULT '1',
                                 `cus_id` int NOT NULL,
                                 PRIMARY KEY (`id`),
                                 KEY `item_code_fkfk_idx` (`item_code`),
                                 KEY `inv_code_details_fk_idx` (`inv_code`),
                                 KEY `cus_id_details_fk_idx` (`cus_id`),
                                 CONSTRAINT `cus_id_details_fk` FOREIGN KEY (`cus_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE,
                                 CONSTRAINT `invCode_K` FOREIGN KEY (`inv_code`) REFERENCES `sales` (`inv_code`) ON DELETE CASCADE,
                                 CONSTRAINT `item_code_fkfk` FOREIGN KEY (`item_code`) REFERENCES `items` (`code`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `sales_pay`
--

DROP TABLE IF EXISTS `sales_pay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_pay` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `inv_code` int NOT NULL,
                             `date` varchar(10) NOT NULL,
                             `paid` double NOT NULL,
                             `cus_id` int NOT NULL,
                             `user_id` int NOT NULL,
                             `paid_type` varchar(45) DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             KEY `customerId_iddx` (`cus_id`),
                             KEY `INVCODEFK_idx` (`inv_code`),
                             CONSTRAINT `custId` FOREIGN KEY (`cus_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE,
                             CONSTRAINT `INVCODEFK` FOREIGN KEY (`inv_code`) REFERENCES `sales` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `outsales`
--

DROP TABLE IF EXISTS `outsales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `outsales` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `code` varchar(100) NOT NULL,
                            `date` varchar(10) NOT NULL,
                            `price` double NOT NULL,
                            `cus_id` int NOT NULL,
                            `user_id` int NOT NULL,
                            `paid_type` varchar(45) DEFAULT NULL,
                            `due_date` varchar(10) DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `customerID_idx` (`cus_id`),
                            CONSTRAINT `customer_ID` FOREIGN KEY (`cus_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `outsales_pay`
--

DROP TABLE IF EXISTS `outsales_pay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `outsales_pay` (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `inv_code` int NOT NULL,
                                `date` varchar(10) NOT NULL,
                                `paid` double NOT NULL,
                                `cus_id` int NOT NULL,
                                `user_id` int NOT NULL,
                                `paid_type` varchar(45) DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                KEY `custoId_iddx` (`cus_id`),
                                KEY `INVCode_FK_idx` (`inv_code`),
                                CONSTRAINT `custIDD` FOREIGN KEY (`cus_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE,
                                CONSTRAINT `INVCode_FK` FOREIGN KEY (`inv_code`) REFERENCES `outsales` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `showadvanced`
--

DROP TABLE IF EXISTS `showadvanced`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `showadvanced` (
                                `storePrice` int NOT NULL,
                                PRIMARY KEY (`storePrice`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;




SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));
