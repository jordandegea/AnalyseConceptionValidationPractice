package loaders;

import model.PartieModel;
import model.PersonnageModel;
import model.UniversModel;

/**
 * Created by william on 05/04/16.
 */
public class UniversLoader extends AbstractLoader<UniversModel> {
    public UniversModel get(PartieModel partie) {
        if (!isLoaded())
            setObject(PartieModel.getDAO().getUnivers(partie));

        return getObject();
    }

    public UniversModel get(PersonnageModel personnage) {
        if (!isLoaded())
            setObject(PersonnageModel.getDAO().getUnivers(personnage));

        return getObject();
    }
}
