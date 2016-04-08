<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8" />
        <title>Login</title>
    </head>
    <body>

        <h2>Login</h2>

        ${error}
        <form action="" method="post" accept-charset="UTF-8">
            <label>Login :</label><input type="text" name="login" value="${joueur.login}" requested /><br/>
            <label>Mot de passe :</label><input type="password" name="password" requested /> <br/>
            
            <input type="submit" value="Valider" />
            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
            <input type="hidden" name="action" value="LOGIN" />
        </form>
        <a href="joueur?action=NEW">S'enregistrer</a>
    </body>
</html>
