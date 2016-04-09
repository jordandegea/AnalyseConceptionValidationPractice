package loaders;

import dao.BiographieDAO;
import dao.DAOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BioInitialeModel;
import model.BiographieModel;

/**
 * Created by william on 05/04/16.
 */
public class BioInitialeLoader extends AbstractLoader<BioInitialeModel> {

    public BioInitialeLoader(BioInitialeModel bioInitiale) {
        super(bioInitiale);
    }
    public BioInitialeLoader() {
        super();
    }
    
    public BioInitialeModel get(BiographieModel bio) {
        if (!isLoaded())
            try {
                setObject(((BiographieDAO)BiographieModel.getDAO()).getBioInitiale(bio));
        } catch (DAOException ex) {
            Logger.getLogger(BioInitialeLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getObject();
    }
}
