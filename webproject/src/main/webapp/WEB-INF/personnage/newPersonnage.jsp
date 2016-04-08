<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8" />
        <title>Créer personnage</title>
    </head>
    <body>

        <h2>Créer personnage</h2>

        <form action="" method="post" accept-charset="UTF-8">
            <label>Nom :</label><input type="text" name="nomPerso" value="${personnage.nomPerso}" requested /><br/>
            <label>Date de naissance :</label><input type="text" name="dateNaiss" value="${personnage.dateNaiss}" requested /><br/>
            <label>Profession :</label><input type="text" name="profession" value="${personnage.profession}" requested /><br/>
            <label>Portrait :</label><input type="text" name="portrait" value="remplacer par upload" requested /><br/>
            <label>Univers :</label><input type="text" name="univers" value="remplacer par select" requested /><br/>
            
            <label>Biographie initiale :</label><textarea name="bioInitiale"></textarea><br/>
            
            <input type="submit" value="Enregistrer" />
            <!-- Pour indiquer au contrôleur quelle action faire, on utilise un champ caché -->
            <input type="hidden" name="action" value="LOGOUT" />
        </form>
    </body>
</html>
