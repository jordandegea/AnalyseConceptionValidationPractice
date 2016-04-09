/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import dao.DAOException;
import model.AbstractBaseModel;

/**
 *
 * @author william
 */
public abstract class AbstractValidator<T extends AbstractBaseModel> {
    public void createValidate(T object) throws DAOException, ValidatorException {
        modelValidate(object);
    }
    public void editValidate(T object) throws DAOException, ValidatorException {
        modelValidate(object);
    }
    public void deleteValidate(T object) throws DAOException, ValidatorException {
        modelValidate(object);
    }
    
    protected abstract void modelValidate(T object) throws DAOException, ValidatorException;
}
