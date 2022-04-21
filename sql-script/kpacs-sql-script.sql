DROP DATABASE IF EXISTS `kpac`;
CREATE DATABASE kpac;
USE kpac;

DROP TABLE IF EXISTS `knowledgepackage`;
CREATE TABLE `knowledgepackage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(250) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `creationDate` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
LOCK TABLES `knowledgepackage` WRITE;

INSERT INTO `knowledgepackage` VALUES (1,'title1','kjsk dskjdskdl','11-11-2011'),(2,'title2','kjeh ekje dijd','12-12-2012'),(6,'hello title','kj khd fdis','20-04-2022'),(7,'title 564','sajdk ewoijoe kdj','21-04-2022'),(8,'surie oiswq wqijd','woiq oiwueo oiqwe qwoijeowq swqiojw','21-04-2022');
UNLOCK TABLES;

DROP TABLE IF EXISTS `knowledgepackageset`;
CREATE TABLE `knowledgepackageset` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
LOCK TABLES `knowledgepackageset` WRITE;

INSERT INTO `knowledgepackageset` VALUES (2,'k title2'),(3,'k titl3'),(6,'tlp6 jfds'),(7,'jhdis 7 erfjs'),(8,'tile kp6'),(9,'bello metrl');
UNLOCK TABLES;

DROP TABLE IF EXISTS `kpskp`;
CREATE TABLE `kpskp` (
  `kpacSetId` bigint(20) NOT NULL,
  `kpacId` bigint(20) NOT NULL,
  PRIMARY KEY (`kpacSetId`,`kpacId`),
  KEY `kpskp_ibfk_2` (`kpacId`),
  CONSTRAINT `kpskp_ibfk_1` FOREIGN KEY (`kpacSetId`) REFERENCES `knowledgepackageset` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `kpskp_ibfk_2` FOREIGN KEY (`kpacId`) REFERENCES `knowledgepackage` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
LOCK TABLES `kpskp` WRITE;

INSERT INTO `kpskp` VALUES (2,1),(7,2),(9,2),(9,6),(8,7),(8,8);
UNLOCK TABLES;