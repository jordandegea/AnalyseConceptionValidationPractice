/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import model.ParagrapheModel;

/**
 *
 * @author william
 */
public class ParagrapheValidator extends AbstractValidator<ParagrapheModel> {
    final private static ParagrapheValidator instance = new ParagrapheValidator();
    
    public static ParagrapheValidator instance() {
        return instance;
    }
    
    private ParagrapheValidator(){};

    @Override
    protected void modelValidate(ParagrapheModel object) throws ValidatorException {
        if (object.getContenu().length() > 2048) 
            throw new ValidatorException("Un paragraphe ne pas faire plus de 2048 caractères");
    }
}
