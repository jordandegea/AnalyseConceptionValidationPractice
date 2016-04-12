package model;

import dao.DAOException;
import loaders.PartieLoader;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by william on 05/04/16.
 */
public class ResumePartieModel extends EpisodeModel {
    private PartieLoader partie;

    public ResumePartieModel(int id, Date date, boolean ecritureEnCours) {
        super(id, date, ecritureEnCours);
        
        partie = new PartieLoader();
    }
    
    public ResumePartieModel(Date date, ArrayList<String> paragraphes, PartieModel partie) {
        super(date, false, paragraphes);
        
        this.partie = new PartieLoader(partie);
    }

    public PartieModel getPartie() throws DAOException {
        return partie.get(this);
    }
}
