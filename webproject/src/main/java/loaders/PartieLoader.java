package loaders;

import model.*;

import java.util.Set;

/**
 * Created by william on 05/04/16.
 */
public class PartieLoader extends AbstractLoader<PartieModel> {
    public Set<PartieModel> get(PersonnageModel personnage) {
        if (!isLoaded())
            setObjectSet(PersonnageModel.getDAO().getParties(personnage));

        return getObjectSet();
    }

    public Set<PartieModel> get(JoueurModel joueur) {
        if (!isLoaded())
            setObjectSet(JoueurModel.getDAO().getParties(personnage));

        return getObjectSet();
    }

    public PartieModel get(ResumeModel resume) {
        if (!isLoaded())
            setObject(ResumeModel.getDAO().getPartie(resume));

        return getObject();
    }
}
