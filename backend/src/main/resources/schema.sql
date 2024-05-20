-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 20 May 2024, 10:19:50
-- Sunucu sürümü: 10.4.32-MariaDB
-- PHP Sürümü: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `turkdil`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `community`
--

CREATE TABLE `community` (
  `id` int(11) NOT NULL,
  `is_private` bit(1) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `community_seq`
--

CREATE TABLE `community_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `community_templates`
--

CREATE TABLE `community_templates` (
  `community_id` int(11) NOT NULL,
  `templates_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `field`
--

CREATE TABLE `field` (
  `id` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `field_seq`
--

CREATE TABLE `field_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `post`
--

CREATE TABLE `post` (
  `community_id` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `template_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `body` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`body`)),
  `title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `post_seq`
--

CREATE TABLE `post_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `template`
--

CREATE TABLE `template` (
  `community_id` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `fields` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`fields`))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `template_seq`
--

CREATE TABLE `template_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` enum('USER','MEMBER','ADMIN') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `user_community`
--

CREATE TABLE `user_community` (
  `community_id` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `pending` bit(1) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `user_community_seq`
--

CREATE TABLE `user_community_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `user_seq`
--

CREATE TABLE `user_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `community`
--
ALTER TABLE `community`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UniqueName` (`name`);

--
-- Tablo için indeksler `community_templates`
--
ALTER TABLE `community_templates`
  ADD UNIQUE KEY `UK_e91fhvce8fn6oridveehsddpl` (`templates_id`),
  ADD KEY `FKb1j7ayey8arlr6dkvi2jr3vh8` (`community_id`);

--
-- Tablo için indeksler `field`
--
ALTER TABLE `field`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKokm06ignilxux2n1anwepgun7` (`community_id`),
  ADD KEY `FKke8ctpergnt4jvhkgihcchfme` (`template_id`),
  ADD KEY `FK72mt33dhhs48hf9gcqrq4fxte` (`user_id`);

--
-- Tablo için indeksler `template`
--
ALTER TABLE `template`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjcmn7eb8qtvj976xg086tjs1c` (`community_id`),
  ADD KEY `FKb6vyut9pfy3injob2ir7xgd3o` (`user_id`);

--
-- Tablo için indeksler `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UniqueEmail` (`email`),
  ADD UNIQUE KEY `UniqueNickname` (`nickname`);

--
-- Tablo için indeksler `user_community`
--
ALTER TABLE `user_community`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UniqueNumberAndStatus` (`user_id`,`community_id`,`role`),
  ADD KEY `FKvx9uvin2jevvy9sychijjx5d` (`community_id`);

--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `community_templates`
--
ALTER TABLE `community_templates`
  ADD CONSTRAINT `FKb1j7ayey8arlr6dkvi2jr3vh8` FOREIGN KEY (`community_id`) REFERENCES `community` (`id`),
  ADD CONSTRAINT `FKgj9v819wmpbr83dcp2b91x6w8` FOREIGN KEY (`templates_id`) REFERENCES `template` (`id`);

--
-- Tablo kısıtlamaları `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `FK72mt33dhhs48hf9gcqrq4fxte` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKke8ctpergnt4jvhkgihcchfme` FOREIGN KEY (`template_id`) REFERENCES `template` (`id`),
  ADD CONSTRAINT `FKokm06ignilxux2n1anwepgun7` FOREIGN KEY (`community_id`) REFERENCES `community` (`id`);

--
-- Tablo kısıtlamaları `template`
--
ALTER TABLE `template`
  ADD CONSTRAINT `FKb6vyut9pfy3injob2ir7xgd3o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKjcmn7eb8qtvj976xg086tjs1c` FOREIGN KEY (`community_id`) REFERENCES `community` (`id`);

--
-- Tablo kısıtlamaları `user_community`
--
ALTER TABLE `user_community`
  ADD CONSTRAINT `FKby95n7b70bd1oymg3k7buqyfd` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKvx9uvin2jevvy9sychijjx5d` FOREIGN KEY (`community_id`) REFERENCES `community` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
