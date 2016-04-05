package loaders;

import model.JoueurModel;
import model.PartieModel;
import model.PersonnageModel;
import model.UniversModel;

import java.util.Set;

/**
 * Created by william on 05/04/16.
 */
public class PersonnageLoader extends AbstractLoader<PersonnageModel> {
    public Set<PersonnageModel> get(UniversModel univers) {
        if (!isLoaded())
            setObjectSet(UniversModel.getDAO().getPersonnages(univers));

        return getObjectSet();
    }

    public Set<PersonnageModel> get(PartieModel partie) {
        if (!isLoaded())
            setObjectSet(PartieModel.getDAO().getPersonnages(partie));

        return getObjectSet();
    }

    public Set<PersonnageModel> getPersonnagesOwned(JoueurModel joueur) {
        if (!isLoaded())
            setObjectSet(JoueurModel.getDAO().getPersonnagesOwned(joueur));

        return getObjectSet();
    }

    public Set<PersonnageModel> getPersonnagesManaged(JoueurModel joueur) {
        if (!isLoaded())
            setObjectSet(JoueurModel.getDAO().getPersonnagesManaged(joueur));

        return getObjectSet();
    }
}
