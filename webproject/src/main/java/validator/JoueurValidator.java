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

/**
 *
 * @author william
 */
public class JoueurValidator extends AbstractValidator {
    final private static JoueurValidator instance = new JoueurValidator();
    
    public static JoueurValidator instance() {
        return instance;
    }
    
    private JoueurValidator(){};
    
    public void loginValidate(JoueurModel joueur, String password) throws ValidatorException {
        if (joueur == null)
            throw new ValidatorException("Login inconnu");
        if (!joueur.getPassword().equals(password))
            throw new ValidatorException("Combinaison login/mot de passe incorrecte");
    }
    
    public void createValidate(JoueurModel joueur, String pwConfirm) throws DAOException, ValidatorException {
        this.joueurValidate(joueur);
        if (!joueur.getPassword().equals(pwConfirm))
            throw new ValidatorException("Les mots de passe ne correspondent pas");
        
        JoueurModel j = JoueurDAO.instance().get(joueur.getLogin());
        if (j != null)
            throw new ValidatorException("Ce login existe déjà");
    }
    
    private void joueurValidate(JoueurModel j) throws ValidatorException {
        if (j.getLogin() == null || j.getLogin().length() == 0)
            throw new ValidatorException("Le login ne peut être vide");
        if (j.getPassword() == null)
            throw new ValidatorException("Le mot de passe ne peut être vide");
        if (j.getPassword().length() < 6)
            throw new ValidatorException("Le mot de passe doit faire 6 caractères ou plus");
        if (j.getEmail() == null || j.getEmail().length() == 0)
            throw new ValidatorException("L'email ne peut être vide");
    }
}
