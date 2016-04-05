package model;

import loaders.BiographieLoader;

import java.sql.Date;

/**
 * Created by william on 05/04/16.
 */
public class TransitionModel extends EpisodeModel {
    private BiographieLoader biographie;

    public TransitionModel(int id, Date dateEpisode, boolean ecritureEnCours) {
        super(id, dateEpisode, ecritureEnCours);
        biographie = new BiographieLoader();
    }

    public BiographieModel getBiographie() {
        return this.biographie.get(this);
    }
}
