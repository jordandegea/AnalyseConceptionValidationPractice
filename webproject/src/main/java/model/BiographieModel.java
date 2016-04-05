package model;

import dao.AbstractDataBaseDAO;
import loaders.BioInitialeLoader;
import loaders.ResumeLoader;

import java.util.Set;

/**
 * Created by william on 05/04/16.
 */
public class BiographieModel extends AbstractBaseModel {
    private BioInitialeLoader bioInitiale;
    private ResumeLoader resumes;

    public BiographieModel(int id) {
        super(id);
        bioInitiale = new BioInitialeLoader();
        resumes = new ResumeLoader();
    }

    public BioInitialeModel getBioInitiale() {
        return bioInitiale.get(this);
    }

    public Set<ResumeModel> getResumes() {
        return resumes.get(this);
    }
}