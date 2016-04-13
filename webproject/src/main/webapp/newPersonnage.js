/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

nbParagraphes=0;

function addTextArea(){
    alert("ntm");
        nbParagraphes=nbParagraphes+1;
        $("#ajout").append('<label class="col-lg-2 control-label"></label><div class="form-group"><textarea class="form-control" name="paragraphe'+nbParagraphes+'" required></textarea><label class="col-lg-2 control-label"></label><label><input type="checkbox" id="isPrivate'+nbParagraphes+'" name="isPrivate'+nbParagraphes+'" value="isPrivate'+nbParagraphes+'"> Paragraphe Privé</label></div>');
        
}
