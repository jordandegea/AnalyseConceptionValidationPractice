package loaders;

import dao.DAOException;
import dao.JoueurDAO;
import dao.PartieDAO;
import dao.PersonnageDAO;
import dao.ResumeDAO;
import model.*;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by william on 05/04/16.
 */
public class PartieLoader extends AbstractLoader<PartieModel> {

    public Set<PartieModel> get(PersonnageModel personnage) throws DAOException {
        if (!isLoaded()) {
            setObjectSet(PersonnageDAO.instance().getParties(personnage));
        }

        return getObjectSet();
    }

    public Set<PartieModel> get(JoueurModel joueur) throws DAOException {
        if (!isLoaded()) {
            setObjectSet(JoueurDAO.instance().getParties(joueur));
        }

        return getObjectSet();
    }

    public PartieModel getEnCours(PersonnageModel personnage) throws DAOException {
        if (!isLoaded()) {
            setObject(PersonnageDAO.instance().getPartieEnCours(personnage));
        }

        return getObject();
    }

    public PartieModel get(ResumeModel resume) throws DAOException {
        if (!isLoaded()) {
            setObject(ResumeDAO.instance().getPartie(resume));
        }

        return getObject();
    }
}
