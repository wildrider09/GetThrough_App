-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: fdb19.awardspace.net
-- Generation Time: Apr 13, 2018 at 03:39 PM
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
-- Table structure for table `buspass`
--

CREATE TABLE `buspass` (
  `passno` varchar(50) NOT NULL,
  `student_id` varchar(50) NOT NULL,
  `student_name` varchar(50) NOT NULL,
  `fathers_name` varchar(50) NOT NULL,
  `validity` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `buspass`
--

INSERT INTO `buspass` (`passno`, `student_id`, `student_name`, `fathers_name`, `validity`) VALUES
('DIT_01', 'ab01', 'abhishek', 'Shammi Garg', 'validity 2018-2019'),
('DIT_02', 'admin001', 'admin', 'Kevin Johnson', 'validity 2018-2019');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buspass`
--
ALTER TABLE `buspass`
  ADD PRIMARY KEY (`passno`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
