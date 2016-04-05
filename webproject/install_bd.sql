SET AUTOCOMMIT ON;

CREATE TABLE Univers(
 	idUnivers int primary key,
 	nomUnivers varchar(50) unique
);

CREATE TABLE Joueur(
	idJoueur int primary key
	login varchar(50) unique,
	mdp varchar(256),
	email varchar(128)
);

CREATE TABLE Partie(
 	idPartie int primary key,
	titrePartie varchar(256),
	resumePartie varchar(1024),
	datePartie date,
	lieu varchar(50),
	termine integer, -- booléen
	idUnivers REFERENCES Univers(idUnivers)
	loginMJ REFERENCES Joueur(login)
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
	idBioIitiale int REFERENCES Episode(idEpisode)
);

CREATE TABLE Personnage(
	idPersonnage int primary key,
	nomPerso varchar(50),
	dateNaissance date,
	profession varchar(256),
	portrait varchar(512),
	login varchar(50) REFERENCES Joueur(login),
	nomUnivers varchar(50) REFERENCES Univers(nomUnivers),
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
	secret integer, -- booléen
	contenu contenu(512),
	idEpisode int REFERENCES Episode(idEpisode)
);



CREATE TABLE EpisodeBiographie(
	idEpisode int REFERENCES Episode(idEpisode),
	idBiographie int REFERENCES Biographie(idBiographie),
	primary key (idEpisode,idBiographie)
);

CREATE TABLE MJ(
	idPerso int REFERENCES Personnage(idPersonnage),
	login varchar(50) REFERENCES Joueur(login),
	primary key (idPerso,login)
);

--DROP TABLE MJ;
--DROP TABLE EpisodeBiographie;
--DROP TABLE Paragraphe;
--DROP TABLE Resume;
--DROP TABLE Personnage;
--DROP TABLE Biographie;
--DROP TABLE Episode;
--DROP TABLE Partie;
--DROP TABLE Joueur;
--DROP TABLE Univers;

