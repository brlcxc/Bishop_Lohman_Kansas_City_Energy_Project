CREATE DATABASE CustomerDataDB;
USE CustomerDataDB;

CREATE TABLE `Customer` (
  `CustomerID` char(8) NOT NULL,
  `CustomerFirstName` varchar(25) DEFAULT NULL,
  `CustomerLastName` varchar(25) DEFAULT NULL,
  `Email` varchar(30) DEFAULT NULL,
  `PhoneNumber` varchar(20) DEFAULT NULL,
  `Address` varchar(30) DEFAULT NULL,
  `City` varchar(20) DEFAULT NULL,
  `State` char(2) DEFAULT NULL,
  `Zip` char(5) DEFAULT NULL,
  `EnergyTariff` double DEFAULT NULL,
  `MeterType` varchar(20) DEFAULT NULL,
  `EnergyUsed` double DEFAULT NULL,
  `TotalDue` double DEFAULT NULL,
  `RemainingBalance` double DEFAULT NULL,
  `Paid` bool DEFAULT NULL,
  PRIMARY KEY (`CustomerID`)
);