/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import dao.DAOException;
import java.util.Set;
import model.BiographieModel;
import model.EpisodeModel;

/**
 *
 * @author william
 */
public class BiographieValidator extends AbstractValidator<BiographieModel> {
    final private static BiographieValidator instance = new BiographieValidator();
    
    public static BiographieValidator instance() {
        return instance;
    }
    
    private BiographieValidator(){};

    @Override
    protected void modelValidate(BiographieModel object) throws ValidatorException, DAOException {
        if (object.getBioInitiale() == null)
            throw new ValidatorException("Une biographie doit avoir une biographie initiale");
        
        Set<EpisodeModel> episodes = object.getFullBiographie();
        for (EpisodeModel episode : episodes) {
            EpisodeValidator.instance().modelValidate(episode);
        }
    }
}
