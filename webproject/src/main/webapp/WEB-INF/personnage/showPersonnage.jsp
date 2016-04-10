
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
                ${personnage.MJ.login}
            </c:when>
            <c:otherwise>
                Pas de MJ
            </c:otherwise>
        </c:choose>
    </li>
    <li>Biographie </li>
    ${personnage.biographie.getParagraphesBiographieAll()};
</ul>

<form action="personnage" method="GET" accept-charset="UTF-8">
    <input type="submit" value="Proposer à un MJ" />
    <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
    <input type="hidden" name="action" value="FINDMJ" />
    <input type="hidden" name="idPerso" value="${personnage.id}" />
</form>
<form action="joueur" method="GET" accept-charset="UTF-8">
    <input type="submit" value="Retour" />
    <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
    <input type="hidden" name="action" value="SHOW" />
</form>

    
<script type="text/javascript" src="../public/js/formularise.js">
</script>
    
<jsp:include page="../include/foot.jsp" />
