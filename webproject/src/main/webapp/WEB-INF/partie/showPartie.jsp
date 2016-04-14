
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/head.jsp" >
    <jsp:param name="title" value="Creer une partie"/>
</jsp:include>

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
                            ${perso.nomPerso} - <a href='personnage?action=SHOW&idPerso=${perso.id}'>voir</a>
                        </c:forEach>


                        <c:if test="${!partie.partieFinie}">
                            <form action="partie" method="GET" accept-charset="UTF-8">
                                <input type="submit" class="btn btn-primary btn-block" value="Ajouter personnage" />
                                <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
                                <input type="hidden" name="action" value="ADDPERSO" />
                                <input type="hidden" name="idPartie" value="${partie.id}" />
                            </form>
                        </center>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="form-horizontal"> 
        <form action="resumePartie" method="GET" accept-charset="UTF-8">
            <input type="submit" class="btn btn-warning btn-block" onclick="return confirm('Êtes-vous sûr de vouloir terminer cette partie ?')" value="Terminer partie et entrer résumé" />
            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
            <input type="hidden" name="action" value="NEW" />
            <input type="hidden" name="idPartie" value="${partie.id}" />
        </form>

        <form action="partie" method="POST" accept-charset="UTF-8">
            <input type="submit" class="btn btn-warning btn-block" onclick="return confirm('Êtes-vous sûr de vouloir annuler cette partie ?')" value="Annuler partie" />
            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
            <input type="hidden" name="action" value="DELETE" />
            <input type="hidden" name="idPartie" value="${partie.id}" />
        </form>
    </div>
</c:if>

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
    <form action="joueur" method="GET" accept-charset="UTF-8">
        <input type="submit" class="btn btn-primary btn-block"  value="Retour" />
        <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
        <input type="hidden" name="action" value="SHOW" />
    </form>
</div>
<jsp:include page="../include/foot.jsp" />
