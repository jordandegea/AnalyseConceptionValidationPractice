/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import dao.DAOException;
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

    public void askJoueurTransferValidate(PersonnageModel perso, JoueurModel j) throws DAOException, ValidatorException {
        if (perso.getMJ().equals(j))
            throw new ValidatorException("On ne pas transférer le personnage au MJ");
        if (perso.getOwner().equals(j))
            throw new ValidatorException("Un joueur ne pas se transférer un personnage à lui même");
    }
    @Override
    protected void modelValidate(PersonnageModel object) throws DAOException, ValidatorException {
        if (object.getNomPerso().length() > 128)
            throw new ValidatorException("Le nom du personnage doit faire moins de 128 caractères");
        if (object.getNomPerso().length() == 0)
            throw new ValidatorException("Le nom du personnage ne peut être vide");
        if (object.getDateNaiss().length() > 128)
            throw new ValidatorException("La date de naissance du personnage doit faire moins de 128 caractères");
        if (object.getDateNaiss().length() == 0)
            throw new ValidatorException("La date de naissance du personnage ne peut être vide");
        if (object.getProfession().length() > 256)
            throw new ValidatorException("La profession du personnage doit faire moins de 256 caractères");
        if (object.getProfession().length() == 0)
            throw new ValidatorException("La profession du personnage ne peut être vide");
        if (object.getPortrait().length() > 512)
            throw new ValidatorException("Le portrait du personnage doit faire moins de 512 caractères");
        
        BiographieValidator.instance().createValidate(object.getBiographie());
    }
    
    
}
