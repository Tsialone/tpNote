etudiants (id_etudiant, etu, nom, prenoms,  date_naissance);
matieres  (id_matiere, ue, credit, intitule);

semestres (id_semestre, code, libelle);

options (id_option, libelle); procédurale(genre les annees sans options), dev, web et design, base de donnés et reseau

matieres_groupe (id_matiere_groupe, matiere_id, optionnel:default(false) );

options_matiere_groupe (id_options_matiere, matiere_goupe__id)

semestres_matiere_groupe (id_semestre_matiere_groupe , omg_id, created);

sessions (id_session, semestre_id, date) date est unique

etudiants_note_semestre (id_etudiant_note, etudiant_id, semestres_id, matieres_id, note, session_id);

annees_ecollage (id_anne_ecollage, montant, created)

etudiants_inscription (id_inscription_etudiant, annee , etudiant_id,  date_inscription ) annee et etudiant_id sont uniques;

ei_paiement (id_ei_paiement, ei_id, montant, date_paiement );



-- ============================================================
--                 RESET + CREATION DES TABLES
-- ============================================================

DROP VIEW IF EXISTS vue_notes_detail;
DROP TABLE IF EXISTS ei_paiement CASCADE;
DROP TABLE IF EXISTS etudiants_inscription CASCADE;
DROP TABLE IF EXISTS annees_ecollage CASCADE;
DROP TABLE IF EXISTS etudiants_note_semestre CASCADE;
DROP TABLE IF EXISTS sessions CASCADE;
DROP TABLE IF EXISTS semestres_matiere_groupe CASCADE;
DROP TABLE IF EXISTS options_matiere_groupe CASCADE;
DROP TABLE IF EXISTS matieres_groupe CASCADE;
DROP TABLE IF EXISTS options CASCADE;
DROP TABLE IF EXISTS semestres_matiere CASCADE;
DROP TABLE IF EXISTS semestres CASCADE;
DROP TABLE IF EXISTS matieres CASCADE;
DROP TABLE IF EXISTS etudiants CASCADE;

-- 1) Étudiants
CREATE TABLE etudiants (
    id_etudiant SERIAL PRIMARY KEY,
    etu VARCHAR(20) NOT NULL,
    nom VARCHAR(100) NOT NULL,
    prenoms VARCHAR(150) NOT NULL,
    date_naissance DATE NOT NULL
);

-- 2) Matières
CREATE TABLE matieres (
    id_matiere SERIAL PRIMARY KEY,
    ue VARCHAR(20) NOT NULL,
    credit INT NOT NULL,
    intitule VARCHAR(200) NOT NULL
);

-- 3) Semestres
CREATE TABLE semestres (
    id_semestre SERIAL PRIMARY KEY,
    code VARCHAR(20) NOT NULL,
    libelle VARCHAR(50) NOT NULL
);

-- 4) Options
CREATE TABLE options (
    id_option SERIAL PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL
);

-- 5) Groupes de matières
CREATE TABLE matieres_groupe (
    id_matiere_groupe SERIAL PRIMARY KEY,
    matiere_id INT NOT NULL,
    optionnel BOOLEAN DEFAULT FALSE,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (matiere_id) REFERENCES matieres(id_matiere)
);

-- 6) Association options <-> groupes de matières
CREATE TABLE options_matiere_groupe (
    id_options_matiere SERIAL PRIMARY KEY,
    matiere_groupe_id INT NOT NULL,
    option_id INT NOT NULL,
    FOREIGN KEY (matiere_groupe_id) REFERENCES matieres_groupe(id_matiere_groupe),
    FOREIGN KEY (option_id) REFERENCES options(id_option)
);

-- 7) Semestres <-> groupes de matières
CREATE TABLE semestres_matiere_groupe (
    id_semestre_matiere_groupe SERIAL PRIMARY KEY,
    matiere_groupe_id INT NOT NULL,
    semestre_id INT NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (matiere_groupe_id) REFERENCES matieres_groupe(id_matiere_groupe),
    FOREIGN KEY (semestre_id) REFERENCES semestres(id_semestre)
);

-- 8) Sessions
CREATE TABLE sessions (
    id_session SERIAL PRIMARY KEY,
    semestre_id INT NOT NULL,
    date DATE UNIQUE NOT NULL,
    FOREIGN KEY (semestre_id) REFERENCES semestres(id_semestre)
);

-- 9) Notes étudiants
CREATE TABLE etudiants_note_semestre (
    id_etudiant_note SERIAL PRIMARY KEY,
    etudiant_id INT NOT NULL,
    semestres_id INT NOT NULL,
    matieres_id INT NOT NULL,
    note NUMERIC(4,2) NOT NULL,
    session_id INT NOT NULL,
    FOREIGN KEY (etudiant_id) REFERENCES etudiants(id_etudiant),
    FOREIGN KEY (semestres_id) REFERENCES semestres(id_semestre),
    FOREIGN KEY (matieres_id) REFERENCES matieres(id_matiere),
    FOREIGN KEY (session_id) REFERENCES sessions(id_session)
);

-- 10) Années scolaires
CREATE TABLE annees_ecollage (
    id_anne_ecollage SERIAL PRIMARY KEY,
    montant NUMERIC(10,2) NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 11) Inscriptions
CREATE TABLE etudiants_inscription (
    id_inscription_etudiant SERIAL PRIMARY KEY,
    annee INT NOT NULL,
    etudiant_id INT NOT NULL,
    date_inscription DATE NOT NULL DEFAULT CURRENT_DATE,
    UNIQUE (annee, etudiant_id),
    FOREIGN KEY (etudiant_id) REFERENCES etudiants(id_etudiant),
    FOREIGN KEY (annee) REFERENCES annees_ecollage(id_anne_ecollage)
);

-- 12) Paiements
CREATE TABLE ei_paiement (
    id_ei_paiement SERIAL PRIMARY KEY,
    ei_id INT NOT NULL,
    montant NUMERIC(10,2) NOT NULL,
    date_paiement DATE NOT NULL DEFAULT CURRENT_DATE,
    FOREIGN KEY (ei_id) REFERENCES etudiants_inscription(id_inscription_etudiant)
);

-- ============================================================
--                  INSERT DONNÉES EXEMPLES
-- ============================================================

-- Étudiant
INSERT INTO etudiants (etu, nom, prenoms, date_naissance)
VALUES ('ETU2025-001', 'Rakoto', 'Jean Michel', '2002-08-15');

-- Matières semestre 1
INSERT INTO matieres (ue, intitule, credit) VALUES
('INF101', 'Programmation procédurale', 7),
('INF104', 'HTML et Introduction au Web', 5),
('INF107', 'Informatique de base', 4),
('MATH101', 'Arithmétique', 4),
('MTH102', 'Analyse mathématique', 6),
('ORG101', 'Technique de communication', 4);

-- Semestre
INSERT INTO semestres (code, libelle) VALUES
('S1', 'Semestre 1'),
('S2', 'Semestre 2')
('S3', 'Semestre 3')
('S4', 'Semestre 4')
('S5', 'Semestre 5')
('S6', 'Semestre 6')



-- Options
INSERT INTO options (libelle) VALUES
('Procedurale'), ('Dev'), ('Web & Design'), ('Base de données & Réseau');

-- Groupes de matières et liaison options
INSERT INTO matieres_groupe (matiere_id, optionnel) VALUES
(1, FALSE),(2,FALSE),(3,FALSE),(4,FALSE),(5,FALSE),(6,FALSE);

INSERT INTO options_matiere_groupe (matiere_groupe_id, option_id) VALUES
(1,1),(2,3),(3,2),(4,1),(5,1),(6,1);

-- Semestre <-> groupes
INSERT INTO semestres_matiere_groupe (matiere_groupe_id, semestre_id) VALUES
(1,1),(2,1),(3,1),(4,1),(5,1),(6,1),
(1,1),(2,1),(3,1),(4,1),(5,1),(6,6);


-- Session
INSERT INTO sessions (semestre_id, date) VALUES (1,'2025-01-20');

-- Note étudiant (INF101 seulement)
INSERT INTO etudiants_note_semestre (etudiant_id, semestres_id, matieres_id, note, session_id)
VALUES (currval('etudiants_id_etudiant_seq'), currval('semestres_id_semestre_seq'), 1, 14.50, currval('sessions_id_session_seq'));

-- ============================================================
--                        VIEW
-- ============================================================

CREATE OR REPLACE VIEW vue_notes_detail AS
SELECT 
    m.ue,
    m.intitule,
    m.credit,
    COALESCE(ens.note,0)::NUMERIC(4,2) AS note_sur_20,
    s.date AS session_date
FROM matieres m
LEFT JOIN etudiants_note_semestre ens ON m.id_matiere = ens.matieres_id
LEFT JOIN sessions s ON ens.session_id = s.id_session
ORDER BY m.ue;

-- Afficher la vue
SELECT * FROM vue_notes_detail;


voici les matieres du semestre 4 
Element algorithmique
Mini