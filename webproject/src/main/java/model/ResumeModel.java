package model;

import loaders.BiographieLoader;
import loaders.PartieLoader;

import java.sql.Date;

/**
 * Created by william on 05/04/16.
 */
public class ResumeModel extends EpisodeModel {
    private PartieLoader partie;
    private BiographieLoader biographie;

    public ResumeModel(int id, Date dateEpisode, boolean ecritureEnCours) {
        super(id, dateEpisode, ecritureEnCours);
        partie = new PartieLoader();
        biographie = new BiographieLoader();
    }

    public PartieModel getPartie() {
        return partie.get(this);
    }

    public BiographieModel getBiographie() {
        return this.biographie.get(this);
    }
}