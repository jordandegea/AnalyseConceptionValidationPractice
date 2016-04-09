
<jsp:include page="include/head.jsp" >
    <jsp:param name="title" value="Login"/>
</jsp:include>


<div class="row">
    <div id="content" class="col-md-12">
        <h2>Login</h2>

        ${error}
        <form action="" method="post" accept-charset="UTF-8">
            <label>Login :</label><input type="text" name="login" value="${joueur.login}" requested /><br/>
            <label>Mot de passe :</label><input type="password" name="password" requested /> <br/>

            <input type="submit" value="Valider" />
            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
            <input type="hidden" name="action" value="LOGIN" />
        </form>
        <a href="register">S'enregistrer</a>
        
    </div>
</div>


<jsp:include page="include/foot.jsp" />
