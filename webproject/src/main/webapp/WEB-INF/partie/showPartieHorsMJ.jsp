
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/head.jsp" >
    <jsp:param name="title" value="Creer une partie"/>
</jsp:include>
<br/>
<div class="row">
    <div class="panel-group col-xd-12 col-lg-6 col-lg-offset-3">
        <div class="panel panel-default">
            <div class="panel-heading"><center><h2>${partie}</h2></center>
            </div>
            <div class="panel-body">
                <div class="form-horizontal">
                    <center>
                        <label>Titre : </label>
                        ${partie.titrePartie}
                    </center>
                </div>
                <div class="form-horizontal">
                    <center>
                        <label> Date :
                        </label>${partie.date}
                    </center>
                </div>
                <div class="form-horizontal">
                    <center>
                        <label>Lieu : </label> 
                        ${partie.lieu}
                    </center>
                </div>
                <div class="form-horizontal">
                    <center>
                        <label>Univers : </label>
                        ${partie.univers}
                    </center>
                </div>
                <div class="form-horizontal">
                    <center>
                        <label>Résumé de la situation initiale : </label>
                        <br/>${partie.resumeInitial}
                    </center>
                </div>

            </div>
        </div>
    </div>
</div>
</br>
<div class="row">
    <div class="panel-group col-xd-12 col-lg-6 col-lg-offset-3">
        <div class="panel panel-default">
            <div class="panel-heading"><center><h2>Participants</h2></center>
            </div>
            <div class="panel-body">
                <div class="form-horizontal">
                    <center>
                        <c:forEach items="${partie.personnages}" var="perso">
                            ${perso.nomPerso} - <a href='personnage?action=SHOW&idPerso=${perso.id}'>voir</a> <br/>
                        </c:forEach>
                    </center>
                </div>
            </div>
        </div>
    </div>
</div>
<br/>
<br/>
<c:if test="${partie.partieFinie}">
    <div class="row">
        <div class="panel-group col-xd-12 col-lg-6 col-lg-offset-3">
            <div class="panel panel-default">
                <div class="panel-heading"><center>    <h2>Résumé</h2></center>
                </div>
                <div class="panel-body">
                    <div class="form-horizontal"> 
                        <center>
                            ${partie.resumePartie.getAllPart()}
                        </center>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
<br/>
<div class="form-horizontal"> 
    <div class="row">
        <form action="joueur" method="GET" accept-charset="UTF-8">
            <input type="submit" class="btn btn-primary btn-block"  value="Retour" />
            <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
            <input type="hidden" name="action" value="SHOW" />
        </form>
    </div>
</div>
<jsp:include page="../include/foot.jsp" />
