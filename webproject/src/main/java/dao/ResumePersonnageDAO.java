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
import model.BiographieModel;
import model.PartieModel;
import model.ResumePartieModel;
import model.ResumePersonnageModel;

/**
 *
 * @author JordanLeMagnifique
 */
public class ResumePersonnageDAO extends EpisodeDAO {

    final private static ResumePersonnageDAO instanceUnique = new ResumePersonnageDAO();

    public static ResumePersonnageDAO instance() {
        return instanceUnique;
    }

    private ResumePersonnageDAO(/*DataSource ds*/) {
        super(/*ds*/);
    }

    // Personal DAOs Methods
    public BiographieModel getBiographie(ResumePersonnageModel resume) throws DAOException {
        BiographieModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT b.idBiographie FROM Biographie b, ResumePersonnage r WHERE b.idBioInitiale = r.idEpisode AND r.idEpisode= "+ resume.getId());
            if (rs.next()) {
                result = new BiographieModel(rs.getInt("idBiographie"));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError ResumeDAO.getBiographie() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;    
    }

    public PartieModel getPartie(ResumePersonnageModel resume) throws DAOException {
        PartieModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT p.idPartie , p.titrePartie, p.resumePartie, p.datePartie, p.lieu, p.termine, p.idUnivers, p.idJoueur  FROM ResumePersonnage r, Partie p  WHERE r.idPartie = p.idPartie AND r.idEpisode= "+ resume.getId());
            if (rs.next()) {
                result = new PartieModel(rs.getInt("idPartie"),rs.getString("titrePartie"), rs.getString("resumePartie"), rs.getString("datePartie"), rs.getString("lieu"), ((rs.getInt("termine")==1)?true:false));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError ResumeDAO.getPartie() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;     
    }
    
    public PartieModel getPartie(ResumePartieModel resume) throws DAOException {
        PartieModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT p.idPartie , p.titrePartie, p.resumePartie, p.datePartie, p.lieu, p.termine, p.idUnivers, p.idJoueur  "
                            + "FROM Partie p ON r.idPartie = p.idPartie "
                            + "WHERE p.idResume= "+ resume.getId());
            if (rs.next()) {
                result = new PartieModel(rs.getInt("idPartie"),rs.getString("titrePartie"), rs.getString("resumePartie"), rs.getString("datePartie"), rs.getString("lieu"), ((rs.getInt("termine")==1)?true:false));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError ResumeDAO.getPartie() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;     
    }

    @Override
    public AbstractBaseModel get(int id) throws DAOException {
        ResumePersonnageModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Episode WHERE idEpisode='" + id + "' AND typeEpisode='ResumePersonnage' ");
            if (rs.next()) {
                result = new ResumePersonnageModel(id, rs.getDate("dateEpisode"), rs.getBoolean("ecritureEnCours"));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    public Set<AbstractBaseModel> getAll() throws DAOException {
        Set<AbstractBaseModel> result = new HashSet<>();
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Episode WHERE typeEpisode='ResumePersonnage' ");
            while (rs.next()) {
                ResumePersonnageModel ouvrage
                        = new ResumePersonnageModel(rs.getInt("idEpisode"), rs.getDate("dateEpisode"), rs.getBoolean("ecritureEnCours"));
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
        if (!(object instanceof ResumePersonnageModel)) {
            throw new DAOException("Wrong object parameter in insert, require ResumeModel");
        }
        int affectedRows = 0;
        int id = this.getId();
        ResumePersonnageModel resume = (ResumePersonnageModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("INSERT INTO Episode (ecritureEnCours, typeEpisode, dateEpisode, idEpisode) VALUES (?,?,?,?)");
            st.setBoolean(1, resume.isEcritureEnCours());
            st.setString(2, "ResumePersonnage");
            st.setDate(3, resume.getDate());
            st.setInt(4, id);
            affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new DAOException("Creating Episode failed, no rows affected.");
            }

            st = conn.prepareStatement("INSERT INTO ResumePersonnage (idEpisode, idPartie ) VALUES (?,?)");
            st.setInt(1, id);
            st.setInt(2, resume.getPartie().getId());

        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        
        resume.setId(id);
        return affectedRows;
    }

    @Override
    public int update(Object object) throws DAOException {
        if (!(object instanceof ResumePersonnageModel)) {
            throw new DAOException("Wrong object parameter in update, require ResumeModel");
        }
        int affectedRows = 0;
        ResumePersonnageModel resume = (ResumePersonnageModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("UPDATE ResumePersonnage SET idPartie=? WHERE idEpisode=?");
            st.setInt(1, resume.getPartie().getId());
            st.setInt(2, resume.getId());
            affectedRows = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return super.update(object);
    }

    @Override
    public int delete(Object object) throws DAOException {
        if (!(object instanceof ResumePersonnageModel)) {
            throw new DAOException("Wrong object parameter in delete, require ResumeModel");
        }
        int affectedRows = 0;
        ResumePersonnageModel resume = (ResumePersonnageModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("DELETE FROM ResumePersonnage WHERE idEpisode=?");
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
