CREATE TABLE `client` (
  `client_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(40) NOT NULL,
  `gender` varchar(1) NOT NULL COMMENT 'M, F, O',
  `age` int(11) DEFAULT NULL,
  `maritalstatus` varchar(1) DEFAULT NULL,
  `profession` varchar(45) DEFAULT NULL,
  `education` varchar(45) DEFAULT NULL,
  `address` varchar(60) DEFAULT NULL,
  `city` varchar(20) NOT NULL,
  `state` varchar(25) NOT NULL,
  `pin` int(11) DEFAULT NULL,
  `country` varchar(15) DEFAULT NULL,
  `mobile` varchar(15) NOT NULL,
  `phonenumber2` varchar(15) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `reference` varchar(30) DEFAULT NULL,
  `status` varchar(1) NOT NULL COMMENT 'Y : Ongoing, C : Closed, L : Left',
  `followupdate` date DEFAULT NULL,
  `created_on` date DEFAULT NULL,
  `updated_on` date DEFAULT NULL,
  PRIMARY KEY (`client_id`),
  KEY `MOBILE` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;