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

    public Set<PersonnageModel> get(UniversModel univers) throws DAOException {
        if (!isLoaded()) {
            setObjectSet(((UniversDAO) UniversModel.getDAO()).getPersonnages(univers));
        }

        return getObjectSet();
    }

    public Set<PersonnageModel> get(PartieModel partie) throws DAOException {
        if (!isLoaded()) {
            setObjectSet(((PartieDAO) PartieModel.getDAO()).getPersonnages(partie));
        }

        return getObjectSet();
    }

    public Set<PersonnageModel> getPersonnagesOwned(JoueurModel joueur) throws DAOException {
        if (!isLoaded()) {
            setObjectSet(JoueurDAO.instance().getPersonnagesOwned(joueur));
        }

        return getObjectSet();
    }

    public Set<PersonnageModel> getPersonnagesManaged(JoueurModel joueur) throws DAOException {
        if (!isLoaded()) {
            setObjectSet(((JoueurDAO) JoueurModel.getDAO()).getPersonnagesManaged(joueur));
        }

        return getObjectSet();
    }
}
