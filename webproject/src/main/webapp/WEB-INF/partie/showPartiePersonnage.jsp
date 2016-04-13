
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/head.jsp" >
    <jsp:param name="title" value="Creer une partie"/>
</jsp:include>

<h2>${partie}</h2>
<ul>
    <li>Titre�: ${partie.titrePartie}</li>
    <li>Date�: ${partie.date}</li>
    <li>Lieu�: ${partie.lieu}</li>
    <li>Univers�: ${partie.univers}</li>
    <li>R�sum� de la situation initiale�: <br/>${partie.resumeInitial}</li>
</ul>

<h2>Participants</h2>
<ul>
    <c:forEach items="${partie.personnages}" var="perso">
        <li>${perso.nomPerso} - <a href='personnage?action=SHOW&idPerso=${perso.id}'>voir</a></li>
    </c:forEach>
</ul>

<c:if test="${!partie.partieFinie}">
<!-- Ici saisie des �pisodes -->
<form action="Transition" method="GET" accept-charset="UTF-8">
    <input type="submit" value="Rajouter un �pisode de transition" />
    <!-- Pour indiquer au contr�leur quelle action faire, on utilise un champ cach� -->
    <input type="hidden" name="action" value="NEWTRANSI" />
    <input type="hidden" name="idPartie" value="${partie.id}" />
    
</form>
</c:if>

<c:if test="${partie.partieFinie}">
<h2>R�sum�</h2>
    ${partie.resumePartie.getAllPart()}
</c:if>

<form action="joueur" method="GET" accept-charset="UTF-8">
    <input type="submit" value="Retour" />
    <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
    <input type="hidden" name="action" value="SHOW" />
</form>

<jsp:include page="../include/foot.jsp" />
