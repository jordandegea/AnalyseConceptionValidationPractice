<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/head.jsp" >
    <jsp:param name="title" value="Résumé de la partie"/>
</jsp:include>

<h2>Entrer le résumé de la partie</h2>

${error}
<form action="resumePartie" method="post" accept-charset="UTF-8">
    <input type="date" name="date">
    <input type="button" name="ajout" id="ajout" value="Paragraphe +" onclick="addTextArea()"/>  
    
    <div id="ajout"></div>
    
    <input class="btn btn-warning btn-block" type="submit" value="Enregistrer" />
    <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
    <input  type="hidden" name="action" value="CREATE" />
</form>

<form action="partie" method="GET" accept-charset="UTF-8">
    <input class="fbtn btn-warning btn-block" type="submit" value="Retour" />
    <!-- Pour indiquer au contr?leur quelle action faire, on utilise un champ cach? -->
    <input type="hidden" name="action" value="SHOW" />
    <input type="hidden" name="idPartie" value="${idPartie}" />
</form>

<script type="text/javascript">
    var i = 0;
function addTextArea(){
        var input = document.createElement("textarea");
        input.id = "textareaid" + i;
        input.name = "textareaname" + i;
        var saut = document.createElement("br");
        var saut2 = document.createElement("br");
        var label = document.createElement('label')
        label.htmlFor = "checkid";
        label.appendChild(document.createTextNode('Paragraphe privé'));;
        var ajout = document.getElementById("ajout");
        ajout.parentNode.insertBefore(saut,ajout);
        ajout.parentNode.insertBefore(input,ajout);
        ajout.parentNode.insertBefore(saut2,ajout);
        ajout.parentNode.insertBefore(label,ajout);        
        ajout.parentNode.insertBefore(saut,ajout);
        i = i+1;
}
</script>
<jsp:include page="../include/foot.jsp" />

