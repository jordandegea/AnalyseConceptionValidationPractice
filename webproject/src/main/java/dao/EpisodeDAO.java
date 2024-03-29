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
import java.util.LinkedHashSet;
import java.util.Set;
import model.AbstractBaseModel;
import model.EpisodeModel;
import model.ParagrapheModel;

/**
 *
 * @author JordanLeMagnifique
 */
public class EpisodeDAO extends AbstractDataBaseDAO {

    final private static EpisodeDAO instanceUnique = new EpisodeDAO();

    public static EpisodeDAO instance() {
        return instanceUnique;
    }

    protected EpisodeDAO(/*DataSource ds*/) {
        super(/*ds*/);
    }

    // Personal DAOs Methods
    public Set<ParagrapheModel> getParagraphes(EpisodeModel episode) throws DAOException {
        Set<ParagrapheModel> result = new LinkedHashSet<>();
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Paragraphe WHERE idEpisode='" + episode.getId() + "' ORDER BY numeroPar");
            while (rs.next()) {
                ParagrapheModel para
                        = new ParagrapheModel(rs.getInt("idParagraphe"), rs.getBoolean("secret"), rs.getString("contenu"), rs.getInt("numeroPar"));
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
        int affectedRows = 0;
        int id = this.getId();
        EpisodeModel episode = (EpisodeModel) object;
        Connection conn = null;
        try {
            conn = getConnection();

            PreparedStatement st
                    = conn.prepareStatement("INSERT INTO Episode (validationJoueur, validationMJ, typeEpisode, dateEpisode, idEpisode) VALUES (?,?,?,?,?)");
            st.setBoolean(1, episode.isValidJoueur());
            st.setBoolean(2, episode.isValidMJ());
            st.setString(3, "Transition");
            st.setDate(4, episode.getDate());
            st.setInt(5, id);
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
        for (ParagrapheModel p : episode.getParagraphes()) {
            p.setEpisode(episode);
            ParagrapheDAO.instance().insert(p);
        }

        return affectedRows;
    }

    public int insertEpisodeBiographie(Object object, int n_bm) throws DAOException {
        if (!(object instanceof EpisodeModel)) {
            throw new DAOException("Wrong object parameter in insert, require EpisodeModel");
        }
        int affectedRows = 0;
        int id = this.getId();
        EpisodeModel episode = (EpisodeModel) object;
        Connection conn = null;
        try {
            conn = getConnection();

            PreparedStatement st
                    = conn.prepareStatement("INSERT INTO EpisodeBiographie (idEpisode, idBiographie) VALUES (?,?)");
            st.setInt(1, episode.getId());
            st.setInt(2, n_bm);
            affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new DAOException("Creating BiographieEpisode failed, no rows affected.");
            }

        } catch (SQLException e) {
            throw new DAOException("DBError EpisodeDAO.insert() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return affectedRows;
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
                    = conn.prepareStatement("UPDATE Episode SET validationJoueur=?, validationMJ=?, dateEpisode=? WHERE idEpisode=?");
            st.setBoolean(1, episode.isValidJoueur());
            st.setBoolean(2, episode.isValidMJ());
            st.setDate(3, episode.getDate());
            st.setInt(4, episode.getId());
            affectedRows = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError EpisodeDAO.update() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return affectedRows;
    }

    @Override
    public int delete(Object object) throws DAOException {
        if (!(object instanceof EpisodeModel)) {
            throw new DAOException("Wrong object parameter in delete, require EpisodeModel");
        }
        int affectedRows = 0;
        EpisodeModel episode = (EpisodeModel) object;
        for (ParagrapheModel p : episode.getParagraphes()) {
            ParagrapheDAO.instance().delete(p);
        }
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st = conn.prepareStatement("DELETE FROM Episode WHERE idEpisode=?");
            st.setInt(1, episode.getId());
            affectedRows = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError EpisodeDAO.delete() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return affectedRows;
    }

    @Override
    public AbstractBaseModel get(int id) throws DAOException {
        EpisodeModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Episode WHERE idEpisode=" + id);
            if (rs.next()) {
                result = new EpisodeModel(rs.getInt("idEpisode"), rs.getDate("dateEpisode"), rs.getBoolean("validationJoueur"), rs.getBoolean("validationMJ"));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError EpisodeDAO.getParagraphes() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
    
    public Set<EpisodeModel> getEpModifiables(int id) throws DAOException {
        Set<EpisodeModel> result = new LinkedHashSet<EpisodeModel>();
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Episode e, Personnage p, EpisodeBiographie eb WHERE p.idBiographie = eb.idBiographie AND eb.idEpisode = E.idEpisode AND idPersonnage =" + id + " AND e.validationJoueur = 0 and e.validationMJ = 0 ORDER BY dateEpisode");
            while (rs.next()) {
                EpisodeModel em = new EpisodeModel(rs.getInt("idEpisode"), rs.getDate("dateEpisode"), rs.getBoolean("validationJoueur"), rs.getBoolean("validationMJ"));
                result.add(em);
            }
        } catch (SQLException e) {
            throw new DAOException("DBError EpisodeDAO.getParagraphes() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    @Override
    public Set<? extends AbstractBaseModel> getAll() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
