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

    public ResumeModel get(PartieModel partie) throws DAOException {
        if (!isLoaded()) {
            setObject(((PartieDAO) PartieModel.getDAO()).getResume(partie));
        }

        return getObject();
    }

    public Set<ResumeModel> get(BiographieModel bio) throws DAOException {
        if (!isLoaded()) {
                setObjectSet(BiographieDAO.instance().getResumes(bio));
        }

        return getObjectSet();
    }
}
