/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.AbstractBaseModel;
import model.BioInitialeModel;
import model.BiographieModel;
import model.ResumeModel;

/**
 *
 * @author JordanLeMagnifique
 */
public class BioInitialeDAO extends EpisodeDAO {

    final private static BioInitialeDAO instanceUnique = new BioInitialeDAO();
    
    
    static BioInitialeDAO instance() {
        return instanceUnique;
    }
    
    private BioInitialeDAO(/*DataSource ds*/) {
        super(/*ds*/);
    }

    // Personal DAOs Methods
    public BiographieModel getBiographie(BioInitialeModel bio) throws DAOException{
        // TODO: complete that
        throw new DAOException("Not Implemented Yet");
    }
    
    
    // Override Methods
    @Override
    public AbstractBaseModel get(int id) throws DAOException {
        return super.get(id, "BioInitiale");
    }
    
    @Override
    public List<AbstractBaseModel> getAll() throws DAOException {
        return super.getAll("BioInitiale");
    }

}
