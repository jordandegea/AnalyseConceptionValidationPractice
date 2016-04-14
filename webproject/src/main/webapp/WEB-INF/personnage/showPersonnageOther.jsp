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
                                </c:when>
                                <c:otherwise>
                                    Pas de MJ

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
                                <hr/>
                                <c:forEach items="${personnage.biographie.getBioInit()}" var="ep">
                                    <c:forEach items="${ep.getParagraphes()}" var="par">
                                        <c:if test="${!par.isSecret()}">
                                            <p> ${par.getContenu()} </p> </hr>
                                        </c:if>
                                    </c:forEach> 
                                </c:forEach> 
                                <c:forEach items="${personnage.biographie.getTransition()}" var="ep">
                                    <br/>
                                    <u>Episode du ${ep.getDate()}  </u> <br/>
                                        <c:forEach items="${ep.getParagraphes()}" var="p">
                                            ${p.contenu} 
                                        <br/>

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

<script type="text/javascript" src="../public/js/formularise.js">
</script>

<jsp:include page="../include/foot.jsp" />
