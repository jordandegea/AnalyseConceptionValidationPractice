<jsp:include page="../include/head.jsp" >
    <jsp:param name="title" value="Personnage ${personnage.nomPerso}"/>
</jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<h2><center>Personnage ${personnage.nomPerso}</center></h2>
<div class="row">
    <div class="panel-group col-xd-12 col-lg-6 col-lg-offset-3">
        <div class="panel panel-default">
            <div class="panel-heading"><center><img class="img-circle" src="${personnage.portrait}" alt="${personnage.portrait}"/></center>
            </div>
            <div class="panel-body">
                <div class="form-horizontal">
                    <center>
                        <label>
                            Nom : 
                        </label>
                        ${personnage.nomPerso} 
                </div>
                </center>
                <div class="form-horizontal">
                    <center>
                        <label>
                            Date de naissance : 
                        </label>
                        ${personnage.dateNaiss}
                    </center>
                </div>
                <div class="form-horizontal">
                    <center>
                        <label>
                            Profession : 
                        </label>
                        ${personnage.profession}
                    </center>
                </div>
                <div class="form-horizontal">
                    <center>
                        <label>
                            Univers : 
                        </label>
                        ${personnage.univers}
                    </center>
                    <div class="form-horizontal">
                        <center>
                            <label>
                                MJ : 
                            </label>
                            <c:choose>
                                <c:when test="${personnage.MJ != null}">
                                    ${personnage.MJ.login} <c:if test="${personnage.demandeMJ}"><i>(en attente de validation)</i></c:if> 
                                    <c:choose>
                                        <c:when test="${peutchangerdemj != null}">
                                            <form action="personnage" method="POST" accept-charset="UTF-8">
                                                <input type="submit" onclick="return confirm('Êtes-vous sûr de vouloir quitter ce MJ?')" value="Quitter ce MJ" />
                                                <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
                                                <input type="hidden" name="action" value="LEAVEMJ" />
                                                <input type="hidden" name="idPerso" value="${personnage.id}" />
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <br />
                                            Impossible de changer de MJ tant que vous êtes dans une partie en cours. 
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    Pas de MJ
                                    <form action="personnage" method="GET" accept-charset="UTF-8">
                                        <input type="submit" class="btn btn-primary btn-block" value="Proposer à un MJ" />
                                        <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
                                        <input type="hidden" name="action" value="FINDMJ" />
                                        <input type="hidden" name="idPerso" value="${personnage.id}" />
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </center>
                    </div>
                    <hr/>
                    <div class="form-horizontal">
                        <center>
                            <label>
                                Biographie Initiale
                            </label>

                            <i>
                                ${personnage.biographie.getParagraphesBiographieAll()} 
                                <hr/>
                                <c:forEach items="${personnage.biographie.getTransition()}" var="ep">
                                    <br/>
                                    <u>Episode du ${ep.getDate()}  </u> <br/>
                                        <c:forEach items="${ep.getParagraphes()}" var="p">
                                            ${p.contenu} 

                                    </c:forEach>
                                </c:forEach>
                            </i>
                        </center>
                        <hr/>
                        <center>
                            <h2>Parties</h2>
                            <c:forEach items="${personnage.parties}" var="partie">
                                <label>${partie} - <a href='partie?action=SHOW&idPartie=${partie.id}'>voir</a></label>
                            </center>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Ici saisie des épisodes -->
<form action="personnage" method="GET" accept-charset="UTF-8">

    <h3> Nouvel Episode </h3> 
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

    <input type="submit" class="btn btn-primary btn-block" value="Valider  l'épisode de transition" />
    <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
    <input type="hidden" name="action" value="NEWTRANSI" />
    <input type="hidden" name="idPerso" value="${personnage.id}" />
</form>
<br/>
<form action="personnage" method="GET" accept-charset="UTF-8">
    <input type="submit" class="btn btn-warning btn-block" value="Transférer Personnage" />
    
    <input type="hidden" name="action" value="TRANSFER" />
    <input type="hidden" name="idPerso" value="${personnage.id}" />
</form>

</form>
<br/>
<form action="joueur" method="GET" accept-charset="UTF-8">
    <input type="submit" class="btn btn-primary btn-block" value="Retour" />
    <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
    <input type="hidden" name="action" value="SHOW" />
</form>


<script type="text/javascript" src="../public/js/formularise.js">
</script>

<script type="text/javascript">
    var nbParagraphes = 1;

    function addTextArea() {
        nbParagraphes = nbParagraphes + 1;
        $("#ajout").append('<label class="col-lg-2 control-label"></label><div class="form-group"><textarea class="form-control" name="paragraphe' + nbParagraphes + '" required></textarea><label class="col-lg-2 control-label"></label><label><input type="checkbox" id="isPrivate' + nbParagraphes + '" name="isPrivate' + nbParagraphes + '" value="isPrivate' + nbParagraphes + '"> Paragraphe Privé</label></div>');
    }
</script>

<jsp:include page="../include/foot.jsp" />
