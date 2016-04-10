<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="https://bootswatch.com/flatly/bootstrap.min.css" />
        <title>Trouver un MJ</title>
    </head>
    <body>

        <h2>Trouver un MJ</h2>
        Choisissez un MJ à qui proposer ce personnage:

        ${error}
        <form action="personnage" method="POST" accept-charset="UTF-8">
            <select name="idMJ" >
                <c:forEach items="${potentialMJ}" var="mj">
                    <option value="${mj.id}">${mj.login}</option>
                </c:forEach>
            </select><br/>

            <input type="submit" value="Proposer" />
            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
            <input type="hidden" name="action" value="ASKMJ" />
            <input type="hidden" name="idPerso" value="${idPerso}" />
        </form>

        <form action="personnage" method="GET" accept-charset="UTF-8">
            <input type="submit" value="Retour" />
            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
            <input type="hidden" name="action" value="SHOW" />
            <input type="hidden" name="idPerso" value="${idPerso}" />
        </form>
    </body>
</html>
