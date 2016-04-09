/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import dao.DAOException;
import dao.JoueurDAO;
import java.util.Set;
import model.AbstractBaseModel;
import model.BiographieModel;
import model.EpisodeModel;
import model.JoueurModel;
import model.ParagrapheModel;
import model.PersonnageModel;

/**
 *
 * @author william
 */
public class EpisodeValidator extends AbstractValidator<EpisodeModel> {
    final private static EpisodeValidator instance = new EpisodeValidator();
    
    public static EpisodeValidator instance() {
        return instance;
    }
    
    private EpisodeValidator(){};

    @Override
    protected void modelValidate(EpisodeModel object) throws DAOException, ValidatorException {
        for (ParagrapheModel p : object.getParagraphes()) {
            ParagrapheValidator.instance().createValidate(p);
        }
    }
}
