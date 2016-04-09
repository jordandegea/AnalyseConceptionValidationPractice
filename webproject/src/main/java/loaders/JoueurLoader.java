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
    
    public JoueurModel get(PartieModel partie) {
        if (!isLoaded())
            try {
                setObject(((PartieDAO)PartieModel.getDAO()).getJoueur(partie));
        } catch (DAOException ex) {
            Logger.getLogger(JoueurLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getObject();
    }

    public JoueurModel getMJ(PersonnageModel personnage) {
        if (!isLoaded())
            try {
                setObject(((PersonnageDAO)PersonnageModel.getDAO()).getMJ(personnage));
        } catch (DAOException ex) {
            Logger.getLogger(JoueurLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getObject();
    }

    public JoueurModel getOwner(PersonnageModel personnage) {
        if (!isLoaded())
            try {
                setObject(((PersonnageDAO)PersonnageModel.getDAO()).getOwner(personnage));
        } catch (DAOException ex) {
            Logger.getLogger(JoueurLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getObject();
    }
}
