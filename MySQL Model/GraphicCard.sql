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
  `CUSTOMER_ID` INT NOT NULL,
  `USERNAME` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(45) NOT NULL,
  `EMAIL` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`CUSTOMER_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GraphicCards`.`NVIDIA_REQUEST`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GraphicCards`.`NVIDIA_REQUEST` (
  `ID` INT NOT NULL,
  `MODEL` VARCHAR(45) NOT NULL,
  `INVENTORY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GraphicCards`.`AMD_REQUEST`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GraphicCards`.`AMD_REQUEST` (
  `ID` INT NOT NULL,
  `MODEL` VARCHAR(45) NOT NULL,
  `INVENTORY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GraphicCards`.`MANAGER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GraphicCards`.`MANAGER` (
  `MANAGER_ID` INT NOT NULL,
  `USERNAME` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(45) NOT NULL,
  `EMAIL` VARCHAR(45) NOT NULL,
  `NVIDIA_ID` INT NOT NULL,
  `AMD_ID` INT NOT NULL,
  PRIMARY KEY (`MANAGER_ID`),
  INDEX `fk_MANAGER_NVIDIA1_idx` (`NVIDIA_ID` ASC) VISIBLE,
  INDEX `fk_MANAGER_AMD1_idx` (`AMD_ID` ASC) VISIBLE,
  CONSTRAINT `fk_MANAGER_NVIDIA1`
    FOREIGN KEY (`NVIDIA_ID`)
    REFERENCES `GraphicCards`.`NVIDIA_REQUEST` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MANAGER_AMD1`
    FOREIGN KEY (`AMD_ID`)
    REFERENCES `GraphicCards`.`AMD_REQUEST` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GraphicCards`.`GRAPHIC_CARD`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GraphicCards`.`GRAPHIC_CARD` (
  `GRAPHIC_ID` INT NOT NULL,
  `MANUFACTURER` VARCHAR(45) NOT NULL,
  `RAM(GB)` VARCHAR(45) NOT NULL,
  `BOOST_CLOCK` INT NOT NULL,
  `PRICE` INT NOT NULL,
  `MODEL` VARCHAR(45) NOT NULL,
  `MEMORY_TYPE` VARCHAR(45) NOT NULL,
  `INVENTORY` VARCHAR(45) NOT NULL,
  `CUSTOMER_CUSTOMER_ID` INT NOT NULL,
  `MANAGER_MANAGER_ID` INT NOT NULL,
  PRIMARY KEY (`GRAPHIC_ID`),
  INDEX `fk_GRAPHIC_CARD_CUSTOMER_idx` (`CUSTOMER_CUSTOMER_ID` ASC) VISIBLE,
  INDEX `fk_GRAPHIC_CARD_MANAGER1_idx` (`MANAGER_MANAGER_ID` ASC) VISIBLE,
  CONSTRAINT `fk_GRAPHIC_CARD_CUSTOMER`
    FOREIGN KEY (`CUSTOMER_CUSTOMER_ID`)
    REFERENCES `GraphicCards`.`CUSTOMER` (`CUSTOMER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_GRAPHIC_CARD_MANAGER1`
    FOREIGN KEY (`MANAGER_MANAGER_ID`)
    REFERENCES `GraphicCards`.`MANAGER` (`MANAGER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GraphicCards`.`PURCHASE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GraphicCards`.`PURCHASE` (
  `PURCHASE_ID` INT NOT NULL,
  `USERNAME` VARCHAR(45) NOT NULL,
  `MODEL` VARCHAR(45) NOT NULL,
  `MANUFACTURER` VARCHAR(45) NOT NULL,
  `QUANTITY` INT NOT NULL,
  `CUSTOMER_CUSTOMER_ID` INT NOT NULL,
  PRIMARY KEY (`PURCHASE_ID`),
  INDEX `fk_PURCHASE_CUSTOMER1_idx` (`CUSTOMER_CUSTOMER_ID` ASC) VISIBLE,
  CONSTRAINT `fk_PURCHASE_CUSTOMER1`
    FOREIGN KEY (`CUSTOMER_CUSTOMER_ID`)
    REFERENCES `GraphicCards`.`CUSTOMER` (`CUSTOMER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
