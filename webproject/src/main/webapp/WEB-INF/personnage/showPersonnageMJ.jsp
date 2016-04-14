<jsp:include page="../include/head.jsp" >
    <jsp:param name="title" value="Personnage ${personnage.nomPerso}"/>
</jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br/>
<form action="joueur" method="GET" accept-charset="UTF-8">
    <input type="submit" class="btn btn-primary" value="Retour" />
    <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
    <input type="hidden" name="action" value="SHOW" />
</form>

<h2><center>Personnage ${personnage.nomPerso}</center></h2>
<div class="row">
    <div class="panel-group col-xd-12 col-lg-6 col-lg-offset-3">
        <div class="panel panel-default">
            <div class="panel-heading"><center><img  width="128px" height="128px"  class="img-circle" src="${personnage.portrait}" alt="${personnage.portrait}"/></center>
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
                                </c:when>
                                <c:otherwise>
                                    Pas de MJ

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
                                                <p> ${par.getContenu()} </p> 
                                            </div>
                                        </c:if>
                                        <c:if test="${!par.isSecret()}">
                                            <p> ${par.getContenu()} </p> 
                                        </c:if>
                                    </c:forEach> 
                                </c:forEach>                             <hr/>
                                <c:forEach items="${personnage.biographie.getTransition()}" var="ep">
                                    <br/>
                                    <u>Episode du ${ep.getDate()}  </u> <br/>
                                        <c:forEach items="${ep.getParagraphes()}" var="p">
                                            ${p.contenu} 
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
<!-- Ici saisie des épisodes -->


<script type="text/javascript" src="../public/js/formularise.js">
</script>

<jsp:include page="../include/foot.jsp" />
