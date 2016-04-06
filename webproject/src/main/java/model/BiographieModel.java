package model;

import loaders.BioInitialeLoader;
import loaders.ResumeLoader;

import java.util.Set;
import loaders.TransitionLoader;

/**
 * Created by william on 05/04/16.
 */
public class BiographieModel extends AbstractBaseModel {
    private int idBiographie;
    private int idBioInitiale;
    
    private BioInitialeLoader bioInitiale;
    private ResumeLoader resumes;
    private TransitionLoader transitions;

    public BiographieModel(int id, int idBiographie, int idBioInitiale) {
        super(id);
        
        this.idBioInitiale = idBioInitiale;
        this.idBiographie = idBiographie;
        
        bioInitiale = new BioInitialeLoader();
        resumes = new ResumeLoader();
    }

    public BioInitialeModel getBioInitiale() {
        return bioInitiale.get(this);
    }

    public Set<ResumeModel> getResumes() {
        return resumes.get(this);
    }
    public Set<TransitionModel> getTransition() {
        return transitions.get(this);
    }
    public int getIdBiographie() {
        return idBiographie;
    }

    public int getIdBioInitiale() {
        return idBioInitiale;
    }
}