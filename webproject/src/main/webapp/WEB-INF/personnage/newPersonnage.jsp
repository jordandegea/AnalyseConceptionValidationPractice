<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/head.jsp" >
    <jsp:param name="title" value="Nouveau Personnage"/>
</jsp:include>

<h2>Créer personnage</h2>

${error}
<form action="personnage" method="post" accept-charset="UTF-8">
    <div class="form-group">
        <label class="col-lg-2 control-label">Nom :</label>
        <input class="form-control" type="text" name="nomPerso" value="${personnage.nomPerso}" required />
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label">Date de naissance :</label>
        <input class="form-control" type="text" name="dateNaiss" value="${personnage.dateNaiss}" required />
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label">Profession :</label>
        <input class="form-control" type="text" name="profession" value="${personnage.profession}" required />
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label">Portrait :</label>
        <input class="form-control" type="url" name="portrait" required placeholder="URL de l'image"/>
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label">Univers :</label>
        <select class="form-control" name="univers" required>
            <c:forEach items="${univers}" var="item">
                <option value="${item.id}">${item.nom}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label">Biographie :</label>
    </div>
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
</form>
<br/>
<form action="joueur" method="GET" accept-charset="UTF-8">
    <input class="btn btn-warning btn-block" type="submit" value="Retour" />
    <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
    <input type="hidden" name="action" value="SHOW" />
</form>

<script type="text/javascript">
    var nbParagraphes=1;

    function addTextArea(){
            nbParagraphes=nbParagraphes+1;
            $("#ajout").append('<label class="col-lg-2 control-label"></label><div class="form-group"><textarea class="form-control" name="paragraphe'+nbParagraphes+'" required></textarea><label class="col-lg-2 control-label"></label><label><input type="checkbox" id="isPrivate'+nbParagraphes+'" name="isPrivate'+nbParagraphes+'" value="isPrivate'+nbParagraphes+'"> Paragraphe Privé</label></div>');

    }
</script>


<jsp:include page="../include/foot.jsp" />

