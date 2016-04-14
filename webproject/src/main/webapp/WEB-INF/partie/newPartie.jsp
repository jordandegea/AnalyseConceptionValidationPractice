
<jsp:include page="../include/head.jsp" >
    <jsp:param name="title" value="Creer une partie"/>
</jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Cr�er partie</h2>

${error}
<form action="partie" method="post" accept-charset="UTF-8">
    <div class="form-group">
        <label class="col-lg-2 control-label">Titre�:</label>
        <div class="col-lg-10">
            <input class="form-control" type="text" name="titre" value="${partie.titre}" required />
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label">Date :</label>
        <div class="col-lg-10">
            <input class="form-control" type="text" name="date" value="${partie.date}" required />
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label">Lieu�:</label>
        <div class="col-lg-10">
            <input class="form-control" type="text" name="lieu" value="${partie.lieu}" required />
        </div>
    </div>
    <div class="form-group">
        <div class="row">
        <label class="col-lg-2 control-label">Univers�:</label>
        <select class="form-control col-lg-10" name="univers" required>
            <c:forEach items="${univers}" var="item">
                <option value="${item.id}">${item.nom}</option>
            </c:forEach>
        </select>
        </div>
    </div>
    <div class="form-group">
            <div class="row">
        <label class="col-lg-2 control-label">R�sum� de la situation initiale�:</label>
        <textarea class="form-control col-lg-10" name="resumeInitial" required >${partie.resumeInitial}</textarea>
    </div>
    </div>
    <br/>
    <div class="row">
            <input class="btn btn-primary btn-block" type="submit" value="Enregistrer" />
            <!-- Pour indiquer au contr�leur quelle action faire, on utilise un champ cach� -->
            <input  type="hidden" name="action" value="CREATE" />
    </div>
</form>
<br/>

<form action="joueur" method="GET" accept-charset="UTF-8">
    <div class="row">
        <div class="form-group">
            <input class="btn btn-warning btn-block" type="submit" value="Retour" />
            <!-- Pour indiquer au contr�leur quelle action faire, on utilise un champ cach� -->
            <input type="hidden" name="action" value="SHOW" />
        </div>
    </div>
</form>


<jsp:include page="../include/foot.jsp" />