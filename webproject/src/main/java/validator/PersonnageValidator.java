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
import model.PersonnageModel;

/**
 *
 * @author william
 */
public class PersonnageValidator extends AbstractValidator<PersonnageModel> {
    final private static PersonnageValidator instance = new PersonnageValidator();
    
    public static PersonnageValidator instance() {
        return instance;
    }
    
    private PersonnageValidator(){};
    
    public void askMJValidate(PersonnageModel perso, JoueurModel mj) throws DAOException, ValidatorException {
        if (perso.getMJ() != null)
            throw new ValidatorException("Le personnage a déjà un MJ");
        if (perso.getOwner().equals(mj))
            throw new ValidatorException("Un MJ ne peut jouer un personnage");
    }

    @Override
    protected void modelValidate(PersonnageModel object) throws DAOException, ValidatorException {
        if (object.getNomPerso().length() > 128)
            throw new ValidatorException("Le nom du personnage doit faire moins de 128 caractères");
        if (object.getDateNaiss().length() > 128)
            throw new ValidatorException("La date de naissance du personnage doit faire moins de 128 caractères");
        if (object.getProfession().length() > 256)
            throw new ValidatorException("La profession du personnage doit faire moins de 256 caractères");
        if (object.getPortrait().length() > 512)
            throw new ValidatorException("Le portrait du personnage doit faire moins de 512 caractères");
        
        BiographieValidator.instance().createValidate(object.getBiographie());
    }
    
    
}
