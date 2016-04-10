
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/head.jsp" >
    <jsp:param name="title" value="Profil"/>
</jsp:include>

<div class="col-xs-12 col-md-6 col-lg-4">
    <h2>Joueur</h2>
    <ul>
        <li>${joueur.login}</li>
        <li>${joueur.email}</li>
    </ul>
</div>

<div class="col-xs-12 col-md-6 col-lg-4">
    <h2>Mes personnages</h2>
    <ul>
        <c:forEach items="${joueur.getPersonnagesOwned()}" var="perso">
            <li>${perso.nomPerso} - <a href='personnage?action=SHOW&idPerso=${perso.id}'>voir</a> - <a href='personnage?action=EDIT&idPerso=${perso.id}'>modifier</a></li>

        </c:forEach>
    </ul>

    <form action="personnage" method="GET" accept-charset="UTF-8">
        <input type="submit" value="Créer personnage" />

        <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
        <input type="hidden" name="action" value="NEW" />
    </form>
</div>

<div class="col-xs-12 col-md-6 col-lg-4">
    <h2>Mes parties</h2>
    <ul>
        <c:forEach items="${joueur.partiesManaged}" var="partie">
            <li>${partie} - <a href='partie?action=SHOW&idPartie=${partie.id}'>voir</a></li>
            </c:forEach>
    </ul>
    <form action="partie" method="GET" accept-charset="UTF-8">
        <input type="submit" value="Créer partie" />
        <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->

        <input type="hidden" name="action" value="NEW" />
    </form>
</div>
    
<div class="col-xs-12 col-md-6 col-lg-4">
    <h2>Joueurs MJ</h2>
    <ul>
        <c:forEach items="${persosMJ}" var="perso">
            <li>${perso.nomPerso} - <a href='personnage?action=SHOW&idPerso=${perso.id}'>voir</a></li>
            </c:forEach>
    </ul>
</div>

<div class="col-xs-12 col-md-6 col-lg-4">
    <h2>Demandes MJ</h2>
        ${error}
    <ul>
        <c:forEach items="${demandeursMJ}" var="perso">
            <li>${perso.nomPerso} - <a href='personnage?action=SHOW&idPerso=${perso.id}'>voir</a></li>
            <form action="mj" method="POST" accept-charset="UTF-8">
                <input type="submit" value="Accepter" />
                <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
                <input type="hidden" name="action" value="ACCEPT" />
                <input type="hidden" name="idPerso" value="${perso.id}" />
            </form>
            <form action="mj" method="POST" accept-charset="UTF-8">
                <input type="submit" value="Refuser" />
                <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
                <input type="hidden" name="action" value="REJECT" />
                <input type="hidden" name="idPerso" value="${perso.id}" />
            </form>
            </c:forEach>
    </ul>
</div>


<jsp:include page="../include/foot.jsp" />
