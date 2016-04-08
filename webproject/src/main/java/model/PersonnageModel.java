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

    private int idJoueur;

    private int idUnivers ;
    private int idPartie ;
    private int idBiographie ;
    
    private JoueurLoader owner;
    private UniversLoader univers;
    private PartieLoader parties;
    private PartieLoader partie;
    private JoueurLoader MJ;
    private BiographieLoader biographie;

    public PersonnageModel(int id, String nomPerso, String dateNaiss, String profession, String portrait,
    int idJoueur, int idUnivers, int idPartie, int idBiographie) {
        super(id);
        this.nomPerso = nomPerso;
        this.dateNaiss = dateNaiss;
        this.profession = profession;
        this.portrait = portrait;
        
        this.idJoueur = idJoueur;
        this.idUnivers = idUnivers;
        this.idPartie = idPartie;
        this.idBiographie = idBiographie;
        
        owner = new JoueurLoader();
        univers = new UniversLoader();
        parties = new PartieLoader();
        MJ = new JoueurLoader();
    }

    public PersonnageModel(String nomPerso, String dateNaiss, String profession, String portrait,
    int idJoueur, int idUnivers, int idPartie, int idBiographie) {
        this.nomPerso = nomPerso;
        this.dateNaiss = dateNaiss;
        this.profession = profession;
        this.portrait = portrait;
        
        this.idJoueur = idJoueur;
        this.idUnivers = idUnivers;
        this.idPartie = idPartie;
        this.idBiographie = idBiographie;
        
        owner = new JoueurLoader();
        univers = new UniversLoader();
        parties = new PartieLoader();
        MJ = new JoueurLoader();
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
    
    
    public int getIdJoueur() {
        return idJoueur;
    }

    public int getIdUnivers() {
        return idUnivers;
    }

    public int getIdPartie() {
        return idPartie;
    }

    public int getIdBiographie() {
        return idBiographie;
    }
}
