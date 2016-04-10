package loaders;

import dao.BiographieDAO;
import dao.DAOException;
import dao.PartieDAO;
import model.BiographieModel;
import model.PartieModel;
import model.ResumePersonnageModel;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ResumePartieModel;

/**
 * Created by william on 05/04/16.
 */
public class ResumePartieLoader extends AbstractLoader<ResumePartieModel> {

    public ResumePartieModel get(PartieModel partie) throws DAOException {
        if (!isLoaded()) {
            setObject(((PartieDAO) PartieModel.getDAO()).getResumePartie(partie));
        }

        return getObject();
    }

}
