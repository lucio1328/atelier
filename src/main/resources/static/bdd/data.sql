-- marques
insert into marques(nom_marque) values ('hp'),
('Asus'),
('Acer'),
('Lenovo'),
('Dell');

-- genre
insert into genre(libelle) values ('Homme'),
('Femme');

-- statut
insert into statut(libelle) values ('En attente'),
('En cours'),
('Termine');

-- type mouvement
insert into type_mouvement(libelle) values ('Entree'),
('Sortie');

-- Categories
insert into categories(libelle) values ('Gamer'),('Bureau');

insert into type_reparation(libelle) values ('Ecran Vaky');

SELECT r FROM reparations r LEFT JOIN r.techniciens t LEFT JOIN r.client c LEFT JOIN r.ordinateurs ordi LEFT JOIN r.statut s LEFT JOIN r.type_reparation rep WHERE r.date_debut >= '2025-01-16' AND r.date_fin <= 2025-01-16;