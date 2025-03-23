-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 13, 2024 at 03:38 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Database: `db_mahasiswa`
--

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--
DROP TABLE IF EXISTS `mahasiswa`;

CREATE TABLE `mahasiswa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nim` varchar(10) NOT NULL UNIQUE,
  `nama` varchar(255) NOT NULL,
  `jenis_kelamin` ENUM('Laki-laki', 'Perempuan') NOT NULL,
  `jurusan` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`nim`, `nama`, `jenis_kelamin`, `jurusan`) VALUES
('2203999', 'Amelia Zalfa Julianti', 'Perempuan', 'Teknik Informatika'),
('2202292', 'Muhammad Iqbal Fadhilah', 'Laki-laki', 'Sistem Informasi'),
('2202346', 'Muhammad Rifky Afandi', 'Laki-laki', 'Teknik Elektro'),
('2210239', 'Muhammad Hanif Abdillah', 'Laki-laki', 'Teknik Informatika'),
('2202046', 'Nurainun', 'Perempuan', 'Manajemen Informatika'),
('2205101', 'Kelvin Julian Putra', 'Laki-laki', 'Teknik Mesin'),
('2200163', 'Rifanny Lysara Annastasya', 'Perempuan', 'Teknik Sipil'),
('2202869', 'Revana Faliha Salma', 'Perempuan', 'Sistem Informasi'),
('2209489', 'Rakha Dhifiargo Hariadi', 'Laki-laki', 'Teknik Informatika'),
('2203142', 'Roshan Syalwan Nurilham', 'Laki-laki', 'Teknik Industri'),
('2200311', 'Raden Rahman Ismail', 'Laki-laki', 'Teknik Elektro'),
('2200978', 'Ratu Syahirah Khairunnisa', 'Perempuan', 'Teknik Kimia'),
('2204509', 'Muhammad Fahreza Fauzan', 'Laki-laki', 'Sistem Informasi'),
('2205027', 'Muhammad Rizki Revandi', 'Laki-laki', 'Teknik Mesin'),
('2203484', 'Arya Aydin Margono', 'Laki-laki', 'Teknik Sipil'),
('2200481', 'Marvel Ravindra Dioputra', 'Laki-laki', 'Teknik Informatika'),
('2209889', 'Muhammad Fadlul Hafiizh', 'Laki-laki', 'Teknik Kimia'),
('2206697', 'Rifa Sania', 'Perempuan', 'Manajemen Informatika'),
('2207260', 'Imam Chalish Rafidhul Haque', 'Laki-laki', 'Teknik Industri'),
('2204343', 'Meiva Labibah Putri', 'Perempuan', 'Sistem Informasi');

COMMIT;
