DROP SCHEMA IF EXISTS `DziennikMobilny`;

CREATE SCHEMA `DziennikMobilny`;

use `DziennikMobilny`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `student_group`;

CREATE TABLE `student_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `student_detail`;

CREATE TABLE `student_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `index_number` varchar(128) DEFAULT NULL,
  `specialization` varchar(45) DEFAULT NULL,
  `student_group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_STUDENT_GROUP_idx` (`student_group_id`),
  CONSTRAINT `FK_STUDENT_GROUP` FOREIGN KEY (`student_group_id`) REFERENCES `student_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `user_detail`;

CREATE TABLE `user_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(128) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `telephone_number` varchar(45) DEFAULT NULL,
  `student_detail_id` int(11) DEFAULT NULL,
  `admin_detail_id` int(11) DEFAULT NULL,
  `teacher_detail_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_STUDENT_DETAIL_idx` (`student_detail_id`),
  KEY `FK_ADMIN_DETAIL_idx` (`admin_detail_id`),
  KEY `FK_TEACHER_DETAIL_idx` (`teacher_detail_id`),
  CONSTRAINT `FK_STUDENT_DETAIL` FOREIGN KEY (`student_detail_id`) REFERENCES `student_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ADMIN_DETAIL` FOREIGN KEY (`admin_detail_id`) REFERENCES `admin_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_TEACHER_DETAIL` FOREIGN KEY (`teacher_detail_id`) REFERENCES `teacher_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_detail_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`user_name`),
  KEY `FK_DETAIL_idx` (`user_detail_id`),
  CONSTRAINT `FK_DETAIL` FOREIGN KEY (`user_detail_id`) REFERENCES `user_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

