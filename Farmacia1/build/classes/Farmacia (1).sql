-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 05-06-2023 a las 15:13:52
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `Farmacia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `doctor`
--

CREATE TABLE `doctor` (
  `mail` varchar(50) NOT NULL,
  `pass` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `last_log` date DEFAULT NULL,
  `session` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `doctor`
--

INSERT INTO `doctor` (`mail`, `pass`, `name`, `last_log`, `session`) VALUES
('carlos@gmail.com', 'asdfgh', 'Carlos', NULL, NULL),
('juan@gmail.com', '67890', 'Juan', '2023-06-04', 1171993256),
('laura@gmail.com', 'qwerty', 'Laura', NULL, NULL),
('maria@gmail.com', 'abcdef', 'Maria', NULL, NULL),
('paco@gmail.com', '12345', 'Paco', '2023-06-01', 1753028772);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicine`
--

CREATE TABLE `medicine` (
  `id` int(10) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `tmax` double DEFAULT NULL,
  `tmin` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `medicine`
--

INSERT INTO `medicine` (`id`, `name`, `tmax`, `tmin`) VALUES
(1, 'Medicine A', 10.5, 5.5),
(2, 'Medicine B', 8.2, 3.7),
(3, 'Medicine C', 7.9, 4.1),
(4, 'Medicine D', 9.6, 6.2),
(5, 'Medicine E', 6.8, 3.9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `patient`
--

CREATE TABLE `patient` (
  `mail` varchar(50) NOT NULL,
  `name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `patient`
--

INSERT INTO `patient` (`mail`, `name`) VALUES
('david@gmail.com', 'David'),
('emma@gmail.com', 'Emma'),
('john@gmail.com', 'John'),
('lucas@gmail.com', 'Lucas'),
('sarah@gmail.com', 'Sarah');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `xip`
--

CREATE TABLE `xip` (
  `id` int(10) NOT NULL,
  `doctor_mail` varchar(50) DEFAULT NULL,
  `id_medicine` int(10) DEFAULT NULL,
  `id_patient` varchar(50) DEFAULT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `xip`
--

INSERT INTO `xip` (`id`, `doctor_mail`, `id_medicine`, `id_patient`, `date`) VALUES
(1, 'paco@gmail.com', 1, 'john@gmail.com', '2023-05-22'),
(2, 'paco@gmail.com', 3, 'sarah@gmail.com', '2023-05-21'),
(3, 'juan@gmail.com', 2, 'emma@gmail.com', '2023-05-20'),
(4, 'juan@gmail.com', 4, 'david@gmail.com', '2023-05-19'),
(5, 'maria@gmail.com', 5, 'lucas@gmail.com', '2023-05-18'),
(6, 'paco@gmail.com', 4, 'lucas@gmail.com', '2023-03-23'),
(7, 'paco@gmail.com', 1, 'david@gmail.com', '2222-02-22'),
(8, 'paco@gmail.com', 3, 'sarah@gmail.com', '2024-03-23'),
(10, 'juan@gmail.com', 5, 'john@gmail.com', '2024-03-28'),
(19, 'juan@gmail.com', 3, 'john@gmail.com', '2024-02-24'),
(23, 'paco@gmail.com', 2, 'emma@gmail.com', '2222-02-22'),
(55, 'paco@gmail.com', 2, 'emma@gmail.com', '3333-03-31');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`mail`);

--
-- Indices de la tabla `medicine`
--
ALTER TABLE `medicine`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`mail`);

--
-- Indices de la tabla `xip`
--
ALTER TABLE `xip`
  ADD PRIMARY KEY (`id`),
  ADD KEY `doctor_mail` (`doctor_mail`),
  ADD KEY `id_medicine` (`id_medicine`),
  ADD KEY `id_patient` (`id_patient`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `medicine`
--
ALTER TABLE `medicine`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `xip`
--
ALTER TABLE `xip`
  ADD CONSTRAINT `xip_ibfk_1` FOREIGN KEY (`doctor_mail`) REFERENCES `doctor` (`mail`),
  ADD CONSTRAINT `xip_ibfk_2` FOREIGN KEY (`id_medicine`) REFERENCES `medicine` (`id`),
  ADD CONSTRAINT `xip_ibfk_3` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`mail`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
