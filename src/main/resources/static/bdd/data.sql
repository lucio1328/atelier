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

SELECT * FROM composant_recommande a LEFT JOIN a. WHERE (EXTRACT(MONTH FROM a.date) = 1 OR IS NULL) AND (EXTRACT(YEAR FROM a.date) = 2024 OR IS NULL);