-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 14, 2022 at 04:36 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `klinik`
--

-- --------------------------------------------------------

--
-- Table structure for table `t_detail_obat_rekam_medis`
--

CREATE TABLE `t_detail_obat_rekam_medis` (
  `kd_detail_obat_rekam_medis` int(11) NOT NULL,
  `kd_rekam_medis` varchar(20) NOT NULL,
  `kd_obat` varchar(20) NOT NULL,
  `obat` varchar(50) NOT NULL,
  `harga` int(11) NOT NULL,
  `jumlah_keluar` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Triggers `t_detail_obat_rekam_medis`
--
DELIMITER $$
CREATE TRIGGER `stok_keluar` AFTER INSERT ON `t_detail_obat_rekam_medis` FOR EACH ROW BEGIN 
UPDATE t_obat SET stok_obat = stok_obat - NEW.jumlah_keluar
WHERE kd_obat = NEW.kd_obat;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `t_detail_tindakan_rekam_medis`
--

CREATE TABLE `t_detail_tindakan_rekam_medis` (
  `kd_detail_tindakan_rekam_medis` int(11) NOT NULL,
  `kd_rekam_medis` varchar(20) NOT NULL,
  `kd_tindakan` varchar(20) NOT NULL,
  `tindakan` varchar(200) NOT NULL,
  `harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `t_dokter`
--

CREATE TABLE `t_dokter` (
  `kd_dokter` varchar(11) NOT NULL,
  `nama_dokter` varchar(50) NOT NULL,
  `jenis_kelamin` varchar(20) NOT NULL,
  `no_hp` varchar(50) NOT NULL,
  `spesialis` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_dokter`
--

INSERT INTO `t_dokter` (`kd_dokter`, `nama_dokter`, `jenis_kelamin`, `no_hp`, `spesialis`) VALUES
('DR0001', 'Dr. Tirta', 'Laki-Laki', '029182192811', 'Umum'),
('DR0002', 'Dr. Soebandono Munah', 'Perempuan', '093919281312', 'Umum'),
('DR0003', 'Dr. Andiman Budiman', 'Laki-Laki', '021912048043', 'Spesialis THT'),
('DR0004', 'Udin', 'Laki-Laki', '0999993', 'Umum');

-- --------------------------------------------------------

--
-- Table structure for table `t_kunjungan`
--

CREATE TABLE `t_kunjungan` (
  `kd_kunjungan` varchar(20) NOT NULL DEFAULT '',
  `tgl_kunjungan` date NOT NULL DEFAULT current_timestamp(),
  `kd_pasien` varchar(20) NOT NULL DEFAULT '',
  `keluhan` varchar(200) NOT NULL DEFAULT '',
  `kd_dokter` varchar(20) NOT NULL DEFAULT '',
  `status_rekam_medis` varchar(100) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `t_obat`
--

CREATE TABLE `t_obat` (
  `kd_obat` varchar(20) NOT NULL,
  `nama_obat` varchar(50) NOT NULL,
  `stok_obat` int(11) NOT NULL,
  `harga_obat` int(11) NOT NULL,
  `keterangan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_obat`
--

INSERT INTO `t_obat` (`kd_obat`, `nama_obat`, `stok_obat`, `harga_obat`, `keterangan`) VALUES
('OBT0001', 'Paracetamol', 102, 5000, 'Obat Penurun Panas'),
('OBT0002', 'Acarboze', 95, 4000, 'Obat Antidiabetes'),
('OBT0003', 'Panadol  Merah', 100, 5000, 'Obat Pusing');

-- --------------------------------------------------------

--
-- Table structure for table `t_obat_masuk`
--

CREATE TABLE `t_obat_masuk` (
  `kd_obat` varchar(20) NOT NULL,
  `tgl_masuk` date NOT NULL,
  `nama_supplier` varchar(50) NOT NULL,
  `nama_obat` varchar(50) NOT NULL,
  `stok_masuk` int(11) NOT NULL,
  `harga_obat` int(11) NOT NULL,
  `keterangan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_obat_masuk`
--

INSERT INTO `t_obat_masuk` (`kd_obat`, `tgl_masuk`, `nama_supplier`, `nama_obat`, `stok_masuk`, `harga_obat`, `keterangan`) VALUES
('OBT0001', '2022-06-14', 'kimia', 'kdlksl', 2, 233, 'dsd'),
('OBT0001', '2022-06-14', 'kimia', 'paracetamol', 2, 1222, 'dsds');

--
-- Triggers `t_obat_masuk`
--
DELIMITER $$
CREATE TRIGGER `stok` AFTER INSERT ON `t_obat_masuk` FOR EACH ROW BEGIN 
INSERT INTO t_obat SET kd_obat = NEW.kd_obat, 
stok_obat = NEW.stok_masuk, 
nama_obat = NEW.nama_obat,
keterangan = NEW.keterangan,
harga_obat = NEW.harga_obat
ON DUPLICATE KEY UPDATE stok_obat = stok_obat + NEW.stok_masuk;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `t_pasien`
--

CREATE TABLE `t_pasien` (
  `kd_pasien` varchar(20) NOT NULL,
  `nama_pasien` varchar(50) NOT NULL,
  `umur` varchar(20) NOT NULL,
  `jenis_kelamin` varchar(20) NOT NULL,
  `no_hp` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `t_petugas`
--

CREATE TABLE `t_petugas` (
  `kd_petugas` varchar(20) NOT NULL,
  `nama_petugas` varchar(50) NOT NULL,
  `jenis_kelamin` varchar(20) NOT NULL,
  `no_hp` varchar(20) NOT NULL,
  `profesi` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_petugas`
--

INSERT INTO `t_petugas` (`kd_petugas`, `nama_petugas`, `jenis_kelamin`, `no_hp`, `profesi`) VALUES
('KP0005', 'Rifky', 'Laki-Laki', '08997734', 'Dokter Umum');

-- --------------------------------------------------------

--
-- Table structure for table `t_rekam_medis`
--

CREATE TABLE `t_rekam_medis` (
  `kd_rekam_medis` varchar(20) NOT NULL,
  `tgl_rekam_medis` date NOT NULL,
  `kd_kunjungan` varchar(20) NOT NULL,
  `hasil_diagnosa` varchar(200) NOT NULL,
  `total_biaya` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `t_tindakan`
--

CREATE TABLE `t_tindakan` (
  `kd_tindakan` varchar(20) NOT NULL,
  `tindakan` varchar(50) NOT NULL,
  `harga_tindakan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_tindakan`
--

INSERT INTO `t_tindakan` (`kd_tindakan`, `tindakan`, `harga_tindakan`) VALUES
('TDK0001', 'Konsultasi / Premedikasi', 50000),
('TDK0002', 'Pembersihan Karang Gigi', 200000),
('TDK0003', 'Penambalan Gigi', 150000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `t_detail_obat_rekam_medis`
--
ALTER TABLE `t_detail_obat_rekam_medis`
  ADD PRIMARY KEY (`kd_detail_obat_rekam_medis`);

--
-- Indexes for table `t_detail_tindakan_rekam_medis`
--
ALTER TABLE `t_detail_tindakan_rekam_medis`
  ADD PRIMARY KEY (`kd_detail_tindakan_rekam_medis`);

--
-- Indexes for table `t_dokter`
--
ALTER TABLE `t_dokter`
  ADD PRIMARY KEY (`kd_dokter`);

--
-- Indexes for table `t_kunjungan`
--
ALTER TABLE `t_kunjungan`
  ADD PRIMARY KEY (`kd_kunjungan`);

--
-- Indexes for table `t_obat`
--
ALTER TABLE `t_obat`
  ADD PRIMARY KEY (`kd_obat`);

--
-- Indexes for table `t_pasien`
--
ALTER TABLE `t_pasien`
  ADD PRIMARY KEY (`kd_pasien`);

--
-- Indexes for table `t_petugas`
--
ALTER TABLE `t_petugas`
  ADD PRIMARY KEY (`kd_petugas`);

--
-- Indexes for table `t_rekam_medis`
--
ALTER TABLE `t_rekam_medis`
  ADD PRIMARY KEY (`kd_rekam_medis`);

--
-- Indexes for table `t_tindakan`
--
ALTER TABLE `t_tindakan`
  ADD PRIMARY KEY (`kd_tindakan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `t_detail_obat_rekam_medis`
--
ALTER TABLE `t_detail_obat_rekam_medis`
  MODIFY `kd_detail_obat_rekam_medis` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `t_detail_tindakan_rekam_medis`
--
ALTER TABLE `t_detail_tindakan_rekam_medis`
  MODIFY `kd_detail_tindakan_rekam_medis` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
