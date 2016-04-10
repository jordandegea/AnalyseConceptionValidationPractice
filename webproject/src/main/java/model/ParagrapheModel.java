package model;

import loaders.EpisodeLoader;

/**
 * Created by william on 05/04/16.
 */
public class ParagrapheModel extends AbstractBaseModel {
    private boolean secret;
    private String contenu;
    private int numPar;
    private EpisodeLoader episode;

    public ParagrapheModel(int id, boolean secret, String contenu, int num) {
        super(id);
        this.secret = secret;
        this.contenu = contenu;
        this.episode = new EpisodeLoader();
        this.numPar = num;
    }

    public ParagrapheModel(boolean secret, String contenu, int num) {
        this.secret = secret;
        this.contenu = contenu;
        this.numPar = num;
        this.episode = new EpisodeLoader();
    }

    public boolean isSecret() {
        return secret;
    }

    public void setSecret(boolean secret) {
        this.secret = secret;
    }

    public String getContenu() {
        return contenu;
    }
    
    public void setNumeroPar(int num) {
        this.numPar = num;
    }
    public int getNumeroPar() {
        return numPar;
    }
    
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    
    public EpisodeModel getEpisode(){
        return episode.get(this);
    }
    
    public void setEpisode(EpisodeModel e) {
        this.episode.setObject(e);
    }
}
