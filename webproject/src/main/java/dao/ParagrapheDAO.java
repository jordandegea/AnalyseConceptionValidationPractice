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
import java.util.HashSet;
import java.util.Set;
import model.AbstractBaseModel;
import model.BioInitialeModel;
import model.EpisodeModel;
import model.ParagrapheModel;
import model.ResumePersonnageModel;
import model.TransitionModel;

/**
 *
 * @author JordanLeMagnifique
 */
public class ParagrapheDAO extends AbstractDataBaseDAO{
    final private static ParagrapheDAO instanceUnique = new ParagrapheDAO();

    public static ParagrapheDAO instance() {
        return instanceUnique;
    }

    private ParagrapheDAO(/*DataSource ds*/) {
        super(/*ds*/);
    }

    // Personal DAOs Methods
    public EpisodeModel getEpisode(ParagrapheModel paragraphe) throws DAOException{
        EpisodeModel result = null;
        String type ;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Episode WHERE idEpisode='" + paragraphe.getId() + "'  ");
            if (rs.next()) {
                type = rs.getString("typeEpisode");
                if(type.equals("Resume")){
                    result = new ResumePersonnageModel(rs.getInt("idEpisode"), rs.getDate("dateEpisode"), rs.getBoolean("validationJoueur"), rs.getBoolean("validationMJ"));
                }else if(type.equals("Bio Initiale")){
                    result = new BioInitialeModel(rs.getInt("idEpisode"));
                }else if(type.equals("Transition")){
                    result = new TransitionModel(rs.getInt("idEpisode"), rs.getDate("dateEpisode"), rs.getBoolean("validationJoueur"), rs.getBoolean("validationMJ"));
                }else{
                    throw new DAOException("DBError : Episode Error");
                }
            }
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
    
    
    // Override Methods
    @Override
    public ParagrapheModel get(int id) throws DAOException {
        ParagrapheModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Paragraphe WHERE idParagraphe='" + id + "'");
            if (rs.next()) {
                result = new ParagrapheModel(id,rs.getBoolean("secret"), rs.getString("contenu"), rs.getInt("numeroPar"));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError ParagrapheDAO.get() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    @Override
    public Set<AbstractBaseModel> getAll() throws DAOException {
        Set<AbstractBaseModel> result = new HashSet<>();
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Paragraphe");
            while (rs.next()) {
                ParagrapheModel ouvrage
                     = new ParagrapheModel(rs.getInt("idParagraphe"),rs.getBoolean("secret"), rs.getString("contenu"), rs.getInt("numeroPar"));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError ParagrapheDAO.getAll() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    @Override
    public int insert(Object object) throws DAOException {
        if (!(object instanceof ParagrapheModel)) {
            throw new DAOException("Wrong object parameter in insert, require ParagrapheModel");
        }
        int affectedRows = 0;
        int id = this.getId();
        ParagrapheModel paragraphe = (ParagrapheModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("INSERT INTO Paragraphe (secret, contenu, idEpisode, numeroPar, idParagraphe) VALUES (?,?,?,?,?)");
            st.setBoolean(1, paragraphe.isSecret());
            st.setString(2, paragraphe.getContenu());
            st.setInt(3,  paragraphe.getEpisode().getId());
            st.setInt(4,  paragraphe.getNumeroPar());
            st.setInt(5, id);
            affectedRows = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError ParagrapheDAO.insert() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        paragraphe.setId(id);
        return affectedRows ;
    }

    @Override
    public int update(Object object) throws DAOException {
        if (!(object instanceof ParagrapheModel)) {
            throw new DAOException("Wrong object parameter in update, require ParagrapheModel");
        }
        int affectedRows = 0 ; 
        ParagrapheModel paragraphe = (ParagrapheModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("UPDATE Paragraphe SET secret=?, contenu=?, idEpisode=? WHERE idParagraphe=?");
            st.setBoolean(1, paragraphe.isSecret());
            st.setString(2, paragraphe.getContenu());
            st.setInt(3,  paragraphe.getEpisode().getId());
            st.setInt(4, paragraphe.getId());
            affectedRows = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError ParagrapheDAO.update() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return affectedRows ;
    }

    @Override
    public int delete(Object object) throws DAOException {
        if (!(object instanceof ParagrapheModel)) {
            throw new DAOException("Wrong object parameter in delete, require ParagrapheModel");
        }
        int affectedRows = 0;
        ParagrapheModel paragraphe = (ParagrapheModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("DELETE FROM Paragraphe  WHERE idParagraphe=?");
            st.setInt(1, paragraphe.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError ParagrapheDAO.delete() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return affectedRows ;
    }
    
    public int updateVisibility(Object object) throws DAOException {
        if (!(object instanceof ParagrapheModel)) {
            throw new DAOException("Wrong object parameter in update, require ParagrapheModel");
        }
        int affectedRows = 0 ; 
        ParagrapheModel paragraphe = (ParagrapheModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("UPDATE Paragraphe SET secret=? WHERE idParagraphe=?");
            paragraphe.setSecret(!paragraphe.isSecret());
            st.setBoolean(1, paragraphe.isSecret());
            st.setInt(2, paragraphe.getId());
            affectedRows = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError ParagrapheDAO.updateVisibility() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return affectedRows ;
    }
    
    public int getNumPerso(int id) throws DAOException {
        int result = 0;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT p.idPersonnage FROM Paragraphe par, Personnage p, EpisodeBiographie eb WHERE p.idBiographie = eb.idBiographie AND eb.idEpisode =  par.idEpisode AND par.idParagraphe='" + id + "'");
            if (rs.next()) {
                result = rs.getInt("idPersonnage");
            }
        } catch (SQLException e) {
            throw new DAOException("DBError ParagrapheDAO.getNumPerso() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
}
