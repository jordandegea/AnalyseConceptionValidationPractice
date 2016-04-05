package loaders;

import model.EpisodeModel;
import model.ParagrapheModel;

import java.util.Set;

/**
 * Created by william on 05/04/16.
 */
public class ParagrapheLoader extends AbstractLoader<ParagrapheModel> {
    public Set<ParagrapheModel> get(EpisodeModel episode) {
        if (!isLoaded())
            setObjectSet(EpisodeModel.getDAO().getParagraphe(episode));

        return getObjectSet();
    }
}
