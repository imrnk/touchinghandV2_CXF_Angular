CREATE TABLE `session` (
  `session_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `client_id` int(10) unsigned NOT NULL,
  `session_date` date NOT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `followup_date` date DEFAULT NULL,
  PRIMARY KEY (`session_id`),
  KEY `fk_session_client_id_idx` (`client_id`),
  CONSTRAINT `fk_session_client_id` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
