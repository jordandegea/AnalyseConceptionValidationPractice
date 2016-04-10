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
        ${personnage.profession}<a class="btn btn-warning" onclick="formularise(this, event, ${personnage.id}, 'modifier');"
           href="controleur?action=getOuvrage&view=modifier&id=${ouvrage.id}">
            modifier
        </a>
    </li>
    <li>Univers : ${personnage.univers}</li>
    <li>MJ : 
        <c:choose>
            <c:when test="${personnage.MJ != null}">
                ${personnage.MJ.login} <c:if test="${personnage.demandeMJ}"><i>(en attente de validation)</i></c:if> 
                <form action="personnage" method="POST" accept-charset="UTF-8">
                    <input type="submit" onclick="return confirm('Êtes-vous sûr de vouloir quitter ce MJ?')" value="Quitter ce MJ" />
                    <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
                    <input type="hidden" name="action" value="LEAVEMJ" />
                    <input type="hidden" name="idPerso" value="${personnage.id}" />
                </form>
            </c:when>
            <c:otherwise>
                Pas de MJ
                <form action="personnage" method="GET" accept-charset="UTF-8">
                    <input type="submit" value="Proposer à un MJ" />
                    <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
                    <input type="hidden" name="action" value="FINDMJ" />
                    <input type="hidden" name="idPerso" value="${personnage.id}" />
                </form>
            </c:otherwise>
        </c:choose>
    </li>
    <li>Biographie </li>
    ${personnage.biographie.getParagraphesBiographieAll()}
</ul>

<h2>Parties</h2>
<ul>
    <c:forEach items="${personnage.parties}" var="partie">
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
