use `DziennikMobilny`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `admin_detail`;

CREATE TABLE `admin_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `flags` varchar(45) DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `teacher_detail`;

CREATE TABLE `teacher_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `time_table`;

CREATE TABLE `time_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_TIMETABLE_STUDENTGR_idx` (`student_group_id`),
  CONSTRAINT `FK_TIMETABLE_STUDENTGR` FOREIGN KEY (`student_group_id`) REFERENCES `student_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `subject`;

CREATE TABLE `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `detail` varchar(45) DEFAULT NULL,
  `time` varchar(45) DEFAULT NULL,
  `time_table_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_SUBJECT_TIMETABLE_idx` (`time_table_id`),
  CONSTRAINT `FK_SUBJECT_TIMETABLE` FOREIGN KEY (`time_table_id`) REFERENCES `time_table` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `marks`;

CREATE TABLE `marks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_detail_id` int(11) DEFAULT NULL,
  `teacher_detail_id` int(11) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  `value` varchar(3) DEFAULT NULL,
  `creation_time` varchar(11) DEFAULT NULL,
  `eddition_time` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_MARK_STUDENT_idx` (`student_detail_id`),
  KEY `FK_MARK_TEACHER_idx` (`teacher_detail_id`),
  KEY `FK_MARK_SUBJECT_idx` (`subject_id`),
  CONSTRAINT `FK_MARK_STUDENT` FOREIGN KEY (`student_detail_id`) REFERENCES `student_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_MARK_TEACHER` FOREIGN KEY (`teacher_detail_id`) REFERENCES `teacher_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_MARK_SUBJECT` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;