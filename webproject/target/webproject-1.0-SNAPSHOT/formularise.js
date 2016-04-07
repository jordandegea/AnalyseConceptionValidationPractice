var oldline;
var currline = null;

function formularise(elt, event, id, linkName) {
    annuler();
    event.preventDefault();
    
    currline = elt.parentNode.parentNode;
    if (linkName == 'modifier') {
        oldline = currline.innerHTML;
        currline.cells[0].innerHTML = "<input type='text' name='auteur' value='" + line.cells[0].innerHTML + "' />";
        currline.cells[1].innerHTML = "<input type='text' name='titre' value='" + line.cells[1].innerHTML + "' />";
        currline.cells[2].innerHTML = "<button type='submit'>confirmer</button>"
        currline.cells[3].innerHTML = "<button type='button' onclick='annuler();'>annuler</button>"
    } else if (linkName == 'annuler') {
        currline.innerHTML = oldline;
    } else {
        alert("Erreur");
    }
}

function annuler() {
    if (currline != null) {
        currline.innerHTML = oldline;
        currline = null;
    }
}