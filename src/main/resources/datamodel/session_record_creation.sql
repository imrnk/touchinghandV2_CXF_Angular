CREATE TABLE `session_record` (
  `record_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `session_id` int(11) unsigned NOT NULL,
  `impression` varchar(500) DEFAULT NULL,
  `feedback` varchar(1000) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  UNIQUE KEY `record_id_UNIQUE` (`record_id`),
  UNIQUE KEY `session_id_UNIQUE` (`session_id`),
  KEY `fk_session_record_1_idx` (`session_id`),
  CONSTRAINT `fk_session_record_1` FOREIGN KEY (`session_id`) REFERENCES `session` (`session_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
