<jsp:include page="../include/head.jsp" >
    <jsp:param name="title" value="Personnage ${personnage.nomPerso}"/>
</jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br/>
<form action="joueur" method="GET" accept-charset="UTF-8">
    <input type="submit"  class="btn btn-primary" value="Retour" />
    <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
    <input type="hidden" name="action" value="SHOW" />
</form>

<h2><center>Personnage ${personnage.nomPerso}</center></h2>

<div class="row">
    <div class="panel-group col-xd-12 col-lg-6 col-lg-offset-3">
        <div class="panel panel-default">
            <div class="panel-heading"><center><img width="128px" height="128px" class="img-circle" src="${personnage.portrait}" alt="${personnage.portrait}"/></center>
            </div>
            <div class="panel-body">
                <div class="form-horizontal">
                    <label>
                        Nom : 
                    </label>
                    ${personnage.nomPerso} 
                </div>
                <div class="form-horizontal">
                    <label>
                        Date de naissance : 
                    </label>
                    ${personnage.dateNaiss}
                </div>
                <div class="form-horizontal">
                    <label>
                        Profession : 
                    </label>
                    ${personnage.profession}
                </div>
                <div class="form-horizontal">
                    <label>
                        Univers : 
                    </label>
                    ${personnage.univers}
                    <div class="form-horizontal">
                        <label>
                            MJ : 
                        </label>
                        <c:choose>
                            <c:when test="${personnage.MJ != null}">
                                ${personnage.MJ.login} <c:if test="${personnage.demandeMJ}"><i>(en attente de validation)</i></c:if> 
                                <c:choose>
                                    <c:when test="${peutchangerdemj != null}">
                                        <form action="personnage" method="POST" accept-charset="UTF-8">
                                            <input class="btn btn-warning" type="submit" onclick="return confirm('Êtes-vous sûr de vouloir quitter ce MJ?')" value="Quitter ce MJ" />
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
                                    <input type="submit" class="btn btn-primary" value="Proposer à un MJ" />
                                    <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
                                    <input type="hidden" name="action" value="FINDMJ" />
                                    <input type="hidden" name="idPerso" value="${personnage.id}" />
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <hr/>
                    <div class="form-horizontal">
                        <label>
                            Biographie Initiale
                        </label>

                        <i>
                            <c:forEach items="${personnage.biographie.getBioInit()}" var="ep">
                                <c:forEach items="${ep.getParagraphes()}" var="par">
                                    <c:if test="${par.isSecret()}">
                                        <div class="text-warning">
                                            <p> ${par.getContenu()} </p> </hr>
                                        </div>
                                        <form action="personnage" method="GET" accept-charset="UTF-8">
                                            <input type="submit" class="btn btn-primary"   value="Révéler" paragraphe />
                                            <input type="hidden" name="action" value="REVEAL" />
                                            <input type="hidden" name="idPar" value=" + pm.getId()  +" /> 
                                        </form> 
                                        <br/>
                                    </c:if>  
                                    <c:if test="${!par.isSecret()}">
                                        <p> ${par.getContenu()} </p> </hr>

                                    </c:if>

                                </c:forEach> 
                            </c:forEach>                                 <hr/>
                            <c:forEach items="${personnage.biographie.getTransition()}" var="ep">
                                <br/>
                                <u>Episode du ${ep.getDate()}  </u> <br/>
                                    <c:forEach items="${ep.getParagraphes()}" var="p">
                                        <c:if test="${p.isSecret()}">
                                        <div class="text-warning">
                                            <p> ${p.getContenu()} </p> </hr>
                                        </div>
                                        <form action="personnage" method="GET" accept-charset="UTF-8">
                                            <input type="submit" class="btn btn-primary"   value="Révéler" paragraphe />
                                            <input type="hidden" name="action" value="REVEAL" />
                                            <input type="hidden" name="idPar" value=" + pm.getId()  +" /> 
                                        </form> 
                                        <br/>
                                    </c:if>
                                    <c:if test="${!p.isSecret()}">
                                        ${p.contenu}
                                    </c:if>
                                    <br/>

                                </c:forEach>
                            </c:forEach>
                        </i>
                        <hr/>
                        <h2>Parties</h2>
                        <c:forEach items="${personnage.parties}" var="partie">
                            <label>${partie} - <a href='partie?action=SHOW&idPartie=${partie.id}'>voir</a></label>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<br/>
<div class="form-horizontal">

    <form action="personnage" method="GET" accept-charset="UTF-8">
        <input type="submit" class="btn btn-primary" value="Saisir un nouvel episode" />
        <input type="hidden" name="action" value="NEWEP" />
        <input type="hidden" name="idPerso" value="${personnage.id}" />
    </form>
    <br/>

    <form action="personnage" method="GET" accept-charset="UTF-8">
        <input type="submit" class="btn btn-warning" value="Transférer Personnage" />

        <input type="hidden" name="action" value="TRANSFER" />
        <input type="hidden" name="idPerso" value="${personnage.id}" />
    </form>

</form>
<br/>
</div>

<script type="text/javascript" src="../public/js/formularise.js">
</script>



<jsp:include page="../include/foot.jsp" />
