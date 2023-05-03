CREATE SCHEMA IF NOT EXISTS `Hospital_System` DEFAULT CHARACTER SET utf8 ;
USE `Hospital_System` ;

CREATE TABLE IF NOT EXISTS `Hospital_System`.`User` (
  `User_id` INT NOT NULL,
  `TC_id` VARCHAR(15) NOT NULL,
  `User_name` VARCHAR(255) NULL,
  `User_password` VARCHAR(255),
  `User_type` ENUM('patient', 'doctor', 'chiefphysician') DEFAULT 'patient',
  PRIMARY KEY (`User_id`))
ENGINE = InnoDB;

SELECT * FROM hospital_system.user;

INSERT INTO `hospital_system`.`user` (`User_id`, `TC_id`, `User_name`, `User_password`, `User_type`) VALUES ('4', '10053027480', 'Deno', '1258', 'patient');
INSERT INTO `hospital_system`.`user` (`User_id`, `TC_id`, `User_name`, `User_password`, `User_type`) VALUES ('5', '10053027481', 'Alaa', '4589', 'doctor');
INSERT INTO `hospital_system`.`user` (`User_id`, `TC_id`, `User_name`, `User_password`, `User_type`) VALUES ('3', '10053027482', 'Joe', '89654', 'doctor');
INSERT INTO `hospital_system`.`user` (`User_id`, `TC_id`, `User_name`, `User_password`, `User_type`) VALUES ('1', '10053027483', 'Kate', '25469', 'patient');
INSERT INTO `hospital_system`.`user` (`User_id`, `TC_id`, `User_name`, `User_password`, `User_type`) VALUES ('2', '100530274845', 'Maxim', '1489', 'doctor');
INSERT INTO `hospital_system`.`user` (`User_id`, `TC_id`, `User_name`, `User_password`, `User_type`) VALUES ('8', '1005302748894', 'Shela', '4589', 'patient');
INSERT INTO `hospital_system`.`user` (`User_id`, `TC_id`, `User_name`, `User_password`, `User_type`) VALUES ('9', '10053027484789', 'George', '1189', 'chiefphysician');
INSERT INTO `hospital_system`.`user` (`User_id`, `TC_id`, `User_name`, `User_password`, `User_type`) VALUES ('10', '100502748894', 'Diana', '1145', 'doctor');
INSERT INTO `hospital_system`.`user` (`User_id`, `TC_id`, `User_name`, `User_password`, `User_type`) VALUES ('8', '100530274889', 'Greek', '1254', 'doctor');
INSERT INTO `hospital_system`.`user` (`User_id`, `TC_id`, `User_name`, `User_password`, `User_type`) VALUES ('7', '10053027494', 'James', '2236', 'doctor');
INSERT INTO `hospital_system`.`user` (`User_id`, `TC_id`, `User_name`, `User_password`, `User_type`) VALUES ('6', '100530284789', 'Emily', '5189', 'patient');

CREATE TABLE IF NOT EXISTS `Hospital_System`.`clinic` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
SELECT * FROM hospital_system.clinic;
INSERT INTO `hospital_system`.`clinic` (`id`, `name`) VALUES ('3', 'Cardiovascular surgeon Clinic');
INSERT INTO `hospital_system`.`clinic` (`id`, `name`) VALUES ('4', 'Internal Diseases Clinic');
INSERT INTO `hospital_system`.`clinic` (`id`, `name`) VALUES ('5', 'Brain Diseases Clinic');
INSERT INTO `hospital_system`.`clinic` (`id`, `name`) VALUES ('1', 'Eye Diseases Clinic');
INSERT INTO `hospital_system`.`clinic` (`id`, `name`) VALUES ('2', 'Ear Nose Throat Clinic');

CREATE TABLE IF NOT EXISTS `Hospital_System`.`whour` (
  `whour_id` INT NOT NULL,
  `doctor_id` INT NOT NULL,
  `doctor_name` VARCHAR(255) NOT NULL,
  `wdate` VARCHAR(255) ,
  `status` ENUM('P','A') DEFAULT 'A',
  PRIMARY KEY (`whour_id`))
ENGINE = InnoDB;

SELECT * FROM hospital_system.whour;
INSERT INTO `hospital_system`.`whour` (`whour_id`, `doctor_id`, `doctor_name`, `wdate`, `status`) VALUES ('7', '2', 'Maxim', '19.08.2022', 'A');
INSERT INTO `hospital_system`.`whour` (`whour_id`, `doctor_id`, `doctor_name`, `wdate`, `status`) VALUES ('8', '7', 'James', '11.11.2022', 'A');
INSERT INTO `hospital_system`.`whour` (`whour_id`, `doctor_id`, `doctor_name`, `wdate`, `status`) VALUES ('5', '10', 'Diana', '9.10.2022', 'A');
INSERT INTO `hospital_system`.`whour` (`whour_id`, `doctor_id`, `doctor_name`, `wdate`, `status`) VALUES ('6', '8', 'Greek', '10.10.2022', 'P');
INSERT INTO `hospital_system`.`whour` (`whour_id`, `doctor_id`, `doctor_name`, `wdate`, `status`) VALUES ('4', '5', 'Alaa', '15.09.2022', 'P');

CREATE TABLE IF NOT EXISTS `Hospital_System`.`worker` (
  `id` INT  NOT NULL,
  `User_id` INT NOT NULL,
  `Clinic_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SELECT * FROM hospital_system.worker;
INSERT INTO `hospital_system`.`worker` (`id`, `User_id`, `Clinic_id`) VALUES ('1', '9', '5');
INSERT INTO `hospital_system`.`worker` (`id`, `User_id`, `Clinic_id`) VALUES ('2', '3', '4');
INSERT INTO `hospital_system`.`worker` (`id`, `User_id`, `Clinic_id`) VALUES ('4', '5', '1');
INSERT INTO `hospital_system`.`worker` (`id`, `User_id`, `Clinic_id`) VALUES ('3', '2', '2');
INSERT INTO `hospital_system`.`worker` (`id`, `User_id`, `Clinic_id`) VALUES ('5', '10', '3');
INSERT INTO `hospital_system`.`worker` (`id`, `User_id`, `Clinic_id`) VALUES ('6', '8', '4');

CREATE TABLE IF NOT EXISTS `Hospital_System`.`appointment` (
  `id` INT NOT NULL,
  `doctorID` INT NOT NULL,
  `doctorName` VARCHAR(255) NOT NULL,
  `patientID` INT NOT NULL,
  `patientName` VARCHAR(255) NOT NULL,
  `appDate` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SELECT * FROM hospital_system.appointment;
INSERT INTO `hospital_system`.`appointment` (`id`, `doctorID`, `doctorName`, `patientID`, `patientName`, `appDate`) VALUES ('1', '2', 'Maxim', '12', 'Snow', '26.12.2022');
INSERT INTO `hospital_system`.`appointment` (`id`, `doctorID`, `doctorName`, `patientID`, `patientName`, `appDate`) VALUES ('5', '7', 'James', '13', 'Vera', '27.12.2022');
INSERT INTO `hospital_system`.`appointment` (`id`, `doctorID`, `doctorName`, `patientID`, `patientName`, `appDate`) VALUES ('6', '10', 'Diana', '14', 'Siena', '28.12.2022');
INSERT INTO `hospital_system`.`appointment` (`id`, `doctorID`, `doctorName`, `patientID`, `patientName`, `appDate`) VALUES ('2', '5', 'Alaa', '16', 'Katy', '29.12.2022');
INSERT INTO `hospital_system`.`appointment` (`id`, `doctorID`, `doctorName`, `patientID`, `patientName`, `appDate`) VALUES ('8', '8', 'Greek', '23', 'Bob', '30.12.2022');

SET SQL_SAFE_UPDATES = 0;
DELETE FROM appointment WHERE appDate= '28.12.2022';
UPDATE whour SET status='P' WHERE doctor_name='Maxim' AND wdate='26.12.2022' ;
SELECT * FROM user WHERE User_type = 'doctor';

SELECT u.User_id,u.TC_id,u.User_type,u.User_name,u.User_password FROM worker w LEFT JOIN User u ON w.id = u.User_id WHERE clinic_id=2;