DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `contrato`;
CREATE TABLE `contrato` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cliente_id` int DEFAULT NULL,
  `item_id` int DEFAULT NULL,
  `data_inicio` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `finalizado` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `cliente_id` (`cliente_id`),
  KEY `item_id` (`item_id`),
  CONSTRAINT `contrato_ibfk_1` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  CONSTRAINT `contrato_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
);

DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `disponivel` tinyint(1) DEFAULT NULL,
  `tipo` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

SELECT * FROM contrato;

