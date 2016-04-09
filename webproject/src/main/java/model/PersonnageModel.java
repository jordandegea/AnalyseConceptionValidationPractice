package model;

import loaders.JoueurLoader;
import loaders.PartieLoader;
import loaders.UniversLoader;

import java.sql.Date;
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
    
    private JoueurLoader owner;
    private UniversLoader univers;
    private PartieLoader parties;
    private PartieLoader partie;
    private JoueurLoader MJ;
    private BiographieLoader biographie;

    public PersonnageModel(int id, String nomPerso, String dateNaiss, String profession, String portrait) {
        super(id);
        this.nomPerso = nomPerso;
        this.dateNaiss = dateNaiss;
        this.profession = profession;
        this.portrait = portrait;
        
        owner = new JoueurLoader();
        univers = new UniversLoader();
        parties = new PartieLoader();
        MJ = new JoueurLoader();
    }

    public PersonnageModel(String nomPerso, String dateNaiss, String profession, String portrait, BioInitialeModel bioInitiale, JoueurModel Joueur, UniversModel univers) {
        this.nomPerso = nomPerso;
        this.dateNaiss = dateNaiss;
        this.profession = profession;
        this.portrait = portrait;
        
        owner = new JoueurLoader(Joueur);
        this.univers = new UniversLoader(univers);
        parties = new PartieLoader();
        partie = new PartieLoader();
        MJ = new JoueurLoader();
        biographie = new BiographieLoader(bioInitiale);
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

    public BiographieModel getBiographie(){
        return biographie.get(this);
    }
    
    public JoueurModel getOwner() {
        return owner.getOwner(this);
    }

    public UniversModel getUnivers() {
        return univers.get(this);
    }

    public Set<PartieModel> getParties() {
        return parties.get(this);
    }

    public PartieModel getPartieEnCours() {
        return partie.getEnCours(this);
    }

    public JoueurModel getMJ() {
        return MJ.getMJ(this);
    }
}
