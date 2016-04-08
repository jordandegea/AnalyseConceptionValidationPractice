/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import model.AbstractBaseModel;

/**
 *
 * @author william
 */
public abstract class AbstractValidator {
    public void createValidate(AbstractBaseModel object) throws ValidatorException {
    }
    public void editValidate(AbstractBaseModel object) throws ValidatorException {
    }
    public void deleteValidate(AbstractBaseModel object) throws ValidatorException {
    }
}
