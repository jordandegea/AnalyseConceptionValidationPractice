SET AUTOCOMMIT ON;

CREATE SEQUENCE id
MINVALUE 1
START WITH 100
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
	validationJoueur integer, -- booléen
	validationMJ integer, --booleen
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

--------------------------------------------------------
--  Fichier créé - vendredi-avril-15-2016   
--------------------------------------------------------
REM INSERTING into DEGEAJ.BIOGRAPHIE
SET DEFINE OFF;
Insert into DEGEAJ.BIOGRAPHIE (IDBIOGRAPHIE,IDBIOINITIALE) values ('12','13');
Insert into DEGEAJ.BIOGRAPHIE (IDBIOGRAPHIE,IDBIOINITIALE) values ('19','20');
Insert into DEGEAJ.BIOGRAPHIE (IDBIOGRAPHIE,IDBIOINITIALE) values ('27','28');
REM INSERTING into DEGEAJ.EPISODE
SET DEFINE OFF;
Insert into DEGEAJ.EPISODE (IDEPISODE,DATEEPISODE,VALIDATIONJOUEUR,VALIDATIONMJ,TYPEEPISODE) values ('13',to_date('01/01/70','DD/MM/RR'),'0','0','Transition');
Insert into DEGEAJ.EPISODE (IDEPISODE,DATEEPISODE,VALIDATIONJOUEUR,VALIDATIONMJ,TYPEEPISODE) values ('20',to_date('01/01/70','DD/MM/RR'),'0','0','Transition');
Insert into DEGEAJ.EPISODE (IDEPISODE,DATEEPISODE,VALIDATIONJOUEUR,VALIDATIONMJ,TYPEEPISODE) values ('28',to_date('01/01/70','DD/MM/RR'),'0','0','Transition');
Insert into DEGEAJ.EPISODE (IDEPISODE,DATEEPISODE,VALIDATIONJOUEUR,VALIDATIONMJ,TYPEEPISODE) values ('32',to_date('01/01/80','DD/MM/RR'),'0','0','ResumePartie');
REM INSERTING into DEGEAJ.EPISODEBIOGRAPHIE
SET DEFINE OFF;
REM INSERTING into DEGEAJ.JOUEUR
SET DEFINE OFF;
Insert into DEGEAJ.JOUEUR (IDJOUEUR,MDP,EMAIL,LOGIN) values ('2','jordan','jordan.degea@gmail.com','jordan');
Insert into DEGEAJ.JOUEUR (IDJOUEUR,MDP,EMAIL,LOGIN) values ('4','simona','simon.rabourg@gmail.com','simon');
Insert into DEGEAJ.JOUEUR (IDJOUEUR,MDP,EMAIL,LOGIN) values ('17','william','william@gmail.com','william');
Insert into DEGEAJ.JOUEUR (IDJOUEUR,MDP,EMAIL,LOGIN) values ('25','raynald','raynald@gmail.com','raynald');
REM INSERTING into DEGEAJ.MJ
SET DEFINE OFF;
Insert into DEGEAJ.MJ (IDPERSONNAGE,IDMJ) values ('26','2');
Insert into DEGEAJ.MJ (IDPERSONNAGE,IDMJ) values ('11','2');
Insert into DEGEAJ.MJ (IDPERSONNAGE,IDMJ) values ('18','2');
REM INSERTING into DEGEAJ.PARAGRAPHE
SET DEFINE OFF;
Insert into DEGEAJ.PARAGRAPHE (IDPARAGRAPHE,SECRET,CONTENU,NUMEROPAR,IDEPISODE) values ('14','0','En 200 ans d''histoire, Zed fut le premier ninja Ã  maÃ®triser les techniques antiques de la voie interdite. Il dÃ©fia son clan et son maÃ®tre, rejetant ainsi l''ordre et la discipline auxquels il s''Ã©tait soumis toute sa vie. DÃ©sormais, Zed accorde sa force Ã  ceux qui veulent Ã©tudier les ombres et Ã©limine tous ceux qui prÃ©fÃ¨rent rester dans l''ignorance.','0','13');
Insert into DEGEAJ.PARAGRAPHE (IDPARAGRAPHE,SECRET,CONTENU,NUMEROPAR,IDEPISODE) values ('15','0','Orphelin, Zed a Ã©tÃ© recueilli par un grand maÃ®tre ninja qui a assurÃ© son entraÃ®nement. Un seul autre Ã©lÃ¨ve semblait capable d''Ã©galer Zed, et cet Ã©lÃ¨ve n''Ã©tait autre que le fils du maÃ®tre, Shen. Zed sentit qu''il ne pourrait jamais s''attirer les faveurs de son maÃ®tre, chaque combat contre son rival se soldant par un match nul.','1','13');
Insert into DEGEAJ.PARAGRAPHE (IDPARAGRAPHE,SECRET,CONTENU,NUMEROPAR,IDEPISODE) values ('21','0','Chef d''un clan secret de guerriers mystiques, Shen incarne l''Å¿il du crÃ©puscule, chargÃ© de prÃ©server l''Ã©quilibre dans le monde. Souhaitant Ã©chapper Ã  toutes les formes de confusion provoquÃ©es par les Ã©motions, les prÃ©jugÃ©s ou l''ego, Shen lutte continuellement, sa lame spirituelle Ã  la main, pour se maintenir sur le chemin invisible du jugement impartial.','0','20');
Insert into DEGEAJ.PARAGRAPHE (IDPARAGRAPHE,SECRET,CONTENU,NUMEROPAR,IDEPISODE) values ('22','0','VÃ©ritable Ã©nigme pour le royaume tÃ©nÃ©breux des esprits comme pour celui, mortel, des Ãªtres humains, Shen n''a sa place ni dans l''un, ni dans l''autre. Il est le rÃ©ceptacle d''une fusion peu orthodoxe entre Ã¢me humaine et puissance arcanique. Pour les deux camps, il constitue un Ã©lÃ©ment Ã  craindre. Il est immuable. Il est constant. Il n''a d''autre allÃ©geance que sa mission.','1','20');
Insert into DEGEAJ.PARAGRAPHE (IDPARAGRAPHE,SECRET,CONTENU,NUMEROPAR,IDEPISODE) values ('23','1','Bien qu''il continue de remplir son devoir sans faillir, Shen doit lutter pour contenir la colÃ¨re qui le ronge suite au meurtre de son pÃ¨re par Zed, un homme qu''il considÃ©rait autrefois comme son frÃ¨re. Son ennemi jurÃ©. ','2','20');
Insert into DEGEAJ.PARAGRAPHE (IDPARAGRAPHE,SECRET,CONTENU,NUMEROPAR,IDEPISODE) values ('29','0','Il existe un ancien ordre originaire des Ã®les ioniennes qui est dÃ©vouÃ© au maintien de l''Ã©quilibre. Ordre, chaos, lumiÃ¨re, tÃ©nÃ¨bres... Tout doit exister en parfaite harmonie, car c''est ainsi que fonctionne l''univers. Cet ordre s''appelle l''ordre Kinkou, et il emploie trois guerriers de l''ombre pour servir sa cause Ã  travers le monde. Kennen est l''un de ces guerriers. C''est Ã  lui qu''on confia la mission sacrÃ©e de la Poursuite du soleil, de transmettre sans relÃ¢che la justice du Kinkou au monde.','0','28');
Insert into DEGEAJ.PARAGRAPHE (IDPARAGRAPHE,SECRET,CONTENU,NUMEROPAR,IDEPISODE) values ('30','0','NÃ© Ã  Bandle, on dit qu''il a quittÃ© le ventre maternel d''un bond fulgurant et a esquivÃ© la sage-femme qui l''a mis au monde. Ses parents pensaient que son hyperactivitÃ© s''apaiserait en grandissant, mais son Ã©nergie se rÃ©vÃ©la sans limite et mÃªme sa formidable vitesse ne la canalisait pas. MalgrÃ© ses dons Ã©poustouflants, personne ne le remarqua (ou ne put l''attraper aprÃ¨s une de ses nombreuses blagues), jusqu''au jour oÃ¹, sur un coup de tÃªte, il escalada le grand mur du Placidium en courant. Lorsque le Kinkou apprit cet exploit, Kennen fut rapidement convoquÃ©. On estima que le rÃ´le du CÅ¿ur de la tempÃªte lui correspondait parfaitement, car il devait dÃ©livrer les paroles du Kinkou et infliger les chÃ¢timents Ã  toute vitesse dans tout le royaume. Il travaille dÃ©sormais avec Akali et Shen pour maintenir l''Ã©quilibre de Valoran. Et c''est ainsi que leur mission sacrÃ©e les mena aux Champs de justice.','1','28');
REM INSERTING into DEGEAJ.PARTICIPATIONPARTIE
SET DEFINE OFF;
Insert into DEGEAJ.PARTICIPATIONPARTIE (IDPERSONNAGE,IDPARTIE) values ('11','31');
Insert into DEGEAJ.PARTICIPATIONPARTIE (IDPERSONNAGE,IDPARTIE) values ('18','31');
Insert into DEGEAJ.PARTICIPATIONPARTIE (IDPERSONNAGE,IDPARTIE) values ('18','33');
Insert into DEGEAJ.PARTICIPATIONPARTIE (IDPERSONNAGE,IDPARTIE) values ('26','31');
REM INSERTING into DEGEAJ.PARTIE
SET DEFINE OFF;
Insert into DEGEAJ.PARTIE (IDPARTIE,TITREPARTIE,RESUMEPARTIE,DATEPARTIE,LIEU,TERMINE,IDRESUME,IDUNIVERS,IDMJ) values ('31','Le retour de Zed','AprÃ¨s ses longues recherches sur les techniques obscurs, Zed dÃ©cida de corrompre tous les ninjas qu''il avait croisÃ©. Sur ce, il s''Ã©quipa et marcha Ã  travers les ombres pour retrouver ces ninjas. ','an 1280','Champs de justice','1','32','10','2');
Insert into DEGEAJ.PARTIE (IDPARTIE,TITREPARTIE,RESUMEPARTIE,DATEPARTIE,LIEU,TERMINE,IDRESUME,IDUNIVERS,IDMJ) values ('33','Dans la lumiÃ¨re','Dans la ville de lumiÃ¨re, certains individus tente de prendre le pouvoir et d''anÃ©antir les tÃ©nÃ¨bres. ','an 1290','Runeterra - Ionia','0',null,'10','2');
REM INSERTING into DEGEAJ.PARTIEENCOURS
SET DEFINE OFF;
Insert into DEGEAJ.PARTIEENCOURS (IDPERSONNAGE,IDPARTIE) values ('18','33');
REM INSERTING into DEGEAJ.PERSONNAGE
SET DEFINE OFF;
Insert into DEGEAJ.PERSONNAGE (IDPERSONNAGE,NOMPERSO,DATENAISSANCE,PROFESSION,PORTRAIT,DEMANDEMJ,IDJOUEUR,IDUNIVERS,IDBIOGRAPHIE) values ('11','Zed','an 1200','Ninja / Assassin','http://ddragon.leagueoflegends.com/cdn/img/champion/splash/Zed_0.jpg','0','4','10','12');
Insert into DEGEAJ.PERSONNAGE (IDPERSONNAGE,NOMPERSO,DATENAISSANCE,PROFESSION,PORTRAIT,DEMANDEMJ,IDJOUEUR,IDUNIVERS,IDBIOGRAPHIE) values ('18','Shen','an 1200','Ninja - Å¿il du crÃ©puscule','http://ddragon.leagueoflegends.com/cdn/img/champion/splash/Shen_0.jpg','0','17','10','19');
Insert into DEGEAJ.PERSONNAGE (IDPERSONNAGE,NOMPERSO,DATENAISSANCE,PROFESSION,PORTRAIT,DEMANDEMJ,IDJOUEUR,IDUNIVERS,IDBIOGRAPHIE) values ('26','Kennen','an 1250','Ninja - CÅ¿ur de la tempÃªte','http://ddragon.leagueoflegends.com/cdn/img/champion/splash/Kennen_0.jpg','0','25','10','27');
REM INSERTING into DEGEAJ.RESUMEPERSONNAGE
SET DEFINE OFF;
REM INSERTING into DEGEAJ.UNIVERS
SET DEFINE OFF;
Insert into DEGEAJ.UNIVERS (IDUNIVERS,NOMUNIVERS) values ('5','Narnia');
Insert into DEGEAJ.UNIVERS (IDUNIVERS,NOMUNIVERS) values ('6','La Terre du Milieu');
Insert into DEGEAJ.UNIVERS (IDUNIVERS,NOMUNIVERS) values ('7','Pirates des Caraibes');
Insert into DEGEAJ.UNIVERS (IDUNIVERS,NOMUNIVERS) values ('8','Azeroth');
Insert into DEGEAJ.UNIVERS (IDUNIVERS,NOMUNIVERS) values ('9','Fondation, Trantor');
Insert into DEGEAJ.UNIVERS (IDUNIVERS,NOMUNIVERS) values ('10',' Runeterra');
