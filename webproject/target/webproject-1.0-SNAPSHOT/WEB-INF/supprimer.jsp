<%-- 
    Document   : delete
    Created on : 1 mars 2016, 10:30:10
    Author     : duclotw
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Supprimer</h1>
        Êtes-vous sûr de vouloir supprimer ${ouvrage} ?
        <form action="controleur?action=supprimer&id=${ouvrage.id}">
            <input type="submit" value="Confirmer" />
        </form>
    </body>
</html>
