CREATE TABLE `stocks` (
  `stock_id` int(11) NOT NULL AUTO_INCREMENT,
  `ticker` varchar(50) NOT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `purchase_date` date DEFAULT NULL,
  PRIMARY KEY (`stock_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

