/*
 Navicat Premium Data Transfer

 Source Server         : MyLink_To_MySQL
 Source Server Type    : MySQL
 Source Server Version : 50730 (5.7.30)
 Source Host           : localhost:3306
 Source Schema         : javadb

 Target Server Type    : MySQL
 Target Server Version : 50730 (5.7.30)
 File Encoding         : 65001

 Date: 05/06/2023 22:30:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job` enum('图书管理员','甜点管理员') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `salary` double NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `join_date` date NULL DEFAULT NULL,
  `work_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (11, 'zzk', '00000', '图书管理员', 340.58, 20, '2002-05-02', '8:00-12:00');
INSERT INTO `admin` VALUES (12, 'fhb', '00000', '甜点管理员', 310.52, 20, '2012-08-23', '12:00-17:00');
INSERT INTO `admin` VALUES (13, 'kdy', '00000', '图书管理员', 225.02, 20, '2012-07-31', '17:00-22:00');
INSERT INTO `admin` VALUES (14, 'Sarah Gonzalez', 'jBHuN1k5xc', '甜点管理员', 176.69, 30, '2012-03-26', '8:00-12:00');
INSERT INTO `admin` VALUES (15, 'Shimizu Yamato', 'zo4Vknbmrf', '图书管理员', 839.84, 19, '2015-01-11', '12:00-17:00');
INSERT INTO `admin` VALUES (16, 'Gary Peterson', 'JMREuOcFg7', '甜点管理员', 870.18, 23, '2002-07-03', '17:00-22:00');
INSERT INTO `admin` VALUES (17, 'Koo Yu Ling', 'QEBwUODt46', '图书管理员', 425.12, 32, '2011-11-19', '8:00-12:00');
INSERT INTO `admin` VALUES (18, 'Micheal Powell', 'OFr7T2RnLn', '甜点管理员', 455.89, 45, '2012-06-24', '12:00-17:00');
INSERT INTO `admin` VALUES (19, 'Harada Yuna', '5T89b8Y28O', '图书管理员', 445.76, 44, '2019-09-27', '17:00-22:00');
INSERT INTO `admin` VALUES (20, 'Donna Griffin', 'r3i9lQ6gdt', '甜点管理员', 749.96, 22, '2021-04-03', '8:00-13:00');

SET FOREIGN_KEY_CHECKS = 1;
