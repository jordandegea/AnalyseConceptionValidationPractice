package loaders;

import model.JoueurModel;
import model.PartieModel;
import model.PersonnageModel;

/**
 * Created by william on 05/04/16.
 */
public class JoueurLoader extends AbstractLoader<JoueurModel> {
    public JoueurModel get(PartieModel partie) {
        if (!isLoaded())
            setObject(PartieModel.getDAO().getJoueur(partie));

        return getObject();
    }

    public JoueurModel getMJ(PersonnageModel personnage) {
        if (!isLoaded())
            setObject(PersonnageModel.getDAO().getMJ(personnage));

        return getObject();
    }

    public JoueurModel getOwner(PersonnageModel personnage) {
        if (!isLoaded())
            setObject(PersonnageModel.getDAO().getOwner(personnage));

        return getObject();
    }
}
