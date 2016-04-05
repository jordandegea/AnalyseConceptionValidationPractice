package loaders;

import model.BioInitialeModel;
import model.BiographieModel;

/**
 * Created by william on 05/04/16.
 */
public class BioInitialeLoader extends AbstractLoader<BioInitialeModel> {
    public BioInitialeModel get(BiographieModel bio) {
        if (!isLoaded())
            setObject(BiographieModel.getDAO().getBioInitiale(bio));

        return getObject();
    }
}
