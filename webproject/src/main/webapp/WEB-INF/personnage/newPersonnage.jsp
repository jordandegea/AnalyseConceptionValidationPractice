<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8" />
        <title>Créer personnage</title>
    </head>
    <body>

        <h2>Créer personnage</h2>
        
        ${error}
        <form action="personnage" method="post" accept-charset="UTF-8">
            <label>Nom :</label><input type="text" name="nomPerso" value="${personnage.nomPerso}" requested /><br/>
            <label>Date de naissance :</label><input type="text" name="dateNaiss" value="${personnage.dateNaiss}" requested /><br/>
            <label>Profession :</label><input type="text" name="profession" value="${personnage.profession}" requested /><br/>
            <label>Portrait :</label><input type="file" name="portrait" requested /><br/>
            <label>Univers :</label>
            <select name="univers" >
                <c:forEach items="${univers}" var="item">
                    <option value="${item.id}">${item.nom}</option>
                </c:forEach>
            </select><br/>

            TODO : ajout dynamique de paragraphes (avec option secret) pour la bio initiale<br/>

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
