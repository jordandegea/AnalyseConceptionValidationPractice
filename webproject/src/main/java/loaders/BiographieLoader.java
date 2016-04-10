package loaders;

import dao.BioInitialeDAO;
import dao.DAOException;
import dao.PersonnageDAO;
import dao.ResumePersonnageDAO;
import dao.TransitionDAO;
import model.*;

/**
 * Created by william on 05/04/16.
 */
public class BiographieLoader extends AbstractLoader<BiographieModel> {
    public BiographieLoader() {
        super();
    }
    
    public BiographieLoader(BiographieModel bio) {
        super(bio);
    }
    
    public BiographieLoader(BioInitialeModel bioInitiale) {
        super(new BiographieModel(bioInitiale));
    }
    
    public BiographieModel get(BioInitialeModel bioInitiale) throws DAOException {
        if (!isLoaded())
                setObject(BioInitialeDAO.instance().getBiographie(bioInitiale));
        return getObject();
    }

    public BiographieModel get(TransitionModel transition) throws DAOException {
        if (!isLoaded())
                setObject(TransitionDAO.instance().getBiographie(transition));

        return getObject();
    }

    public BiographieModel get(ResumePersonnageModel resume) throws DAOException {
        if (!isLoaded())
                setObject(ResumePersonnageDAO.instance().getBiographie(resume));

        return getObject();
    }

    public BiographieModel get(PersonnageModel personnage) throws DAOException {
        if (!isLoaded())
                setObject(PersonnageDAO.instance().getBiographie(personnage));
        return getObject();
    }
}
