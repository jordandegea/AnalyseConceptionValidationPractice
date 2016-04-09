/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import dao.DAOException;
import dao.JoueurDAO;
import model.AbstractBaseModel;
import model.JoueurModel;
import model.PartieModel;
import model.PersonnageModel;

/**
 *
 * @author william
 */
public class PartieValidator extends AbstractValidator<PartieModel> {
    final private static PartieValidator instance = new PartieValidator();
    
    public static PartieValidator instance() {
        return instance;
    }
    
    private PartieValidator(){};
    
    public void enrollValidate(PartieModel partie, PersonnageModel perso) throws ValidatorException, DAOException {
        if (partie.isPartieFinie())
            throw new ValidatorException("Impossible d'enrôler un personnage dans une partie terminée");
        if (partie.getMJ().getId() == perso.getOwner().getId())
            throw new ValidatorException("Le MJ ne peut faire jouer un personnage");
        if (partie.getUnivers().getId() == perso.getUnivers().getId())
            throw new ValidatorException("Le personnage doit être dans l'univers de la partie");
        if (perso.getPartieEnCours() != null)
            throw new ValidatorException("Le personnage est déjà engagé dans une partie");
        if (partie.getMJ().getId() == perso.getMJ().getId())
            throw new ValidatorException("Le MJ du personnage doit être le MJ de la partie");
    }

    @Override
    protected void modelValidate(PartieModel object) throws DAOException, ValidatorException {
        if (object.getTitrePartie().length() > 256)
            throw new ValidatorException("Le titre doit faire moins de 256 caractères");
        if (object.getResumeInitial().length() > 2048)
            throw new ValidatorException("Le résumé de la situation initiale doit faire moins de 2048 caractères");
        if (object.getDate().length() > 128)
            throw new ValidatorException("La date doit faire moins de 128 caractères");
        if (object.getLieu().length() > 128)
            throw new ValidatorException("Le lieu doit faire moins de 128 caractères");
        if (object.getMJ() == null)
            throw new ValidatorException("La partie n'a pas de MJ");
        if (object.getUnivers() == null)
            throw new ValidatorException("La partie n'a pas d'univers");
    }
}
