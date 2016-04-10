package model;

import dao.DAOException;
import loaders.JoueurLoader;
import loaders.PersonnageLoader;
import loaders.ResumePersonnageLoader;
import loaders.UniversLoader;

import java.util.Set;
import loaders.ResumePartieLoader;

/**
 * Created by william on 05/04/16.
 */
public class PartieModel extends AbstractBaseModel {
    private String titrePartie;
    private String resumeInitial;
    private String date;
    private String lieu;
    private boolean partieFinie;
    
    private UniversLoader univers;
    private JoueurLoader MJ;
    private PersonnageLoader personnages;
    private ResumePartieLoader resumePartie;
    private ResumePersonnageLoader resumePersonnage;

    public PartieModel(int id, String titrePartie, String résumé, String date, String lieu, boolean partieFinie) {
        super(id);
        this.titrePartie = titrePartie;
        this.resumeInitial = résumé;
        this.date = date;
        this.lieu = lieu;
        this.partieFinie = partieFinie;
        
        univers = new UniversLoader();
        MJ = new JoueurLoader();
        personnages = new PersonnageLoader();
        resumePartie = new ResumePartieLoader();
        resumePersonnage = new ResumePersonnageLoader();
    }

    public PartieModel(String titrePartie, String date, String lieu, String résumé, JoueurModel MJ, UniversModel univers) {
        this.titrePartie = titrePartie;
        this.resumeInitial = résumé;
        this.date = date;
        this.lieu = lieu;
        this.partieFinie = false;
        
        this.univers = new UniversLoader(univers);
        this.MJ = new JoueurLoader(MJ);
        personnages = new PersonnageLoader();
        resumePartie = new ResumePartieLoader();
        resumePersonnage = new ResumePersonnageLoader();
    }

    public String getTitrePartie() {
        return titrePartie;
    }

    public void setTitrePartie(String titrePartie) {
        this.titrePartie = titrePartie;
    }

    public String getResumeInitial() {
        return resumeInitial;
    }

    public void setResumeInitial(String résumé) {
        this.resumeInitial = résumé;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public JoueurModel getMJ() throws DAOException {
        return MJ.get(this);
    }

    public Set<PersonnageModel> getPersonnages() throws DAOException {
        return this.personnages.get(this);
    }

    public ResumePartieModel getResume() throws DAOException {
        return this.resumePartie.get(this);
    }
    
    public Set<ResumePersonnageModel> getResumesPersonnage() throws DAOException {
        return this.resumePersonnage.get(this);
    }
}