package model;

import loaders.BiographieLoader;
import loaders.PartieLoader;

import java.sql.Date;

/**
 * Created by william on 05/04/16.
 */
public class ResumeModel extends EpisodeModel {
    private int idEpisode;
    private int idPartie;

    
    private PartieLoader partie;
    private BiographieLoader biographie;

    public ResumeModel(int id, Date date, boolean ecritureEnCours, int idEpisode, int idPartie) {
        super(id, date, ecritureEnCours);
        
        this.idEpisode = idEpisode ; 
        this.idPartie = idPartie;
        
        partie = new PartieLoader();
        biographie = new BiographieLoader();
    }

    public PartieModel getPartie() {
        return partie.get(this);
    }

    public BiographieModel getBiographie() {
        return this.biographie.get(this);
    }
    
    public int getIdEpisode() {
        return idEpisode;
    }

    public int getIdPartie() {
        return idPartie;
    }
}
