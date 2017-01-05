CREATE DATABASE `pharmacy` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE pharmacy.`workitem` (
  `workItemID` varchar(45) NOT NULL,
  `workItemStatus` varchar(45) DEFAULT NULL,
  `orderID` varchar(45) NOT NULL,
  `newElement` varchar(45) DEFAULT NULL,
  `orderStatus` varchar(45) DEFAULT NULL,
  `clientID` varchar(45) DEFAULT NULL,
  `prescriberName` varchar(45) DEFAULT NULL,
  `prescriberId` varchar(45) DEFAULT NULL,
  `prescriberstate` varchar(45) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`workItemID`,`orderID`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `orderID_UNIQUE` (`orderID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

CREATE TABLE pharmacy.`lineitems` (
  `orderID` varchar(45) DEFAULT NULL,
  `drugName` varchar(45) DEFAULT NULL,
  `quantity` varchar(45) DEFAULT NULL,
  `rphId` varchar(45) DEFAULT NULL,
  KEY `FK_WorkitemOrderID_idx` (`orderID`),
  CONSTRAINT `FK_WorkitemOrderID` FOREIGN KEY (`orderID`) REFERENCES `workitem` (`orderID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE pharmacy.`pharmacy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pharmacyID` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `pharmacyID_UNIQUE` (`pharmacyID`),
  UNIQUE KEY `state_UNIQUE` (`state`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE pharmacy.`pharmacist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pharmacistId` varchar(45) NOT NULL,
  `state` varchar(45) DEFAULT NULL,
  `certification` varchar(45) DEFAULT NULL,
  `pharmacyId` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`,`pharmacistId`),
  UNIQUE KEY `pharmacistId_UNIQUE` (`pharmacistId`),
  KEY `FK_PharmacyId_idx` (`pharmacyId`),
  CONSTRAINT `FK_PharmacyId` FOREIGN KEY (`pharmacyId`) REFERENCES `pharmacy` (`pharmacyID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE pharmacy.`rph_workitem_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pharmacistid` varchar(45) NOT NULL,
  `orderId` varchar(45) NOT NULL,
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `pharmacistid_UNIQUE` (`pharmacistid`),
  UNIQUE KEY `orderId_UNIQUE` (`orderId`),
  CONSTRAINT `FK_orderID` FOREIGN KEY (`orderId`) REFERENCES `workitem` (`orderID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_pharmacistId` FOREIGN KEY (`pharmacistid`) REFERENCES `pharmacist` (`pharmacistId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

COMMIT;