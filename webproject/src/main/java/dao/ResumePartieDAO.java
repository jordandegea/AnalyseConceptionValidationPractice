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
import model.BiographieModel;
import model.PartieModel;
import model.PersonnageModel;
import model.ResumePartieModel;
import model.ResumePartieModel;

/**
 *
 * @author JordanLeMagnifique
 */
public class ResumePartieDAO extends EpisodeDAO {

    final private static ResumePartieDAO instanceUnique = new ResumePartieDAO();

    public static ResumePartieDAO instance() {
        return instanceUnique;
    }

    private ResumePartieDAO(/*DataSource ds*/) {
        super(/*ds*/);
    }

    public PartieModel getPartie(ResumePartieModel resume) throws DAOException {
        PartieModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT p.idPartie , p.titrePartie, p.resumePartie, p.datePartie, p.lieu, p.termine, p.idUnivers, p.idJoueur  "
                            + "FROM Partie p "
                            + "WHERE p.idResume="+ resume.getId());
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
        ResumePartieModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Episode WHERE idEpisode='" + id + "' AND typeEpisode='ResumePartie' ");
            if (rs.next()) {
                result = new ResumePartieModel(id, rs.getDate("dateEpisode"), rs.getBoolean("ecritureEnCours"));
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
            ResultSet rs = st.executeQuery("SELECT * FROM Episode WHERE typeEpisode='ResumePartie' ");
            while (rs.next()) {
                ResumePartieModel ouvrage
                        = new ResumePartieModel(rs.getInt("idEpisode"), rs.getDate("dateEpisode"), rs.getBoolean("ecritureEnCours"));
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
        if (!(object instanceof ResumePartieModel)) {
            throw new DAOException("Wrong object parameter in insert, require ResumeModel");
        }
        int affectedRows = 0;
        int id = this.getId();
        ResumePartieModel resume = (ResumePartieModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("INSERT INTO Episode (ecritureEnCours, typeEpisode, dateEpisode, idEpisode) VALUES (?,?,?,?)");
            st.setBoolean(1, resume.isEcritureEnCours());
            st.setString(2, "ResumePartie");
            st.setDate(3, resume.getDate());
            st.setInt(4, id);
            affectedRows = st.executeUpdate();

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
        return super.update(object);
    }

    @Override
    public int delete(Object object) throws DAOException {
        if (!(object instanceof ResumePartieModel)) {
            throw new DAOException("Wrong object parameter in delete, require ResumeModel");
        }
        int affectedRows = 0;
        ResumePartieModel resume = (ResumePartieModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st = conn.prepareStatement("DELETE FROM Episode WHERE idEpisode=?");
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
