
<jsp:include page="include/head.jsp" >
    <jsp:param name="title" value="Login"/>
</jsp:include>


<div class="row">
    <div id="content" class="col-md-12">
        <h2>Login</h2>

        <div class="col-xd-12 col-lg-6">
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

                <input class="btn btn-primary btn-block" type="submit" value="Valider" />
                <!-- Pour indiquer au controleur quelle action faire, on utilise un champ caché -->
                <input type="hidden" name="action" value="LOGIN" />
            </form>
        </div>

        <div class="col-xd-12 col-lg-6">
            <a class="btn btn-warning btn-block" href="register">S'enregistrer</a>
        </div>

    </div>
</div>


<jsp:include page="include/foot.jsp" />
