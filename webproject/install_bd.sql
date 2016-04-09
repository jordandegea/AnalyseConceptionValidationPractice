SET AUTOCOMMIT ON;

CREATE SEQUENCE id
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;

CREATE TABLE Univers(
 	idUnivers int primary key,
 	nomUnivers varchar(50) unique
);

CREATE TABLE Joueur(
	idJoueur int primary key,
	mdp varchar(256),
	email varchar(128),
	login varchar(50) unique
);

CREATE TABLE Partie(
 	idPartie int primary key,
	titrePartie varchar(256),
	resumePartie varchar(1024),
	datePartie date,
	lieu varchar(50),
	termine integer, -- booléen
	idUnivers REFERENCES Univers(idUnivers),
	idJoueur REFERENCES Joueur(idJoueur)
);

CREATE TABLE Episode(
	idEpisode int primary key,
	dateEpisode date,
	ecritureEnCours integer, -- booléen
	typeEpisode varchar(200),
	CONSTRAINT chk_typeEpisode CHECK (typeEpisode IN ('Bio Initiale', 'Transition', 'Resume'))
);

CREATE TABLE Biographie(
	idBiographie int primary key,
	idBioInitiale REFERENCES Episode(idEpisode)
);

CREATE TABLE Personnage(
	idPersonnage int primary key,
	nomPerso varchar(50),
	dateNaissance date,
	profession varchar(256),
	portrait varchar(512),
	idJoueur REFERENCES Joueur(idJoueur),
	idUnivers REFERENCES Univers(idUnivers),
	idPartie int REFERENCES Partie(idPartie),
	idBiographie int REFERENCES Biographie(idBiographie)
);

CREATE TABLE Resume(
	idEpisode int REFERENCES Episode(idEpisode),
	idPartie int REFERENCES Partie(idPartie),
	primary key (idEpisode,idPartie)
);

CREATE TABLE Paragraphe(
	idParagraphe int primary key,
	secret integer,
	contenu varchar(512),
	idEpisode int REFERENCES Episode(idEpisode)
);

CREATE TABLE EpisodeBiographie(
	idEpisode int REFERENCES Episode(idEpisode),
	idBiographie int REFERENCES Biographie(idBiographie),
	primary key (idEpisode,idBiographie)
);

CREATE TABLE MJ(
	idPerso int REFERENCES Personnage(idPersonnage),
	idJoueur REFERENCES Joueur(idJoueur),
	primary key (idPerso,idJoueur)
);

INSERT INTO Univers (idUnivers, nomUnivers) VALUES (id.nextval, 'Narnia');
INSERT INTO Univers (idUnivers, nomUnivers) VALUES (id.nextval, 'La Terre du Milieu');
INSERT INTO Univers (idUnivers, nomUnivers) VALUES (id.nextval, 'Pirates des Caraibes');
INSERT INTO Univers (idUnivers, nomUnivers) VALUES (id.nextval, 'Azeroth');
INSERT INTO Univers (idUnivers, nomUnivers) VALUES (id.nextval, 'Fondation, Trantor');

INSERT INTO Joueur (idJoueur, login, mdp, email) VALUES (id.nextval, 'william', 'william', 'william.duclot@gmail.com');
