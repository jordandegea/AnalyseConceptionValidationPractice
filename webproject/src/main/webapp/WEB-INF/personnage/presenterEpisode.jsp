<jsp:include page="../include/head.jsp" >
    <jsp:param name="title" value="Personnage ${personnage.nomPerso}"/>
</jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Ici saisie des épisodes -->
<form action="personnage" method="GET" accept-charset="UTF-8">

    <h3> Nouvel Episode </h3> 
    <div class="form-group">
        ${error} <br/>
        <label name="dateLabel"> Date :</label>
        <label name="dateValue" > ${episode.date} </label>
    </div>

    <br/>
    <c:forEach items="${episode.getParagraphes()}" var="par">
        <div class="form-group">
            <textarea class="form-control" name="paragraphe"  required> ${par.contenu} </textarea>
        </div>
    </c:forEach>
    <div class="form-group">
        <input type="submit" class="btn btn-primary" value="Valider l'épisode de transition" />
        <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
        <input type="hidden" name="action" value="VALIDERTRANSI" />
        <input type="hidden" name="idPerso" value="${personnage.id}" />
    </div>
</form>

<jsp:include page="../include/foot.jsp" />

