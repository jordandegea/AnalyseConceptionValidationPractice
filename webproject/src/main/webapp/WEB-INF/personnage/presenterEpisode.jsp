<jsp:include page="../include/head.jsp" >
    <jsp:param name="title" value="Personnage ${personnage.nomPerso}"/>
</jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Ici saisie des épisodes -->
<form action="personnage" method="GET" accept-charset="UTF-8">

    <h3> Episode à valider  </h3> 
    <div class="form-group">
        ${error} <br/>
        Personnage : <label name="perso"> ${personnage.nomPerso} </label> <br/>
        Appartient à : <label name="proprio">${personnage.getOwner().login} </label> <br/>
        Date :
        <label name="dateValue" > ${episode.date} </label>
    </div>

    <br/>
    <c:set var="count" value="1" scope="page" />
    <form>
        <c:forEach items="${episode.getParagraphes()}" var="par">
            <div class="form-group">
                <textarea class="form-control" name="${count}"  required> ${par.contenu} </textarea>
            </div>
            <c:set var="count" value="${count + 1}" scope="page"/>
        </c:forEach>
        <div class="form-group">
            <input type="submit" class="btn btn-primary" value="Valider l'épisode de transition" />
            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
            <input type="hidden" name="action" value="VALIDERTRANSI" />
            <input type="hidden" name="idEp" value="${episode.id}" />
        </div>
    </form>
    <form>
        <div class="form-group">
            <input type="submit" class="btn btn-warning" value="Refuser l'épisode de transition" />
            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
            <input type="hidden" name="action" value="REFUSERTRANSI" />
            <input type="hidden" name="idEp" value="${episode.id}" />
        </div>
    </form>
</form>

<jsp:include page="../include/foot.jsp" />

