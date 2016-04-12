
<jsp:include page="../include/head.jsp" >
    <jsp:param name="title" value="Enrôler un personange"/>
</jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Enrôler un personnage</h2>
Sélectionnez un personnage à enrôler pour la partie <strong>"${partie.titrePartie}"</strong> :

${error}
<form action="partie" method="POST" accept-charset="UTF-8">
    <select class="form-control" name="idPerso" required>
        <c:forEach items="${enrollable}" var="perso">
            <option value="${perso.id}">${perso.nomPerso}</option>
        </c:forEach>
    </select><br/>

    <input class="btn btn-primary"type="submit" value="Enregistrer" />
    <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
    <input type="hidden" name="action" value="ENROLL" />
    <input type="hidden" name="idPartie" value="${partie.id}" />
</form>

<form action="partie" method="GET" accept-charset="UTF-8">
    <input class="btn btn-warning" type="submit" value="Retour" />
    <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
    <input type="hidden" name="action" value="SHOW" />
    <input type="hidden" name="idPartie" value="${partie.id}" />
</form>

<jsp:include page="../include/foot.jsp" />
