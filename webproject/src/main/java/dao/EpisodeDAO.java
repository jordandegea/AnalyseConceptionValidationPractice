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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.AbstractBaseModel;
import model.BiographieModel;
import model.EpisodeModel;
import model.JoueurModel;
import model.ParagrapheModel;
import model.PartieModel;
import model.ResumeModel;
import model.TransitionModel;

/**
 *
 * @author JordanLeMagnifique
 */
public class EpisodeDAO extends AbstractDataBaseDAO{
    final private static EpisodeDAO instanceUnique = new EpisodeDAO();

    public static EpisodeDAO instance() {
        return instanceUnique;
    }

    protected EpisodeDAO(/*DataSource ds*/) {
        super(/*ds*/);
    }
    
    // Personal DAOs Methods
    public Set<ParagrapheModel> getParagraphes(EpisodeModel episode) throws DAOException{
        Set<ParagrapheModel> result = new HashSet<>();
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Paragraphe WHERE idEpisode='"+episode.getId()+"'");
            while (rs.next()) {
                ParagrapheModel para
                        = new ParagrapheModel(rs.getInt("idParagraphe"),rs.getBoolean("secret"), rs.getString("contenu"));
                result.add(para);
            }
        } catch (SQLException e) {
            throw new DAOException("DBError EpisodeDAO.getParagraphes() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
    
    
    // Override Methods
    
    @Override
    public int insert(Object object) throws DAOException {
        if (!(object instanceof EpisodeModel)) {
            throw new DAOException("Wrong object parameter in insert, require EpisodeModel");
        }
        int affectedRows = 0 ;
        int id = this.getId();
        EpisodeModel episode = (EpisodeModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement st = 
                    conn.prepareStatement("INSERT INTO Episode (ecritureEnCours, typeEpisode, dateEpisode, idEpisode) VALUES (?,?,?,?)");
            st.setBoolean(1, episode.isEcritureEnCours());
            st.setString(2, "Transition");
            st.setDate(3, episode.getDate());
            st.setInt(4, id);
            affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new DAOException("Creating Episode failed, no rows affected.");
            }

        } catch (SQLException e) {
            throw new DAOException("DBError EpisodeDAO.insert() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        episode.setId(id);
        for (ParagrapheModel p : episode.getParagraphes())
            ParagrapheDAO.instance().insert(p);
        
        return affectedRows ;
    }
    

    
    @Override
    public int update(Object object) throws DAOException {
        if (!(object instanceof EpisodeModel)) {
            throw new DAOException("Wrong object parameter in update, require EpisodeModel");
        }
        int affectedRows = 0;
        EpisodeModel episode = (EpisodeModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("UPDATE Episode SET ecritureEnCours=?, dateEpisode=? WHERE idEpisode=?");
            st.setBoolean(1, episode.isEcritureEnCours());
            st.setDate(2, episode.getDate());
            st.setInt(3, episode.getId());
            affectedRows = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError EpisodeDAO.update() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return affectedRows ;
    }
    
    

    @Override
    public int delete(Object object) throws DAOException {
        if (!(object instanceof EpisodeModel)) {
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
            throw new DAOException("DBError EpisodeDAO.delete() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return affectedRows ;
    }

    @Override
    public AbstractBaseModel get(int id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<? extends AbstractBaseModel> getAll() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
