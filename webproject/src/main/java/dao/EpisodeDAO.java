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
import model.EpisodeModel;
import model.ResumeModel;
import model.TransitionModel;

/**
 *
 * @author JordanLeMagnifique
 */
public abstract class EpisodeDAO extends AbstractDataBaseDAO{
    
    public AbstractBaseModel get(int id, String by) throws DAOException {
        ResumeModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Episode WHERE idEpisode='" + id + "' AND typeEpisode='"+by+"' ");
            if (rs.next()) {
                result = new ResumeModel(id, rs.getDate("dateResume"), rs.getBoolean("ecritureEnCours"));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
    
    public List<AbstractBaseModel> getAll(String by) throws DAOException {
        List<AbstractBaseModel> result = new ArrayList<>();
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Episode WHERE typeEpisode='"+by+"' ");
            while (rs.next()) {
                ResumeModel ouvrage
                    = new ResumeModel(rs.getInt("idEpisode"), rs.getDate("dateResume"), rs.getBoolean("ecritureEnCours"));
                result.add(ouvrage);
            }
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
    
    
    @Override
    public int insert(Object object) throws DAOException {
        if (!(object instanceof TransitionModel)) {
            throw new DAOException("Wrong object parameter in insert, require EpisodeModel");
        }
        int affectedRows = 0 ; 
        EpisodeModel episode = (EpisodeModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st = 
                    conn.prepareStatement("INSERT INTO Episode (dateEpisode, ecritureEnCours, typeEpisode ) VALUES (?,?,?)");
            st.setDate(1, episode.getDateEpisode());
            st.setBoolean(2, episode.isEcritureEnCours());
            st.setString(3, "Transition");
            affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new DAOException("Creating Episode failed, no rows affected.");
            }

        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return affectedRows ;
    }
    

    
    @Override
    public int update(Object object) throws DAOException {
        if (!(object instanceof TransitionModel)) {
            throw new DAOException("Wrong object parameter in update, require EpisodeModel");
        }
        int affectedRows = 0;
        EpisodeModel episode = (EpisodeModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("UPDATE Episode SET dateEpisode=?, ecritureEnCours=? WHERE idEpisode=?");
            st.setDate(1, episode.getDateEpisode());
            st.setBoolean(2, episode.isEcritureEnCours());
            st.setInt(3, episode.getId());
            affectedRows = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return affectedRows ;
    }
    
    

    @Override
    public int delete(Object object) throws DAOException {
        if (!(object instanceof TransitionModel)) {
            throw new DAOException("Wrong object parameter in delete, require EpisodeModel");
        }
        int affectedRows = 0;
        EpisodeModel episode = (EpisodeModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st = conn.prepareStatement("DELETE FROM Episode WHERE idEpisode=?");
            st.setInt(1, episode .getId());
            affectedRows = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return affectedRows ;
    }
}
