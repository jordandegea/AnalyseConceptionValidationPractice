<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="https://bootswatch.com/flatly/bootstrap.min.css" />
        <title>Créer partie</title>
    </head>
    <body>

        <h2>Créer partie</h2>
        
        ${error}
        <form action="partie" method="post" accept-charset="UTF-8">
            <label>Titre :</label><input type="text" name="titre" value="${partie.titre}" requested /><br/>
            <label>Date :</label><input type="text" name="date" value="${partie.date}" requested /><br/>
            <label>Lieu :</label><input type="text" name="lieu" value="${partie.lieu}" requested /><br/>
            <label>Univers :</label>
            <select name="univers" >
                <c:forEach items="${univers}" var="item">
                    <option value="${item.id}">${item.nom}</option>
                </c:forEach>
            </select><br/>
            <label>Résumé de la situation initiale :</label><textarea name="resumeInitial" requested >${partie.resumeInitial}</textarea><br/>

            <input type="submit" value="Enregistrer" />
            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
            <input type="hidden" name="action" value="CREATE" />
        </form>

        <form action="joueur" method="GET" accept-charset="UTF-8">
            <input type="submit" value="Retour" />
            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
            <input type="hidden" name="action" value="SHOW" />
        </form>
    </body>
</html>
