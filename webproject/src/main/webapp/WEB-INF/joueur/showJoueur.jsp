
<jsp:include page="../include/head.jsp" >
    <jsp:param name="title" value="Profil"/>
</jsp:include>

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
    <input type="submit" value="CrÃ©er partie" />
    <!-- Pour indiquer au contrÃ´leur quelle action faire, on utilise un champ cachÃ© -->
    <input type="hidden" name="action" value="NEW" />
</form>
<form action="" method="post" accept-charset="UTF-8">
    <input type="submit" value="Déconnexion" />
    <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
    <input type="hidden" name="action" value="LOGOUT" />
</form>

<jsp:include page="../include/foot.jsp" />
