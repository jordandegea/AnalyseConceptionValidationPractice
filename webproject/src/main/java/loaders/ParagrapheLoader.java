package loaders;

import dao.DAOException;
import dao.EpisodeDAO;
import model.EpisodeModel;
import model.ParagrapheModel;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by william on 05/04/16.
 */
public class ParagrapheLoader extends AbstractLoader<ParagrapheModel> {

    public Set<ParagrapheModel> get(EpisodeModel episode) throws DAOException {
        if (!isLoaded()) {
            setObjectSet(EpisodeDAO.instance().getParagraphes(episode));
        }

        return getObjectSet();
    }
}
