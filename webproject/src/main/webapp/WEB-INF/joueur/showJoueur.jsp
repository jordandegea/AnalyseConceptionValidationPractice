
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/head.jsp" >
    <jsp:param name="title" value="Profil"/>
</jsp:include>
<div class="row">
    <br/>
    <div class="panel-group col-xs-12 col-md-6 col-lg-4">
        <div class="panel panel-default">
            <div class="panel-heading"><center><h2>Info</h2></center>
            </div>
            <div class="panel-body">
                <div class="form-horizontal">
                    <center>
                        <label>  ${joueur.login} </label> <br/>
                        <label>${joueur.email} </label>
                    </center>
                </div>

            </div>
        </div>
    </div>
    <div class="panel-group col-xs-12 col-md-6 col-lg-4">
        <div class="panel panel-default">
            <div class="panel-heading"><center><h2>Mes personnages</h2></center>
            </div>
            <div class="panel-body">
                <div class="form-horizontal">
                    <c:forEach items="${joueur.getPersonnagesOwned()}" var="perso">
                        <div class="col-xd-2"> ${perso.nomPerso} - <a href='personnage?action=SHOW&idPerso=${perso.id}'>voir</a> - <a href='personnage?action=EDIT&idPerso=${perso.id}'>modifier</a> </div>
                    </c:forEach>
                    <br/>

                    <form action="personnage" method="GET" accept-charset="UTF-8">
                        <input type="submit" class="btn btn-primary" value="Créer personnage" />

                        <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
                        <input type="hidden" name="action" value="NEW" />
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="panel-group col-xd-12 col-md-6 col-lg-4">
        <div class="panel panel-default">

            <div class="panel-heading"><center><h2>Mes parties</h2></center>
            </div>

            <div class="panel-body">
                <div class="form-horizontal">

                    <c:forEach items="${joueur.partiesManaged}" var="partie">
                        <div class="col-xd-2"> ${partie}  - <a href='partie?action=SHOW&idPartie=${partie.id}'>voir</a> </div>
                    </c:forEach>
                    <br/>
                    <form action="partie" method="GET" accept-charset="UTF-8">
                        <input type="submit" class="btn btn-primary" value="Créer partie" />
                        <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->

                        <input type="hidden" name="action" value="NEW" />
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="panel-group col-xs-12 col-md-6 col-lg-4">
        <div class="panel panel-default">

            <div class="panel-heading"><center><h2>Joueurs MJ</h2></center>
            </div>
            <div class="panel-body">
                <div class="form-horizontal">
                    <c:forEach items="${persosMJ}" var="perso">
                        <div class=""col-xd-2">  ${perso.nomPerso} - <a href='personnage?action=SHOW&idPerso=${perso.id}'>voir</a> <br/> </div>
                        </c:forEach>
                </div>
            </div>
        </div>
    </div>
    <div class="panel-group col-xs-12 col-md-6 col-lg-4">
        <div class="panel panel-default">
            <div class="panel-heading"><center><h2>Demandes MJ</h2></center>
            </div>
            <div class="panel-body">
                ${error}
                <center>
                    <c:forEach items="${demandeursMJ}" var="perso">
                        ${perso.nomPerso} - <a href='personnage?action=SHOW&idPerso=${perso.id}'>voir</a> 
                        <br/>
                        <form class="form-horizontal" action="mj"  method="POST" accept-charset="UTF-8">
                            <input type="submit" class="btn btn-primary" value="Accepter" />
                            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
                            <input type="hidden" class="btn btn-primary" name="action" value="ACCEPT" />
                            <input type="hidden" name="idPerso" value="${perso.id}" />
                        </form>
                        <br/>
                        <form class="form-horizontal" action="mj" method="POST" accept-charset="UTF-8">
                            <input type="submit" class="btn btn-warning" value="Refuser" />
                            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
                            <input type="hidden" name="action" value="REJECT" />
                            <input type="hidden" name="idPerso" value="${perso.id}" />
                        </form>
                    </c:forEach>
                </center>
            </div>
        </div>
        <br/>
    </div>
    <div class="panel-group col-xs-12 col-md-6 col-lg-4">
        <div class="panel panel-default">
            <div class="panel-heading"><center><h2>Validation d'épisode</h2></center>
            </div>
            <div class="panel-body">
                ${error}
                <center>
                    <c:forEach items="${demandesEpisode}" var="ep">
                        ${perso.nomPerso} - <a href='personnage?action=VOIREP&idPerso=${perso.id}'>voir</a> 
                        <br/>
                        <form class="form-horizontal" action="mj"  method="POST" accept-charset="UTF-8">
                            <input type="submit" class="btn btn-primary" value="Accepter" />
                            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
                            <input type="hidden" class="btn btn-primary" name="action" value="ACCEPTEP" />
                            <input type="hidden" name="idPerso" value="${perso.id}" />
                        </form>
                        <br/>
                        <form class="form-horizontal" action="mj" method="POST" accept-charset="UTF-8">
                            <input type="submit" class="btn btn-warning" value="Refuser" />
                            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
                            <input type="hidden" name="action" value="REJECTEP" />
                            <input type="hidden" name="idPerso" value="${perso.id}" />
                        </form>
                    </c:forEach>

                </center>
            </div>
        </div>
        <br/>
    </div>
</div>


<jsp:include page="../include/foot.jsp" />
