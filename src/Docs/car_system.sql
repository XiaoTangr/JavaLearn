/*
 Navicat Premium Dump SQL

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80043 (8.0.43)
 Source Host           : localhost:3306
 Source Schema         : car_system

 Target Server Type    : MySQL
 Target Server Version : 80043 (8.0.43)
 File Encoding         : 65001

 Date: 13/11/2025 11:16:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for commercial_vehicles
-- ----------------------------
DROP TABLE IF EXISTS `commercial_vehicles`;
CREATE TABLE `commercial_vehicles`
(
    `vehicle_id`    bigint         NOT NULL,
    `load_capacity` decimal(10, 2) NOT NULL,
    `cargo_volume`  decimal(10, 2) NOT NULL,
    PRIMARY KEY (`vehicle_id`) USING BTREE,
    CONSTRAINT `commercial_vehicles_ibfk_1` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`vehicle_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`
(
    `order_id`    bigint         NOT NULL AUTO_INCREMENT,
    `user_id`     bigint         NOT NULL,
    `vehicle_id`  bigint         NOT NULL,
    `buy_count`   int            NOT NULL,
    `total_price` decimal(10, 2) NULL DEFAULT NULL,
    `create_time` bigint         NULL DEFAULT NULL,
    PRIMARY KEY (`order_id`) USING BTREE,
    INDEX `user_id` (`user_id` ASC) USING BTREE,
    INDEX `vehicle_id` (`vehicle_id` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for passenger_vehicles
-- ----------------------------
DROP TABLE IF EXISTS `passenger_vehicles`;
CREATE TABLE `passenger_vehicles`
(
    `vehicle_id` bigint                                                       NOT NULL,
    `seat_count` int                                                          NOT NULL,
    `fuel_type`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    PRIMARY KEY (`vehicle_id`) USING BTREE,
    CONSTRAINT `passenger_vehicles_ibfk_1` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`vehicle_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `user_id`    bigint                                                        NOT NULL AUTO_INCREMENT,
    `user_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `user_name`  varchar(225) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `password`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `active`     tinyint(1)                                                    NOT NULL DEFAULT 1,
    PRIMARY KEY (`user_id`) USING BTREE,
    UNIQUE INDEX `user_email` (`user_email` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for vehicles
-- ----------------------------
DROP TABLE IF EXISTS `vehicles`;
CREATE TABLE `vehicles`
(
    `vehicle_id`    bigint                                                        NOT NULL AUTO_INCREMENT,
    `vehicle_type`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `vehicle_brand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL     DEFAULT NULL,
    `vehicle_model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL     DEFAULT NULL,
    `vehicle_price` decimal(10, 2)                                                NOT NULL,
    `vehicle_stock` int                                                           NOT NULL DEFAULT 0,
    PRIMARY KEY (`vehicle_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 19
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer_service
-- ----------------------------
DROP TABLE IF EXISTS `customer_service`;
CREATE TABLE `customer_service`
(
    `id`          bigint                                                                                          NOT NULL AUTO_INCREMENT,
    `user_id`     bigint                                                                                          NOT NULL,
    `type`        enum ('consultation','complaint','feedback') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   NOT NULL,
    `subject`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci                                   NOT NULL,
    `content`     text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci                                           NOT NULL,
    `status`      enum ('open','processing','resolved','closed') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'open',
    `priority`    enum ('low','medium','high') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci                   NOT NULL DEFAULT 'medium',
    `create_time` bigint                                                                                          NOT NULL,
    `update_time` bigint                                                                                          NULL     DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `user_id` (`user_id` ASC) USING BTREE,
    CONSTRAINT `customer_service_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for service_ratings
-- ----------------------------
DROP TABLE IF EXISTS `service_ratings`;
CREATE TABLE `service_ratings`
(
    `id`          bigint                                                NOT NULL AUTO_INCREMENT,
    `service_id`  bigint                                                NOT NULL,
    `user_id`     bigint                                                NOT NULL,
    `rating`      int                                                   NOT NULL, -- 评分 1-5
    `comment`     text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
    `create_time` bigint                                                NOT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `service_id` (`service_id` ASC) USING BTREE,
    CONSTRAINT `service_ratings_ibfk_1` FOREIGN KEY (`service_id`) REFERENCES `customer_service` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
    CONSTRAINT `service_ratings_chk_1` CHECK ((`rating` between 1 and 5))
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `user_id`     bigint NOT NULL,
    `vehicle_id`  bigint NOT NULL,
    `quantity`    int    NOT NULL DEFAULT 1,
    `create_time` bigint NOT NULL,
    `update_time` bigint NOT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `user_id` (`user_id` ASC) USING BTREE,
    INDEX `vehicle_id` (`vehicle_id` ASC) USING BTREE,
    CONSTRAINT `shopping_cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
    CONSTRAINT `shopping_cart_ibfk_2` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`vehicle_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
