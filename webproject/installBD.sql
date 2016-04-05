CREATE SEQUENCE id_seq;

CREATE TABLE bibliographie (
       id number(6) DEFAULT id_seq.nextval PRIMARY KEY,
       auteur varchar(100) NOT NULL,
       titre varchar(100) NOT NULL
);

INSERT INTO bibliographie (auteur, titre) VALUES
   ('Jules Verne','Voyage au centre de la terre');
INSERT INTO bibliographie (auteur, titre) VALUES
   ('Arnaldur Indridason','L''homme du lac');
INSERT INTO bibliographie (auteur, titre) VALUES
   ('Victor Hugo','Les misérables');