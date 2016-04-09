
<jsp:include page="include/head.jsp" >
    <jsp:param name="title" value="Inscription"/>
</jsp:include>


<h2>Inscription</h2>

${error}
<form action="register" method="POST" accept-charset="UTF-8">
    <label>Login :</label><input type="text" name="login" value="${joueur.login}" requested /><br/>
    <label>Mot de passe :</label><input type="password" name="password" requested /> <br/>
    <label>Confirmez mot de passe :</label><input type="password" name="confirm" requested /> <br/>
    <label>Email :</label><input type="email" name="email" value="${joueur.email}" requested /> <br/>

    <input type="submit" value="Valider" />
</form>
<a href="login">Vous avez déjà un compte ?</a>



<jsp:include page="include/foot.jsp" />
