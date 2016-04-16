package model;

import dao.DAOException;
import loaders.BioInitialeLoader;
import loaders.ResumePersonnageLoader;

import java.util.Set;
import java.util.TreeSet;
import loaders.TransitionLoader;

/**
 * Created by william on 05/04/16.
 */
public class BiographieModel extends AbstractBaseModel {
    private BioInitialeLoader bioInitiale;
    private ResumePersonnageLoader resumes;
    private TransitionLoader transitions;

    public BiographieModel(int id) {
        super(id);
        
        bioInitiale = new BioInitialeLoader();
        resumes = new ResumePersonnageLoader();
        transitions = new TransitionLoader();
    }

    public BiographieModel(BioInitialeModel bioInitiale) {
        this.bioInitiale = new BioInitialeLoader(bioInitiale);
        resumes = new ResumePersonnageLoader();
        transitions = new TransitionLoader();
    }

    public BioInitialeModel getBioInitiale() throws DAOException {
        return bioInitiale.get(this);
    }
    
    public void setBioInitiale(BioInitialeModel bi) {
        bioInitiale.setObject(bi);
    }

    public Set<ResumePersonnageModel> getResumes() throws DAOException {
        return resumes.get(this);
    }
    
    public Set<TransitionModel> getTransition() throws DAOException {
        return transitions.get(this);
    }
    
    public void setTransitions(Set<TransitionModel> tm) {
        transitions.setObjectSet(tm);
    }
    
    public Set<EpisodeModel> getFullBiographie() throws DAOException {
        Set<EpisodeModel> episodes = new TreeSet<>();
        episodes.add(this.getBioInitiale());
        episodes.addAll(this.getResumes());
        episodes.addAll(this.getTransition());
        return episodes;
    }
    
    public Set<EpisodeModel> getBioInit() throws DAOException {
        Set<EpisodeModel> episodes = new TreeSet<>();
        episodes.add(this.getBioInitiale());
        return episodes;
    }

      
}