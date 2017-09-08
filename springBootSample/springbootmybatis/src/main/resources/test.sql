/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.40-0+wheezy1-log : Database - mydb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mydb` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `mydb`;

/*Table structure for table `tbl_user` */

DROP TABLE IF EXISTS `tbl_user`;

CREATE TABLE `tbl_user` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `username` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '名字',
  `password` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `age` smallint(6) DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户表';

/*Data for the table `tbl_user` */

insert  into `tbl_user`(`id`,`username`,`password`,`age`) values ('1111111e','赵飞燕','888888',32),('1111333e','曹操','888888',32),('22123fjdkfjdkfjse','Lisa',NULL,22),('222222d','赵子龙','888888',25),('2222232d','司马懿','888888',25),('3333322','张颌','888888',16),('3333331','孙权','888888',16),('402881e458c2e6ab0158c2e6ab840000','pkaq',NULL,24),('402881e458c2e9230158c2e923670000','诸葛亮','3333333',24),('4028b881521c318301521c3183d20000','aaron',NULL,30),('4028b881521c3fc601521c3fc6200000','kaka',NULL,25),('4028b8815230d2f5015230d2f5cf0000','andoni',NULL,30),('abcdd33df','Tompus',NULL,29),('abcdds23f','Gelin',NULL,28),('abcddsfdf','kuli',NULL,22),('sdf343dfeerer','Lucy',NULL,25);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
