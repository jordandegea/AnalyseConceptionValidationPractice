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
        <input class="form-control" type="file" name="portrait" required />
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label">Univers :</label>
        <select class="form-control" name="univers" required>
            <c:forEach items="${univers}" var="item">
                <option value="${item.id}">${item.nom}</option>
            </c:forEach>
        </select>
    </div>

    <input type="button" name="ajout" id="ajout" value="Paragraphe +" onclick="addTextArea()"/>  
    
    <div id="ajout"></div>
    
    <input class="btn btn-warning btn-block" type="submit" value="Enregistrer" />
    <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
    <input  type="hidden" name="action" value="CREATE" />
</form>

<form action="joueur" method="GET" accept-charset="UTF-8">
    <input class="fbtn btn-warning btn-block" type="submit" value="Retour" />
    <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
    <input type="hidden" name="action" value="SHOW" />
</form>

<script type="text/javascript">
    var i = 0;
function addTextArea(){
        var input = document.createElement("textarea");
        input.id = "textareaid" + i;
        input.name = "textareaname" + i;
        var saut = document.createElement("br");
        var saut2 = document.createElement("br");
        var check = document.createElement ("input");
        check.type = "checkbox";
        check.value = 1;
        check.id = "checkid" + i;
        check.name = "checkname" + i;
        var label = document.createElement('label')
        label.htmlFor = "checkid";
        label.appendChild(document.createTextNode('Paragraphe privé'));;
        var ajout = document.getElementById("ajout");
        ajout.parentNode.insertBefore(saut,ajout);
        ajout.parentNode.insertBefore(input,ajout);
        ajout.parentNode.insertBefore(saut2,ajout);
        ajout.parentNode.insertBefore(check,ajout); 
        ajout.parentNode.insertBefore(label,ajout);        
        ajout.parentNode.insertBefore(saut,ajout);
        i = i+1;
}
</script>


<jsp:include page="../include/foot.jsp" />

