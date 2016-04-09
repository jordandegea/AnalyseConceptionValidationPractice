<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="https://bootswatch.com/flatly/bootstrap.min.css" />
        <title>Personnage ${personnage.nomPerso}</title>
    </head>
    <body>

        <h2>Personnage ${personnage.nomPerso}</h2>

        <img src="${personnage.portrait}" alt="portrait"/>
        <ul>
            <li>Nom : ${personnage.nomPerso}Nom : ${personnage.nomPerso}</li>
            <li>Date de naissance : ${personnage.dateNaiss}</li>
            <li>Profession : ${personnage.profession}</li>
            <li>Univers : ${personnage.univers}</li>
            <li>Biographie : TODO</li>
        </ul>

        <form action="joueur" method="GET" accept-charset="UTF-8">
            <input type="submit" value="Retour" />
            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
            <input type="hidden" name="action" value="SHOW" />
        </form>
    </body>
</html>
