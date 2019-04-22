CREATE TABLE `cities` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `cityname` varchar(600) COLLATE utf8_czech_ci NOT NULL,
 `statename` varchar(60) COLLATE utf8_czech_ci NOT NULL,
 `cityid` int(11) DEFAULT NULL,
 PRIMARY KEY (`id`),
 KEY `statename` (`statename`),
 CONSTRAINT `cities_ibfk_1` FOREIGN KEY (`statename`) REFERENCES `states` (`statename`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci
