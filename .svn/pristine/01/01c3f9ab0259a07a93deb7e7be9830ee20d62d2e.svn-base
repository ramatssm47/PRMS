SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema phoenix
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `phoenix` ;
CREATE SCHEMA IF NOT EXISTS `phoenix` DEFAULT CHARACTER SET utf8 ;
USE `phoenix` ;

-- -----------------------------------------------------
-- Table `phoenix`.`annualschedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`annualschedule` ;

CREATE TABLE IF NOT EXISTS `phoenix`.`annualschedule` (
  `Year` INT(11) NOT NULL,
  PRIMARY KEY (`Year`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `phoenix`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`user` ;

CREATE TABLE IF NOT EXISTS `phoenix`.`user` (
  `UID` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(40) NOT NULL,
  `Password` VARCHAR(20) NOT NULL,
  `Status` INT(11) NOT NULL,
  `Email_Id` VARCHAR(20) NULL DEFAULT NULL,
  `Contact` DECIMAL(8,0) NULL DEFAULT NULL,
  `userId` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`UID`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `phoenix`.`weeklyschedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`weeklyschedule` ;

CREATE TABLE IF NOT EXISTS `phoenix`.`weeklyschedule` (
  `WSID` INT(11) NOT NULL AUTO_INCREMENT,
  `Year` INT(11) NOT NULL,
  `WeekNo` INT(11) NOT NULL,
  PRIMARY KEY (`WSID`),
  INDEX `FK_idx` (`Year` ASC),
  CONSTRAINT `FK_As`
    FOREIGN KEY (`Year`)
    REFERENCES `phoenix`.`annualschedule` (`Year`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `phoenix`.`programslot`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`programslot` ;

CREATE TABLE IF NOT EXISTS `phoenix`.`programslot` (
  `PSID` INT(11) NOT NULL AUTO_INCREMENT,
  `PName` VARCHAR(45) NULL DEFAULT NULL,
  `DayNo` VARCHAR(45) NULL DEFAULT NULL,
  `PRESID` INT(11) NULL DEFAULT NULL,
  `PRODID` INT(11) NULL DEFAULT NULL,
  `Status` INT(11) NULL DEFAULT NULL,
  `radio-program_progID` INT(11) NOT NULL,
  `DateFrom` DATETIME NOT NULL,
  `DateTo` DATETIME NOT NULL,
  `WSIDF` INT(11) NOT NULL,
  PRIMARY KEY (`PSID`, `radio-program_progID`, `WSIDF`),
  INDEX `fk_Program-Slot_radio-program1_idx` (`radio-program_progID` ASC),
  INDEX `fk_presenter_idx` (`PRESID` ASC),
  INDEX `fk_producer_idx` (`PRODID` ASC),
  INDEX `test_idx` (`WSIDF` ASC),
  CONSTRAINT `fk_presenter`
    FOREIGN KEY (`PRESID`)
    REFERENCES `phoenix`.`user` (`UID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_producer`
    FOREIGN KEY (`PRODID`)
    REFERENCES `phoenix`.`user` (`UID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `test`
    FOREIGN KEY (`WSIDF`)
    REFERENCES `phoenix`.`weeklyschedule` (`WSID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `phoenix`.`radioprogram`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`radioprogram` ;

CREATE TABLE IF NOT EXISTS `phoenix`.`radioprogram` (
  `name` VARCHAR(25) NOT NULL,
  `progID` INT(11) NOT NULL AUTO_INCREMENT,
  `typicalDuration` TIME NULL DEFAULT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`progID`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `phoenix`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`role` ;

CREATE TABLE IF NOT EXISTS `phoenix`.`role` (
  `RID` INT(11) NOT NULL AUTO_INCREMENT,
  `RName` VARCHAR(45) NOT NULL,
  `Status` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`RID`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `phoenix`.`userrole`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`userrole` ;

CREATE TABLE IF NOT EXISTS `phoenix`.`userrole` (
  `UID` INT(11) NOT NULL,
  `RID` INT(11) NOT NULL,
  INDEX `uid_idx` (`UID` ASC),
  INDEX `rid_idx` (`RID` ASC),
  CONSTRAINT `rid`
    FOREIGN KEY (`RID`)
    REFERENCES `phoenix`.`role` (`RID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `uid`
    FOREIGN KEY (`UID`)
    REFERENCES `phoenix`.`user` (`UID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



INSERT INTO `phoenix`.`role` (`RName`, `Status`) VALUES ('Presenter ', '1');
INSERT INTO `phoenix`.`role` (`RName`, `Status`) VALUES ('Producer', '1');
INSERT INTO `phoenix`.`role` (`RName`, `Status`) VALUES ('Admin', '1');
INSERT INTO `phoenix`.`role` (`RName`, `Status`) VALUES ('Station Manager', '1');


INSERT INTO `phoenix`.`radioprogram` (`name`, `typicalDuration`, `description`, `status`) VALUES ('Morning Break', '30', 'News', '1');
INSERT INTO `phoenix`.`radioprogram` (`name`, `typicalDuration`, `description`, `status`) VALUES ('Evening Break', '1', 'News', '1');


INSERT INTO `phoenix`.`user` (`Name`, `Password`, `Status`, `Email_Id`, `Contact`, `userId`) VALUES ('Dinesh', 'dinesh123', '1', 'dinesh@gmail.com', '94486261', 'dineshmoo');
INSERT INTO `phoenix`.`user` (`Name`, `Password`, `Status`, `Email_Id`, `Contact`, `userId`) VALUES ('Preeti Ramachandran', 'preeti123', '1', 'preeti@gmail.com', '85908868', 'preeti');


INSERT INTO `phoenix`.`annualschedule` (`Year`) VALUES ('2014');
INSERT INTO `phoenix`.`annualschedule` (`Year`) VALUES ('2015');
INSERT INTO `phoenix`.`annualschedule` (`Year`) VALUES ('2016');
