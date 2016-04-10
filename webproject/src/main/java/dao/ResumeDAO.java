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
import model.PartieModel;
import model.PersonnageModel;
import model.ResumeModel;

/**
 *
 * @author JordanLeMagnifique
 */
public class ResumeDAO extends EpisodeDAO {

    final private static ResumeDAO instanceUnique = new ResumeDAO();

    public static ResumeDAO instance() {
        return instanceUnique;
    }

    private ResumeDAO(/*DataSource ds*/) {
        super(/*ds*/);
    }

    // Personal DAOs Methods
    public BiographieModel getBiographie(ResumeModel resume) throws DAOException {
        BiographieModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT b.idBiographie  FROM Biographie b, Resume r WHERE b.idBioInitiale = r.idEpisode AND r.idEpisode= "+ resume.getId());
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

    public PartieModel getPartie(ResumeModel resume) throws DAOException {
        PartieModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT p.idPartie , p.titrePartie, p.resumePartie, p.datePartie, p.lieu, p.termine, p.idUnivers, p.idJoueur  FROM Resume r, Partie p  WHERE r.idPartie = p.idPartie AND r.idEpisode= "+ resume.getId());
            if (rs.next()) {
                result = new PartieModel(rs.getInt("idPartie"),rs.getString("titrePartie"), rs.getString("resumePartie"), rs.getString("datePartie"), rs.getString("lieu"), ((rs.getInt("termine")==1)?true:false));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError ResumeDAO.getPartie() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;     }

    @Override
    public AbstractBaseModel get(int id) throws DAOException {
        ResumeModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Episode WHERE idEpisode='" + id + "' AND typeEpisode='Resume' ");
            if (rs.next()) {
                result = new ResumeModel(id, rs.getDate("dateEpisode"), rs.getBoolean("ecritureEnCours"));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    public List<AbstractBaseModel> getAll() throws DAOException {
        List<AbstractBaseModel> result = new ArrayList<>();
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Episode WHERE typeEpisode='Resume' ");
            while (rs.next()) {
                ResumeModel ouvrage
                        = new ResumeModel(rs.getInt("idEpisode"), rs.getDate("dateEpisode"), rs.getBoolean("ecritureEnCours"));
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
        if (!(object instanceof ResumeModel)) {
            throw new DAOException("Wrong object parameter in insert, require ResumeModel");
        }
        int affectedRows = 0;
        int id = this.getId();
        ResumeModel resume = (ResumeModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("INSERT INTO Episode (ecritureEnCours, typeEpisode, dateEpisode, idEpisode) VALUES (?,?,?,?)");
            st.setBoolean(1, resume.isEcritureEnCours());
            st.setString(2, "Resume");
            st.setDate(3, resume.getDate());
            st.setInt(4, id);
            affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new DAOException("Creating Episode failed, no rows affected.");
            }

            st = conn.prepareStatement("INSERT INTO Resume (idEpisode, idPartie ) VALUES (?,?)");
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
        int affectedRows = 0;
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
