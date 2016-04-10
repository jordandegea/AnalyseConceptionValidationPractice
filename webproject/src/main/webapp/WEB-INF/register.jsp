
<jsp:include page="include/head.jsp" >
    <jsp:param name="title" value="Inscription"/>
</jsp:include>


<div class="row">
    <div id="content" class="col-md-12">
        <h2>Inscription</h2>

        <div class="col-xd-12 col-lg-6">

            ${error}
            <form class="form-horizontal" action="register" method="POST" accept-charset="UTF-8">

                <div class="form-group"> 
                    <label class="col-xd-12 col-lg-4 control-label">Login :</label>
                    <div class="col-xs-12 col-lg-8">
                        <input class="form-control" type="text" name="login" value="${joueur.login}" requested />
                    </div>
                </div>
                <div class="form-group"> 
                    <label class="col-xd-12 col-lg-4 control-label">Mot de passe :</label>
                    <div class="col-xs-12 col-lg-8">
                        <input class="form-control" type="password" name="password" requested />
                    </div>
                </div>
                <div class="form-group"> 
                    <label class="col-xd-12 col-lg-4 control-label">Confirmez mot de passe :</label>
                    <div class="col-xs-12 col-lg-8">
                        <input class="form-control" type="password" name="confirm" requested />
                    </div>
                </div>
                <div class="form-group"> 
                    <label class="col-xd-12 col-lg-4 control-label">Email :</label>
                    <div class="col-xs-12 col-lg-8">
                        <input class="form-control" type="email" name="email" value="${joueur.email}" requested />
                    </div>
                </div>

                <input class="btn btn-primary btn-block" type="submit" value="Valider" />
            </form>
        </div>
        <div class="col-xd-12 col-lg-6">
            <a class="btn btn-warning btn-block" href="login">Vous avez déjà un compte ?</a>
        </div>
    </div>    
</div>



<jsp:include page="include/foot.jsp" />
