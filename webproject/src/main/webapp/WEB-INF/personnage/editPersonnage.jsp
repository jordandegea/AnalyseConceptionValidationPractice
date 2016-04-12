<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/head.jsp" >
    <jsp:param name="title" value="Editer Personnage ${personnage.nomPerso}"/>
</jsp:include>

<h2>Editer personnage</h2>

${error}
<form action="personnage" method="post" accept-charset="UTF-8">
    <div class="form-group">
        <label class="col-lg-2 control-label">Nom :</label>
        <input class="form-control" type="text" name="nomPerso" value="${personnage.nomPerso}" readonly/>
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label">Date de naissance :</label>
        <input class="form-control" type="text" name="dateNaiss" value="${personnage.dateNaiss}" readonly />
    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label">Profession :</label>
        <input class="form-control" type="text" name="profession" value="${personnage.profession}" required />
    </div>
    
    <input type="hidden" name="idPerso" value="${idPerso}" />
    <input class="btn btn-warning btn-block" type="submit" value="Enregistrer" />
    <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
    <input  type="hidden" name="action" value="UPDATE" />
</form>

<jsp:include page="../include/foot.jsp" />

