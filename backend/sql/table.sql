DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE TABLE users (
   id_user SERIAL PRIMARY KEY , 
   username VARCHAR (255) UNIQUE , 
   password VARCHAR(255) NOT NULL ,
   role VARCHAR(255) NOT NULL  
);

INSERT INTO users (username, password, role) VALUES
('admin', 'admin', 'ADMIN');

CREATE TABLE pilotes(
   id_pilote SERIAL PRIMARY KEY,
   nom VARCHAR(255) NOT NULL
);

CREATE TABLE rallye_categories(
   id_categorie SERIAL PRIMARY KEY,
   libelle VARCHAR(255)
);

CREATE TABLE rallyes(
   id_rallye SERIAL PRIMARY KEY,
   categorie_id INTEGER NOT NULL REFERENCES rallye_categories(id_categorie)
);

CREATE TABLE speciales(
   id_speciale SERIAL PRIMARY KEY,
   distance NUMERIC(15,2)
);

CREATE TABLE equipages(
   id_equipage SERIAL PRIMARY KEY,
   rallye_id INTEGER NOT NULL REFERENCES rallyes(id_rallye),
   pilote_id INTEGER NOT NULL REFERENCES pilotes(id_pilote),
   copilote_id INTEGER NOT NULL REFERENCES pilotes(id_pilote)
);

CREATE TABLE rallye_speciales(
   rallye_id INTEGER NOT NULL REFERENCES rallyes(id_rallye),
   speciale_id INTEGER NOT NULL REFERENCES speciales(id_speciale),
   PRIMARY KEY(rallye_id, speciale_id)
);

CREATE TABLE equipage_speciales(
   equipage_id INTEGER NOT NULL REFERENCES equipages(id_equipage),
   speciale_id INTEGER NOT NULL REFERENCES speciales(id_speciale),
   temps TIME,
   PRIMARY KEY(equipage_id, speciale_id)
);

-- ============================
-- INSERT DATA
-- ============================

-- Pilotes
INSERT INTO pilotes (id_pilote, nom) VALUES
(1, 'Sébastien Loeb'), (2, 'Daniel Elena'), (3, 'Sébastien Ogier'), (4, 'Julien Ingrassia'),
(5, 'Thierry Neuville'), (6, 'Nicolas Gilsoul'), (7, 'Ott Tänak'), (8, 'Martin Järveoja'),
(9, 'Pierre Martineau'), (10, 'Lucas Dubois'), (11, 'Antoine Bernard'), (12, 'Thomas Legrand'),
(13, 'Marie Delacroix'), (14, 'Sophie Laurent'), (15, 'Kevin Moreau'), (16, 'Maxime Petit');

-- Rally categories
INSERT INTO rallye_categories (id_categorie, libelle) VALUES
(1, 'R5'), (2, 'R4'), (3, 'M12'), (4, 'M11'), (5, 'M10');

-- Rallyes
INSERT INTO rallyes (id_rallye, categorie_id) VALUES
(1, 1), (2, 2), (3, 3), (4, 4), (5, 5);

-- Spéciales
INSERT INTO speciales (id_speciale, distance) VALUES
(1, 12.45), (2, 8.73), (3, 15.32), (4, 6.94), (5, 11.18);

-- Rallye-spéciales
INSERT INTO rallye_speciales (rallye_id, speciale_id) VALUES
(1,1),(1,2),(1,3),(1,4),(1,5),
(2,1),(2,2),(2,3),(2,4),(2,5),
(3,1),(3,2),(3,3),(3,4),(3,5),
(4,1),(4,2),(4,3),(4,4),(4,5),
(5,1),(5,2),(5,3),(5,4),(5,5);

-- Equipages
INSERT INTO equipages (id_equipage, rallye_id, pilote_id, copilote_id) VALUES
(1, 1, 1, 2), (2, 1, 3, 4), (3, 1, 5, 6),
(4, 2, 7, 8), (5, 2, 9, 10),
(6, 3, 11, 12), (7, 3, 13, 14),
(8, 4, 15, 16), (9, 4, 1, 4),
(10,5, 9, 12);

-- Temps par équipage et par spéciale
INSERT INTO equipage_speciales (equipage_id, speciale_id, temps) VALUES
(1, 1, '00:08:35'),(1, 2, '00:06:12'),(1, 3, '00:10:47'),(1, 4, '00:04:58'),(1, 5, '00:07:52'),
(2, 1, '00:08:42'),(2, 2, '00:06:18'),(2, 3, '00:10:55'),(2, 4, '00:05:03'),(2, 5, '00:07:58'),
(3, 1, '00:08:48'),(3, 2, '00:06:25'),(3, 3, '00:11:02'),(3, 4, '00:05:08'),(3, 5, '00:08:05'),
(4, 1, '00:09:15'),(4, 2, '00:06:42'),(4, 3, '00:11:28'),(4, 4, '00:05:25'),(4, 5, '00:08:22'),
(5, 1, '00:09:32'),(5, 2, '00:06:55'),(5, 3, '00:11:45'),(5, 4, '00:05:38'),(5, 5, '00:08:41'),
(6, 1, '00:10:15'),(6, 2, '00:07:28'),(6, 3, '00:12:35'),(6, 4, '00:06:12'),(6, 5, '00:09:18'),
(7, 1, '00:10:42'),(7, 2, '00:07:45'),(7, 3, '00:13:08'),(7, 4, '00:06:35'),(7, 5, '00:09:52'),
(8, 1, '00:11:05'),(8, 2, '00:08:12'),(8, 3, '00:13:45'),(8, 4, '00:06:58'),(8, 5, '00:10:28'),
(9, 1, '00:09:45'),(9, 2, '00:07:02'),(9, 3, '00:12:18'),(9, 4, '00:05:52'),(9, 5, '00:08:55'),
(10,1, '00:12:25'),(10,2,'00:09:08'),(10,3,'00:15:32'),(10,4,'00:07:45'),(10,5,'00:11:42');
