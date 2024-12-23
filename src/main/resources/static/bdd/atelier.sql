CREATE TABLE genre (
   id SERIAL PRIMARY KEY,
   libelle VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE clients (
   id SERIAL PRIMARY KEY,
   nom VARCHAR(50) NOT NULL,
   prenom VARCHAR(50),
   date_naissance DATE NOT NULL,
   email VARCHAR(50),
   telephone VARCHAR(50) NOT NULL,
   adresse VARCHAR(50) NOT NULL,
   id_genre INTEGER NOT NULL,
   FOREIGN KEY(id_genre) REFERENCES genre(id)
);

CREATE TABLE pieces_detachees (
   id SERIAL PRIMARY KEY,
   nom_piece VARCHAR(50) NOT NULL UNIQUE,
   reference VARCHAR(50) NOT NULL UNIQUE,
   description VARCHAR(255)
);

CREATE TABLE techniciens (
   id SERIAL PRIMARY KEY,
   nom VARCHAR(50) NOT NULL,
   prenom VARCHAR(50),
   date_naissance DATE NOT NULL,
   email VARCHAR(50) NOT NULL UNIQUE,
   mot_de_passe VARCHAR(50) NOT NULL,
   id_genre INTEGER NOT NULL,
   FOREIGN KEY(id_genre) REFERENCES genre(id)
);

CREATE TABLE specialites (
   id SERIAL PRIMARY KEY,
   libelle VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE statut (
   id SERIAL PRIMARY KEY,
   libelle VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE achat_pieces (
   id SERIAL PRIMARY KEY,
   quantite INTEGER NOT NULL,
   quantite_disponible INTEGER NOT NULL,
   prix_unitaire NUMERIC(20,5) NOT NULL,
   date_achat DATE NOT NULL,
   id_piece_detachee INTEGER NOT NULL,
   FOREIGN KEY(id_piece_detachee) REFERENCES pieces_detachees(id)
);

CREATE TABLE type_mouvement (
   id SERIAL PRIMARY KEY,
   libelle VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE reparations (
   id SERIAL PRIMARY KEY,
   description VARCHAR(50) NOT NULL,
   date_debut DATE NOT NULL,
   date_fin DATE NOT NULL,
   id_technicien INTEGER NOT NULL,
   id_client INTEGER NOT NULL,
   id_statut INTEGER NOT NULL,
   FOREIGN KEY(id_technicien) REFERENCES techniciens(id),
   FOREIGN KEY(id_client) REFERENCES clients(id),
   FOREIGN KEY(id_statut) REFERENCES statut(id)
);

CREATE TABLE factures (
   id SERIAL PRIMARY KEY,
   montant NUMERIC(20,5) NOT NULL,
   date_facture DATE NOT NULL,
   id_reparation INTEGER NOT NULL,
   FOREIGN KEY(id_reparation) REFERENCES reparations(id)
);

CREATE TABLE mouvements_stock (
   id SERIAL PRIMARY KEY,
   quantite INTEGER NOT NULL,
   date_mouvement DATE NOT NULL,
   id_achat_piece INTEGER NOT NULL,
   id_reparation INTEGER NOT NULL,
   id_type_mouvement INTEGER NOT NULL,
   FOREIGN KEY(id_achat_piece) REFERENCES achat_pieces(id),
   FOREIGN KEY(id_reparation) REFERENCES reparations(id),
   FOREIGN KEY(id_type_mouvement) REFERENCES type_mouvement(id)
);

CREATE TABLE specialite_technicien (
   id_technicien INTEGER NOT NULL,
   id_specialite INTEGER NOT NULL,
   PRIMARY KEY(id_technicien, id_specialite),
   FOREIGN KEY(id_technicien) REFERENCES techniciens(id),
   FOREIGN KEY(id_specialite) REFERENCES specialites(id)
);

CREATE TABLE reparation_pieces (
   id_piece_detachee INTEGER NOT NULL,
   id_reparation INTEGER NOT NULL,
   quantite INTEGER NOT NULL,
   PRIMARY KEY(id_piece_detachee, id_reparation),
   FOREIGN KEY(id_piece_detachee) REFERENCES pieces_detachees(id),
   FOREIGN KEY(id_reparation) REFERENCES reparations(id)
);
