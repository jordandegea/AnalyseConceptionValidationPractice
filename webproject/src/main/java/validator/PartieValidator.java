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
