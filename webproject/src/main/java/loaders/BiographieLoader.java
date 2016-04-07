package loaders;

import dao.BioInitialeDAO;
import dao.DAOException;
import dao.PersonnageDAO;
import dao.ResumeDAO;
import dao.TransitionDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

/**
 * Created by william on 05/04/16.
 */
public class BiographieLoader extends AbstractLoader<BiographieModel> {
    public BiographieModel get(BioInitialeModel bioInitiale) {
        if (!isLoaded())
            try {
                setObject(((BioInitialeDAO)BioInitialeModel.getDAO()).getBiographie(bioInitiale));
        } catch (DAOException ex) {
            Logger.getLogger(BiographieLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getObject();
    }

    public BiographieModel get(TransitionModel transition) {
        if (!isLoaded())
            try {
                setObject(((TransitionDAO)TransitionModel.getDAO()).getBiographie(transition));
        } catch (DAOException ex) {
            Logger.getLogger(BiographieLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getObject();
    }

    public BiographieModel get(ResumeModel resume) {
        if (!isLoaded())
            try {
                setObject(((ResumeDAO)ResumeModel.getDAO()).getBiographie(resume));
        } catch (DAOException ex) {
            Logger.getLogger(BiographieLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getObject();
    }

    public BiographieModel get(PersonnageModel personnage) {
        if (!isLoaded())
            try {
                setObject(((PersonnageDAO)PersonnageModel.getDAO()).getBiographie(personnage));
        } catch (DAOException ex) {
            Logger.getLogger(BiographieLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getObject();
    }
}
