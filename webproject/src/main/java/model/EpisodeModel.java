package model;

import dao.DAOException;
import dao.EpisodeDAO;
import loaders.ParagrapheLoader;

import java.sql.Date;
import java.util.Set;

/**
 * Created by william on 05/04/16.
 */
public class EpisodeModel extends AbstractBaseModel implements Comparable {
    private boolean ecritureEnCours;
    private ParagrapheLoader paragraphes;
    private Date date;

    public EpisodeModel(int id, Date date, boolean ecritureEnCours) {
        super(id);
        this.ecritureEnCours = ecritureEnCours;
        paragraphes = new ParagrapheLoader();
        this.date = date;
    }

    public EpisodeModel(Date date, boolean ecritureEnCours) {
        this.ecritureEnCours = ecritureEnCours;
        paragraphes = new ParagrapheLoader();
        this.date = date;
    }

    public boolean isEcritureEnCours() {
        return ecritureEnCours;
    }

    public void setEcritureEnCours(boolean ecritureEnCours) {
        this.ecritureEnCours = ecritureEnCours;
    }

    public Set<ParagrapheModel> getParagraphes() throws DAOException {
        return paragraphes.get(this);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int compareTo(Object t) {
        if (t == null)
            throw new NullPointerException();
        if (!(t instanceof EpisodeModel))
            throw new ClassCastException();
            
        EpisodeModel other = (EpisodeModel) t;
        
        return this.getDate().compareTo(other.getDate());
    }
    
    public static EpisodeDAO getDAO() {
        return EpisodeDAO.instance();
    }
    
    public String getAllPart() throws DAOException {
        String s="";
        for ( ParagrapheModel pm: this.getParagraphes() ){
            s += "<p style=\"margin-left:20px\"> ";
            s += pm.getContenu();
            s += "</p>";
            s += "</hr>";
        }
        return s;
    }
    
    public String getAllPartPublic() throws DAOException {
        String s="";
        for ( ParagrapheModel pm: this.getParagraphes() ){
            if (!pm.isSecret()) {
                s += "<p style=\"margin-left:20px\"> ";
                s += pm.getContenu();
                s += "</p>";
                s += "</hr>";
            }
        }
        return s;
    }
    public void setParagraphes(Set<ParagrapheModel> paras){
        this.paragraphes.setObjectSet(paras);
    }
}
