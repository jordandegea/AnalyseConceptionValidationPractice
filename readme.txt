## Project ACVL & WEB - ENSIMAG 2A
____________________________

By DANTIGNY Raynald, DE GEA Jordan, DUCLOT William, RABOURG Simon

[Lien du sujet](http://chamilo2.grenet.fr/inp/courses/ENSIMAG4MMCAWEB/document/Sujet_ISI_2016.pdf)


# Requirements

Apache Tomcat or TomEE installé
Library JDBC installé et linké avec ApacheTomcat

# Configuration

## JDBC

Ouvrir le fichier : webproject/src/main/webapp/META-INF/context.xml
Modifier les paramètres souhaités

## TomCat

Ouvrir le fichier settings.xml et entrez vos identifiants Tomcat

## SQL

Dans votre base de données oracle, executez les lignes présentes dans install_bd.sql

# Execution

Lancer le serveur Tomcat
mvn clean install