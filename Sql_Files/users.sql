-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: fdb19.awardspace.net
-- Generation Time: Apr 13, 2018 at 03:47 PM
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
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `student_id` varchar(50) NOT NULL,
  `student_name` varchar(100) NOT NULL,
  `student_email` varchar(100) NOT NULL,
  `student_phone` varchar(10) NOT NULL,
  `student_password` varchar(100) NOT NULL,
  `student_course` varchar(100) NOT NULL,
  `student_dept` varchar(50) NOT NULL,
  `student_receipt` varchar(25) NOT NULL,
  `pass_issued` varchar(10) NOT NULL,
  `uniform_issued` varchar(50) NOT NULL,
  `role` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`student_id`, `student_name`, `student_email`, `student_phone`, `student_password`, `student_course`, `student_dept`, `student_receipt`, `pass_issued`, `uniform_issued`, `role`) VALUES
('admin001', 'admin', 'admin@aditi.atwebpages.com', '1234567890', 'admin@123', 'testcourse', 'testdept', 'adt123', 'issued', 'session booked', 'student'),
('ab01', 'abhishek', 'ak01645@gmail.com', '9874651230', 'ab01', 'cse', 'cse', '0192', 'not issued', 'session booked', 'student'),
('tr01', 'amazed', 'amazed@gmail.com', '9599362404', 'amazed@123', 'truck_vendor', 'trucks', 'not applicable', 'not app', 'not app', 'food vendor'),
('st01', 'student01', 'stdent@yahoo.com', '9887654321', 'st01', 'BTECH', 'CSE', 'st092', 'not issued', 'not booked', 'student');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`student_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
