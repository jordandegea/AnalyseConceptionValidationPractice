package model;

import loaders.ParagrapheLoader;

import java.sql.Date;
import java.util.Set;

/**
 * Created by william on 05/04/16.
 */
public class EpisodeModel extends AbstractBaseModel {
    private Date dateEpisode;
    private boolean ecritureEnCours;
    private ParagrapheLoader paragraphes;

    public EpisodeModel(int id, Date dateEpisode, boolean ecritureEnCours) {
        super(id);
        this.dateEpisode = dateEpisode;
        this.ecritureEnCours = ecritureEnCours;
        paragraphes = new ParagrapheLoader();
    }

    public Date getDateEpisode() {
        return dateEpisode;
    }

    public void setDateEpisode(Date dateEpisode) {
        this.dateEpisode = dateEpisode;
    }

    public boolean isEcritureEnCours() {
        return ecritureEnCours;
    }

    public void setEcritureEnCours(boolean ecritureEnCours) {
        this.ecritureEnCours = ecritureEnCours;
    }

    public Set<ParagrapheModel> getParagraphes() {
        return paragraphes.get(this);
    }
}
