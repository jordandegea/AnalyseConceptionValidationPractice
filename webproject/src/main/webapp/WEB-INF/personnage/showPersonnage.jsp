
<jsp:include page="../include/head.jsp" >
    <jsp:param name="title" value="Personnage ${personnage.nomPerso}"/>
</jsp:include>


<h2>Personnage ${personnage.nomPerso}</h2>

<img src="${personnage.portrait}" alt="${personnage.portrait}"/>
<ul>
    <li>Nom : ${personnage.nomPerso}</li>
    <li>Date de naissance : ${personnage.dateNaiss}</li>
    <li>Profession : ${personnage.profession}</li>
    <li>Univers : ${personnage.univers}</li>
    <li>Biographie : TODO</li>
</ul>

<form action="joueur" method="GET" accept-charset="UTF-8">
    <input type="submit" value="Retour" />
    <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
    <input type="hidden" name="action" value="SHOW" />
</form>

<jsp:include page="../include/foot.jsp" />
