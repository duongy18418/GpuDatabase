-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema GraphicCards
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `GraphicCards` ;

-- -----------------------------------------------------
-- Schema GraphicCards
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `GraphicCards` DEFAULT CHARACTER SET utf8 ;
USE `GraphicCards` ;

-- -----------------------------------------------------
-- Table `GraphicCards`.`CUSTOMER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GraphicCards`.`CUSTOMER` (
  `CUSTOMER_ID` INT NOT NULL AUTO_INCREMENT,
  `USERNAME` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(45) NOT NULL,
  `EMAIL` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`CUSTOMER_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GraphicCards`.`NVIDIA_REQUEST`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GraphicCards`.`NVIDIA_REQUEST` (
  `NVIDIA_ID` INT NOT NULL AUTO_INCREMENT,
  `GRAPHIC_ID` INT NOT NULL,
  `QUANTITY` INT NOT NULL,
  PRIMARY KEY (`NVIDIA_ID`),
  INDEX `fk_NVIDIA_REQUEST_GRAPHIC_CARD1_idx` (`GRAPHIC_ID` ASC) VISIBLE,
  CONSTRAINT `fk_NVIDIA_REQUEST_GRAPHIC_CARD1`
    FOREIGN KEY (`GRAPHIC_ID`)
    REFERENCES `GraphicCards`.`GRAPHIC_CARD` (`GRAPHIC_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GraphicCards`.`AMD_REQUEST`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GraphicCards`.`AMD_REQUEST` (
  `AMD_ID` INT NOT NULL AUTO_INCREMENT,
  `GRAPHIC_ID` INT NOT NULL,
  `QUANTITY` INT NOT NULL,
  PRIMARY KEY (`AMD_ID`),
  INDEX `fk_AMD_REQUEST_GRAPHIC_CARD1_idx` (`GRAPHIC_ID` ASC) VISIBLE,
  CONSTRAINT `fk_AMD_REQUEST_GRAPHIC_CARD1`
    FOREIGN KEY (`GRAPHIC_ID`)
    REFERENCES `GraphicCards`.`GRAPHIC_CARD` (`GRAPHIC_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GraphicCards`.`MANAGER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GraphicCards`.`MANAGER` (
  `MANAGER_ID` INT NOT NULL AUTO_INCREMENT,
  `USERNAME` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(45) NOT NULL,
  `EMAIL` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`MANAGER_ID`))
ENGINE = InnoDB;

INSERT INTO MANAGER VALUES ('1', 'admin', 'admin', 'manager@gmail.com');
-- -----------------------------------------------------
-- Table `GraphicCards`.`GRAPHIC_CARD`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GraphicCards`.`GRAPHIC_CARD` (
  `GRAPHIC_ID` INT NOT NULL AUTO_INCREMENT,
  `MODEL` VARCHAR(45) NOT NULL,
  `MANUFACTURER` VARCHAR(45) NOT NULL,
  `RAM(GB)` INT NOT NULL,
  `MEMORY_TYPE` VARCHAR(45) NOT NULL,
  `BOOST_CLOCK` INT NOT NULL,
  `PRICE` DOUBLE NOT NULL,
  `INVENTORY` INT NOT NULL,
  PRIMARY KEY (`GRAPHIC_ID`))
ENGINE = InnoDB;

INSERT INTO GRAPHIC_CARD VALUE ('1', 'RTX 3070 FE', 'NVIDIA', '8', 'GDDR6', '1.73', '499.99', '6');
INSERT INTO GRAPHIC_CARD VALUE ('2', 'RTX 3080 FE', 'NVIDIA', '10', 'GDDR6X', '1.83', '699.99', '2');
INSERT INTO GRAPHIC_CARD VALUE ('3', 'RTX 3090 FE', 'NVIDIA', '24', 'GDDR6X', '1.70', '1499.99', '0');
INSERT INTO GRAPHIC_CARD VALUE ('4', 'RADEON RX 6700XT', 'AMD', '12', 'GDDR6', '2.62', '499.99', '4');
INSERT INTO GRAPHIC_CARD VALUE ('5', 'RADEON RX 6800XT', 'AMD', '16', 'GDDR6', '2.30', '649.99', '1');
INSERT INTO GRAPHIC_CARD VALUE ('6', 'RADEON RX 6900XT', 'AMD', '16', 'GDDR6', '2.30', '999.99', '0');

-- -----------------------------------------------------
-- Table `GraphicCards`.`PURCHASE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GraphicCards`.`PURCHASE` (
  `PURCHASE_ID` INT NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` INT NOT NULL,
  `GRAPHIC_ID` INT NOT NULL,
  `QUANTITY` INT NOT NULL,
  PRIMARY KEY (`PURCHASE_ID`),
  INDEX `fk_PURCHASE_CUSTOMER1_idx` (`CUSTOMER_ID` ASC) VISIBLE,
  INDEX `fk_PURCHASE_GRAPHIC_CARD1_idx` (`GRAPHIC_ID` ASC) VISIBLE,
  CONSTRAINT `fk_PURCHASE_CUSTOMER1`
    FOREIGN KEY (`CUSTOMER_ID`)
    REFERENCES `GraphicCards`.`CUSTOMER` (`CUSTOMER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PURCHASE_GRAPHIC_CARD1`
    FOREIGN KEY (`GRAPHIC_ID`)
    REFERENCES `GraphicCards`.`GRAPHIC_CARD` (`GRAPHIC_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
