<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8" />
        <title>Joueur</title>
    </head>
    <body>

        <h2>Joueur</h2>
        <ul>
            <li>${joueur.login}</li>
            <li>${joueur.email}</li>
        </ul>
        
        
        <form action="" method="post" accept-charset="UTF-8">
            <input type="submit" value="Déconnexion" />
            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
            <input type="hidden" name="action" value="LOGOUT" />
        </form>
        <form action="personnage" method="GET" accept-charset="UTF-8">
            <input type="submit" value="Créer personnage" />
            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
            <input type="hidden" name="action" value="NEW" />
        </form>
    </body>
</html>
