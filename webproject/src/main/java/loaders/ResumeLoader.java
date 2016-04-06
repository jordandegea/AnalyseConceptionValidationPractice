package loaders;

import dao.BiographieDAO;
import dao.DAOException;
import dao.PartieDAO;
import model.BiographieModel;
import model.PartieModel;
import model.ResumeModel;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by william on 05/04/16.
 */
public class ResumeLoader extends AbstractLoader<ResumeModel> {
    public ResumeModel get(PartieModel partie) {
        if (!isLoaded())
            try {
                setObject(((PartieDAO)PartieModel.getDAO()).getResume(partie));
        } catch (DAOException ex) {
            Logger.getLogger(ResumeLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getObject();
    }

    public Set<ResumeModel> get(BiographieModel bio) {
        if (!isLoaded())
            try {
                setObjectSet(((BiographieDAO)BiographieModel.getDAO()).getResumes(bio));
        } catch (DAOException ex) {
            Logger.getLogger(ResumeLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getObjectSet();
    }
}
