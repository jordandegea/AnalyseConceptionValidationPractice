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
    public Set<PartieModel> get(PersonnageModel personnage) {
        if (!isLoaded())
            try {
                setObjectSet(((PersonnageDAO)PersonnageModel.getDAO()).getParties(personnage));
        } catch (DAOException ex) {
            Logger.getLogger(PartieLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getObjectSet();
    }

    public Set<PartieModel> get(JoueurModel joueur) {
        if (!isLoaded())
            try {
                setObjectSet(((JoueurDAO)JoueurModel.getDAO()).getParties(joueur));
        } catch (DAOException ex) {
            Logger.getLogger(PartieLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getObjectSet();
    }
    public PartieModel getEnCours(PersonnageModel personnage) {
        if (!isLoaded())
            try {
                setObject(((PersonnageDAO)PersonnageModel.getDAO()).getPartieEnCours(personnage));
        } catch (DAOException ex) {
            Logger.getLogger(PartieLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getObject();
    }

    public PartieModel get(ResumeModel resume) {
        if (!isLoaded())
            try {
                setObject(((ResumeDAO)ResumeModel.getDAO()).getPartie(resume));
        } catch (DAOException ex) {
            Logger.getLogger(PartieLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getObject();
    }
}
