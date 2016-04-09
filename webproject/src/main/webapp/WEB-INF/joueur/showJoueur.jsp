<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="https://bootswatch.com/flatly/bootstrap.min.css" />
        <title>Joueur</title>
    </head>
    <body>

        <h2>Joueur</h2>
        <ul>
            <li>${joueur.login}</li>
            <li>${joueur.email}</li>
        </ul>

        <h2>Mes personnages</h2>
        <ul>
            <c:forEach items="${joueur.getPersonnagesOwned()}" var="perso">
                <li>${perso.nomPerso} - <a href='personnage?action=SHOW&idPerso=${perso.id}'>voir</a></li>
            </c:forEach>
        </ul>

        <form action="personnage" method="GET" accept-charset="UTF-8">
            <input type="submit" value="Créer personnage" />
            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
            <input type="hidden" name="action" value="NEW" />
        </form>
        <form action="partie" method="GET" accept-charset="UTF-8">
            <input type="submit" value="Créer partie" />
            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
            <input type="hidden" name="action" value="NEW" />
        </form>
        <form action="" method="post" accept-charset="UTF-8">
            <input type="submit" value="Déconnexion" />
            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
            <input type="hidden" name="action" value="LOGOUT" />
        </form>
    </body>
</html>
