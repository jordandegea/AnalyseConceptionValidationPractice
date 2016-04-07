package loaders;

import dao.DAOException;
import dao.ParagrapheDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.EpisodeModel;
import model.ParagrapheModel;

/**
 * Created by JordanLeMagnifique
 */
public class EpisodeLoader extends AbstractLoader<EpisodeModel> {
    public EpisodeModel get(ParagrapheModel paragraphe) {
        if (!isLoaded())
            try {
                setObject(((ParagrapheDAO)ParagrapheModel.getDAO()).getEpisode(paragraphe));
        } catch (DAOException ex) {
            Logger.getLogger(EpisodeLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getObject();
    }
}
