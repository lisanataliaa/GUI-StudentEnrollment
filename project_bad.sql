-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 26, 2024 at 02:26 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project_bad`
--

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `studentID` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`studentID`, `name`, `gender`) VALUES
('ST001', 'Joan Tanu', 'Female'),
('ST002', 'Marcello Wijaya', 'Male'),
('ST003', 'Marjo Kim', 'Male'),
('ST004', 'Stephanie Alyia', 'Female'),
('ST005', 'Meisya Putri', 'Female'),
('ST006', 'Kevin Santoso', 'Male'),
('ST007', 'Kelly Adina', 'Female'),
('ST009', 'Jason Wijaya', 'Male'),
('ST011', 'Rania Putria', 'Female'),
('ST012', 'Melvin Jonathan', 'Male'),
('ST013', 'Janita Nadia', 'Female'),
('ST014', 'Lisa Natalia', 'Female'),
('ST015', 'Ryan Aria', 'Male'),
('ST016', 'Jennie Rubyjane', 'Female');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`studentID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
