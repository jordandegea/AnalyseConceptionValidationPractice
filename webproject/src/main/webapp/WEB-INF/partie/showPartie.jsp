
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/head.jsp" >
    <jsp:param name="title" value="Creer une partie"/>
</jsp:include>

<h2>${partie.titrePartie} <c:if test="${partie.partieFinie}"><b>(terminée)</b></c:if></h2>
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

<c:if test="${!partie.partieFinie}">
<form action="partie" method="POST" accept-charset="UTF-8">
    <input type="submit" onclick="return confirm('Êtes-vous sûr de vouloir terminer cette partie ?')" value="Terminer partie" />
    <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
    <input type="hidden" name="action" value="END" />
    <input type="hidden" name="idPartie" value="${partie.id}" />
</form>
</c:if>

<form action="joueur" method="GET" accept-charset="UTF-8">
    <input type="submit" value="Retour" />
    <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
    <input type="hidden" name="action" value="SHOW" />
</form>

<jsp:include page="../include/foot.jsp" />
