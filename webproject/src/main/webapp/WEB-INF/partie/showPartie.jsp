<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="https://bootswatch.com/flatly/bootstrap.min.css" />
        <title>${partie.titrePartie}</title>
    </head>
    <body>

        <h2>${partie.titrePartie}</h2>

        <ul>
            <li>Titre : ${partie.titrePartie}</li>
            <li>Date : ${partie.date}</li>
            <li>Lieu : ${partie.lieu}</li>
            <li>Univers : ${partie.univers}</li>
            <li>Résumé de la situation initiale : <br/>${partie.resumeInitial}</li>
        </ul>
        
        <h2>Participants</h2>
        <ul>
            <c:forEach items="${partie.personnages}" var="perso">
                <li>${perso.nomPerso} - <a href='personnage?action=SHOW&idPerso=${perso.id}'>voir</a></li>
            </c:forEach>
        </ul>
        <form action="partie" method="GET" accept-charset="UTF-8">
            <input type="submit" value="Ajouter personnage" />
            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
            <input type="hidden" name="action" value="ADDPERSO" />
            <input type="hidden" name="idPartie" value="${partie.id}" />
        </form>

        <form action="joueur" method="GET" accept-charset="UTF-8">
            <input type="submit" value="Retour" />
            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
            <input type="hidden" name="action" value="SHOW" />
        </form>
    </body>
</html>
