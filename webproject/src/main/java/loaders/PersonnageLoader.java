package loaders;

import dao.DAOException;
import dao.JoueurDAO;
import dao.PartieDAO;
import dao.UniversDAO;
import model.JoueurModel;
import model.PartieModel;
import model.PersonnageModel;
import model.UniversModel;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by william on 05/04/16.
 */
public class PersonnageLoader extends AbstractLoader<PersonnageModel> {
    public Set<PersonnageModel> get(UniversModel univers) {
        if (!isLoaded())
            try {
                setObjectSet(((UniversDAO)UniversModel.getDAO()).getPersonnages(univers));
        } catch (DAOException ex) {
            Logger.getLogger(PersonnageLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getObjectSet();
    }

    public Set<PersonnageModel> get(PartieModel partie) {
        if (!isLoaded())
            try {
                setObjectSet(((PartieDAO)PartieModel.getDAO()).getPersonnages(partie));
        } catch (DAOException ex) {
            Logger.getLogger(PersonnageLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getObjectSet();
    }

    public Set<PersonnageModel> getPersonnagesOwned(JoueurModel joueur) {
        if (!isLoaded())
            try {
                setObjectSet(((JoueurDAO)JoueurModel.getDAO()).getPersonnagesOwned(joueur));
        } catch (DAOException ex) {
            Logger.getLogger(PersonnageLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getObjectSet();
    }

    public Set<PersonnageModel> getPersonnagesManaged(JoueurModel joueur) {
        if (!isLoaded())
            try {
                setObjectSet(((JoueurDAO)JoueurModel.getDAO()).getPersonnagesManaged(joueur));
        } catch (DAOException ex) {
            Logger.getLogger(PersonnageLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getObjectSet();
    }
}
