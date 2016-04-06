package loaders;

import model.*;

/**
 * Created by william on 05/04/16.
 */
public class BiographieLoader extends AbstractLoader<BiographieModel> {
    public BiographieModel get(BioInitialeModel bioInitiale) {
        if (!isLoaded())
            setObject(BioInitialeModel.getDAO().getBiographie(bioInitiale));

        return getObject();
    }

    public BiographieModel get(TransitionModel transition) {
        if (!isLoaded())
            setObject(TransitionModel.getDAO().getBiographie(transition));

        return getObject();
    }

    public BiographieModel get(ResumeModel resume) {
        if (!isLoaded())
            setObject(ResumeModel.getDAO().getBiographie(resume));

        return getObject();
    }

    public BiographieModel get(PersonnageModel personnage) {
        if (!isLoaded())
            setObject(PersonnageModel.getDAO().getBiographie(personnage));

        return getObject();
    }
}
