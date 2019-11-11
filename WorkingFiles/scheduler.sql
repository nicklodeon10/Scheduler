-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.17 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for scheduler
CREATE DATABASE IF NOT EXISTS `scheduler` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `scheduler`;

-- Dumping structure for table scheduler.employee
CREATE TABLE IF NOT EXISTS `employee` (
  `emp_id` bigint(20) NOT NULL,
  `emp_email` varchar(255) DEFAULT NULL,
  `emp_name` varchar(255) DEFAULT NULL,
  `emp_password` varchar(255) DEFAULT NULL,
  `emp_phone` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `active` bit(1) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`emp_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table scheduler.employee: 5 rows
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`emp_id`, `emp_email`, `emp_name`, `emp_password`, `emp_phone`, `user_name`, `active`, `created_by`, `created_date`, `modified_by`, `modified_date`) VALUES
	(4, '10.devang1@gmail.com', 'Another Devang', 'devang1', '8948031871', 'devang1', b'1', NULL, '0000-00-00 00:00:00', NULL, NULL),
	(3, '10.devang@gmail.com', 'Devang Singh', 'devang', '8948031877', 'devang', b'1', NULL, '0000-00-00 00:00:00', NULL, NULL),
	(5, '10.devang2@gmail.com', 'Another Devang', 'devang2', '8948031872', 'devang2', b'1', NULL, '0000-00-00 00:00:00', NULL, NULL),
	(6, '10.devang3@gmail.com', 'Another Devang', 'devang3', '8948031873', 'devang3', b'1', NULL, '0000-00-00 00:00:00', NULL, NULL),
	(7, '10.devang4@gmail.com', 'Another Devang', 'devang4', '8948031874', 'devang4', b'1', NULL, '0000-00-00 00:00:00', NULL, NULL);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;

-- Dumping structure for table scheduler.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table scheduler.hibernate_sequence: 4 rows
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(68),
	(68),
	(68),
	(68);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Dumping structure for table scheduler.meeting
CREATE TABLE IF NOT EXISTS `meeting` (
  `meeting_id` bigint(20) NOT NULL,
  `end_time` datetime DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `meeting_title` varchar(255) DEFAULT NULL,
  `participant_status` varchar(255) DEFAULT NULL,
  `participants` varchar(255) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `organiser_emp_id` bigint(20) DEFAULT NULL,
  `active` bit(1) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`meeting_id`),
  KEY `FKd7otndl44ylgg8itpe30jw5vl` (`organiser_emp_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table scheduler.meeting: 2 rows
/*!40000 ALTER TABLE `meeting` DISABLE KEYS */;
INSERT INTO `meeting` (`meeting_id`, `end_time`, `location`, `meeting_title`, `participant_status`, `participants`, `start_time`, `organiser_emp_id`, `active`, `created_by`, `created_date`, `modified_by`, `modified_date`) VALUES
	(55, '2020-11-10 12:00:00', 'Meeting Hall', 'Test Meeting', 'maybe cancelled ', '4 5', '2020-11-10 10:00:00', 3, b'1', NULL, '0000-00-00 00:00:00', NULL, NULL),
	(63, '2020-11-10 12:00:00', 'Meeting Hall', 'Test Meeting', 'approved nores ', '3', '2020-11-10 10:00:00', 5, b'1', NULL, '0000-00-00 00:00:00', NULL, NULL),
	(999, '2019-11-10 12:00:00', 'Meeting Hall', 'Past Meeting', 'maybe cancelled ', '4 5', '2019-11-10 10:00:00', 3, b'1', NULL, '0000-00-00 00:00:00', NULL, NULL);
/*!40000 ALTER TABLE `meeting` ENABLE KEYS */;

-- Dumping structure for table scheduler.notification
CREATE TABLE IF NOT EXISTS `notification` (
  `not_id` bigint(20) NOT NULL,
  `from_emp_id` varchar(255) DEFAULT NULL,
  `not_message` varchar(255) DEFAULT NULL,
  `not_time` datetime DEFAULT NULL,
  `seen` bit(1) NOT NULL,
  `to_emp_emp_id` bigint(20) DEFAULT NULL,
  `meeting_id` bigint(20) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`not_id`),
  KEY `FKsc3lqx42tc5prohov7rgi9y2q` (`to_emp_emp_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table scheduler.notification: 9 rows
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` (`not_id`, `from_emp_id`, `not_message`, `not_time`, `seen`, `to_emp_emp_id`, `meeting_id`, `created_by`, `created_date`, `modified_by`, `modified_date`) VALUES
	(57, '3', 'Test Meeting, Organiser: Devang Singh, Time: 2019-11-10T10:00', '2019-11-06 14:57:04', b'0', 5, 55, NULL, '0000-00-00 00:00:00', NULL, NULL),
	(56, '3', 'Test Meeting, Organiser: Devang Singh, Time: 2019-11-10T10:00', '2019-11-06 14:57:04', b'0', 4, 55, NULL, '0000-00-00 00:00:00', NULL, NULL),
	(59, '5', 'Test Meeting, Organiser: Devang Singh, Time: 2019-11-10T10:00, Approved By: Another Devang', '2019-11-06 15:14:38', b'0', 3, 55, NULL, '0000-00-00 00:00:00', NULL, NULL),
	(60, '5', 'Test Meeting, Organiser: Devang Singh, Time: 2019-11-10T10:00, Approved By: Another Devang', '2019-11-06 15:19:17', b'0', 3, 55, NULL, '0000-00-00 00:00:00', NULL, NULL),
	(61, '4', 'Test Meeting, Organiser: Devang Singh, Time: 2019-11-10T10:00, Maybe By: Another Devang', '2019-11-06 15:19:37', b'0', 3, 55, NULL, '0000-00-00 00:00:00', NULL, NULL),
	(62, '5', 'Test Meeting, Organiser: Devang Singh, Time: 2019-11-10T10:00, Cancelled By: Another Devang', '2019-11-06 15:19:48', b'0', 3, 55, NULL, '0000-00-00 00:00:00', NULL, NULL),
	(64, '5', 'Test Meeting, Organiser: Another Devang, Time: 2019-11-10T10:00', '2019-11-06 15:29:01', b'0', 3, 63, NULL, '0000-00-00 00:00:00', NULL, NULL),
	(65, '5', 'Test Meeting, Organiser: Another Devang, Time: 2019-11-10T10:00', '2019-11-06 15:29:01', b'0', 4, 63, NULL, '0000-00-00 00:00:00', NULL, NULL),
	(67, '3', 'Test Meeting, Organiser: Another Devang, Time: 2019-11-10T10:00, Approved By: Devang Singh', '2019-11-06 15:36:49', b'0', 5, 63, NULL, '0000-00-00 00:00:00', NULL, NULL);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;

-- Dumping structure for table scheduler.reminder
CREATE TABLE IF NOT EXISTS `reminder` (
  `rem_id` bigint(20) NOT NULL,
  `due_time` datetime DEFAULT NULL,
  `rem_message` varchar(255) DEFAULT NULL,
  `emp_emp_id` bigint(20) DEFAULT NULL,
  `active` bit(1) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`rem_id`),
  KEY `FKeyjwgrvhvrvsvt9x79yrhjmma` (`emp_emp_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table scheduler.reminder: 1 rows
/*!40000 ALTER TABLE `reminder` DISABLE KEYS */;
INSERT INTO `reminder` (`rem_id`, `due_time`, `rem_message`, `emp_emp_id`, `active`, `created_by`, `created_date`, `modified_by`, `modified_date`) VALUES
	(66, '2020-11-10 10:00:00', 'Test Meeting, Organiser: Another Devang, Time: 2019-11-10T10:00', 3, b'1', NULL, '0000-00-00 00:00:00', NULL, NULL);
/*!40000 ALTER TABLE `reminder` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
