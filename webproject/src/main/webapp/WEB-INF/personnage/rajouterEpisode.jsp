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
        <label name="dateLabel"> Date (aaaa-mm-jj) </label>
            <input type="date" name="dateEpisode"/>
    </div>
    <br/>
    <input type="button" class="btn btn-primary" onClick="addTextArea()" value="Ajouter Un Paragraphe"/>
    <div id="ajout">
        <br/>
        <label class="control-label"></label>
        <div class="form-group">
            <textarea class="form-control" name="paragraphe1" required></textarea>
            <label class="col-lg-2 control-label"></label><label><input type="checkbox" id="isPrivate1" name="isPrivate1" value="isPrivate1"> Paragraphe Privé</label>
        </div>
    </div>
<div class="form-group">

    <input type="submit" class="btn btn-primary" value="Valider  l'épisode de transition" />
    <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
    <input type="hidden" name="action" value="NEWTRANSI" />
    <input type="hidden" name="idPerso" value="${personnage.id}" />
</div>
</form>

<script type="text/javascript">
    var nbParagraphes = 1;

    function addTextArea() {
        nbParagraphes = nbParagraphes + 1;
        $("#ajout").append('<label class=" control-label"></label><div class="form-group"><textarea class="form-control" name="paragraphe' + nbParagraphes + '" required></textarea><label class="col-lg-2 control-label"></label><label><input type="checkbox" id="isPrivate' + nbParagraphes + '" name="isPrivate' + nbParagraphes + '" value="isPrivate' + nbParagraphes + '"> Paragraphe Privé</label></div>');
    }
</script>

<jsp:include page="../include/foot.jsp" />

