-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 14, 2022 at 09:39 AM
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
  `kd_obat` varchar(200) NOT NULL,
  `obat` varchar(200) NOT NULL,
  `harga` int(11) NOT NULL,
  `jumlah_keluar` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_detail_obat_rekam_medis`
--

INSERT INTO `t_detail_obat_rekam_medis` (`kd_detail_obat_rekam_medis`, `kd_rekam_medis`, `kd_obat`, `obat`, `harga`, `jumlah_keluar`) VALUES
(16, 'RMS0002', 'OBT0002', 'Acarboze', 4000, 0),
(17, 'RMS0002', 'OBT0003', 'Panadol  Merah', 5000, 0),
(40, 'RMS0004', 'OBT0003', 'Panadol  Merah', 15000, 3);

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

--
-- Dumping data for table `t_detail_tindakan_rekam_medis`
--

INSERT INTO `t_detail_tindakan_rekam_medis` (`kd_detail_tindakan_rekam_medis`, `kd_rekam_medis`, `kd_tindakan`, `tindakan`, `harga`) VALUES
(39, 'RMS0002', 'TDK0001', 'Konsultasi / Premedikasi', 50000),
(54, 'RMS0003', 'TDK0001', 'Konsultasi / Premedikasi', 50000);

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

--
-- Dumping data for table `t_kunjungan`
--

INSERT INTO `t_kunjungan` (`kd_kunjungan`, `tgl_kunjungan`, `kd_pasien`, `keluhan`, `kd_dokter`, `status_rekam_medis`) VALUES
('KNJ0001', '2020-08-07', 'PS0001', 'Gigi Geraham Bolong', 'Sudah Diperiksa', 'Sudah Diperiksa'),
('KNJ0002', '2022-06-13', 'PS0007', 'Sakit', 'DR0003', 'Belum Rekam Medis'),
('KNJ0003', '2022-06-13', 'PS0006', 'Sakit perut', 'DR0001', 'Belum Rekam Medis'),
('KNJ0004', '2022-06-13', 'PS0006', 'Sakit Guyzz', 'DR0001', 'Belum Rekam Medis'),
('KNJ0005', '2022-06-13', 'PS0007', 'Sakit demam', 'DR0003', 'Belum Rekam Medis'),
('KNJ0006', '2022-06-13', 'PS0008', 'Sakit', 'DR0004', 'Belum Rekam Medis'),
('KNJ0007', '2022-06-13', 'PS0002', 'Sakit nih', 'DR0003', 'Belum Rekam Medis'),
('KNJ0008', '2022-06-13', 'PS0006', 'Pusing', 'DR0002', 'Belum Rekam Medis'),
('KNJ0009', '2022-06-14', 'PS0007', 'Darah Tinggi', 'DR0001', 'Sudah Rekam Medis');

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
('OBT0001', 'Paracetamol', 100, 5000, 'Obat Penurun Panas'),
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
('OBT0001', '2022-06-14', 'Kimia', 'Paracetamol', 5, 5000, 'Obat Penurun Panas');

--
-- Triggers `t_obat_masuk`
--
DELIMITER $$
CREATE TRIGGER `stok` AFTER INSERT ON `t_obat_masuk` FOR EACH ROW BEGIN 
UPDATE t_obat SET stok_obat = stok_obat + NEW.stok_masuk
WHERE kd_obat = NEW.kd_obat;
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

--
-- Dumping data for table `t_pasien`
--

INSERT INTO `t_pasien` (`kd_pasien`, `nama_pasien`, `umur`, `jenis_kelamin`, `no_hp`) VALUES
('PS0001', 'Bianco Akbar', '50', 'Laki-Laki', '08291923818'),
('PS0002', 'Venni Legia', '40', 'Perempuan', '07628191728'),
('PS0003', 'Indi Syafira', '23', 'Perempuan', '097261128'),
('PS0004', 'Muhammad Rifqi Subchan', '22', 'Laki-Laki', '09281827128'),
('PS0005', 'Novi', '22', 'Perempuan', '08963727'),
('PS0006', 'Rifky', '20', 'Laki-Laki', '08965544'),
('PS0007', 'Letty', '40', 'Perempuan', '0896666677');

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
('KP0005', 'Rifky', 'Laki-Laki', '08997734', 'Dokter Umum'),
('PTG0001', 'Reyfa Refian', 'Laki-Laki', '089650682001', '');

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

--
-- Dumping data for table `t_rekam_medis`
--

INSERT INTO `t_rekam_medis` (`kd_rekam_medis`, `tgl_rekam_medis`, `kd_kunjungan`, `hasil_diagnosa`, `total_biaya`) VALUES
('RMS0002', '2020-08-09', 'KNJ0002', 'Pasien terdiagnosa diare', 59000),
('RMS0003', '2022-06-14', 'KNJ0009', 'Darah Tinggi aja', 59000);

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
-- Indexes for table `t_obat_masuk`
--
ALTER TABLE `t_obat_masuk`
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
  MODIFY `kd_detail_obat_rekam_medis` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `t_detail_tindakan_rekam_medis`
--
ALTER TABLE `t_detail_tindakan_rekam_medis`
  MODIFY `kd_detail_tindakan_rekam_medis` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
