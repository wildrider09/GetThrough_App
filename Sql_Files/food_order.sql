-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: fdb19.awardspace.net
-- Generation Time: Apr 13, 2018 at 03:40 PM
-- Server version: 5.7.20-log
-- PHP Version: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `2666161_aditi`
--

-- --------------------------------------------------------

--
-- Table structure for table `food_order`
--

CREATE TABLE `food_order` (
  `order_id` varchar(50) NOT NULL,
  `order_amount` varchar(50) NOT NULL,
  `order_status` varchar(50) NOT NULL,
  `order_item_id` varchar(50) NOT NULL,
  `student_id` varchar(50) NOT NULL,
  `truck_id` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `food_order`
--

INSERT INTO `food_order` (`order_id`, `order_amount`, `order_status`, `order_item_id`, `student_id`, `truck_id`) VALUES
('03:01:17am', '59.56', 'dispatched', '03:01:17am', 'admin001', 'tr01'),
('04:07:18pm', '10', 'dispatched', '04:07:18pm', 'admin001', 'tr01'),
('03:10:52pm', '69.56', 'dispatched', '03:10:52pm', 'admin001', 'tr01'),
('10:09:50am', '69.56', 'dispatched', '10:09:50am', 'admin001', 'tr01'),
('10:09:52am', '69.56', 'dispatched', '10:09:52am', 'admin001', 'tr01'),
('10:24:23am', '59.56', 'dispatched', '10:24:23am', 'admin001', 'tr01'),
('10:25:14am', '10', 'decline', '10:25:14am', 'admin001', 'tr01'),
('10:25:14am', '10', 'decline', '10:25:14am', 'admin001', 'tr01'),
('04:53:23am', '59.56', 'dispatched', '04:53:23am', 'admin001', 'tr01'),
('05:33:01pm', '100', 'pending', '05:33:01pm', 'admin001', 'tr02'),
('10:05:19am', '59.56', 'decline', '10:05:19am', 'admin001', 'tr01'),
('11:54:29am', '59.56', 'decline', '11:54:29am', 'admin001', 'tr01'),
('02:59:13pm', '69.56', 'dispatched', '02:59:13pm', 'admin001', 'tr01');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
