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
import java.util.HashSet;
import java.util.Set;
import model.AbstractBaseModel;
import model.BioInitialeModel;
import model.BiographieModel;

/**
 *
 * @author JordanLeMagnifique
 */
public class BioInitialeDAO extends EpisodeDAO {

    final private static BioInitialeDAO instanceUnique = new BioInitialeDAO();
    
    
    public static BioInitialeDAO instance() {
        return instanceUnique;
    }
    
    private BioInitialeDAO(/*DataSource ds*/) {
        super(/*ds*/);
    }

    // Personal DAOs Methods
    public BiographieModel getBiographie(BioInitialeModel bio) throws DAOException{
        BiographieModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Biographie WHERE idBioInitiale='" + bio.getId() + "' ");
            if (rs.next()) {
                result = new BiographieModel(rs.getInt("idBiographie"));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        
        result.setBioInitiale(bio);
        return result;
    }
    
    
    // Override Methods
    @Override
    public AbstractBaseModel get(int id) throws DAOException {
        BioInitialeModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Episode WHERE idEpisode='" + id + "' AND typeEpisode='BioInitiale' ");
            if (rs.next()) {
                result = new BioInitialeModel(id);
            }
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
    
    @Override
    public Set<BioInitialeModel> getAll() throws DAOException {
        Set<BioInitialeModel> result = new HashSet<>();
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Episode WHERE typeEpisode='BioInitiale' ");
            while (rs.next()) {
                BioInitialeModel bio
                    = new BioInitialeModel(rs.getInt("idEpisode"));
                result.add(bio);
            }
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

}
