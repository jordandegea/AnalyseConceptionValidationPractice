/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.AbstractBaseModel;
import model.BioInitialeModel;
import model.BiographieModel;
import model.ResumeModel;
import model.TransitionModel;

/**
 *
 * @author JordanLeMagnifique
 */
public class TransitionDAO extends EpisodeDAO{
    final private static TransitionDAO instanceUnique = new TransitionDAO();
    
    
    static TransitionDAO instance() {
        return instanceUnique;
    }
    
    private TransitionDAO(/*DataSource ds*/) {
        super(/*ds*/);
    }

    // Personal DAOs Methods
    public BiographieModel getBiographie(TransitionModel bio) throws DAOException{
        // TODO: complete that
        throw new DAOException("Not Implemented Yet");
    }
    
    
    // Override Methods
    @Override
    public AbstractBaseModel get(int id) throws DAOException {
        return super.get(id, "Transition");
    }
    
    @Override
    public List<AbstractBaseModel> getAll() throws DAOException {
        return super.getAll("Transition");
    }
}
