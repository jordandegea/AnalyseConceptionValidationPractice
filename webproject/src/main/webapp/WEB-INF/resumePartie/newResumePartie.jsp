<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/head.jsp" >
    <jsp:param name="title" value="Résumé de la partie"/>
</jsp:include>

<h2>Entrer le résumé de la partie</h2>

${error}
<form action="resumePartie" method="post" accept-charset="UTF-8">
    Date (aaaa-mm-jj) : <input type="date" name="date" required><br/>
    Entrez le résumé :<br/>
    <div class="form-group">
        <input type="button" class="btn btn-primary" onClick="addTextArea()" value="Ajouter Un Paragraphe"/>
        
        <div id="ajout">
            <br/>
            <label class="col-lg-2 control-label"></label>
            <div class="form-group">
                <textarea class="form-control" name="paragraphe1" required></textarea>
                <label class="col-lg-2 control-label"></label><label><input type="checkbox" id="isPrivate1" name="isPrivate1" value="isPrivate1"> Paragraphe Privé</label>
            </div>
        </div>
    </div>
    
    <input class="btn btn-warning btn-block" type="submit" value="Enregistrer" />
    <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
    <input  type="hidden" name="action" value="CREATE" />
    <input type="hidden" name="idPartie" value="${idPartie}" />
</form>

<form action="partie" method="GET" accept-charset="UTF-8">
    <input class="fbtn btn-warning btn-block" type="submit" value="Retour" />
    <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
    <input type="hidden" name="action" value="SHOW" />
    <input type="hidden" name="idPartie" value="${idPartie}" />
</form>

<script type="text/javascript">
    var nbParagraphes=1;

    function addTextArea(){
            nbParagraphes=nbParagraphes+1;
            $("#ajout").append('<label class="col-lg-2 control-label"></label><div class="form-group"><textarea class="form-control" name="paragraphe'+nbParagraphes+'" required></textarea></div>');

    }
</script>
<jsp:include page="../include/foot.jsp" />

