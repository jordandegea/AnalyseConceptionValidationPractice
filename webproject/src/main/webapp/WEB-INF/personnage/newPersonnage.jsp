<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/head.jsp" >
    <jsp:param name="title" value="Nouveau Personnage"/>
</jsp:include>

<h2>Créer personnage</h2>

${error}
<form action="personnage" method="post" accept-charset="UTF-8">
    <div class="form-group">
        <label class="col-lg-2 control-label">Nom :</label>
        <input class="form-control" type="text" name="nomPerso" value="${personnage.nomPerso}" requested />
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label">Date de naissance :</label>
        <input class="form-control" type="text" name="dateNaiss" value="${personnage.dateNaiss}" requested />
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label">Profession :</label>
        <input class="form-control" type="text" name="profession" value="${personnage.profession}" requested />
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label">Portrait :</label>
        <input class="form-control" type="file" name="portrait" requested />
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label">Univers :</label>
        <select class="form-control" name="univers" >
            <c:forEach items="${univers}" var="item">
                <option value="${item.id}">${item.nom}</option>
            </c:forEach>
        </select>
    </div>

    TODO : ajout dynamique de paragraphes (avec option secret) pour la bio initiale<br/>

    <input class="btn btn-warning btn-block" type="submit" value="Enregistrer" />
    <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
    <input  type="hidden" name="action" value="CREATE" />
</form>

<form action="joueur" method="GET" accept-charset="UTF-8">
    <input class="fbtn btn-warning btn-block" type="submit" value="Retour" />
    <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
    <input type="hidden" name="action" value="SHOW" />
</form>


<jsp:include page="../include/foot.jsp" />

