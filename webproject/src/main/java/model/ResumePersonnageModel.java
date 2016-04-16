package model;

import dao.DAOException;
import loaders.BiographieLoader;
import loaders.PartieLoader;

import java.sql.Date;

/**
 * Created by william on 05/04/16.
 */
public class ResumePersonnageModel extends EpisodeModel {
    private PartieLoader partie;
    private BiographieLoader biographie;

    public ResumePersonnageModel(int id, Date date, boolean validationJoueur, boolean validationMJ) {
        super(id, date, validationJoueur, validationMJ);
        
        partie = new PartieLoader();
        biographie = new BiographieLoader();
    }

    public PartieModel getPartie() throws DAOException {
        return partie.get(this);
    }

    public BiographieModel getBiographie() throws DAOException {
        return this.biographie.get(this);
    }
}
