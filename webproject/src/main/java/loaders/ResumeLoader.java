package loaders;

import model.BiographieModel;
import model.PartieModel;
import model.ResumeModel;

import java.util.Set;

/**
 * Created by william on 05/04/16.
 */
public class ResumeLoader extends AbstractLoader<ResumeModel> {
    public ResumeModel get(PartieModel partie) {
        if (!isLoaded())
            setObject(PartieModel.getDAO().getResume(partie));

        return getObject();
    }

    public Set<ResumeModel> get(BiographieModel bio) {
        if (!isLoaded())
            setObjectSet(BiographieModel.getDAO().getResumes(bio));

        return getObjectSet();
    }
}
