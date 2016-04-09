<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="https://bootswatch.com/flatly/bootstrap.min.css" />
        <title>Enrôler un personnage</title>
    </head>
    <body>

        <h2>Enrôler un personnage</h2>
        Sélectionnez un personnage à enrôler pour la partie "${partie.titrePartie}" (${enrollable.size()}):
        
        ${error}
        <form action="partie" method="POST" accept-charset="UTF-8">
            <select name="idPerso" >
                <c:forEach items="${enrollable}" var="perso">
                    <option value="${perso.id}">${perso.nomPerso}</option>
                </c:forEach>
            </select><br/>

            <input type="submit" value="Enregistrer" />
            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
            <input type="hidden" name="action" value="ENROLL" />
        </form>

        <form action="partie" method="GET" accept-charset="UTF-8">
            <input type="submit" value="Retour" />
            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
            <input type="hidden" name="action" value="SHOW" />
            <input type="hidden" name="idPartie" value="${partie.id}" />
        </form>
    </body>
</html>
