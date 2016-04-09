
<jsp:include page="../include/head.jsp" >
    <jsp:param name="title" value="Creer une partie"/>
</jsp:include>

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
    <input type="hidden" name="action" value="ENROLL" />
</form>

<form action="joueur" method="GET" accept-charset="UTF-8">
    <input type="submit" value="Retour" />
    <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
    <input type="hidden" name="action" value="SHOW" />
</form>

<jsp:include page="../include/foot.jsp" />
