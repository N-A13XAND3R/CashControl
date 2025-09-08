-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema cashcontroldb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `cashcontroldb` ;

-- -----------------------------------------------------
-- Schema cashcontroldb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cashcontroldb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `cashcontroldb` ;

-- -----------------------------------------------------
-- Table `cashcontroldb`.`informacionpersonal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cashcontroldb`.`informacionpersonal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_persona` DOUBLE NOT NULL,
  `usuario` VARCHAR(255) NOT NULL,
  `contraseña` VARCHAR(255) NOT NULL,
  `nombre` VARCHAR(255) NOT NULL,
  `apellido` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `telefono` DOUBLE NULL DEFAULT NULL,
  `ingresosmensuales` DOUBLE NULL DEFAULT NULL,
  `transporte` DOUBLE NULL DEFAULT NULL,
  `alimentacion` DOUBLE NULL DEFAULT NULL,
  `servicios` DOUBLE NULL DEFAULT NULL,
  `educacion` DOUBLE NULL DEFAULT NULL,
  `entretenimiento` DOUBLE NULL DEFAULT NULL,
  `personal` DOUBLE NULL DEFAULT NULL,
  `excedentefindemes` DOUBLE NULL DEFAULT NULL,
  `perfilconsumo` DOUBLE NULL DEFAULT NULL,
  `metodopagomasusado` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cashcontroldb`.`transacciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cashcontroldb`.`transacciones` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `persona` INT NOT NULL,
  `fecha_registro` DATE NOT NULL,
  `monto` VARCHAR(45) NULL,
  `metodo_de_pago` VARCHAR(45) NULL,
  `tipo_de_transaccion` VARCHAR(45) NULL,
  `nuevo_presupuesto_diario` VARCHAR(45) NULL,
  PRIMARY KEY (`id`, `persona`),
  CONSTRAINT `fk_transacciones_informacionpersonal`
    FOREIGN KEY (`persona`)
    REFERENCES `cashcontroldb`.`informacionpersonal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cashcontroldb`.`caracteristicas_pago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cashcontroldb`.`caracteristicas_pago` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `persona` INT NOT NULL,
  `fecha_registro` DATE NOT NULL,
  `costo_por_transaccion` VARCHAR(45) NULL,
  `cashback` VARCHAR(45) NULL,
  `intereses` VARCHAR(45) NULL,
  `descuento` VARCHAR(45) NULL,
  PRIMARY KEY (`id`, `persona`),
  CONSTRAINT `fk_transacciones_informacionpersonal0`
    FOREIGN KEY (`persona`)
    REFERENCES `cashcontroldb`.`informacionpersonal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
