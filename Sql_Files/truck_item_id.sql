-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: fdb19.awardspace.net
-- Generation Time: Apr 13, 2018 at 03:43 PM
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
-- Table structure for table `truck_item_id`
--

CREATE TABLE `truck_item_id` (
  `truck_item_id` varchar(50) NOT NULL,
  `truck_item` varchar(50) NOT NULL,
  `item_price` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `truck_item_id`
--

INSERT INTO `truck_item_id` (`truck_item_id`, `truck_item`, `item_price`) VALUES
('trit1', 'itemm1', '59.56'),
('trit1', 'panipuri', '10'),
('pt01', 'it1', '100'),
('pt01', 'it2', '200'),
('pt01', 'it3', '300'),
('pt01', 'it4', '400');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
