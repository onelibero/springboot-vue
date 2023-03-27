/*
 Navicat Premium Data Transfer

 Source Server         : mysql8
 Source Server Type    : MySQL
 Source Server Version : 80030 (8.0.30)
 Source Host           : localhost:3306
 Source Schema         : springbootproject

 Target Server Type    : MySQL
 Target Server Version : 80030 (8.0.30)
 File Encoding         : 65001

 Date: 23/02/2023 15:38:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comic_comment
-- ----------------------------
DROP TABLE IF EXISTS `comic_comment`;
CREATE TABLE `comic_comment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `comicId` int NULL DEFAULT NULL,
  `userId` int NULL DEFAULT NULL,
  `commentId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createTime` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comic_comment
-- ----------------------------
INSERT INTO `comic_comment` VALUES (4, 1, 1, '10', 1677069084798);
INSERT INTO `comic_comment` VALUES (5, 1, 1, '11', 1677069131152);
INSERT INTO `comic_comment` VALUES (6, 12, 1, '17', 1677134775770);
INSERT INTO `comic_comment` VALUES (7, 12, 1, '18', 1677134845466);
INSERT INTO `comic_comment` VALUES (8, 12, 1, '19', 1677134869637);
INSERT INTO `comic_comment` VALUES (9, 12, 2, '20', 1677135501394);
INSERT INTO `comic_comment` VALUES (10, 12, 1, '22', 1677135697517);

-- ----------------------------
-- Table structure for comic_table
-- ----------------------------
DROP TABLE IF EXISTS `comic_table`;
CREATE TABLE `comic_table`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '动漫的id',
  `comicName` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '动漫的名字',
  `nickname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '动漫的名字（别名）',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '封面',
  `region` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地区',
  `label` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `remark` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `year` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '年份',
  `updateTime` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新时间',
  `number` int NULL DEFAULT NULL COMMENT '集数',
  `popularity` int NULL DEFAULT NULL COMMENT '人气',
  `url` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '外部链接',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comic_table
-- ----------------------------
INSERT INTO `comic_table` VALUES (12, '海贼王', 'One Piece', '/photo/5a3d3d40-18cd-497f-b20f-eaaccfdf1f5f4.jpg', '日本', '热血冒险', '大海贼时代！', '更新至1070话', '1997', '2022-12-21', 1070, 999999999, 'httpsxxxx');
INSERT INTO `comic_table` VALUES (14, '斗罗大陆第一季', '斗罗大陆动画版', '/photo/a7e35fd2-c23a-4219-a62a-1a6eeccae6e36.jpeg', '大陆', '奇幻 玄幻 穿越 热血 战斗 国产动漫  ', '网络动画《斗罗大陆》改编自中国作家唐家三少原作的同名玄幻小说，由玄机科技制作，', '更新至240集', '2018 ', '2022-12-24', 240, 7888888, 'httpsxxxx');
INSERT INTO `comic_table` VALUES (15, '名侦探柯南', '名侦探柯南 日语版/名探侦コナン Detective Conan', '/photo/a3a284f9-2e43-44b7-bde3-095309bd85bf5.jpg', '日本', '悬疑 推理 犯罪 日本动漫  ', '电视动画《名侦探柯南》改编自青山刚昌创作的、连载于《周刊少年Sunday》上的漫画《名侦探柯南》。', '更新至1124集', '2006 ', '2022-12-24', 1124, 88888888, 'httpsxxx');
INSERT INTO `comic_table` VALUES (16, '咒术回战', '呪術廻戦', '/photo/272b6411-9664-44e9-bcc2-ae6626caca4b7.jpg', '日本', '奇幻 冒险 战斗 日本动漫', '电视动画《咒术回战》改编自芥见下下著作的同名漫画，于2019年11月25日在《周刊少年Jump》52号上发表了动画化的消息。', '完结', '2020 ', '2022-11-02', 24, 4165781, 'httpsxxxx');
INSERT INTO `comic_table` VALUES (17, '火影忍者：博人传之次世代继承者', '博人传 火影忍者新时代', '/photo/4a57bc0c-dc38-4b98-b47b-4cbc4f6bedf73.jpg', '日本', '冒险 奇幻 热血 日本动漫', '日本电视动画《BORUTO -火影新世代-》（中国大陆译名《博人传：火影忍者新时代》）改编自岸本齐史原作并监修、池本干雄编绘', '更新至280集', '2017', '2022-12-24', 280, 6547893, 'httpsxxxx');
INSERT INTO `comic_table` VALUES (18, '终末的女武神', 'Record of Ragnarok', '/photo/7de3b8d4-46fc-4ebf-8d6c-4723d014d3798.jpg', '日本', '战斗 热血 剧情 日本动漫 ', '预计在 2021 年开始播映的电视动画《终末的女武神》，官方就在 15 日这天释出首波视觉图、追加角色&声优以及动画主题曲等情报', '完结', '2021 ', '2022-11-02', 12, 4569842, 'httpsxxxx');
INSERT INTO `comic_table` VALUES (19, '剃须。然后捡到女高中生', '刮掉胡子的我与捡到的女高中生', '/photo/f5c74e4f-a763-4840-a650-54c185ad15ec9.jpg', '日本', '搞笑 爱情 日本动漫', '被单恋5年的对方爽快地甩了的上班族吉田。在喝着酒回家的路上，看见蹲在路边的女高中生——“陪你睡一晚，让我住下吧”', '完结', '2020 ', '2022-10-24', 13, 652165, 'httpsxxxx');
INSERT INTO `comic_table` VALUES (20, '战斗员派遣中！', '派遣战斗员/戦闘員、派遣します！', '/photo/d29eef1f-7069-4d0a-a47b-7353e8b661b51.jpg', '日本', '奇幻 冒险 战斗 日本动漫 ', '电视动画《战斗员派遣中！》改编自晓なつめ著作，カカオ・ランタン负责插画的同名轻小说作品，于2020年3月15日宣布了动画化的消息。', '完结', '2021 ', '2021-08-31', 12, 985613, 'httpsxxxx');
INSERT INTO `comic_table` VALUES (21, '无职转生：到了异世界就拿出真本事 第二季', '无职转生 ～在异世界认真地活下去～ 第二季', '/photo/59a707aa-c726-4aa0-9d5a-9b7a3852d43c10.jpg', '日本', '奇幻 冒险 后宫 战斗 日本动漫', '奇幻 冒险 后宫 战斗 日本动漫', '完结+OVA', '2021', '2022-04-12', 14, 9856132, 'httpsxxxx');
INSERT INTO `comic_table` VALUES (22, '镇魂街第二季', '镇魂街第2季', '/photo/2000dec7-ef83-487a-834c-97320369f9032.jpg', '大陆', '奇幻 热血 战斗 国产动漫 ', '时隔3年之后，无数粉丝翘首以盼的《镇魂街》动画第二季终于是来了，此番第二季一开播就播出了两集，', '完结', '2019 ', '2021-06-26', 10, 9856213, 'httpsxxxx');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评论内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (10, '对面中单打野小袋');
INSERT INTO `comment` VALUES (11, '主播双拳难敌四手');
INSERT INTO `comment` VALUES (14, '我们 那么甜那么美那么相信3');
INSERT INTO `comment` VALUES (15, '我们 那么甜那么美那么相信4');
INSERT INTO `comment` VALUES (16, '我们 那么甜那么美那么相信6');
INSERT INTO `comment` VALUES (17, '为什么你带我走过最难忘的旅行');
INSERT INTO `comment` VALUES (18, '我们那么甜那么美那么相信');
INSERT INTO `comment` VALUES (19, '我们那么疯那么热烈的曾经');
INSERT INTO `comment` VALUES (20, '我们那么疯那么热烈的曾经1');
INSERT INTO `comment` VALUES (21, '主播别emo了');
INSERT INTO `comment` VALUES (22, '还是要奔向各自的幸福和遗憾中老去~~~~\n楼上没你好果子吃，为什么抢我楼');

-- ----------------------------
-- Table structure for comment_reply_content
-- ----------------------------
DROP TABLE IF EXISTS `comment_reply_content`;
CREATE TABLE `comment_reply_content`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `commentId` int NULL DEFAULT NULL,
  `replyId` int NULL DEFAULT NULL,
  `repliedId` int NULL DEFAULT NULL,
  `createTime` bigint NULL DEFAULT NULL,
  `comicCommentId` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment_reply_content
-- ----------------------------
INSERT INTO `comment_reply_content` VALUES (3, 14, 17, 1, 1677123362390, 5);
INSERT INTO `comment_reply_content` VALUES (4, 15, 17, 1, 1677123366203, 5);
INSERT INTO `comment_reply_content` VALUES (6, 21, 17, 1, 1677135543168, 6);

-- ----------------------------
-- Table structure for user_collection
-- ----------------------------
DROP TABLE IF EXISTS `user_collection`;
CREATE TABLE `user_collection`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `comic_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_collection
-- ----------------------------
INSERT INTO `user_collection` VALUES (2, 14, 17);
INSERT INTO `user_collection` VALUES (3, 16, 17);
INSERT INTO `user_collection` VALUES (4, 17, 17);
INSERT INTO `user_collection` VALUES (5, 18, 17);
INSERT INTO `user_collection` VALUES (8, 21, 17);
INSERT INTO `user_collection` VALUES (9, 22, 17);

-- ----------------------------
-- Table structure for user_history
-- ----------------------------
DROP TABLE IF EXISTS `user_history`;
CREATE TABLE `user_history`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `comic_id` int NOT NULL,
  `user_id` int NOT NULL,
  `create_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_history
-- ----------------------------
INSERT INTO `user_history` VALUES (3, 12, 2, '1676976031904');
INSERT INTO `user_history` VALUES (4, 14, 2, '1676977907374');
INSERT INTO `user_history` VALUES (5, 15, 2, '1676978220831');
INSERT INTO `user_history` VALUES (6, 16, 2, '1676978225509');
INSERT INTO `user_history` VALUES (7, 17, 2, '1676978228746');
INSERT INTO `user_history` VALUES (8, 18, 2, '1676978232257');
INSERT INTO `user_history` VALUES (9, 19, 2, '1676978236061');
INSERT INTO `user_history` VALUES (10, 20, 2, '1676978240543');
INSERT INTO `user_history` VALUES (11, 21, 2, '1676978247909');

-- ----------------------------
-- Table structure for user_table
-- ----------------------------
DROP TABLE IF EXISTS `user_table`;
CREATE TABLE `user_table`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `createTime` bigint NULL DEFAULT NULL,
  `isAdmin` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_table
-- ----------------------------
INSERT INTO `user_table` VALUES (1, 'banyan', '8715a4f66d9eeddba20b5453edc41bd7', '/photo/1671955021242_firefox-icon.png', '1799053116@qq.com', '18090532775', 1670253343538, 1);
INSERT INTO `user_table` VALUES (2, 'aaa', '5f329d3ac22671f7b214c461e58c27f3', '/photo/1670830260933_奥特1.jpg', '2069820192@qq.com', '18046613246', 1670830260940, 0);
INSERT INTO `user_table` VALUES (3, 'zch', '93d2e162a109c8a32760ba7ec6296140', '/photo/1670844150054_02--.webp', '2069820192@qq.com', '18040395240', 1670844150054, 0);
INSERT INTO `user_table` VALUES (4, 'admin', 'ed8cbdd8b4979bba449380954105db22', '/photo/1671174239878_假面2.jpg', '2165478@qq.com', '1269543216', 1671174239878, 0);
INSERT INTO `user_table` VALUES (5, 'shuzhi', 'ed8cbdd8b4979bba449380954105db22', '/photo/1671194756288_假面3.jpg', '2165478@qq.com', '1269543216', 1671194756288, 0);
INSERT INTO `user_table` VALUES (6, 'cxk', '5f329d3ac22671f7b214c461e58c27f3', '/photo/1671865868886_奥特2.jpg', '2165478@qq.com', '1269543277', 1671340802241, 1);
INSERT INTO `user_table` VALUES (7, 'lhj1', '63905433b7d619911bb0fb568ed22f77', '/user/1676950955320_自拍照.JPG', '17990531116@qq.com', '17990531116', 1676951418956, 1);
INSERT INTO `user_table` VALUES (17, 'chenchenchen', '204015c25d3aa8afa29065a4494154c8', '/user/1676950955320_自拍照.JPG', '17990531116@qq.com', '17990531116', 1677054228637, 1);

SET FOREIGN_KEY_CHECKS = 1;
