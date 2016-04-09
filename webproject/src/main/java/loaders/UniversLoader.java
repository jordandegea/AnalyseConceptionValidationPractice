package loaders;

import dao.DAOException;
import dao.PartieDAO;
import dao.PersonnageDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PartieModel;
import model.PersonnageModel;
import model.UniversModel;

/**
 * Created by william on 05/04/16.
 */
public class UniversLoader extends AbstractLoader<UniversModel> {
    public UniversLoader(UniversModel u) {
        super(u);
    }
    public UniversLoader() {
        super();
    }
    
    public UniversModel get(PartieModel partie) {
        if (!isLoaded())
            try {
                setObject(PartieDAO.instance().getUnivers(partie));
        } catch (DAOException ex) {
            Logger.getLogger(UniversLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getObject();
    }

    public UniversModel get(PersonnageModel personnage) {
        if (!isLoaded())
            try {
                setObject(PersonnageDAO.instance().getUnivers(personnage));
        } catch (DAOException ex) {
            Logger.getLogger(UniversLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getObject();
    }
}
