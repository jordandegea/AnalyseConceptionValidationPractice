<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/head.jsp" >
    <jsp:param name="title" value="Nouveau Personnage"/>
</jsp:include>


<h2>Trouver un MJ</h2>
Choisissez un MJ à qui proposer ce personnage:

${error}
<form action="personnage" method="POST" accept-charset="UTF-8">
    <select class="form-control" name="idMJ" >
        <c:forEach items="${potentialMJ}" var="mj">
            <option value="${mj.id}">${mj.login}</option>
        </c:forEach>
    </select><br/>

    <input class="btn btn-primary btn-block col-xs-12 col-lg-6" type="submit" value="Proposer" />
    <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
    <input type="hidden" name="action" value="ASKMJ" />
    <input type="hidden" name="idPerso" value="${idPerso}" />
</form>

<form action="personnage" method="GET" accept-charset="UTF-8">
    <input class="btn btn-warning btn-block col-xs-12 col-lg-6" type="submit" value="Retour" />
    <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
    <input type="hidden" name="action" value="SHOW" />
    <input type="hidden" name="idPerso" value="${idPerso}" />
</form>

<jsp:include page="../include/foot.jsp" />