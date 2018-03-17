/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : bishe

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2018-03-17 18:56:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `phone_id` varchar(225) NOT NULL COMMENT '手机号码，',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名字',
  `user_role` int(2) NOT NULL,
  `password` varchar(255) NOT NULL COMMENT '密码',
  `user_state` int(2) NOT NULL COMMENT '是否登录',
  `yn` int(2) NOT NULL COMMENT '是否有效',
  `created` datetime(6) NOT NULL COMMENT '创建时间',
  `modified` datetime(6) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
