DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `disponivel` tinyint(1) DEFAULT NULL,
  `tipo` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `contrato`;
CREATE TABLE contrato (
	id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT,
    item_id INT,
    dias INT,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    FOREIGN KEY (item_id) REFERENCES item(id)
);

ALTER TABLE contrato
DROP FOREIGN KEY contrato_ibfk_2;

ALTER TABLE contrato
ADD CONSTRAINT contrato_ibfk_2
FOREIGN KEY (item_id) REFERENCES item(id)
ON DELETE CASCADE;
