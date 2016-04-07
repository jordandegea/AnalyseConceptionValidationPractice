package loaders;

import dao.BiographieDAO;
import dao.DAOException;
import model.BiographieModel;
import model.PersonnageModel;
import model.TransitionModel;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by william on 05/04/16.
 */
public class TransitionLoader extends  AbstractLoader<TransitionModel> {
    public Set<TransitionModel> get(BiographieModel bio) {
        if (!isLoaded())
            try {
                setObjectSet(((BiographieDAO)BiographieModel.getDAO()).getTransitions(bio));
        } catch (DAOException ex) {
            Logger.getLogger(TransitionLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getObjectSet();
    }
}
