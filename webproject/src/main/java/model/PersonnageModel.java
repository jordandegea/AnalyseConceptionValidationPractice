package model;

import dao.DAOException;
import loaders.JoueurLoader;
import loaders.PartieLoader;
import loaders.UniversLoader;

import java.util.Set;
import loaders.BiographieLoader;

/**
 * Created by william on 05/04/16.
 */
public class PersonnageModel extends AbstractBaseModel {
    private String nomPerso;
    private String dateNaiss;
    private String profession;
    private String portrait;
    private boolean demandeMJ;
    
    private JoueurLoader owner;
    private UniversLoader univers;
    private PartieLoader parties;
    private PartieLoader partie;
    private JoueurLoader MJ;
    private BiographieLoader biographie;

    public PersonnageModel(int id, String nomPerso, String dateNaiss, String profession, String portrait, boolean demandeMJ) {
        super(id);
        this.nomPerso = nomPerso;
        this.dateNaiss = dateNaiss;
        this.profession = profession;
        this.portrait = portrait;
        this.demandeMJ = demandeMJ;
        
        owner = new JoueurLoader();
        univers = new UniversLoader();
        parties = new PartieLoader();
        MJ = new JoueurLoader();
        partie = new PartieLoader();
        biographie = new BiographieLoader();
    }

    public PersonnageModel(String nomPerso, String dateNaiss, String profession, String portrait, BioInitialeModel bioInitiale, JoueurModel joueur, UniversModel univers) {
        this.nomPerso = nomPerso;
        this.dateNaiss = dateNaiss;
        this.profession = profession;
        this.portrait = portrait;
        this.demandeMJ = false;
        this.owner = new JoueurLoader(joueur);
        this.univers = new UniversLoader(univers);
        this.parties = new PartieLoader();
        this.partie = new PartieLoader();
        this.MJ = new JoueurLoader();
        this.biographie = new BiographieLoader(bioInitiale);
    }

    public boolean isDemandeMJ() {
        return demandeMJ;
    }

    public void setDemandeMJ(boolean demandeMJ) {
        this.demandeMJ = demandeMJ;
    }

    public String getNomPerso() {
        return nomPerso;
    }

    public void setNomPerso(String nomPerso) {
        this.nomPerso = nomPerso;
    }

    public String getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(String dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public BiographieModel getBiographie() throws DAOException{
        return biographie.get(this);
    }
    
    public JoueurModel getOwner() throws DAOException {
        return owner.getOwner(this);
    }

    public UniversModel getUnivers() {
        return univers.get(this);
    }

    public Set<PartieModel> getParties() throws DAOException {
        return parties.get(this);
    }

    public PartieModel getPartieEnCours() throws DAOException {
        return partie.getEnCours(this);
    }
    
    public void setPartieEnCours(PartieModel partie) {
        this.partie.setObject(partie);
    }

    public JoueurModel getMJ() throws DAOException {
        return MJ.getMJ(this);
    }
}
