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

CREATE TABLE Episode(
	idEpisode int primary key,
	dateEpisode date,
	ecritureEnCours integer, -- booléen
	typeEpisode varchar(200),
	CONSTRAINT chk_typeEpisode CHECK (typeEpisode IN ('Bio Initiale', 'Transition', 'ResumePersonnage', 'ResumePartie'))
);

CREATE TABLE Partie(
 	idPartie int primary key,
	titrePartie varchar(256),
	resumePartie varchar(2048),
	datePartie varchar(128),
	lieu varchar(128),
	termine integer, -- booléen
	idResume int REFERENCES Episode(idEpisode),
	idUnivers REFERENCES Univers(idUnivers),
	idMJ REFERENCES Joueur(idJoueur)
);

CREATE TABLE Biographie(
	idBiographie int primary key,
	idBioInitiale REFERENCES Episode(idEpisode)
);

CREATE TABLE Personnage(
	idPersonnage int primary key,
	nomPerso varchar(128),
	dateNaissance varchar(128),
	profession varchar(256),
	portrait varchar(512),
	demandeMJ integer, -- booléen
	idJoueur REFERENCES Joueur(idJoueur),
	idUnivers REFERENCES Univers(idUnivers),
	idBiographie int REFERENCES Biographie(idBiographie)
);

CREATE TABLE ParticipationPartie(
    idPersonnage REFERENCES Personnage(idPersonnage),
    idPartie REFERENCES Partie(idPartie),
    primary key (idPersonnage, idPartie)
);

CREATE TABLE PartieEnCours(
    idPersonnage REFERENCES Personnage(idPersonnage),
    idPartie REFERENCES Partie(idPartie),
    primary key (idPersonnage)
);

CREATE TABLE ResumePersonnage(
	idEpisode int REFERENCES Episode(idEpisode),
	idPartie int REFERENCES Partie(idPartie),
	primary key (idEpisode,idPartie)
);

CREATE TABLE Paragraphe(
	idParagraphe int primary key,
	secret integer,
	contenu varchar(2048),
	numeroPar integer NOT NULL,
	idEpisode int REFERENCES Episode(idEpisode)
);

CREATE TABLE EpisodeBiographie(
	idEpisode int REFERENCES Episode(idEpisode),
	idBiographie int REFERENCES Biographie(idBiographie),
	primary key (idEpisode,idBiographie)
);

CREATE TABLE MJ(
	idPersonnage int REFERENCES Personnage(idPersonnage),
	idMJ REFERENCES Joueur(idJoueur),
	primary key (idPersonnage)
);

INSERT INTO Univers (idUnivers, nomUnivers) VALUES (id.nextval, 'Narnia');
INSERT INTO Univers (idUnivers, nomUnivers) VALUES (id.nextval, 'La Terre du Milieu');
INSERT INTO Univers (idUnivers, nomUnivers) VALUES (id.nextval, 'Pirates des Caraibes');
INSERT INTO Univers (idUnivers, nomUnivers) VALUES (id.nextval, 'Azeroth');
INSERT INTO Univers (idUnivers, nomUnivers) VALUES (id.nextval, 'Fondation, Trantor');

INSERT INTO Joueur (idJoueur, login, mdp, email) VALUES (id.nextval, 'william', 'william', 'william.duclot@gmail.com');
INSERT INTO Joueur (idJoueur, login, mdp, email) VALUES (id.nextval, 'simon', 'simon', 'simon.rabourg@gmail.com');

INSERT INTO Personnage (
