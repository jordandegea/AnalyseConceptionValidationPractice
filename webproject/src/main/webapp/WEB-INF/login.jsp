
<jsp:include page="include/head.jsp" >
    <jsp:param name="title" value="Login"/>
</jsp:include>

<div class="row">
    <div id="content" class="col-md-12">
        <div class="panel-group col-xd-12 col-lg-6 col-lg-offset-3">
            <div class="panel panel-default">
                <div class="panel-heading">Login</div>
                <div class="panel-body">
                    ${error}
                    <form class="form-horizontal" action="" method="post" accept-charset="UTF-8">

                        <div class="form-group">
                            <label class="col-xd-12 col-lg-4 control-label">Login :</label>
                            <div class="col-xs-12 col-lg-8">
                                <input class="form-control" type="text" name="login" value="${joueur.login}" requested />
                            </div>
                        </div>
                        <div class="form-group"> 
                            <label class="col-xs-12 col-lg-4 control-label">Mot de passe :</label>
                            <div class="col-xs-12 col-lg-8">
                                <input class="form-control" type="password" name="password" requested /> 
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-xd-12 col-lg-4 col-lg-offset-4">
                                <input class="btn btn-primary btn-block" type="submit" value="Valider" />
                                <!-- Pour indiquer au controleur quelle action faire, on utilise un champ caché -->
                                <input type="hidden" name="action" value="LOGIN" />
                            </div>
                        </div>
                    </form>

                    <form class="form-horizontal" action="register" method="GET" accept-charset="UTF-8">
                        <div class="form-group"> 
                            <div class="col-xd-12 col-lg-4 col-lg-offset-4">
                                <input type="submit" class="btn btn-warning btn-block" value="S'enregistrer"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>


    </div>
</div>


<jsp:include page="include/foot.jsp" />
