-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: fdb19.awardspace.net
-- Generation Time: Apr 13, 2018 at 03:41 PM
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
-- Table structure for table `order_items`
--

CREATE TABLE `order_items` (
  `order_item_id` varchar(50) NOT NULL,
  `order_name` varchar(50) NOT NULL,
  `order_item_price` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order_items`
--

INSERT INTO `order_items` (`order_item_id`, `order_name`, `order_item_price`) VALUES
('08:40:57am', 'itemm1panipuri', '59.5610'),
('08:47:03am', 'itemm1panipuri', '59.5610'),
('08:48:23am', 'itemm1panipuri', '59.5610'),
('08:50:19am', 'itemm1panipuri', '59.5610'),
('08:51:42am', 'itemm1panipuri', '59.5610'),
('08:52:56am', 'itemm1panipuri', '59.5610'),
('08:53:34am', 'itemm1panipuri', '59.5610'),
('08:55:37am', 'apple', '1'),
('08:55:37am', 'banana', '2'),
('08:57:43am', 'apple', '1'),
('08:57:43am', 'apple', '2'),
('08:57:43am', 'banana', '1'),
('08:57:43am', 'banana', '2'),
('09:00:14am', 'itemm1panipuri', '59.5610'),
('09:00:22am', 'apple', '1'),
('09:00:22am', 'banana', '2'),
('09:07:44am', 'panipuri', '10'),
('09:07:44am', 'itemm1', '59.56'),
('09:07:44am', '', ''),
('09:10:32am', 'panipuri', '10'),
('09:10:32am', 'itemm1', '59.56'),
('09:10:32am', '', ''),
('10:43:04am', 'itemm1', '59.56'),
('10:43:04am', '', ''),
('10:51:34am', 'itemm1', '59.56'),
('10:51:34am', '', ''),
('11:36:33am', 'itemm1', '59.56'),
('11:36:33am', 'panipuri', '10'),
('11:36:33am', '', ''),
('12:02:59pm', 'itemm1', '59.56'),
('12:02:59pm', 'panipuri', '10'),
('12:02:59pm', '', ''),
('12:03:06pm', 'itemm1', '59.56'),
('12:03:06pm', 'panipuri', '10'),
('12:03:06pm', '', ''),
('12:05:22pm', 'itemm1', '59.56'),
('12:05:22pm', 'panipuri', '10'),
('12:05:22pm', '', ''),
('12:05:40pm', 'apple', '1'),
('12:05:40pm', 'banana', '2'),
('12:05:54pm', 'apple', '1'),
('12:05:54pm', 'banana', '2'),
('12:06:00pm', 'itemm1', '59.56'),
('12:06:00pm', 'panipuri', '10'),
('12:06:00pm', '', ''),
('12:10:07pm', 'itemm1', '59.56'),
('12:10:07pm', '', ''),
('12:20:06pm', 'it4', '400'),
('12:20:06pm', 'it3', '300'),
('12:20:06pm', 'it2', '200'),
('12:20:06pm', 'it1', '100'),
('12:20:06pm', '', ''),
('10:45:55am', 'it2', '200'),
('10:45:55am', 'it4', '400'),
('10:45:55am', '', ''),
('05:38:23am', 'itemm1', '59.56'),
('05:38:23am', 'panipuri', '10'),
('05:38:23am', '', ''),
('05:38:25am', 'itemm1', '59.56'),
('05:38:25am', 'panipuri', '10'),
('05:38:25am', '', ''),
('06:19:01am', 'panipuri', '10'),
('06:19:01am', '', ''),
('06:19:24am', 'it4', '400'),
('06:19:24am', '', ''),
('06:23:35am', 'itemm1', '59.56'),
('06:23:35am', '', ''),
('06:23:36am', 'itemm1', '59.56'),
('06:23:36am', '', ''),
('06:23:51am', 'itemm1', '59.56'),
('06:23:51am', '', ''),
('06:24:30am', 'it1', '100'),
('06:24:30am', '', ''),
('01:01:11pm', 'it3', '300'),
('01:01:11pm', 'it4', '400'),
('01:01:11pm', '', ''),
('01:01:27pm', 'it4', '400'),
('01:01:27pm', 'it3', '300'),
('01:01:27pm', '', ''),
('05:39:53am', 'itemm1', '59.56'),
('05:39:53am', '', ''),
('05:41:03am', 'itemm1', '59.56'),
('05:41:03am', '', ''),
('03:01:17am', 'itemm1', '59.56'),
('03:01:17am', '', ''),
('02:55:13pm', 'itemm1', '59.56'),
('02:55:13pm', 'panipuri', '10'),
('02:55:13pm', '', ''),
('02:55:19pm', 'itemm1', '59.56'),
('02:55:19pm', 'panipuri', '10'),
('02:55:19pm', '', ''),
('03:09:23pm', 'itemm1', '59.56'),
('03:09:23pm', 'panipuri', '10'),
('03:09:23pm', '', ''),
('03:10:52pm', 'itemm1', '59.56'),
('03:10:52pm', 'panipuri', '10'),
('03:10:52pm', '', ''),
('04:07:18pm', 'panipuri', '10'),
('04:07:18pm', '', ''),
('10:09:50am', 'itemm1', '59.56'),
('10:09:50am', 'panipuri', '10'),
('10:09:50am', '', ''),
('10:09:52am', 'itemm1', '59.56'),
('10:09:52am', 'panipuri', '10'),
('10:09:52am', '', ''),
('10:24:23am', 'itemm1', '59.56'),
('10:24:23am', '', ''),
('10:25:14am', 'panipuri', '10'),
('10:25:14am', '', ''),
('10:25:14am', 'panipuri', '10'),
('10:25:14am', '', ''),
('04:53:23am', 'itemm1', '59.56'),
('04:53:23am', '', ''),
('05:33:01pm', 'it1', '100'),
('05:33:01pm', '', ''),
('10:05:19am', 'itemm1', '59.56'),
('10:05:19am', '', ''),
('11:54:29am', 'itemm1', '59.56'),
('11:54:29am', '', ''),
('02:59:13pm', 'itemm1', '59.56'),
('02:59:13pm', 'panipuri', '10'),
('02:59:13pm', '', '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
