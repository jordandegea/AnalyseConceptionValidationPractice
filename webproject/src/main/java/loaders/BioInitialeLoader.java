package loaders;

import dao.BiographieDAO;
import dao.DAOException;
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

    public BioInitialeModel get(BiographieModel bio) throws DAOException {
        if (!isLoaded()) {
            setObject(BiographieDAO.instance().getBioInitiale(bio));
        }

        return getObject();
    }
}
