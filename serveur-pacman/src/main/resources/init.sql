-- Création de la table "utilisateur"
CREATE TABLE utilisateur (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    pseudo VARCHAR(255) NOT NULL,
    date_inscription TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX (pseudo)
);

-- Insertion de quelques lignes dans la table "utilisateur"
INSERT INTO utilisateur (email, password, pseudo) VALUES
    ('user1@example.com', '2004', 'amine'),
    ('user2@example.com', 'root', 'root'),
    ('user3@example.com', 'user', 'user');

-- Création de la table "partie"
CREATE TABLE partie (
    id INT AUTO_INCREMENT PRIMARY KEY,
    score INT NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    pseudo VARCHAR(255) NOT NULL,
    FOREIGN KEY (pseudo) REFERENCES utilisateur(pseudo)
);

-- Insertion de quelques lignes dans la table "partie"
INSERT INTO partie (score, pseudo) VALUES
    (100, 'amine'),
    (150, 'root'),
    (200, 'user');
