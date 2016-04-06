package model;

import loaders.JoueurLoader;
import loaders.PersonnageLoader;
import loaders.ResumeLoader;
import loaders.UniversLoader;

import java.sql.Date;
import java.util.Set;

/**
 * Created by william on 05/04/16.
 */
public class PartieModel extends AbstractBaseModel {
    private String titrePartie;
    private String résumé;
    private Date date;
    private String lieu;
    private boolean partieFinie;

    private int idJoueur;
    private int idUnivers;
    
    private UniversLoader univers;
    private JoueurLoader MJ;
    private PersonnageLoader personnages;
    private ResumeLoader resume;

    public PartieModel(int id, String titrePartie, String résumé, Date date, String lieu, boolean partieFinie, int idJoueur, int idUnivers) {
        super(id);
        this.titrePartie = titrePartie;
        this.résumé = résumé;
        this.date = date;
        this.lieu = lieu;
        this.partieFinie = partieFinie;
        
        this.idJoueur = idJoueur;
        this.idUnivers = idUnivers;
        
        univers = new UniversLoader();
        MJ = new JoueurLoader();
        personnages = new PersonnageLoader();
        resume = new ResumeLoader();
    }

    public String getTitrePartie() {
        return titrePartie;
    }

    public void setTitrePartie(String titrePartie) {
        this.titrePartie = titrePartie;
    }

    public String getRésumé() {
        return résumé;
    }

    public void setRésumé(String résumé) {
        this.résumé = résumé;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public boolean isPartieFinie() {
        return partieFinie;
    }

    public void setPartieFinie(boolean partieFinie) {
        this.partieFinie = partieFinie;
    }

    public UniversModel getUnivers() {
        return univers.get(this);
    }

    public JoueurModel getMJ() {
        return MJ.get(this);
    }

    public Set<PersonnageModel> getPersonnages() {
        return this.personnages.get(this);
    }

    public ResumeModel getResume() {
        return this.resume.get(this);
    }
}