
<jsp:include page="../include/head.jsp" >
    <jsp:param name="title" value="Creer une partie"/>
</jsp:include>

<h2>Créer partie</h2>

${error}
<form action="partie" method="post" accept-charset="UTF-8">
    <div class="form-group">
        <label class="col-lg-2 control-label">Titre :</label>
        <div class="col-lg-10">
            <input class="form-control" type="text" name="titre" value="${partie.titre}" requested />
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label">Date :</label>
        <div class="col-lg-10">
            <input class="form-control" type="text" name="date" value="${partie.date}" requested />
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label">Lieu :</label>
        <div class="col-lg-10">
            <input class="form-control" type="text" name="lieu" value="${partie.lieu}" requested />
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label">Univers :</label>
        <select class="form-control col-lg-10" name="univers" >
            <c:forEach items="${univers}" var="item">
                <option value="${item.id}">${item.nom}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label">Résumé de la situation initiale :</label>
        <textarea class="form-control col-lg-10" name="resumeInitial" requested >${partie.resumeInitial}</textarea>
    </div>

    <input class="btn btn-primary btn-block" type="submit" value="Enregistrer" />
    <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
    <input  type="hidden" name="action" value="CREATE" />
</form>

<form action="joueur" method="GET" accept-charset="UTF-8">
    <input class="btn btn-warning btn-block" type="submit" value="Retour" />
    <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
    <input type="hidden" name="action" value="SHOW" />
</form>


<jsp:include page="../include/foot.jsp" />