/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import dao.DAOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import model.EpisodeModel;
import model.ParagrapheModel;

/**
 *
 * @author william
 */
public class EpisodeValidator extends AbstractValidator<EpisodeModel> {

    final private static EpisodeValidator instance = new EpisodeValidator();

    public static EpisodeValidator instance() {
        return instance;
    }

    private EpisodeValidator() {
    }

    ;

    @Override
    protected void modelValidate(EpisodeModel object) throws DAOException, ValidatorException {
        for (ParagrapheModel p : object.getParagraphes()) {
            ParagrapheValidator.instance().createValidate(p);
        }
    }

    public void dateValidate(String date) throws ValidatorException {
        if (date.length() == 0)
            throw new ValidatorException("La date doit être précisée");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date.trim());
        } catch (ParseException pe) {
            throw new ValidatorException("Le format de la date n'est pas valide");
        }
    }
}
