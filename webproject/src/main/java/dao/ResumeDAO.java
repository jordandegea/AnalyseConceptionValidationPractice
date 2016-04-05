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
import model.ResumeModel;

/**
 *
 * @author JordanLeMagnifique
 */
public class ResumeDAO extends EpisodeDAO{
    
    final private static ResumeDAO instanceUnique = new ResumeDAO();
    
    
    static ResumeDAO instance() {
        return instanceUnique;
    }
    
    private ResumeDAO(/*DataSource ds*/) {
        super(/*ds*/);
    }

    
    @Override
    public AbstractBaseModel get(int id) throws DAOException {
        return super.get(id, "Resume");
    }
    
    @Override
    public List<AbstractBaseModel> getAll() throws DAOException {
        return super.getAll("Resume");
    }

    @Override
    public int insert(Object object) throws DAOException {
        if (!(object instanceof ResumeModel)) {
            throw new DAOException("Wrong object parameter in insert, require ResumeModel");
        }
        int affectedRows = 0;
        ResumeModel resume = (ResumeModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st = 
                    conn.prepareStatement("INSERT INTO Episode (dateEpisode, ecritureEnCours, typeEpisode ) VALUES (?,?,?)");
            st.setDate(1, resume.getDateEpisode());
            st.setBoolean(2, resume.isEcritureEnCours());
            st.setString(3, "Resume");
            affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new DAOException("Creating Episode failed, no rows affected.");
            }

            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    st = conn.prepareStatement("INSERT INTO Resume (idEpisode, idPartie ) VALUES (?,?)");
                    st.setInt(1, generatedKeys.getInt(1));
                    st.setInt(2, resume.getPartie().getId());
                }else {
                    throw new SQLException("Creating Episode failed, no ID obtained.");
                }
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
        if (!(object instanceof ResumeModel)) {
            throw new DAOException("Wrong object parameter in update, require ResumeModel");
        }
        int affectedRows = 0;
        ResumeModel resume = (ResumeModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("UPDATE Resume SET idPartie=? WHERE idEpisode=?");
            st.setInt(1, resume.getPartie().getId());
            st.setInt(2, resume.getId());
            affectedRows = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return affectedRows;
    }

    @Override
    public int delete(Object object) throws DAOException {
        if (!(object instanceof ResumeModel)) {
            throw new DAOException("Wrong object parameter in delete, require ResumeModel");
        }
        int affectedRows = 0 ;
        ResumeModel resume = (ResumeModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("DELETE FROM Resume WHERE idEpisode=?");
            st.setInt(1, resume.getId());
            
            affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new DAOException("Creating Episode failed, no rows affected.");
            }
            
            st = conn.prepareStatement("DELETE FROM Episode WHERE idEpisode=?");
            st.setInt(1, resume.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return affectedRows;
    }

}