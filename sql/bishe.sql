/*
Navicat MySQL Data Transfer

Source Server         : mysql1
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : bishe

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2018-03-27 21:36:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ps_bench
-- ----------------------------
DROP TABLE IF EXISTS `ps_bench`;
CREATE TABLE `ps_bench` (
  `ps_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '台架号 默认自增 唯一主键',
  `ps_name` varchar(255) NOT NULL COMMENT '台架名称 ',
  `yn` int(2) NOT NULL COMMENT '是否有效',
  `created` datetime(6) NOT NULL COMMENT '创建时间',
  `modified` datetime(6) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`ps_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ps_command
-- ----------------------------
DROP TABLE IF EXISTS `ps_command`;
CREATE TABLE `ps_command` (
  `com_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '该命令记录对应id 自动递增 唯一主键',
  `ps_id` int(11) NOT NULL COMMENT '执行该命令的台架号',
  `com_name` varchar(255) NOT NULL COMMENT '命令名称',
  `phone_id` varchar(255) NOT NULL COMMENT '操作该命令的用户账户',
  `yn` int(2) NOT NULL COMMENT '是否有效',
  `created` datetime(6) NOT NULL COMMENT '创建时间',
  `com_state` int(2) NOT NULL COMMENT '记录命令被执行的状态 0-未执行 1-正在执行 2-执行完成',
  PRIMARY KEY (`com_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ps_datafile
-- ----------------------------
DROP TABLE IF EXISTS `ps_datafile`;
CREATE TABLE `ps_datafile` (
  `dafi_id` int(11) NOT NULL COMMENT '数据文件表id 默认自增 唯一主键',
  `ps_id` int(11) NOT NULL COMMENT '对应台架号',
  PRIMARY KEY (`dafi_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ps_device
-- ----------------------------
DROP TABLE IF EXISTS `ps_device`;
CREATE TABLE `ps_device` (
  `de_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '设备状态表id 默认自增 唯一主键',
  `ps_id` int(11) NOT NULL COMMENT '对应台架号',
  `ps_devname` varchar(255) NOT NULL COMMENT '设备名称',
  `ps_lastrecvtime` datetime NOT NULL COMMENT '最后数据接收时间',
  `ps_devstatus` int(2) NOT NULL COMMENT '状态 0-已连接 1-未连接',
  PRIMARY KEY (`de_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ps_devicealarm
-- ----------------------------
DROP TABLE IF EXISTS `ps_devicealarm`;
CREATE TABLE `ps_devicealarm` (
  `alarm_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '设备报警表id 默认自增 唯一主键',
  `ps_id` int(11) NOT NULL COMMENT '对应台架号',
  `para_name` varchar(255) NOT NULL COMMENT '说明该行参数对应的台架号和台架的具体某个参数',
  `para_value` double(255,6) NOT NULL COMMENT '该参数对应值',
  `yn` int(2) NOT NULL COMMENT '是否有效',
  `created` datetime(6) NOT NULL COMMENT '创建时间',
  `de_state` int(2) NOT NULL COMMENT '对应设备报警状态 0-报警 1-正在报警 2-报过警',
  PRIMARY KEY (`alarm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ps_drive
-- ----------------------------
DROP TABLE IF EXISTS `ps_drive`;
CREATE TABLE `ps_drive` (
  `dr_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '驱动控制信息表id 默认自增 唯一主键',
  `ps_id` int(11) NOT NULL COMMENT '对应台架号',
  `dr_mode` int(2) NOT NULL COMMENT '驱动控制模式 0-P1/P 1-n1/P',
  `dr_load` double(20,1) NOT NULL COMMENT '驱动控制负载 给定负载',
  `dr_ramptime` int(11) NOT NULL COMMENT '斜坡时间',
  `dr_reverse` int(2) NOT NULL COMMENT '是否翻转',
  `dr_remotestatus` int(2) NOT NULL,
  `yn` int(2) NOT NULL COMMENT '是否有效',
  `created` datetime(6) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`dr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ps_fault
-- ----------------------------
DROP TABLE IF EXISTS `ps_fault`;
CREATE TABLE `ps_fault` (
  `fault_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '故障控制信息表id 默认自增 唯一主键',
  `fault_name` varchar(255) NOT NULL COMMENT '操作名称',
  `ps_id` int(11) NOT NULL COMMENT '对应台架号',
  `yn` int(2) NOT NULL COMMENT '是否有效',
  `created` datetime(6) NOT NULL COMMENT '创建时间',
  `phone_id` varchar(255) NOT NULL COMMENT '对应操作用户账号',
  `fault_state` int(2) NOT NULL COMMENT '故障状态 0-故障 1-正常',
  PRIMARY KEY (`fault_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ps_file
-- ----------------------------
DROP TABLE IF EXISTS `ps_file`;
CREATE TABLE `ps_file` (
  `file_id` int(11) NOT NULL COMMENT '文件管理模块文件主键 默认自增 唯一主键',
  `file_name` varchar(255) NOT NULL COMMENT '文件（程控）名称 ',
  `file_test_type` varchar(255) NOT NULL COMMENT '实验类型',
  `ps_id` int(11) NOT NULL COMMENT '测试对应台架的台架号',
  `yn` int(2) NOT NULL COMMENT '是否有效',
  `created` datetime(6) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ps_filselect
-- ----------------------------
DROP TABLE IF EXISTS `ps_filselect`;
CREATE TABLE `ps_filselect` (
  `file_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文件管理模块文件id 默认自增 唯一主键',
  `file_name` varchar(255) NOT NULL COMMENT '对应文件（程控）名称',
  `file_test_type` varchar(255) NOT NULL COMMENT '试验类型',
  `ps_id` int(11) NOT NULL COMMENT '对应实验台架号',
  `file_state` int(2) NOT NULL COMMENT '记录文件被执行的状态 0-未执行 1-正在执行 2-执行完成',
  `yn` int(2) NOT NULL COMMENT '是否有效',
  `created` datetime(6) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ps_gear
-- ----------------------------
DROP TABLE IF EXISTS `ps_gear`;
CREATE TABLE `ps_gear` (
  `gear_id` int(11) NOT NULL COMMENT '档位控制信息表id 默认自增 唯一主键',
  `gear_name` int(4) NOT NULL COMMENT '档位名称类型 0-无档 1-1档 2-2档 3-3档 4-4档 5-5档 6-6档 7-R档',
  `ps_id` int(11) NOT NULL COMMENT '对应台架号',
  `gear_state` int(4) NOT NULL COMMENT '当前台架档位',
  `yn` int(2) NOT NULL COMMENT '是否有效',
  `created` datetime(6) NOT NULL COMMENT '创建时间',
  `phone_id` varchar(255) NOT NULL COMMENT '对应操作用户账号',
  PRIMARY KEY (`gear_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ps_gearbox
-- ----------------------------
DROP TABLE IF EXISTS `ps_gearbox`;
CREATE TABLE `ps_gearbox` (
  `gb_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '变速箱档位表id 默认自增 唯一主键',
  `tested_name` varchar(255) NOT NULL COMMENT '被试件名称',
  `gear_name` int(4) NOT NULL COMMENT '档位名称类型 0-无档 1-1档 2-2档 3-3档 4-4档 5-5档 6-6档 7-R档',
  `ps_id` int(11) NOT NULL COMMENT '对应台架号',
  `gear_order` int(4) NOT NULL COMMENT '档位ID',
  `gear_speed` double(20,4) NOT NULL COMMENT '速比',
  `gear_value` double(20,4) NOT NULL COMMENT '数值',
  PRIMARY KEY (`gb_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ps_load
-- ----------------------------
DROP TABLE IF EXISTS `ps_load`;
CREATE TABLE `ps_load` (
  `lo_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '负载控制管理表id 默认自增 唯一主键',
  `ps_id` int(11) NOT NULL COMMENT '对应台架号',
  `lo_mode` int(11) NOT NULL COMMENT '负载控制模式 0-P1/P 1-n1/P',
  `lo1_speed` double(20,1) NOT NULL COMMENT '负载1转速',
  `lo2-speed` double(20,1) NOT NULL COMMENT '负载2转速',
  `lo_ramptime` int(11) NOT NULL COMMENT '斜坡时间',
  `lo1_reverse` int(2) NOT NULL COMMENT '负载1是否反转',
  `lo2-reverse` int(2) NOT NULL COMMENT '负载2是否反转',
  `yn` int(2) NOT NULL COMMENT '是否有效',
  `created` datetime(6) NOT NULL COMMENT '创建时间',
  `phone_id` varchar(255) NOT NULL COMMENT '对应操作用户账号',
  PRIMARY KEY (`lo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ps_log
-- ----------------------------
DROP TABLE IF EXISTS `ps_log`;
CREATE TABLE `ps_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志功能表id 默认自增 唯一主键',
  `ps_id` int(11) NOT NULL COMMENT '对应台架号',
  `log_errormes` varchar(255) NOT NULL COMMENT '日志记录错误信息',
  `log_time` datetime NOT NULL COMMENT '日志时间',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ps_parameter
-- ----------------------------
DROP TABLE IF EXISTS `ps_parameter`;
CREATE TABLE `ps_parameter` (
  `para_id` int(11) NOT NULL COMMENT '参数管理id,默认自增，唯一主键',
  `ps_id` int(11) NOT NULL COMMENT '该参数对应台架号',
  `para_name` varchar(255) NOT NULL COMMENT '说明该行参数对应的台架号和台架的具体某个参数',
  `para_value` double(255,6) NOT NULL COMMENT '该参数对应值',
  `para_unit` varchar(255) NOT NULL COMMENT '该参数对应单位',
  `para_format` int(2) NOT NULL COMMENT '参数对应显示小数点位数',
  `yn` int(2) NOT NULL COMMENT '是否有效',
  `created` datetime(6) NOT NULL COMMENT '创建时间',
  `modified` datetime(6) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`para_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ps_temp
-- ----------------------------
DROP TABLE IF EXISTS `ps_temp`;
CREATE TABLE `ps_temp` (
  `te_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '温度控制表id 自增 唯一主键',
  `ps_id` int(11) NOT NULL COMMENT '对应台架号',
  `te_name` varchar(255) NOT NULL COMMENT '温度控制名称',
  `te_value` double(20,2) NOT NULL COMMENT '设置值',
  `yn` int(2) NOT NULL COMMENT '是否有效',
  `created` datetime(6) NOT NULL COMMENT '创建时间',
  `phone_id` varchar(255) NOT NULL COMMENT '对应操作用户账号',
  PRIMARY KEY (`te_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ps_testrecord
-- ----------------------------
DROP TABLE IF EXISTS `ps_testrecord`;
CREATE TABLE `ps_testrecord` (
  `test_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '试验记录表id 默认自增 唯一主键',
  `ps_id` int(11) NOT NULL COMMENT '对应台架号',
  `para_name` varchar(255) NOT NULL COMMENT '说明该行参数对应的台架号和台架的具体某个参数',
  `para_value` double(255,6) NOT NULL COMMENT '该参数对应值',
  `yn` int(2) NOT NULL COMMENT '是否有效',
  `created` datetime(6) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`test_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
