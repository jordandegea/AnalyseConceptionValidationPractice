package loaders;

import dao.DAOException;
import dao.PartieDAO;
import dao.PersonnageDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.JoueurModel;
import model.PartieModel;
import model.PersonnageModel;

/**
 * Created by william on 05/04/16.
 */
public class JoueurLoader extends AbstractLoader<JoueurModel> {

    public JoueurLoader(JoueurModel j) {
        super(j);
    }

    public JoueurLoader() {
        super();
    }

    public JoueurModel get(PartieModel partie) throws DAOException {
        if (!isLoaded()) {
            setObject(PartieDAO.instance().getJoueur(partie));
        }

        return getObject();
    }

    public JoueurModel getMJ(PersonnageModel personnage) throws DAOException {
        if (!isLoaded()) {
            setObject(PersonnageDAO.instance().getMJ(personnage));
        }

        return getObject();
    }

    public JoueurModel getOwner(PersonnageModel personnage) throws DAOException {
        if (!isLoaded()) {
            setObject(PersonnageDAO.instance().getOwner(personnage));
        }

        return getObject();
    }
}
