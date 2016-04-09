package model;

import dao.DAOException;
import dao.JoueurDAO;
import loaders.PartieLoader;
import loaders.PersonnageLoader;

import java.util.Set;

/**
 * Created by william on 05/04/16.
 */
public class JoueurModel extends AbstractBaseModel {
    private String login;
    private String password;
    private String email;
    private PersonnageLoader personnagesOwned;
    private PersonnageLoader personnagesManaged;
    private PartieLoader partiesManaged;

    public JoueurModel(int id, String login, String password, String email) {
        super(id);
        this.login = login;
        this.password = password;
        this.email = email;
        personnagesManaged = new PersonnageLoader();
        personnagesOwned = new PersonnageLoader();
        partiesManaged = new PartieLoader();
    }
    
    public JoueurModel(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        personnagesManaged = new PersonnageLoader();
        personnagesOwned = new PersonnageLoader();
        partiesManaged = new PartieLoader();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<PersonnageModel> getPersonnagesOwned() throws DAOException {
        return personnagesOwned.getPersonnagesOwned(this);
    }

    public Set<PersonnageModel> getPersonnagesManaged() throws DAOException {
        return personnagesManaged.getPersonnagesManaged(this);
    }

    public Set<PartieModel> getPartiesManaged() {
        return partiesManaged.get(this);
    }
    
    public static JoueurDAO getDAO() {
        return JoueurDAO.instance();
    }
}
