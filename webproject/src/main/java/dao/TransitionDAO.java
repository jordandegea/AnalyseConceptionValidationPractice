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
    
    
    public static TransitionDAO instance() {
        return instanceUnique;
    }
    
    private TransitionDAO(/*DataSource ds*/) {
        super(/*ds*/);
    }

    // Personal DAOs Methods
    public BiographieModel getBiographie(TransitionModel transition) throws DAOException{
        BiographieModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT b.idBiographie  FROM Biographie b, Resume r WHERE b.idBioInitiale = r.idEpisode AND r.idEpisode= "+ transition.getId());
            if (rs.next()) {
                result = new BiographieModel(rs.getInt("idBiographie"));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError ResumeDAO.getBiographie " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result; 
    }
    
    
    @Override
    public AbstractBaseModel get(int id) throws DAOException {
        TransitionModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Episode WHERE idEpisode='" + id + "' AND typeEpisode='Transition' ");
            if (rs.next()) {
                result = new TransitionModel(id, rs.getDate("dateEpisode"), rs.getBoolean("ecritureEnCours"));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
    
    @Override
    public List<AbstractBaseModel> getAll() throws DAOException {
        List<AbstractBaseModel> result = new ArrayList<>();
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Episode WHERE typeEpisode='Transition' ");
            while (rs.next()) {
                TransitionModel ouvrage
                    = new TransitionModel(rs.getInt("idEpisode"), rs.getDate("dateEpisode"), rs.getBoolean("ecritureEnCours"));
                result.add(ouvrage);
            }
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
}
