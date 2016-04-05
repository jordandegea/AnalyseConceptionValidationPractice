package loaders;

import model.EpisodeModel;
import model.ParagrapheModel;

/**
 * Created by JordanLeMagnifique
 */
public class EpisodeLoader extends AbstractLoader<EpisodeModel> {
    public EpisodeModel get(ParagrapheModel paragraphe) {
        if (!isLoaded())
            setObject(ParagrapheModel.getDAO().getEpisode(paragraphe));

        return getObject();
    }
}
