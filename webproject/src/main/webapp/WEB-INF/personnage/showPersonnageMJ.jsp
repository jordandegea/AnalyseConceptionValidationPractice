<jsp:include page="../include/head.jsp" >
    <jsp:param name="title" value="Personnage ${personnage.nomPerso}"/>
</jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<h2>Personnage ${personnage.nomPerso}</h2>

<img src="${personnage.portrait}" alt="${personnage.portrait}"/>
<ul>
    <li>Nom : ${personnage.nomPerso}</li>
    <li>Date de naissance : ${personnage.dateNaiss}</li>
    <li>Profession : 
        ${personnage.profession}
    </li>
    <li>Univers : ${personnage.univers}</li>
    <li>MJ : 
        <c:choose>
            <c:when test="${personnage.MJ != null}">
                ${personnage.MJ.login}
            </c:when>
            <c:otherwise>
                Pas de MJ
            </c:otherwise>
        </c:choose>
    </li>
    <li>Biographie </li>
    ${personnage.biographie.getParagraphesBiographieAll()}
</ul>

<h2>Parties</h2>
<ul>
    <c:forEach items="${personnages.parties}" var="partie">
        <li>${partie.titrePartie} - <a href='partie?action=SHOW&idPartie=${partie.id}'>voir</a></li>
        </c:forEach>
</ul>

<form action="joueur" method="GET" accept-charset="UTF-8">
    <input type="submit" value="Retour" />
    <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
    <input type="hidden" name="action" value="SHOW" />
</form>


<script type="text/javascript" src="../public/js/formularise.js">
</script>

<jsp:include page="../include/foot.jsp" />
