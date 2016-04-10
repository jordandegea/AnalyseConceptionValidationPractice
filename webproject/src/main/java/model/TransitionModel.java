package model;

import dao.DAOException;
import loaders.BiographieLoader;

import java.sql.Date;

/**
 * Created by william on 05/04/16.
 */
public class TransitionModel extends EpisodeModel {
    private BiographieLoader biographie;

    public TransitionModel(int id, Date date, boolean ecritureEnCours) {
        super(id, date, ecritureEnCours);
        biographie = new BiographieLoader();
    }

    public BiographieModel getBiographie() throws DAOException {
        return this.biographie.get(this);
    }
}
