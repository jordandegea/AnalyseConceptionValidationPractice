package loaders;

import model.BiographieModel;
import model.PersonnageModel;
import model.TransitionModel;

import java.util.Set;

/**
 * Created by william on 05/04/16.
 */
public class TransitionLoader extends  AbstractLoader<TransitionModel> {
    public Set<TransitionModel> get(BiographieModel bio) {
        if (!isLoaded())
            setObjectSet(BiographieModel.getDAO().getTransitions(bio));

        return getObjectSet();
    }
}
