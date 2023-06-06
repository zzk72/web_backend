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

 Date: 06/06/2023 13:31:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `salary` double NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `join_date` date NULL DEFAULT NULL,
  `work_time` enum('8:00-12:00','12:00-17:00','17:00-22:00') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (11, 'zzk', '00000', '图书管理员', 340.58, 2323, '2002-07-02', '8:00-12:00');
INSERT INTO `admin` VALUES (12, 'fhb', '00000', '甜点管理员', 310.52, 20, '2012-08-23', '12:00-17:00');
INSERT INTO `admin` VALUES (13, 'kdy', '00000', '图书管理员', 225.02, 20, '2012-07-31', '17:00-22:00');
INSERT INTO `admin` VALUES (14, 'Sarah Gonzalez', 'jBHuN1k5xc', '甜点管理员', 176.69, 30, '2012-03-26', '8:00-12:00');
INSERT INTO `admin` VALUES (15, 'Shimizu Yamato', 'zo4Vknbmrf', '图书管理员', 839.84, 19, '2015-01-11', '12:00-17:00');
INSERT INTO `admin` VALUES (16, 'Gary Peterson', 'JMREuOcFg7', '甜点管理员', 870.18, 23, '2002-07-03', '17:00-22:00');
INSERT INTO `admin` VALUES (17, 'Koo Yu Ling', 'QEBwUODt46', '图书管理员', 425.12, 32, '2011-11-19', '8:00-12:00');
INSERT INTO `admin` VALUES (18, 'Micheal Powell', 'OFr7T2RnLn', '甜点管理员', 455.89, 45, '2012-06-24', '12:00-17:00');
INSERT INTO `admin` VALUES (19, 'Harada Yuna', '5T89b8Y28O', '图书管理员', 445.76, 44, '2019-09-27', '17:00-22:00');
INSERT INTO `admin` VALUES (20, 'Donna Griffin', 'r3i9lQ6gdt', '甜点管理员', 749.96, 22, '2021-04-03', '8:00-12:00');

-- ----------------------------
-- Table structure for admin_bill
-- ----------------------------
DROP TABLE IF EXISTS `admin_bill`;
CREATE TABLE `admin_bill`  (
  `date` date NULL DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) NOT NULL,
  `bonus` double NULL DEFAULT NULL,
  `salary` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `bill_to_admin`(`admin_id`) USING BTREE,
  CONSTRAINT `bill_to_admin` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 367 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_bill
-- ----------------------------
INSERT INTO `admin_bill` VALUES ('2005-12-12', 267, 17, 300.75, 326.55);
INSERT INTO `admin_bill` VALUES ('2004-12-16', 268, 11, 322.58, 565.24);
INSERT INTO `admin_bill` VALUES ('2015-09-11', 269, 14, 291.42, 344.65);
INSERT INTO `admin_bill` VALUES ('2020-02-11', 270, 18, 381.12, 634.14);
INSERT INTO `admin_bill` VALUES ('2011-10-28', 271, 18, 147.81, 52.76);
INSERT INTO `admin_bill` VALUES ('2008-07-05', 272, 17, 276.09, 62.88);
INSERT INTO `admin_bill` VALUES ('2021-05-03', 273, 17, 517.36, 223.89);
INSERT INTO `admin_bill` VALUES ('2010-09-20', 274, 19, 660.23, 977.91);
INSERT INTO `admin_bill` VALUES ('2002-07-03', 275, 16, 310.11, 340.02);
INSERT INTO `admin_bill` VALUES ('2002-10-20', 276, 12, 80.63, 660.68);
INSERT INTO `admin_bill` VALUES ('2014-12-02', 277, 15, 470.91, 187.2);
INSERT INTO `admin_bill` VALUES ('2015-02-21', 278, 12, 411.33, 203.49);
INSERT INTO `admin_bill` VALUES ('2023-03-19', 279, 14, 506.63, 869.45);
INSERT INTO `admin_bill` VALUES ('2006-05-10', 280, 18, 404.61, 588.79);
INSERT INTO `admin_bill` VALUES ('2005-09-07', 281, 19, 766.62, 223.54);
INSERT INTO `admin_bill` VALUES ('2002-02-20', 282, 13, 970.29, 856.47);
INSERT INTO `admin_bill` VALUES ('2008-12-03', 283, 11, 422.71, 811.26);
INSERT INTO `admin_bill` VALUES ('2013-02-27', 284, 16, 10.48, 671.23);
INSERT INTO `admin_bill` VALUES ('2019-03-06', 285, 20, 254.76, 230.35);
INSERT INTO `admin_bill` VALUES ('2015-04-16', 286, 13, 760.81, 199.36);
INSERT INTO `admin_bill` VALUES ('2019-12-15', 287, 11, 41.65, 924.85);
INSERT INTO `admin_bill` VALUES ('2005-12-18', 288, 20, 66.51, 946.64);
INSERT INTO `admin_bill` VALUES ('2009-09-29', 289, 14, 444.52, 446.01);
INSERT INTO `admin_bill` VALUES ('2015-03-16', 290, 18, 661.84, 971.63);
INSERT INTO `admin_bill` VALUES ('2008-03-18', 291, 18, 340.51, 96.49);
INSERT INTO `admin_bill` VALUES ('2011-09-29', 292, 13, 154.8, 511.63);
INSERT INTO `admin_bill` VALUES ('2003-06-20', 293, 18, 711.35, 610.25);
INSERT INTO `admin_bill` VALUES ('2012-05-19', 294, 16, 841.77, 331.42);
INSERT INTO `admin_bill` VALUES ('2020-05-04', 295, 19, 262.15, 555.78);
INSERT INTO `admin_bill` VALUES ('2002-02-01', 296, 17, 531.3, 14);
INSERT INTO `admin_bill` VALUES ('2010-05-01', 297, 17, 739.88, 637.3);
INSERT INTO `admin_bill` VALUES ('2017-04-07', 298, 19, 509.4, 34.98);
INSERT INTO `admin_bill` VALUES ('2003-02-23', 299, 11, 32.68, 29.78);
INSERT INTO `admin_bill` VALUES ('2008-10-23', 300, 20, 96.55, 104.81);
INSERT INTO `admin_bill` VALUES ('2008-06-19', 301, 17, 461.84, 60.03);
INSERT INTO `admin_bill` VALUES ('2016-11-29', 302, 20, 99.48, 508.75);
INSERT INTO `admin_bill` VALUES ('2002-01-29', 303, 17, 337.37, 600.03);
INSERT INTO `admin_bill` VALUES ('2016-12-20', 304, 15, 242.21, 235.02);
INSERT INTO `admin_bill` VALUES ('2006-01-23', 305, 20, 860.54, 11.47);
INSERT INTO `admin_bill` VALUES ('2004-03-06', 306, 15, 229.41, 226.2);
INSERT INTO `admin_bill` VALUES ('2005-12-29', 307, 20, 327.8, 880.18);
INSERT INTO `admin_bill` VALUES ('2014-10-26', 308, 19, 952.3, 210.34);
INSERT INTO `admin_bill` VALUES ('2005-11-21', 309, 15, 491.27, 252.3);
INSERT INTO `admin_bill` VALUES ('2015-04-10', 310, 18, 944.74, 696.78);
INSERT INTO `admin_bill` VALUES ('2014-06-13', 311, 17, 91.54, 414.25);
INSERT INTO `admin_bill` VALUES ('2018-08-24', 312, 13, 974.51, 513.33);
INSERT INTO `admin_bill` VALUES ('2006-01-25', 313, 17, 145.08, 850.26);
INSERT INTO `admin_bill` VALUES ('2014-06-15', 314, 17, 356.92, 732.72);
INSERT INTO `admin_bill` VALUES ('2021-01-11', 315, 15, 20.53, 816.51);
INSERT INTO `admin_bill` VALUES ('2008-10-18', 316, 15, 196.48, 380.29);
INSERT INTO `admin_bill` VALUES ('2018-12-26', 317, 18, 815.26, 564.85);
INSERT INTO `admin_bill` VALUES ('2022-05-03', 318, 19, 63.78, 806.31);
INSERT INTO `admin_bill` VALUES ('2009-06-24', 319, 11, 956.85, 537.52);
INSERT INTO `admin_bill` VALUES ('2021-04-25', 320, 16, 208.33, 508.8);
INSERT INTO `admin_bill` VALUES ('2020-03-10', 321, 19, 6.63, 285.28);
INSERT INTO `admin_bill` VALUES ('2003-05-22', 322, 12, 355.57, 822.43);
INSERT INTO `admin_bill` VALUES ('2000-01-02', 323, 12, 20.24, 107.7);
INSERT INTO `admin_bill` VALUES ('2012-12-15', 324, 12, 219.72, 253.91);
INSERT INTO `admin_bill` VALUES ('2009-03-08', 325, 11, 742.72, 313.31);
INSERT INTO `admin_bill` VALUES ('2001-05-11', 326, 14, 612.91, 911.63);
INSERT INTO `admin_bill` VALUES ('2004-03-10', 327, 12, 620.34, 258.32);
INSERT INTO `admin_bill` VALUES ('2013-07-03', 328, 19, 343.92, 554.5);
INSERT INTO `admin_bill` VALUES ('2009-04-27', 329, 14, 688.72, 902.52);
INSERT INTO `admin_bill` VALUES ('2018-12-01', 330, 19, 165.05, 809.88);
INSERT INTO `admin_bill` VALUES ('2003-02-01', 331, 19, 681.29, 552.39);
INSERT INTO `admin_bill` VALUES ('2021-07-02', 332, 12, 329.12, 504);
INSERT INTO `admin_bill` VALUES ('2022-06-26', 333, 14, 642.3, 483.17);
INSERT INTO `admin_bill` VALUES ('2003-09-27', 334, 17, 68.8, 874.75);
INSERT INTO `admin_bill` VALUES ('2015-10-19', 335, 18, 525.82, 718.94);
INSERT INTO `admin_bill` VALUES ('2013-02-04', 336, 19, 789.6, 63.23);
INSERT INTO `admin_bill` VALUES ('2017-09-18', 337, 17, 843.32, 596.89);
INSERT INTO `admin_bill` VALUES ('2014-01-31', 338, 19, 350, 203.24);
INSERT INTO `admin_bill` VALUES ('2003-07-22', 339, 17, 3.36, 213.11);
INSERT INTO `admin_bill` VALUES ('2006-10-20', 340, 15, 804.14, 475.42);
INSERT INTO `admin_bill` VALUES ('2006-01-23', 341, 14, 476.05, 18.69);
INSERT INTO `admin_bill` VALUES ('2001-12-19', 342, 16, 30.47, 635.44);
INSERT INTO `admin_bill` VALUES ('2002-07-16', 343, 16, 557.63, 165.96);
INSERT INTO `admin_bill` VALUES ('2000-07-15', 344, 18, 590.4, 917.09);
INSERT INTO `admin_bill` VALUES ('2003-11-16', 345, 16, 625.6, 25.83);
INSERT INTO `admin_bill` VALUES ('2007-10-27', 346, 14, 500.18, 310.39);
INSERT INTO `admin_bill` VALUES ('2018-08-31', 347, 13, 742.43, 932.06);
INSERT INTO `admin_bill` VALUES ('2013-11-29', 348, 12, 59.27, 857.27);
INSERT INTO `admin_bill` VALUES ('2020-01-22', 349, 15, 391.46, 793.28);
INSERT INTO `admin_bill` VALUES ('2005-09-05', 350, 13, 244.59, 105.4);
INSERT INTO `admin_bill` VALUES ('2019-12-16', 351, 11, 689.02, 204.7);
INSERT INTO `admin_bill` VALUES ('2000-09-14', 352, 11, 387.88, 276.68);
INSERT INTO `admin_bill` VALUES ('2016-10-21', 353, 15, 98.89, 273.58);
INSERT INTO `admin_bill` VALUES ('2021-06-18', 354, 13, 483.38, 381.45);
INSERT INTO `admin_bill` VALUES ('2001-05-18', 355, 15, 969.05, 466.38);
INSERT INTO `admin_bill` VALUES ('2006-07-08', 356, 17, 356.61, 188.08);
INSERT INTO `admin_bill` VALUES ('2000-08-27', 357, 15, 809.45, 257.64);
INSERT INTO `admin_bill` VALUES ('2019-11-19', 358, 16, 679.44, 152.25);
INSERT INTO `admin_bill` VALUES ('2005-05-09', 359, 12, 370.36, 582.73);
INSERT INTO `admin_bill` VALUES ('2018-04-01', 360, 20, 727.28, 92.17);
INSERT INTO `admin_bill` VALUES ('2017-11-12', 361, 11, 2.51, 676.73);
INSERT INTO `admin_bill` VALUES ('2009-07-08', 362, 14, 217.55, 308.98);
INSERT INTO `admin_bill` VALUES ('2008-09-03', 363, 20, 924.54, 69.46);
INSERT INTO `admin_bill` VALUES ('2003-08-18', 364, 20, 342.74, 886.02);
INSERT INTO `admin_bill` VALUES ('2003-06-14', 365, 16, 584.91, 274.68);
INSERT INTO `admin_bill` VALUES ('2003-06-22', 366, 15, 945.25, 690.87);

-- ----------------------------
-- Table structure for admin_clock
-- ----------------------------
DROP TABLE IF EXISTS `admin_clock`;
CREATE TABLE `admin_clock`  (
  `id` int(11) NOT NULL,
  `admin_id` int(11) NULL DEFAULT NULL,
  `is_clock` enum('0','1','2') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `to_admin`(`admin_id`) USING BTREE,
  CONSTRAINT `to_admin` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_clock
-- ----------------------------
INSERT INTO `admin_clock` VALUES (1, 17, '0', '2002-07-07');
INSERT INTO `admin_clock` VALUES (2, 20, '1', '2003-06-04');
INSERT INTO `admin_clock` VALUES (3, 12, '2', '2021-10-31');
INSERT INTO `admin_clock` VALUES (4, 20, '2', '2007-12-03');
INSERT INTO `admin_clock` VALUES (5, 16, '1', '2007-01-31');
INSERT INTO `admin_clock` VALUES (6, 15, '2', '2017-06-21');
INSERT INTO `admin_clock` VALUES (7, 20, '1', '2008-10-02');
INSERT INTO `admin_clock` VALUES (8, 19, '1', '2021-12-21');
INSERT INTO `admin_clock` VALUES (9, 16, '1', '2009-03-18');
INSERT INTO `admin_clock` VALUES (10, 12, '2', '2016-09-15');
INSERT INTO `admin_clock` VALUES (11, 19, '0', '2019-11-28');
INSERT INTO `admin_clock` VALUES (12, 19, '1', '2016-11-27');
INSERT INTO `admin_clock` VALUES (13, 12, '2', '2008-01-25');
INSERT INTO `admin_clock` VALUES (14, 18, '1', '2020-06-20');
INSERT INTO `admin_clock` VALUES (15, 15, '2', '2004-12-24');
INSERT INTO `admin_clock` VALUES (16, 12, '0', '2017-12-11');
INSERT INTO `admin_clock` VALUES (17, 11, '2', '2009-07-13');
INSERT INTO `admin_clock` VALUES (18, 16, '0', '2018-02-27');
INSERT INTO `admin_clock` VALUES (19, 16, '1', '2004-03-13');
INSERT INTO `admin_clock` VALUES (20, 18, '1', '2007-10-07');
INSERT INTO `admin_clock` VALUES (21, 20, '2', '2005-09-02');
INSERT INTO `admin_clock` VALUES (22, 19, '0', '2002-12-12');
INSERT INTO `admin_clock` VALUES (23, 11, '1', '2021-03-26');
INSERT INTO `admin_clock` VALUES (24, 18, '0', '2004-01-12');
INSERT INTO `admin_clock` VALUES (25, 11, '1', '2000-02-21');
INSERT INTO `admin_clock` VALUES (26, 19, '2', '2004-10-05');
INSERT INTO `admin_clock` VALUES (27, 11, '1', '2002-05-24');
INSERT INTO `admin_clock` VALUES (28, 16, '1', '2006-03-31');
INSERT INTO `admin_clock` VALUES (29, 11, '2', '2022-07-30');
INSERT INTO `admin_clock` VALUES (30, 19, '2', '2016-09-07');
INSERT INTO `admin_clock` VALUES (31, 18, '0', '2008-11-30');
INSERT INTO `admin_clock` VALUES (32, 15, '2', '2017-12-25');
INSERT INTO `admin_clock` VALUES (33, 20, '1', '2020-10-09');
INSERT INTO `admin_clock` VALUES (34, 18, '1', '2008-12-05');
INSERT INTO `admin_clock` VALUES (35, 20, '0', '2001-10-28');
INSERT INTO `admin_clock` VALUES (36, 12, '1', '2000-10-25');
INSERT INTO `admin_clock` VALUES (37, 16, '1', '2007-04-28');
INSERT INTO `admin_clock` VALUES (38, 17, '1', '2014-05-17');
INSERT INTO `admin_clock` VALUES (39, 16, '1', '2020-11-05');
INSERT INTO `admin_clock` VALUES (40, 20, '0', '2020-03-11');
INSERT INTO `admin_clock` VALUES (41, 20, '2', '2005-03-31');
INSERT INTO `admin_clock` VALUES (42, 14, '2', '2013-06-28');
INSERT INTO `admin_clock` VALUES (43, 17, '0', '2010-10-08');
INSERT INTO `admin_clock` VALUES (44, 12, '0', '2012-12-12');
INSERT INTO `admin_clock` VALUES (45, 17, '0', '2003-04-25');
INSERT INTO `admin_clock` VALUES (46, 19, '0', '2014-05-05');
INSERT INTO `admin_clock` VALUES (47, 18, '0', '2004-08-07');
INSERT INTO `admin_clock` VALUES (48, 20, '0', '2001-12-02');
INSERT INTO `admin_clock` VALUES (49, 17, '0', '2020-12-27');
INSERT INTO `admin_clock` VALUES (50, 20, '2', '2004-09-30');
INSERT INTO `admin_clock` VALUES (51, 19, '0', '2003-02-01');
INSERT INTO `admin_clock` VALUES (52, 11, '1', '2008-09-28');
INSERT INTO `admin_clock` VALUES (53, 19, '2', '2015-03-12');
INSERT INTO `admin_clock` VALUES (54, 14, '1', '2000-04-17');
INSERT INTO `admin_clock` VALUES (55, 11, '2', '2006-12-25');
INSERT INTO `admin_clock` VALUES (56, 11, '2', '2020-06-05');
INSERT INTO `admin_clock` VALUES (57, 17, '1', '2019-05-23');
INSERT INTO `admin_clock` VALUES (58, 11, '0', '2022-08-23');
INSERT INTO `admin_clock` VALUES (59, 20, '1', '2004-01-02');
INSERT INTO `admin_clock` VALUES (60, 17, '0', '2013-04-01');
INSERT INTO `admin_clock` VALUES (61, 16, '2', '2019-06-16');
INSERT INTO `admin_clock` VALUES (62, 17, '1', '2011-05-24');
INSERT INTO `admin_clock` VALUES (63, 19, '1', '2015-06-05');
INSERT INTO `admin_clock` VALUES (64, 16, '0', '2014-05-26');
INSERT INTO `admin_clock` VALUES (65, 20, '2', '2014-04-21');
INSERT INTO `admin_clock` VALUES (66, 11, '1', '2020-01-24');
INSERT INTO `admin_clock` VALUES (67, 13, '2', '2014-01-20');
INSERT INTO `admin_clock` VALUES (68, 15, '1', '2017-03-28');
INSERT INTO `admin_clock` VALUES (69, 11, '2', '2001-10-13');
INSERT INTO `admin_clock` VALUES (70, 14, '2', '2000-12-31');
INSERT INTO `admin_clock` VALUES (71, 12, '0', '2014-12-24');
INSERT INTO `admin_clock` VALUES (72, 14, '0', '2010-02-04');
INSERT INTO `admin_clock` VALUES (73, 11, '1', '2013-11-21');
INSERT INTO `admin_clock` VALUES (74, 18, '2', '2011-09-24');
INSERT INTO `admin_clock` VALUES (75, 17, '1', '2004-04-27');
INSERT INTO `admin_clock` VALUES (76, 13, '0', '2009-10-06');
INSERT INTO `admin_clock` VALUES (77, 13, '0', '2018-06-06');
INSERT INTO `admin_clock` VALUES (78, 12, '0', '2007-04-25');
INSERT INTO `admin_clock` VALUES (79, 15, '1', '2010-03-31');
INSERT INTO `admin_clock` VALUES (80, 11, '2', '2012-10-04');
INSERT INTO `admin_clock` VALUES (81, 20, '0', '2003-04-11');
INSERT INTO `admin_clock` VALUES (82, 14, '0', '2023-01-13');
INSERT INTO `admin_clock` VALUES (83, 19, '1', '2006-11-26');
INSERT INTO `admin_clock` VALUES (84, 18, '2', '2012-02-18');
INSERT INTO `admin_clock` VALUES (85, 19, '0', '2023-02-04');
INSERT INTO `admin_clock` VALUES (86, 19, '1', '2009-01-31');
INSERT INTO `admin_clock` VALUES (87, 20, '1', '2011-06-08');
INSERT INTO `admin_clock` VALUES (88, 13, '2', '2016-09-19');
INSERT INTO `admin_clock` VALUES (89, 12, '1', '2020-12-23');
INSERT INTO `admin_clock` VALUES (90, 14, '0', '2000-07-11');
INSERT INTO `admin_clock` VALUES (91, 13, '2', '2009-11-21');
INSERT INTO `admin_clock` VALUES (92, 16, '1', '2015-08-05');
INSERT INTO `admin_clock` VALUES (93, 20, '1', '2010-12-05');
INSERT INTO `admin_clock` VALUES (94, 18, '1', '2015-10-14');
INSERT INTO `admin_clock` VALUES (95, 17, '0', '2015-07-10');
INSERT INTO `admin_clock` VALUES (96, 14, '1', '2007-08-11');
INSERT INTO `admin_clock` VALUES (97, 16, '0', '2014-01-28');
INSERT INTO `admin_clock` VALUES (98, 17, '0', '2001-10-09');
INSERT INTO `admin_clock` VALUES (99, 11, '1', '2003-10-31');
INSERT INTO `admin_clock` VALUES (100, 12, '1', '2008-12-25');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `author` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `brief_introduction` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `price` double NULL DEFAULT NULL,
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `image_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `classification` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `storage` int(11) NULL DEFAULT NULL,
  `e_price` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 701 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (0, '《红与黑》', '斯坦达尔', '本书真实地再现了法国波旁王朝复辟以后的历史氛围。作者利用细腻的观察和切身的体验,准确生动地描绘了外省生活的封闭狭隘和被铜臭气毒化的心灵.', 38.5, '6soNF4BJ6q', '红与黑.jpg', '法国文学', 108, 13.87);
INSERT INTO `book` VALUES (3, '《百年孤独》', '加西亚·马尔克斯', '《百年孤独》是魔幻现实主义文学的代表作，描写了布恩迪亚家族七代人的传奇故事，以及加勒比海沿岸小镇马孔多的百年兴衰，反映了拉丁美洲一个世纪以来风云 变幻的历史。作品融入神话传说、民间故事、宗教典故等神秘因素，巧妙地糅合了现实与虚幻，展现出一个瑰丽的想象世界，成为20世界重要的经典文学巨著之一。1982年加西亚•马尔克斯获得诺贝尔文学奖，奠定世界级文学大师的地位，很大程度上乃是凭借《百年孤独》的巨大影响。', 62.5, 'c0fwfXILrN', '百年孤独.jpg', '魔幻现实主义', 19, 15.21);
INSERT INTO `book` VALUES (4, '《傲慢与偏见》', '简·奥斯汀', '《傲慢与偏见》是简·奥斯汀的代表作，是一部描写爱情与婚姻的经典小说。作品以男女主人公达西和伊丽莎白由于傲慢和偏见而产生的爱情纠葛为线索，共写了四起姻缘：伊丽莎白与达西、简与宾利、莉迪亚与威克姆、夏洛蒂与柯林斯。这部小说因其对社会关系的真实描绘和机智幽默的写作风格而广受赞誉', 34.3, 'sqYlN6oRRJ', '傲慢与偏见.jpg', '人生哲理', 832, 11.66);
INSERT INTO `book` VALUES (5, '《战争与和平》', '列夫·托尔斯泰', '《战争与和平》(1866—1869)描写1812年俄法战争的全过程，以当时四大贵族家庭的人物活动为线索，反映了1805至1820年间许多重大的历史事件，以及各阶层的现实生活，抨击了那些谈吐优雅，但漠视祖国命运的贵族，歌颂了青年一代在战争中表现出来的爱国主义和英雄主义精神，是一部史诗般的鸿篇巨制。', 48, '63MOCGPopF', '战争与和平.jpg', '俄国文学', 497, 17.91);
INSERT INTO `book` VALUES (6, '《了不起的盖茨比》', 'F·斯科特·菲茨杰拉德', '★ 20世纪全球百部英语小说第2名\r\n★ 村上春树最爱的书\r\n★ 村上春树万字长文导读\r\n★ 全新译本完美呈现原著魅力\r\n★ 如果没有与《了不起的盖茨比》相遇，我写出来的小说会与现在完全不同，或者也许什么都不写。——村上春树\r\n★《了不起的盖茨比》建立在“幻象的破灭”上。正因这样的幻象，世界才如此鲜艳。你无需理会真假，但求沾染那份魔术般的光彩就是了。——菲茨杰拉德\r\n中西部小子盖茨比到东部闯荡一夕致富，他在自己的豪宅夜夜宴客，俨然慷慨荒唐的富豪大亨；他梦幻地看着纽约长岛上一座闪着绿光的灯塔，寻觅着梦寐以求的女人黛西。邻居尼克，眼看着盖茨比的宾客们接受他的招待却冷漠无情，眼看着盖茨比奋力追求那腐败的虚华。盖兹比最后的结局，让尼克对东部浮华的名流生活梦碎，宛如看着繁华楼起再看着它楼塌。', 49.5, '4gjgZm30wV', '了不起的盖茨比.jpg', '人生哲理', 848, 17.72);
INSERT INTO `book` VALUES (7, '《麦田里的守望者》', 'J·D·塞林格', '又译为《麦田捕手》，为美国作家J.D.塞林格于1951年发表的长篇小说。这部有争议的作品原本是面向成年读者的，但迅速因其青春期焦虑和隔绝的主题而在青少年读者中流行。\r\n\r\n该书以主人公霍尔顿·考尔菲德第一人称口吻讲述自己被学校开除学籍后在纽约城游荡将近两昼夜，企图逃出虚伪的成人世界、去寻求纯洁与真理的经历与感受。\r\n\r\n该书于1951年出版之后，立刻引起巨大的轰动，受到读者——特别是青年人——的热烈的欢迎，被翻译为多国语版。小说每年大约有250,000本售出、总计为6500万本。《时代杂志》将《麦田里的守望者》列在“2005年度百大英语小说（自1923年起）”榜上，现代图书馆及其读者也将其列在20世纪百大英文小说榜上。赞赏者认为本书用青少年的口吻平铺直叙，增加了作品的感染力，传神地描写主角的内心思维，并说出了青少年不满成年世界充满虚伪欺瞒的心声。批评者则认为书中主角离经叛道，逃学、吸烟、喝酒又满嘴粗话，会给年轻读者带来不良影响。当时许多图书馆及学校将之列为禁书，并被列在美国最具挑战性图书榜上。但现在这本书却是许多美国学校的指定读物。有的评论家说，它“大大地影响了好几代美国青年”。而且有学者认为，霍尔顿是当代美国文学中最早出现的反英雄形象之一。', 38.8, 'VABS7igh7z', '麦田里的守望者.jpg', '人生哲理', 964, 13.34);
INSERT INTO `book` VALUES (8, '《飘》', '玛格丽特·米切尔', '仅仅写了一部作品就名扬天下并在文坛上占有一席之地的作家是绝无仅有的。而美国女作家玛格丽特·米切尔便是这样一位绝无仅有的作家。她惟一的作品《飘》一经问世便成了美国小说中最畅销的作品。自1936年出版之日起，《飘》这部美国内战时期的罗曼史便打破了所有的出版记录。1937年，小说获得普利策奖。三年后被改编成电影，连电影也成了美国电影史上的经典之作。', 43.5, 'IFcqTBL3sC', '飘.jpg', '美国文学', 17, 16.26);
INSERT INTO `book` VALUES (9, '《平凡的世界》', '路遥', '《平凡的世界》是一部现实主义小说，也是一部小说形式的家族史。作者浓缩了中国西北农村的历史变迁过程，在小说中全景式地表现了中国当代城乡的社会生活。在近十年的广阔背景下，通过复杂的矛盾纠葛，刻划社会各阶层众多普通人的形象。劳动与爱情，挫折与追求，痛苦与欢乐，日常生活与巨大社会冲突，纷繁地交织在一起，深刻地展示了普通人在大时代历史进程中所走过的艰难曲折的道路。', 81.56, 'LYwBbW14NI', '平凡的世界.jpg', '乡土文学', 352, 14.07);
INSERT INTO `book` VALUES (10, '《红楼梦》', ' 曹雪芹', '《红楼梦》是一部百科全书式的长篇小说。以宝黛爱情悲剧为主线，以四大家族的荣辱兴衰为背景，描绘出18世纪中国封建社会的方方面面，以及封建专制下新兴资本主义民主思想的萌动。结构宏大、情节委婉、细节精致，人物形象栩栩如生，声口毕现，堪称中国古代小说中的经 典。', 43.88, '9t8Ai5CnCF', '红楼梦.jpg', '中国古典小说', 794, 12.2);
INSERT INTO `book` VALUES (11, '《悲惨世界》', '维克多·雨果', '\'\'悲惨世界 篇幅浩大，卷帙繁多，作者从1828年起构思，到1845年动笔创作，直至1861年才完稿出书，历时三十余年。雨果的创作动机来自这样一件事实：1801年，一个名叫彼埃尔·莫的穷苦农民，因饥饿而偷了一块面包，被判五年苦役，刑满释放后，持黄色身份证找活儿干又处处碰壁。到了1828年，雨果又着手搜集有关米奥利斯主教及其家庭的资料。这样，他就掌握了这部小说的原始素材，开始酝酿写一个释放的苦役犯受到一位圣徒式的主教的感化而弃恶从善的故事。继而，他又设想把苦役犯变成企业家。在1829年和1830年间，作者还大量搜集有关黑玻璃制造业的材料，这便是冉阿让到海滨蒙特伊，化名为马德兰先生，开办工厂并发迹的由来。', 93.39, 'Im3bWirhg0', '悲惨世界.jpg', '法国文学', 158, 16.69);
INSERT INTO `book` VALUES (12, '《追风筝的人》', '卡勒德·胡赛尼', '《追风筝的人》是美籍阿富汗作家卡勒德·胡赛尼（Khaled Hosseini）的第一部长篇小说，译者李继宏，上海人民出版社于2003年出版，是美国2005年的排名第三的畅销书。全书围绕风筝与阿富汗的两个少年之间展开，一个富家少年与家中仆人关于风筝的故事，关于人性的背叛与救赎。', 38.5, '6soNF4BJ6q', '追风筝的人.jpg', '法国文学', 108, 13.87);
INSERT INTO `book` VALUES (13, '《黑暗森林》', '刘慈欣', '三体人在利用魔法般的科技锁死了地球人的科学之后，庞大的宇宙舰队杀气腾腾地直扑太阳系，意欲清除地球文明。\r\n　　面对前所未有的危局，经历过无数磨难的地球人组建起同样庞大的太空舰队，同时，利用三体人思维透明的致命缺陷，制订了神秘莫测的“面壁计划”，精选出四位“面壁者”。秘密展开对三体人的反击。\r\n　　三体人自身虽然无法识破人类的诡谲计谋，却依靠由地球人中的背叛者挑选出的“破壁人”，与“面壁者”展开智慧博弈……\r\n　　“面壁计划”究竟能否成功？地球人究竟能否在这场你死我活的文明生存竞争中战而胜之？神秘的\r\n　　“黑暗森林”究竟意味着什么？', 62.5, 'c0fwfXILrN', '黑暗森林.jpg', '科幻', 19, 15.21);
INSERT INTO `book` VALUES (14, '《西游记》', '吴承恩', '《西游记》 - 吴承恩', 34.3, 'sqYlN6oRRJ', '西游记.jpg', '中国古典小说', 832, 11.66);
INSERT INTO `book` VALUES (15, '《射雕英雄传》', '金庸', '《射雕英雄传》是金庸先生的经典武侠小说之一。故事发生在宋朝末年，以主人公郭靖为中心展开。郭靖是一个善良而正直的青年，他在逃亡途中结识了神秘的老人黄药师，并从他那里学到了武功的精髓。在成长过程中，郭靖遇到了各种挑战和冒险，结交了许多伙伴，包括江南七侠和神雕侠等。他们一起对抗邪恶势力，并寻找武林至宝“九阳真经”。整个故事情节跌宕起伏，充满了戏剧性的冲突和意想不到的转折。《射雕英雄传》展现了江湖侠义精神的世界，描绘了各式各样的英雄人物，让读者深陷其中，引发对正义、勇气和忠诚的思考。', 48, '63MOCGPopF', '射雕英雄传.jpg', '武侠小说', 497, 17.91);
INSERT INTO `book` VALUES (16, '《霍乱时期的爱情》', '加西亚·马尔克斯', '《霍乱时期的爱情》是诺贝尔文学奖得主加西亚·马尔克斯的经典之作。这部小说以哥伦比亚的一个小镇为背景，讲述了一个富有诗意和魔幻色彩的爱情故事。故事主要围绕着主人公费尔明娜·达萨与弗洛伦蒂诺·阿里萨之间的纠葛展开。两人年少时相爱，然而费尔明娜的父亲却不允许他们在一起，将费尔明娜送入修道院。在分离的年月里，弗洛伦蒂诺一直保持着对费尔明娜的深情，他用一封封充满激情的情书寄托着自己的爱。然而，他们的爱情面临了时间和社会的考验。小说通过描写复杂的人际关系、爱情的坚韧与不灭，以及生命的不可预知性，展现了作者独特的写作风格和对人性的深刻洞察力。', 49.5, '4gjgZm30wV', '霍乱时期的爱情.jpg', '魔幻现实主义', 848, 17.72);
INSERT INTO `book` VALUES (17, '《牧羊少年奇幻之旅》', '保罗·柯艾略', '《牧羊少年奇幻之旅》是保罗·柯艾略的一部寓言式小说。故事以一个年轻的牧羊少年圣地亚哥为主角，他渴望实现自己的梦想，探索未知的世界。圣地亚哥经历了一系列的冒险和遇见了各种各样的人物，其中包括一位神秘的老人墨瑞科，他引导着圣地亚哥踏上寻找宝藏的旅程。在这个旅程中，圣地亚哥遇到了沙漠、绿洲和金字塔等神奇的地方，他也逐渐领悟到了关于自我发现、命运和宇宙奥秘的智慧。这部小说融合了冒险、哲学和魔幻元素，通过简洁而富有启发性的语言，传达了对梦想追求和人生意义的思考。', 38.8, 'VABS7igh7z', '牧羊少年奇幻之旅.jpg', '人生哲理', 964, 13.34);

-- ----------------------------
-- Table structure for book_order
-- ----------------------------
DROP TABLE IF EXISTS `book_order`;
CREATE TABLE `book_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NULL DEFAULT NULL,
  `buy_time` date NULL DEFAULT NULL,
  `discount` double NULL DEFAULT NULL,
  `total_price` double NOT NULL,
  `buy_nums` int(11) NULL DEFAULT NULL,
  `ebook_flag` int(255) NULL DEFAULT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `to_user`(`uid`) USING BTREE,
  INDEX `to_book`(`book_id`) USING BTREE,
  CONSTRAINT `to_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `to_user` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 408 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_order
-- ----------------------------
INSERT INTO `book_order` VALUES (305, 5, '2023-06-04', 0.6, 214.746, 1, 1, 2);
INSERT INTO `book_order` VALUES (306, 5, '2023-06-05', 1, 357.91, 1, 1, 2);
INSERT INTO `book_order` VALUES (308, 15, '2023-02-11', 786.63, 757.89, 174, 838, 117);
INSERT INTO `book_order` VALUES (316, 9, '2014-01-11', 923.9, 515.53, 474, 374, 208);
INSERT INTO `book_order` VALUES (318, 4, '2004-02-14', 367.01, 979.22, 158, 62, 177);
INSERT INTO `book_order` VALUES (322, 3, '2001-03-23', 440.43, 160.87, 670, 985, 200);
INSERT INTO `book_order` VALUES (327, 14, '2001-08-04', 964.03, 926.96, 16, 997, 166);
INSERT INTO `book_order` VALUES (330, 14, '2013-09-30', 767.31, 900.46, 755, 405, 188);
INSERT INTO `book_order` VALUES (331, 0, '2021-06-29', 846.3, 540.95, 234, 460, 152);
INSERT INTO `book_order` VALUES (335, 10, '2006-11-30', 813.8, 559.28, 342, 10, 208);
INSERT INTO `book_order` VALUES (338, 12, '2023-01-01', 284.92, 855.69, 674, 792, 154);
INSERT INTO `book_order` VALUES (345, 17, '2004-08-18', 481.96, 278.6, 96, 469, 128);
INSERT INTO `book_order` VALUES (348, 11, '2009-02-22', 899.96, 78.45, 179, 872, 162);
INSERT INTO `book_order` VALUES (350, 16, '2009-08-17', 573.98, 508.34, 939, 329, 144);
INSERT INTO `book_order` VALUES (369, 14, '2020-05-29', 791.97, 70.51, 705, 849, 198);
INSERT INTO `book_order` VALUES (372, 6, '2002-02-19', 933.63, 615.02, 324, 856, 165);
INSERT INTO `book_order` VALUES (373, 8, '2014-11-25', 748.31, 520.61, 250, 296, 110);
INSERT INTO `book_order` VALUES (375, 15, '2018-09-30', 939.58, 467.53, 494, 7, 175);
INSERT INTO `book_order` VALUES (377, 16, '2012-04-01', 233.9, 466.88, 422, 728, 136);
INSERT INTO `book_order` VALUES (382, 12, '2007-08-02', 114.76, 542.19, 381, 793, 155);
INSERT INTO `book_order` VALUES (384, 13, '2005-04-30', 640.72, 330.84, 571, 742, 137);
INSERT INTO `book_order` VALUES (388, 13, '2004-05-28', 548.63, 901.52, 944, 220, 184);
INSERT INTO `book_order` VALUES (389, 8, '2008-02-19', 760.13, 21.39, 921, 8, 152);
INSERT INTO `book_order` VALUES (390, 17, '2001-07-21', 66.35, 491.95, 98, 266, 207);
INSERT INTO `book_order` VALUES (391, 6, '2009-09-18', 429.36, 132.86, 30, 629, 176);
INSERT INTO `book_order` VALUES (392, 11, '2014-02-18', 881.9, 990.51, 780, 950, 182);
INSERT INTO `book_order` VALUES (403, 17, '2021-12-01', 282.94, 806.51, 889, 61, 189);
INSERT INTO `book_order` VALUES (407, 5, '2023-06-06', 1, 17.91, 1, 1, 2);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NULL DEFAULT NULL,
  `comment` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `uid` int(11) NULL DEFAULT NULL,
  `comment_date` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `comment_to_book`(`book_id`) USING BTREE,
  INDEX `comment_to_user`(`uid`) USING BTREE,
  CONSTRAINT `comment_to_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_to_user` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 197 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (192, 0, 'hhhhhhhh', 2, '2023-06-04');
INSERT INTO `comment` VALUES (193, 0, 'ttttttt', 2, '2023-06-05');
INSERT INTO `comment` VALUES (194, 0, 'ttttttt', 2, '2023-06-05');

-- ----------------------------
-- Table structure for dessert
-- ----------------------------
DROP TABLE IF EXISTS `dessert`;
CREATE TABLE `dessert`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `cost` decimal(10, 2) NULL DEFAULT NULL,
  `brief_introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `image_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `storage` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 167 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of dessert
-- ----------------------------
INSERT INTO `dessert` VALUES (147, '巧克力蛋糕', 9.99, 4.50, '层层巧克力蛋糕，上面覆盖着丝滑的巧克力奶油。', '1.jpg', 56);
INSERT INTO `dessert` VALUES (148, '香草冰淇淋', 4.99, 2.50, '经典的冰淇淋口味，口感柔滑，带有香草的芳香。', '2.jpg', 24);
INSERT INTO `dessert` VALUES (149, '提拉米苏', 7.99, 3.75, '一种意大利传统甜点，由层层咖啡浸泡的软饼干和奶油芝士制成。', '3.jpg', 65);
INSERT INTO `dessert` VALUES (150, '蓝莓派', 6.49, 3.25, '使用新鲜蓝莓制成的馅料，包裹在酥皮饼干中，口感酸甜。', '4.jpg', 22);
INSERT INTO `dessert` VALUES (151, '法式马卡龙', 3.99, 1.75, '杏仁粉制成的轻盈甜点，带有各种不同的口味和色彩。', '5.jpg', 6);
INSERT INTO `dessert` VALUES (152, '柠檬慕斯', 5.99, 2.85, '清爽的柠檬慕斯奶油，搭配松脆的饼干屑。', '6.jpg', 78);
INSERT INTO `dessert` VALUES (153, '草莓奶油蛋糕', 8.49, 4.25, '柔软的蛋糕，覆盖着奶油霜和新鲜草莓。', '7.jpg', 32);
INSERT INTO `dessert` VALUES (154, '巧克力曲奇', 3.49, 1.50, '脆脆的巧克力曲奇饼干，里面还有巧克力碎片。', '8.jpg', 88);
INSERT INTO `dessert` VALUES (155, '红丝绒蛋糕', 7.99, 3.75, '红色的巧克力蛋糕，搭配奶油芝士霜，味道丰富。', '9.jpg', 77);
INSERT INTO `dessert` VALUES (156, '椰香糕点', 4.99, 2.25, '椰子味的小蛋糕，外层覆盖着甜蜜的椰丝。', '10.jpg', 24);
INSERT INTO `dessert` VALUES (157, '抹茶冰淇淋', 5.99, 2.85, '使用抹茶粉制成的冰淇淋，独特的苦甜味道。', '11.jpg', 76);
INSERT INTO `dessert` VALUES (158, '奶油泡芙', 6.49, 3.25, '酥皮泡芙，内部填充着丝滑的奶油。', '12.jpg', 67);
INSERT INTO `dessert` VALUES (159, '蓝莓蛋挞', 4.99, 2.50, '松脆的挞皮，内含蓝莓果酱和柔滑的蛋奶混合物。', '13.jpg', 76);
INSERT INTO `dessert` VALUES (160, '柠檬挞', 4.49, 2.25, '香柠檬味的挞皮，里面是柠檬奶油酱。', '14.jpg', 23);
INSERT INTO `dessert` VALUES (161, '奥利奥芝士蛋糕', 8.99, 4.50, '融合了奥利奥饼干和奶油芝士的美味蛋糕。', '15.jpg', 56);
INSERT INTO `dessert` VALUES (162, '樱桃派', 6.99, 3.50, '用新鲜樱桃制成的果酱，搭配酥皮饼干。', '16.jpg', 6);
INSERT INTO `dessert` VALUES (163, '巧克力棒', 2.99, 1.25, '一种巧克力制成的长形甜点，通常含有坚果或干果。', '17.jpg', 454);
INSERT INTO `dessert` VALUES (164, '布朗尼', 4.49, 2.25, '口感浓郁的巧克力蛋糕，里面还有坚果碎片。', '18.jpg', 34);
INSERT INTO `dessert` VALUES (165, '桃子派', 5.99, 3.25, '新鲜桃子制成的果酱，包裹在酥皮饼干中。', '19.jpg', 56);
INSERT INTO `dessert` VALUES (166, '蛋挞', 3.99, 1.75, '传统的葡式蛋挞，有着浓厚的蛋奶味道和松脆的挞皮。', '20.jpg', 775);

-- ----------------------------
-- Table structure for dessert_order
-- ----------------------------
DROP TABLE IF EXISTS `dessert_order`;
CREATE TABLE `dessert_order`  (
  `buy_nums` int(11) NULL DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dessert_id` int(11) NULL DEFAULT NULL,
  `buy_time` date NULL DEFAULT NULL,
  `discount` double NULL DEFAULT NULL,
  `total_price` double NULL DEFAULT NULL,
  `uid` int(11) NULL DEFAULT NULL,
  `classification` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_to_dessert`(`dessert_id`) USING BTREE,
  INDEX `dessertorder_to_user`(`uid`) USING BTREE,
  CONSTRAINT `dessertorder_to_user` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_to_dessert` FOREIGN KEY (`dessert_id`) REFERENCES `dessert` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1405 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dessert_order
-- ----------------------------

-- ----------------------------
-- Table structure for favorite_books
-- ----------------------------
DROP TABLE IF EXISTS `favorite_books`;
CREATE TABLE `favorite_books`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NULL DEFAULT NULL,
  `book_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fav_to_book`(`book_id`) USING BTREE,
  INDEX `fav_to_user`(`uid`) USING BTREE,
  CONSTRAINT `fav_to_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fav_to_user` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 206 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of favorite_books
-- ----------------------------
INSERT INTO `favorite_books` VALUES (8, 133, 16);
INSERT INTO `favorite_books` VALUES (29, 2, 0);
INSERT INTO `favorite_books` VALUES (47, 2, 0);
INSERT INTO `favorite_books` VALUES (54, 137, 15);
INSERT INTO `favorite_books` VALUES (70, 152, 7);
INSERT INTO `favorite_books` VALUES (100, 124, 10);
INSERT INTO `favorite_books` VALUES (106, 205, 13);
INSERT INTO `favorite_books` VALUES (107, 112, 6);
INSERT INTO `favorite_books` VALUES (108, 181, 13);
INSERT INTO `favorite_books` VALUES (111, 133, 11);
INSERT INTO `favorite_books` VALUES (117, 145, 14);
INSERT INTO `favorite_books` VALUES (122, 201, 7);
INSERT INTO `favorite_books` VALUES (127, 192, 11);
INSERT INTO `favorite_books` VALUES (131, 110, 8);
INSERT INTO `favorite_books` VALUES (133, 188, 17);
INSERT INTO `favorite_books` VALUES (139, 125, 3);
INSERT INTO `favorite_books` VALUES (140, 117, 9);
INSERT INTO `favorite_books` VALUES (146, 195, 14);
INSERT INTO `favorite_books` VALUES (148, 114, 11);
INSERT INTO `favorite_books` VALUES (151, 142, 17);
INSERT INTO `favorite_books` VALUES (163, 185, 7);
INSERT INTO `favorite_books` VALUES (171, 128, 4);
INSERT INTO `favorite_books` VALUES (177, 190, 0);
INSERT INTO `favorite_books` VALUES (181, 189, 7);
INSERT INTO `favorite_books` VALUES (184, 203, 13);
INSERT INTO `favorite_books` VALUES (194, 162, 10);
INSERT INTO `favorite_books` VALUES (204, 156, 5);
INSERT INTO `favorite_books` VALUES (205, 128, 16);

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES (1, 'lWgdNv5VYE', 'Kwong Wing Kuen');
INSERT INTO `manager` VALUES (2, 'EL8obMd7HI', 'Huang Zhennan');
INSERT INTO `manager` VALUES (3, '0OdivX8iIn', 'Marcus Patterson');
INSERT INTO `manager` VALUES (4, 'MxWn5BkvZx', 'Su Jialun');
INSERT INTO `manager` VALUES (5, 'lvQVDGZCnf', 'Chic Tin Lok');
INSERT INTO `manager` VALUES (6, 'syrO2fiNTk', 'Saito Aoshi');
INSERT INTO `manager` VALUES (7, 'xexmO5C5zI', 'Wong Lai Yan');
INSERT INTO `manager` VALUES (8, 'ZQxxxnvDwu', 'Ogawa Rena');
INSERT INTO `manager` VALUES (9, 'cmC9js2IHk', 'Siu Sai Wing');
INSERT INTO `manager` VALUES (10, 'CQIVMpZyvz', 'Bruce Fisher');

-- ----------------------------
-- Table structure for read_record
-- ----------------------------
DROP TABLE IF EXISTS `read_record`;
CREATE TABLE `read_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NULL DEFAULT NULL,
  `book_id` int(11) NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `read_record_to_user`(`uid`) USING BTREE,
  INDEX `read_record_to_book`(`book_id`) USING BTREE,
  CONSTRAINT `read_record_to_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `read_record_to_user` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 407 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of read_record
-- ----------------------------
INSERT INTO `read_record` VALUES (400, 164, 4, '2022-02-28');
INSERT INTO `read_record` VALUES (405, 2, 0, '2023-06-04');
INSERT INTO `read_record` VALUES (406, 2, 0, '2023-06-04');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `balance` double NULL DEFAULT NULL,
  `image_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `vip_class` enum('0','1','2','3') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  `vip_start_date` date NULL DEFAULT NULL,
  `brief_introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 212 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, 'zzk', '000000', 996252.1319999998, '1.jpg', '0', '2023-06-05', 'a JLU student');
INSERT INTO `user` VALUES (103, 'Russell Grant', 's9QA7QtL8D', 257.61, '1.jpg', '0', '2023-06-05', 'zbF2BftxsG');
INSERT INTO `user` VALUES (104, 'Yoshida Akina', 'toQK936w4b', 280.44, '1.jpg', '0', '2023-06-05', 'ZrDYWkmvOn');
INSERT INTO `user` VALUES (105, 'Ren Yuning', 'w1TdKJ6t7T', 122.43, '1.jpg', '0', '2023-06-05', 'jtL5x37Kn8');
INSERT INTO `user` VALUES (106, 'Troy Richardson', 'KtMecixr46', 754.96, '1.jpg', '0', '2023-06-05', 'ZWzI4BwMBV');
INSERT INTO `user` VALUES (107, 'Wu Lu', 'm6lAzjDmrx', 918.06, '1.jpg', '0', '2023-06-05', 'gTc33ihmc6');
INSERT INTO `user` VALUES (108, 'So Chi Yuen', 'sMATzjH9Os', 763.14, '1.jpg', '0', '2023-06-05', 'D1gq9A508f');
INSERT INTO `user` VALUES (109, 'Anita Morris', 'ujacONkl9R', 63.55, '1.jpg', '0', '2023-06-05', 'qq7GTj9PyD');
INSERT INTO `user` VALUES (110, 'Murakami Aoi', 'l6WeoiroNa', 331.55, '1.jpg', '0', '2023-06-05', 'B2DZeFRZIZ');
INSERT INTO `user` VALUES (111, 'Lillian Hall', 'cybYpkqLVN', 881.1, '1.jpg', '0', '2023-06-05', 'lUZyscxPPd');
INSERT INTO `user` VALUES (112, 'Shi Lu', 'gLiI5tKkIj', 214.85, '1.jpg', '0', '2023-06-05', 'wDNXP1VD9f');
INSERT INTO `user` VALUES (113, 'Juan Crawford', 'BgCUOoWem4', 892.2, '1.jpg', '0', '2023-06-05', '3SHPUVp0tI');
INSERT INTO `user` VALUES (114, 'Fan Anqi', 'NENyE6z3x1', 117.8, '1.jpg', '0', '2023-06-05', 'nt65WF3d14');
INSERT INTO `user` VALUES (115, 'Ti Fat', 'xwXfhIx89F', 751.11, '1.jpg', '0', '2023-06-05', 'mqKtc6r4LF');
INSERT INTO `user` VALUES (116, 'Saito Ayano', '1TBmdcvuEE', 835.13, '1.jpg', '0', '2023-06-05', 'L42jGUcVNd');
INSERT INTO `user` VALUES (117, 'Ray Lee', 'wFqeFHUr1s', 584.58, '1.jpg', '0', '2023-06-05', 'IKoXWOPfUb');
INSERT INTO `user` VALUES (118, 'Gloria Reynolds', 'lRtf7jKYMc', 72.15, '1.jpg', '0', '2023-06-05', '9CvEftY52F');
INSERT INTO `user` VALUES (119, 'Xie Jialun', 'Y8heP2TlcL', 377.35, '1.jpg', '0', '2023-06-05', 'do5XaPYgD0');
INSERT INTO `user` VALUES (120, 'Harold Burns', 't5yDM5PzKg', 292.7, '1.jpg', '0', '2023-06-05', 'Ff4yuJolcg');
INSERT INTO `user` VALUES (121, 'Chiba Kazuma', '5cFpPgD1yT', 656.33, '1.jpg', '0', '2023-06-05', 'IWoWbtsdJD');
INSERT INTO `user` VALUES (122, 'Matsui Yamato', 'zDaIr3fytq', 910.44, '1.jpg', '0', '2023-06-05', 'AfXpPTDklq');
INSERT INTO `user` VALUES (123, 'Miu Wai San', 'c70jw81PZr', 327.6, '1.jpg', '0', '2023-06-05', 'M6mQkDdqmD');
INSERT INTO `user` VALUES (124, 'Wu Yunxi', 'KKRBpxb1ZA', 46.14, '1.jpg', '0', '2023-06-05', 'Dpo1OwvdgB');
INSERT INTO `user` VALUES (125, 'Phillip Grant', 'WpUFVLhXuv', 570.45, '1.jpg', '0', '2023-06-05', 'xSWY68r388');
INSERT INTO `user` VALUES (126, 'Ashley Spencer', '0URrXBcAUe', 341.23, '1.jpg', '0', '2023-06-05', 'm9AsiRaojq');
INSERT INTO `user` VALUES (127, 'Noguchi Yuna', 'bkahgjXHFW', 286.83, '1.jpg', '0', '2023-06-05', 'bBEBEQBkN7');
INSERT INTO `user` VALUES (128, 'Kato Misaki', '1xfrKSEcjo', 348.33, '1.jpg', '0', '2023-06-05', 'Ai0id9YkkH');
INSERT INTO `user` VALUES (129, 'Amy Schmidt', 'zm7aHmuRIL', 597.32, '1.jpg', '0', '2023-06-05', 'KPhAZUsXgI');
INSERT INTO `user` VALUES (130, 'Tong Ming Sze', '1u1NWJwuJP', 45.82, '1.jpg', '0', '2023-06-05', 'vGQQ5dzYUQ');
INSERT INTO `user` VALUES (131, 'Endo Hana', 'UP6HbwioEg', 902.01, '1.jpg', '0', '2023-06-05', 'ZGUlfde7zl');
INSERT INTO `user` VALUES (132, 'Shi Lan', '3IlMoIRWki', 5.88, '1.jpg', '0', '2023-06-05', 'W8o99RhQkp');
INSERT INTO `user` VALUES (133, 'Angela Jackson', 'XMJ8uRwEtN', 674.65, '1.jpg', '0', '2023-06-05', 'wgSzUuXegH');
INSERT INTO `user` VALUES (134, 'Chung Sai Wing', 'JDGuA9Grcm', 530.44, '1.jpg', '0', '2023-06-05', 'eR7CDeCQph');
INSERT INTO `user` VALUES (135, 'Siu On Na', 'vS3z9DLDHV', 984.98, '1.jpg', '0', '2023-06-05', '3Mebo7SnjM');
INSERT INTO `user` VALUES (136, 'Hao Lan', 'AIZ4Mbwgkx', 782.79, '1.jpg', '0', '2023-06-05', '6XR0Kihi8F');
INSERT INTO `user` VALUES (137, 'Tong Ka Fai', 'jCuU20rtlx', 914.56, '1.jpg', '0', '2023-06-05', '0F6QYucTBO');
INSERT INTO `user` VALUES (138, 'Tang Lu', 'Zt9zzeYndi', 149.3, '1.jpg', '0', '2023-06-05', 'P1BkM1iTFa');
INSERT INTO `user` VALUES (139, 'Ding Rui', 'myCrGAUAzZ', 139.33, '1.jpg', '0', '2023-06-05', '6nrRYPQlyH');
INSERT INTO `user` VALUES (140, 'Ishii Ayato', 'C9IGLLT8KX', 997.39, '1.jpg', '0', '2023-06-05', 'lzElwgcOUZ');
INSERT INTO `user` VALUES (141, 'Fan Suk Yee', 'vhuhEJtsCV', 233.27, '1.jpg', '0', '2023-06-05', 'Et321COsTN');
INSERT INTO `user` VALUES (142, 'Xue Ziyi', 'KtKNrdOY7l', 298.8, '1.jpg', '0', '2023-06-05', 'xwMr66sGBY');
INSERT INTO `user` VALUES (143, 'Ono Minato', 'aFcx3HxBUF', 458.97, '1.jpg', '0', '2023-06-05', 'IcPW5dFghH');
INSERT INTO `user` VALUES (144, 'Pak Wai San', 'tyBR501aIm', 650.64, '1.jpg', '0', '2023-06-05', 'loT8qnYqOD');
INSERT INTO `user` VALUES (145, 'Chiba Minato', 'C9rQaGJV7Y', 401.24, '1.jpg', '0', '2023-06-05', '3H6a398oOr');
INSERT INTO `user` VALUES (146, 'Carmen Kennedy', 'TwkG6lUogr', 183.05, '1.jpg', '0', '2023-06-05', 'DKbUUplbQG');
INSERT INTO `user` VALUES (147, 'Ku Sum Wing', 'AsyBVQbeJd', 440.92, '1.jpg', '0', '2023-06-05', '307VgpLq7L');
INSERT INTO `user` VALUES (148, 'Wada Nanami', 'GUlgRV70fn', 185.41, '1.jpg', '0', '2023-06-05', 'z7s5CdOsRF');
INSERT INTO `user` VALUES (149, 'Xiang Ziyi', '0RXTNOlPHk', 981.08, '1.jpg', '0', '2023-06-05', 'A77wC1dqO7');
INSERT INTO `user` VALUES (150, 'Zhao Jiehong', 'hp3OtRny0b', 110.38, '1.jpg', '0', '2023-06-05', 'jVMDqT8cnd');
INSERT INTO `user` VALUES (151, 'Yin Lan', 'ceg3W84wCz', 63.39, '1.jpg', '0', '2023-06-05', 'O9JUdxlpO6');
INSERT INTO `user` VALUES (152, 'Don Murray', '54dZYy0AQt', 663.87, '1.jpg', '0', '2023-06-05', 'QFpRoSgezz');
INSERT INTO `user` VALUES (153, 'Marjorie Jenkins', 'MxR6P8h5un', 924.12, '1.jpg', '0', '2023-06-05', 'RQN6PTDB6l');
INSERT INTO `user` VALUES (154, 'Saito Ayato', 'rXkDA12PbI', 784.84, '1.jpg', '0', '2023-06-05', 'ssdiepV1wI');
INSERT INTO `user` VALUES (155, 'Imai Sara', 'gFHo9tCKEQ', 227.31, '1.jpg', '0', '2023-06-05', 'eJCe3fxM7e');
INSERT INTO `user` VALUES (156, 'Yan Anqi', 'dnM2P74mev', 38.4, '1.jpg', '0', '2023-06-05', '3jmSOkq5Mq');
INSERT INTO `user` VALUES (157, 'Ho Tin Lok', 'mugPOIeYHO', 741.79, '1.jpg', '0', '2023-06-05', 'bfGhrSa2E8');
INSERT INTO `user` VALUES (158, 'Yip Ka Ling', 'aw9B5d7L0U', 132.32, '1.jpg', '0', '2023-06-05', 'yfjU5QfOYT');
INSERT INTO `user` VALUES (159, 'Han Shihan', 'g5K4COIOig', 334.69, '1.jpg', '0', '2023-06-05', 'CG0pB9J9y2');
INSERT INTO `user` VALUES (160, 'Sugiyama Ayato', 'VaAKi5KwOg', 271.75, '1.jpg', '0', '2023-06-05', 'g78gXFXKr9');
INSERT INTO `user` VALUES (161, 'Loui Chieh Lun', 'gDkc6ZLHaW', 729.09, '1.jpg', '0', '2023-06-05', '95Ec1ugb19');
INSERT INTO `user` VALUES (162, 'Hirano Ayano', 'ECKqWO4NbJ', 15.38, '1.jpg', '0', '2023-06-05', 'vayJnxG4QT');
INSERT INTO `user` VALUES (163, 'Emma Bell', 'UdMIqV39p6', 959.94, '1.jpg', '0', '2023-06-05', 'vvPrcQO1Zr');
INSERT INTO `user` VALUES (164, 'Imai Shino', 'VpOG7DlZMF', 609.17, '1.jpg', '0', '2023-06-05', 'tKuymhvyec');
INSERT INTO `user` VALUES (165, 'Alan Rivera', 'VYPZY57HIN', 813.56, '1.jpg', '0', '2023-06-05', 'U1nfsOHbYd');
INSERT INTO `user` VALUES (166, 'Otsuka Tsubasa', 'BngCOX7PII', 2.22, '1.jpg', '0', '2023-06-05', 'NWrVFWjKoP');
INSERT INTO `user` VALUES (167, 'Liang Jialun', 'kH0nxTRooE', 771.44, '1.jpg', '0', '2023-06-05', 'i01XLfH6BJ');
INSERT INTO `user` VALUES (168, 'Ren Yunxi', 's9kNKSQsXd', 42.69, '1.jpg', '0', '2023-06-05', 'x3qc43JkG2');
INSERT INTO `user` VALUES (169, 'Nomura Eita', 'Cs9csJ68Cd', 827.45, '1.jpg', '0', '2023-06-05', 'tQ73VFUYKd');
INSERT INTO `user` VALUES (170, 'Lok Sai Wing', 'dxxB6tjHzh', 734.61, '1.jpg', '0', '2023-06-05', '79ZqXQx38u');
INSERT INTO `user` VALUES (171, 'Taniguchi Aoi', 'GHxzUdRb4y', 350.22, '1.jpg', '0', '2023-06-05', '3wuKwRsnQN');
INSERT INTO `user` VALUES (172, 'Terry Russell', 'sPBHYWUawF', 281.4, '1.jpg', '0', '2023-06-05', 'zhF5nZKG77');
INSERT INTO `user` VALUES (173, 'Shirley Graham', 'VAI9zhWJ3r', 96.45, '1.jpg', '0', '2023-06-05', '5FU0z9hY0C');
INSERT INTO `user` VALUES (174, 'Bernard Harris', 'U8mfscUlBa', 385.57, '1.jpg', '0', '2023-06-05', 'SHk6XqGPfT');
INSERT INTO `user` VALUES (175, 'Amber Marshall', 'tLY4F6tg3R', 976.66, '1.jpg', '0', '2023-06-05', 'N2sKZhEavm');
INSERT INTO `user` VALUES (176, 'Justin Williams', 'tgv24WWJUe', 19.35, '1.jpg', '0', '2023-06-05', 'EyM2rEJrdb');
INSERT INTO `user` VALUES (177, 'Maruyama Yuto', 'BRu19on9oe', 63.05, '1.jpg', '0', '2023-06-05', 'Ku2MHV8vIO');
INSERT INTO `user` VALUES (178, 'Xu Xiuying', 'OpWh4FePlQ', 203.62, '1.jpg', '0', '2023-06-05', 'E3iB0lqfDu');
INSERT INTO `user` VALUES (179, 'Chen Zitao', 'MdDM5lX1TE', 413.76, '1.jpg', '0', '2023-06-05', 'AlD1UvKXqg');
INSERT INTO `user` VALUES (180, 'Antonio Garza', 'Pdti1Khh5H', 988.59, '1.jpg', '0', '2023-06-05', '5Dj3HocqP9');
INSERT INTO `user` VALUES (181, 'Meng Ka Keung', 'z5yODPa2UW', 914.48, '1.jpg', '0', '2023-06-05', '48xSFBE5sG');
INSERT INTO `user` VALUES (182, 'Shimada Daichi', 'ow9T66E3e1', 339.68, '1.jpg', '0', '2023-06-05', 'tm9kIZ30Mo');
INSERT INTO `user` VALUES (183, 'Sara Rivera', 'XJeneqoxeW', 520.77, '1.jpg', '0', '2023-06-05', 'zxv8LdW4eQ');
INSERT INTO `user` VALUES (184, 'Su Zhiyuan', 'x7waYbXtqd', 992.33, '1.jpg', '0', '2023-06-05', 'EMStI6xgUr');
INSERT INTO `user` VALUES (185, 'Fu Jiehong', 'tDc0T29H8n', 616.2, '1.jpg', '0', '2023-06-05', 'nkiu2Vzgee');
INSERT INTO `user` VALUES (186, 'Shimada Daisuke', 'AJcIW2UYmu', 18.45, '1.jpg', '0', '2023-06-05', 'WkSMKNha3p');
INSERT INTO `user` VALUES (187, 'Yuen Wing Sze', 'Ld6AV7Sn6k', 771.54, '1.jpg', '0', '2023-06-05', 't4LW1OItLJ');
INSERT INTO `user` VALUES (188, 'Wang Lan', 'AkomvrxyFl', 374.31, '1.jpg', '0', '2023-06-05', 'eCo9HYYsaZ');
INSERT INTO `user` VALUES (189, 'Shannon Murphy', 'tJlkkIcdyr', 145.7, '1.jpg', '0', '2023-06-05', '4ColaigNmL');
INSERT INTO `user` VALUES (190, 'Lau Suk Yee', 'uYyIKC4FCi', 900.82, '1.jpg', '0', '2023-06-05', 'HGoH7YWFNN');
INSERT INTO `user` VALUES (191, 'Kyle Meyer', '8FwrgA0cyL', 167.16, '1.jpg', '0', '2023-06-05', '2vB77HBLgj');
INSERT INTO `user` VALUES (192, 'Rhonda Murray', 'wVTrfoDpBO', 917.4, '1.jpg', '0', '2023-06-05', 'T9RNxuOjUw');
INSERT INTO `user` VALUES (193, 'Hayashi Mai', 'QYQVfYWlHy', 929.68, '1.jpg', '0', '2023-06-05', 'D9VUPV8dFv');
INSERT INTO `user` VALUES (194, 'Ronald Mitchell', '1J8F5NT2nP', 112.58, '1.jpg', '0', '2023-06-05', 'Wz8hHVRpQx');
INSERT INTO `user` VALUES (195, 'Yam Ming Sze', 'h4TBTpLRtL', 247.08, '1.jpg', '0', '2023-06-05', '5zPfN73QA2');
INSERT INTO `user` VALUES (196, 'Sakamoto Misaki', 'iAaSc6TQl5', 126.57, '1.jpg', '0', '2023-06-05', 'IBMTdDHHxL');
INSERT INTO `user` VALUES (197, 'Zhang Xiuying', 'UAzBJq3lcR', 492.87, '1.jpg', '0', '2023-06-05', 'jmvAgnczoQ');
INSERT INTO `user` VALUES (198, 'Steve Ruiz', 'Ia6Krz0BTx', 681.28, '1.jpg', '0', '2023-06-05', '5nR60DHezy');
INSERT INTO `user` VALUES (199, 'Chiba Mitsuki', 'MLqgdEO9fF', 320.87, '1.jpg', '0', '2023-06-05', 'nRBdA5jMCw');
INSERT INTO `user` VALUES (200, 'Tang Ho Yin', '62mSsNEKH9', 48.38, '1.jpg', '0', '2023-06-05', 'seIpJVyYXL');
INSERT INTO `user` VALUES (201, 'Wu Zitao', 'yWlQccckv6', 986.79, '1.jpg', '0', '2023-06-05', 'of7DbXzlIW');
INSERT INTO `user` VALUES (202, 'Diana Sanchez', '6xKqOPxHd5', 26.13, '1.jpg', '0', '2023-06-05', 'lTMaHUC9vR');
INSERT INTO `user` VALUES (203, 'Imai Yuto', 'grsogdYpPg', 533.72, '1.jpg', '0', '2023-06-05', 'WzV2pFi1Bu');
INSERT INTO `user` VALUES (204, 'Lo Wai Lam', 'M0iLHcMhff', 771.43, '1.jpg', '0', '2023-06-05', '16ZaVxLy6T');
INSERT INTO `user` VALUES (205, 'Li Xiaoming', '6CgXBUyyEv', 538.71, '1.jpg', '0', '2023-06-05', 'MlpnMiHF6R');
INSERT INTO `user` VALUES (206, 'Shimizu Rena', 'ACbW12vyvN', 77.63, '1.jpg', '0', '2023-06-05', 'CtAAPpfN04');
INSERT INTO `user` VALUES (207, 'Lu Lu', 'kSql7ZelUg', 829.22, '1.jpg', '0', '2023-06-05', 'xXaPvKRlJs');
INSERT INTO `user` VALUES (208, 'Takeda Rin', '5JbijPIxG6', 583.82, '1.jpg', '0', '2023-06-05', 'i0wzfmXB6k');
INSERT INTO `user` VALUES (209, 'Sakamoto Yuto', 'a5QIRHNQ4b', 983.98, '1.jpg', '0', '2023-06-05', 'gW5uGq7I8d');
INSERT INTO `user` VALUES (210, 'Liu Ziyi', 'UktpdNNXVb', 789.01, '1.jpg', '0', '2023-06-05', '42zR6TNB0R');
INSERT INTO `user` VALUES (211, 'Elaine Morales', 'dHxfpA6yIQ', 447.18, '1.jpg', '0', '2023-06-05', '6oV0EO2E3E');

-- ----------------------------
-- Table structure for vip_index
-- ----------------------------
DROP TABLE IF EXISTS `vip_index`;
CREATE TABLE `vip_index`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `vip_cost` double(10, 2) NULL DEFAULT NULL,
  `class_discount` double NOT NULL,
  `vip_class` int(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vip_index
-- ----------------------------
INSERT INTO `vip_index` VALUES (1, '普通', 0.00, 1, 0);
INSERT INTO `vip_index` VALUES (2, '黄金', 99.00, 0.9, 1);
INSERT INTO `vip_index` VALUES (3, '铂金', 199.00, 0.8, 2);
INSERT INTO `vip_index` VALUES (4, '钻石', 399.00, 0.6, 3);

SET FOREIGN_KEY_CHECKS = 1;
