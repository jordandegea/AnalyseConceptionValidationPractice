package model;

import dao.DAOException;
import loaders.BiographieLoader;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by william on 05/04/16.
 */
public class TransitionModel extends EpisodeModel {
    private BiographieLoader biographie;

    public TransitionModel(int id, Date date, boolean ecritureEnCours) {
        super(id, date, ecritureEnCours);
        biographie = new BiographieLoader();
    }
    
    public TransitionModel(ArrayList<String> paragraphes, ArrayList<Integer> access) {
        super(new Date(0), false, paragraphes, access);
    }

    public BiographieModel getBiographie() throws DAOException {
        return this.biographie.get(this);
    }
}
