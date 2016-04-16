<jsp:include page="../include/head.jsp" >
    <jsp:param name="title" value="Personnage ${personnage.nomPerso}"/>
</jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Ici saisie des épisodes -->
<form action="personnage" method="GET" accept-charset="UTF-8">

    <h3> Modification de l'episode ${episode.id} </h3> 
    <div class="form-group">
        ${error} <br/>
        <label name="dateLabel"> Date (aaaa-mm-jj) </label>
        <input type="date" name="dateEpisode" value="${episode.date}"> 
    </div>
    <br/>
    <div class="form-group">
        <c:set var="count" value="1" scope="page"/>

        <c:forEach items="${episode.getParagraphes()}" var="par">
            <div class="form-group">
                <textarea class="form-control" name="${count}"  required> ${par.contenu} </textarea>
            </div>
                <label class="col-lg-2 control-label"></label><label><input type="checkbox" id="${count + 1}" name="${count + 1}" <c:if test="${par.secret}">checked</c:if>> Paragraphe Privé</label>
            <br/>

            <c:set var="count" value="${count + 2}" scope="page"/>
        </c:forEach>
        <label class="col-lg-2 control-label">  </label> <label><input type="checkbox" name="validationEpisode" id="validationEpisode" /> Envoyer l'épisode au MJ pour validation </label>

        <div class="form-group">
            <input type="submit" class="btn btn-primary" value="Valider l'épisode de transition" />
            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
            <input type="hidden" name="action" value="UPDATETRANSI" />
            <input type="hidden" name="idEp" value="${episode.id}" />
        </div>
    </div>
</form>
<jsp:include page="../include/foot.jsp" />

