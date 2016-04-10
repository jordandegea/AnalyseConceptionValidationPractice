package loaders;

import dao.BiographieDAO;
import dao.DAOException;
import dao.PartieDAO;
import model.BiographieModel;
import model.PartieModel;
import model.ResumePersonnageModel;

import java.util.Set;

/**
 * Created by william on 05/04/16.
 */
public class ResumePersonnageLoader extends AbstractLoader<ResumePersonnageModel> {

    public Set<ResumePersonnageModel> get(PartieModel partie) throws DAOException {
        if (!isLoaded()) {
            setObjectSet(((PartieDAO) PartieModel.getDAO()).getResumesPersonnage(partie));
        }

        return getObjectSet();
    }

    public Set<ResumePersonnageModel> get(BiographieModel bio) throws DAOException {
        if (!isLoaded()) {
                setObjectSet(BiographieDAO.instance().getResumes(bio));
        }

        return getObjectSet();
    }
}
