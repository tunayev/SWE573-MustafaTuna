-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 20 May 2024, 10:27:22
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

--
-- Tablo döküm verisi `community`
--

INSERT INTO `community` (`id`, `is_private`, `description`, `name`) VALUES
(1, b'0', 'asd', 'Almanca 2'),
(2, b'0', 'Çince topluluğu', 'Çince');

--
-- Tablo döküm verisi `community_seq`
--

INSERT INTO `community_seq` (`next_val`) VALUES
(101);

--
-- Tablo döküm verisi `field_seq`
--

INSERT INTO `field_seq` (`next_val`) VALUES
(1);

--
-- Tablo döküm verisi `post`
--

INSERT INTO `post` (`community_id`, `id`, `template_id`, `user_id`, `body`, `title`) VALUES
(1, 1, 52, 1, '[{\"id\":\"23jkae\",\"name\":\"Kısa bilgi\",\"type\":\"text\",\"value\":\"aasd\"},{\"id\":\"5gd2fq\",\"name\":\"Gövde metnim\",\"type\":\"textarea\"},{\"id\":\"22hi7f\",\"name\":\"Mekanımız\",\"type\":\"geolocation\"},{\"id\":\"n6530k\",\"name\":\"Görselimiz\",\"type\":\"image\"},{\"id\":\"x20yc\",\"name\":\"Videoo\",\"type\":\"video\"}]', 'sksdf'),
(2, 2, 102, 2, '[{\"id\":\"pmrekc\",\"name\":\"isim\",\"type\":\"text\",\"value\":\"asdad\"},{\"id\":\"expbo\",\"name\":\"Lokasyonumuz\",\"type\":\"geolocation\",\"value\":\"41.14411395408309,27.175781249999982\"}]', 'Geo post'),
(2, 3, 103, 2, '[{\"id\":\"k63d9\",\"name\":\"Mekan\",\"type\":\"geolocation\",\"value\":\"41.204481423413085,27.351562499999982\"},{\"id\":\"dkens5\",\"name\":\"Tarih\",\"type\":\"date\"},{\"id\":\"saggq\",\"name\":\"Kısa Metnimiz\",\"type\":\"text\",\"value\":\"bu kısadır\"}]', 'Detaylı post'),
(2, 4, 103, 2, '[{\"id\":\"k63d9\",\"name\":\"Mekan\",\"type\":\"geolocation\",\"value\":\"41.11101221449797,27.802001953124982\"},{\"id\":\"dkens5\",\"name\":\"Tarih\",\"type\":\"date\"},{\"id\":\"saggq\",\"name\":\"Kısa Metnimiz\",\"type\":\"text\",\"value\":\"bu kısadır\"}]', 'Detaylı post 2'),
(2, 5, 103, 2, '[{\"id\":\"k63d9\",\"name\":\"Mekan\",\"type\":\"geolocation\",\"value\":\"41.12094457185127,27.395507812499982\"},{\"id\":\"dkens5\",\"name\":\"Tarih\",\"type\":\"date\",\"value\":\"2024-05-15\"},{\"id\":\"saggq\",\"name\":\"Kısa Metnimiz\",\"type\":\"text\",\"value\":\"asdad\"}]', 'asasd');

--
-- Tablo döküm verisi `post_seq`
--

INSERT INTO `post_seq` (`next_val`) VALUES
(101);

--
-- Tablo döküm verisi `template`
--

INSERT INTO `template` (`community_id`, `id`, `user_id`, `created_date`, `name`, `fields`) VALUES
(1, 52, 1, NULL, 'Test Şablonu', '[{\"id\":\"23jkae\",\"name\":\"Kısa bilgi\",\"type\":\"text\"},{\"id\":\"5gd2fq\",\"name\":\"Gövde metnim\",\"type\":\"textarea\"},{\"id\":\"22hi7f\",\"name\":\"Mekanımız\",\"type\":\"geolocation\"},{\"id\":\"n6530k\",\"name\":\"Görselimiz\",\"type\":\"image\"},{\"id\":\"x20yc\",\"name\":\"Videoo\",\"type\":\"video\"}]'),
(2, 102, 2, NULL, 'Test', '[{\"id\":\"pmrekc\",\"name\":\"isim\",\"type\":\"text\"},{\"id\":\"expbo\",\"name\":\"Lokasyonumuz\",\"type\":\"geolocation\"}]'),
(2, 103, 2, NULL, 'Detaylı', '[{\"id\":\"k63d9\",\"name\":\"Mekan\",\"type\":\"geolocation\"},{\"id\":\"dkens5\",\"name\":\"Tarih\",\"type\":\"date\"},{\"id\":\"saggq\",\"name\":\"Kısa Metnimiz\",\"type\":\"text\"}]');

--
-- Tablo döküm verisi `template_seq`
--

INSERT INTO `template_seq` (`next_val`) VALUES
(201);

--
-- Tablo döküm verisi `user`
--

INSERT INTO `user` (`id`, `avatar`, `email`, `nickname`, `password`, `role`) VALUES
(1, NULL, 'mtuna61@gmail.com', 'tunayev', '$2a$10$0KoHgl/eoBisWXpqMPR/YOVzIQ86w7RqCo9/A4JsnKzrU2b.l6jKu', 'USER'),
(2, NULL, 'mtuna612@gmail.com', 'tunayevx', '$2a$10$h8Ry/8QZ/sdSj8txZ5JYv.lSjJYEksx7e/cp42nVjHASoKQLtOKoC', 'USER');

--
-- Tablo döküm verisi `user_community`
--

INSERT INTO `user_community` (`community_id`, `id`, `pending`, `user_id`, `role`) VALUES
(1, 1, b'0', 1, 'ADMIN'),
(1, 2, b'0', 2, 'MEMBER'),
(2, 3, b'0', 2, 'ADMIN');

--
-- Tablo döküm verisi `user_community_seq`
--

INSERT INTO `user_community_seq` (`next_val`) VALUES
(101);

--
-- Tablo döküm verisi `user_seq`
--

INSERT INTO `user_seq` (`next_val`) VALUES
(101);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
