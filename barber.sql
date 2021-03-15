/*
 Navicat Premium Data Transfer

 Source Server         : 2020Mysql
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : barber

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 15/03/2021 11:56:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cost` double(10, 2) UNSIGNED NOT NULL,
  `des` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` int(10) NOT NULL COMMENT '0-消费 1-充值',
  `addTime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `consumer_id` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `consumer_id`(`consumer_id`) USING BTREE,
  CONSTRAINT `item_ibfk_1` FOREIGN KEY (`consumer_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES (1, 'test1', 0.00, '剪头发', 0, '2021-03-12 00:56:22', 4);
INSERT INTO `item` VALUES (2, 'test1', 20.00, '剪头发', 0, '2021-03-12 01:43:28', 4);
INSERT INTO `item` VALUES (3, 'test1', 200.00, '充值', 1, '2021-03-12 01:48:39', 4);
INSERT INTO `item` VALUES (4, 'test1', 20.00, '剪头发', 0, '2021-03-12 01:49:45', 4);
INSERT INTO `item` VALUES (5, 'test1', 20.00, '剪头发', 0, '2021-03-12 11:08:12', 4);
INSERT INTO `item` VALUES (6, '阿海', 100.00, '充值', 1, '2021-03-12 13:04:55', 2);
INSERT INTO `item` VALUES (7, '阿海', 100.00, '充值', 1, '2021-03-12 15:42:40', 2);
INSERT INTO `item` VALUES (8, '阿海', 100.00, '充值', 1, '2021-03-12 15:46:09', 2);
INSERT INTO `item` VALUES (9, '阿海', 10.00, '充值', 1, '2021-03-12 15:46:43', 2);
INSERT INTO `item` VALUES (10, 'test7', 100.00, '充值', 1, '2021-03-12 15:47:36', 10);
INSERT INTO `item` VALUES (11, 'test8', 100.00, '充值', 1, '2021-03-12 15:56:20', 11);
INSERT INTO `item` VALUES (12, '阿海', 100.00, '剪头', 0, '2021-03-12 15:58:30', 2);
INSERT INTO `item` VALUES (13, '阿海', 100.00, '充值', 1, '2021-03-13 14:24:05', 2);
INSERT INTO `item` VALUES (14, '阿海', 200.00, '烫头', 0, '2021-03-13 14:26:34', 2);
INSERT INTO `item` VALUES (15, '阿海', 20.00, '快剪', 0, '2021-03-13 14:28:04', 2);
INSERT INTO `item` VALUES (16, '阿勇', 200.00, '充值', 1, '2021-03-13 14:36:18', 103);
INSERT INTO `item` VALUES (17, '阿勇', 100.00, '烫头', 0, '2021-03-13 14:36:41', 103);
INSERT INTO `item` VALUES (18, '阿勇', 100.00, '充值', 1, '2021-03-13 16:09:49', 103);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role` int(10) NOT NULL DEFAULT 2 COMMENT '1---管理员2---普通用户3---理发师',
  `balance` double(10, 2) UNSIGNED NOT NULL DEFAULT 0.00,
  `addTime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE,
  INDEX `role`(`role`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 104 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123456', 1, 0.00, '2021-02-26 22:26:16');
INSERT INTO `user` VALUES (2, '阿海', '123456', 2, 90.00, '2021-03-13 14:28:04');
INSERT INTO `user` VALUES (3, 'test0', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (4, 'test1', '123456', 2, 160.00, '2021-03-12 11:08:12');
INSERT INTO `user` VALUES (5, 'test2', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (6, 'test3', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (7, 'test4', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (8, 'test5', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (9, 'test6', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (10, 'test7', '123456', 2, 100.00, '2021-03-12 15:47:36');
INSERT INTO `user` VALUES (11, 'test8', '123456', 2, 100.00, '2021-03-12 15:56:20');
INSERT INTO `user` VALUES (12, 'test9', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (13, 'test10', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (14, 'test11', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (15, 'test12', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (16, 'test13', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (17, 'test14', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (18, 'test15', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (19, 'test16', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (20, 'test17', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (21, 'test18', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (22, 'test19', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (23, 'test20', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (24, 'test21', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (25, 'test22', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (26, 'test23', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (27, 'test24', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (28, 'test25', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (29, 'test26', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (30, 'test27', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (31, 'test28', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (32, 'test29', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (33, 'test30', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (34, 'test31', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (35, 'test32', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (36, 'test33', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (37, 'test34', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (38, 'test35', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (39, 'test36', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (40, 'test37', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (41, 'test38', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (42, 'test39', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (43, 'test40', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (44, 'test41', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (45, 'test42', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (46, 'test43', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (47, 'test44', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (48, 'test45', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (49, 'test46', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (50, 'test47', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (51, 'test48', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (52, 'test49', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (53, 'test50', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (54, 'test51', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (55, 'test52', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (56, 'test53', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (57, 'test54', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (58, 'test55', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (59, 'test56', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (60, 'test57', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (61, 'test58', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (62, 'test59', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (63, 'test60', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (64, 'test61', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (65, 'test62', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (66, 'test63', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (67, 'test64', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (68, 'test65', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (69, 'test66', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (70, 'test67', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (71, 'test68', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (72, 'test69', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (73, 'test70', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (74, 'test71', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (75, 'test72', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (76, 'test73', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (77, 'test74', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (78, 'test75', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (79, 'test76', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (80, 'test77', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (81, 'test78', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (82, 'test79', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (83, 'test80', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (84, 'test81', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (85, 'test82', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (86, 'test83', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (87, 'test84', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (88, 'test85', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (89, 'test86', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (90, 'test87', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (91, 'test88', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (92, 'test89', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (93, 'test90', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (94, 'test91', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (95, 'test92', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (96, 'test93', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (97, 'test94', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (98, 'test95', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (99, 'test96', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (100, 'test97', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (101, 'test98', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (102, 'test99', '123456', 2, 0.00, '2021-03-06 14:51:14');
INSERT INTO `user` VALUES (103, '阿勇', '123456', 2, 200.00, '2021-03-13 16:09:49');

SET FOREIGN_KEY_CHECKS = 1;
