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

 Date: 18/11/2025 21:00:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for coupons
-- ----------------------------
DROP TABLE IF EXISTS `coupons`;
CREATE TABLE `coupons`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '优惠券代码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '优惠券名称',
  `discount` decimal(10, 2) NOT NULL COMMENT '折扣金额或比例',
  `min_amount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '最低消费金额',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `is_active` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否激活',
  `total_count` int NOT NULL DEFAULT 0 COMMENT '总数量',
  `used_count` int NOT NULL DEFAULT 0 COMMENT '已使用数量',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of coupons
-- ----------------------------
INSERT INTO `coupons` VALUES (1, 'SAVE10', '满100减10', 10.00, 100.00, '2025-11-18 00:00:00', '2025-12-31 23:59:59', 1, 100, 0);
INSERT INTO `coupons` VALUES (2, 'SAVE50', '满500减50', 50.00, 500.00, '2025-11-18 00:00:00', '2025-12-31 23:59:59', 1, 50, 0);
INSERT INTO `coupons` VALUES (3, 'DISCOUNT10', '9折优惠', 0.90, 0.00, '2025-11-18 00:00:00', '2025-12-31 23:59:59', 1, 200, 0);

-- ----------------------------
-- Table structure for promotions
-- ----------------------------
DROP TABLE IF EXISTS `promotions`;
CREATE TABLE `promotions`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动名称',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '活动描述',
  `type` int NOT NULL COMMENT '活动类型：1-限时折扣，2-满减活动',
  `discount` decimal(10, 2) NOT NULL COMMENT '折扣率(如0.9表示9折)或满减金额',
  `min_purchase` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '最低购买金额（用于满减活动）',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `is_active` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否激活',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of promotions
-- ----------------------------
INSERT INTO `promotions` VALUES (1, '黑色星期五折扣', '黑色星期五限时9折优惠', 1, 0.90, 0.00, '2025-11-28 00:00:00', '2025-11-28 23:59:59', 1);
INSERT INTO `promotions` VALUES (2, '年终大促', '满1000减100', 2, 100.00, 1000.00, '2025-12-01 00:00:00', '2025-12-31 23:59:59', 1);
INSERT INTO `promotions` VALUES (3, '清仓特卖', '清仓商品5折起', 1, 0.50, 0.00, '2025-11-20 00:00:00', '2025-11-30 23:59:59', 1);

-- ----------------------------
-- Table structure for promotion_statistics
-- ----------------------------
DROP TABLE IF EXISTS `promotion_statistics`;
CREATE TABLE `promotion_statistics`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `promotion_id` bigint NOT NULL COMMENT '关联的促销活动ID',
  `coupon_id` bigint NULL DEFAULT NULL COMMENT '关联的优惠券ID（可为空）',
  `participation_count` int NOT NULL DEFAULT 0 COMMENT '参与人数',
  `order_count` int NOT NULL DEFAULT 0 COMMENT '订单数量',
  `total_discount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '总折扣金额',
  `total_sales` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '活动期间总销售额',
  `statistics_date` date NOT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `promotion_id`(`promotion_id` ASC) USING BTREE,
  INDEX `coupon_id`(`coupon_id` ASC) USING BTREE,
  CONSTRAINT `promotion_statistics_ibfk_1` FOREIGN KEY (`promotion_id`) REFERENCES `promotions` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `promotion_statistics_ibfk_2` FOREIGN KEY (`coupon_id`) REFERENCES `coupons` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of promotion_statistics
-- ----------------------------
INSERT INTO `promotion_statistics` VALUES (1, 1, NULL, 150, 80, 12000.00, 240000.00, '2025-11-17');
INSERT INTO `promotion_statistics` VALUES (2, 2, NULL, 200, 120, 15000.00, 300000.00, '2025-11-17');
INSERT INTO `promotion_statistics` VALUES (3, 1, 1, 50, 30, 3000.00, 60000.00, '2025-11-17');

SET FOREIGN_KEY_CHECKS = 1;