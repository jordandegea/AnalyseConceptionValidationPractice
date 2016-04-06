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
import model.EpisodeModel;
import model.ParagrapheModel;

/**
 *
 * @author JordanLeMagnifique
 */
public class ParagrapheDAO extends AbstractDataBaseDAO{
    final private static ParagrapheDAO instanceUnique = new ParagrapheDAO();

    static ParagrapheDAO instance() {
        return instanceUnique;
    }

    private ParagrapheDAO(/*DataSource ds*/) {
        super(/*ds*/);
    }

    // Personal DAOs Methods
    public EpisodeModel getEpisode(ParagrapheModel bio) throws DAOException{
        // TODO: complete that
        throw new DAOException("Not Implemented Yet");
    }
    
    
    // Override Methods
    @Override
    public AbstractBaseModel get(int id) throws DAOException {
        ParagrapheModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Paragraphe WHERE idParagraphe='" + id + "'");
            if (rs.next()) {
                result = new ParagrapheModel(id,rs.getBoolean("portrait"), rs.getString("secret"));
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
            ResultSet rs = st.executeQuery("SELECT * FROM Paragraphe");
            while (rs.next()) {
                ParagrapheModel ouvrage
                     = new ParagrapheModel(rs.getInt("idParagraphe"),rs.getBoolean("portrait"), rs.getString("secret"));
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
        if (!(object instanceof ParagrapheModel)) {
            throw new DAOException("Wrong object parameter in insert, require ParagrapheModel");
        }
        int affectedRows = 0;
        ParagrapheModel paragraphe = (ParagrapheModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("INSERT INTO Paragraphe (secret, contenu, idEpisode) VALUES (?,?,?)");
            st.setBoolean(1, paragraphe.isSecret());
            st.setString(2, paragraphe.getContenu());
            st.setInt(3,  paragraphe.getEpisode().getId());
            affectedRows = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
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
            throw new DAOException("DBError " + e.getMessage(), e);
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
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return affectedRows ;
    }
}
