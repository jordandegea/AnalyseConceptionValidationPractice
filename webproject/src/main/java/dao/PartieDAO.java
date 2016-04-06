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
import java.util.Set;
import model.AbstractBaseModel;
import model.EpisodeModel;
import model.JoueurModel;
import model.ParagrapheModel;
import model.PartieModel;
import model.PersonnageModel;
import model.UniversModel;

/**
 *
 * @author JordanLeMagnifique
 */
public class PartieDAO extends AbstractDataBaseDAO{
    final private static PartieDAO instanceUnique = new PartieDAO();

    static PartieDAO instance() {
        return instanceUnique;
    }

    private PartieDAO(/*DataSource ds*/) {
        super(/*ds*/);
    }

    // Personal DAOs Methods
    public JoueurModel getJoueur(PartieModel bio) throws DAOException{
        // TODO: complete that
        throw new DAOException("Not Implemented Yet");
    }
    
    public Set<PersonnageModel> getPersonnages(PartieModel partie) throws DAOException{
        // TODO: complete that
        throw new DAOException("Not Implemented Yet");
    }
    
    // Override Methods
    @Override
    public AbstractBaseModel get(int id) throws DAOException {
        PartieModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Partie WHERE idPartie='" + id + "'");
            if (rs.next()) {
                result = new PartieModel(
                        id,
                        rs.getString("titrePartie"), 
                        rs.getString("resumePartie"), 
                        rs.getDate("date"), 
                        rs.getString("lieu"), 
                        rs.getBoolean("termine"));
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
            ResultSet rs = st.executeQuery("SELECT * FROM Partie");
            while (rs.next()) {
                PartieModel ouvrage
                     = new PartieModel(
                        rs.getInt("idPartie"),
                        rs.getString("titrePartie"), 
                        rs.getString("resumePartie"), 
                        rs.getDate("date"), 
                        rs.getString("lieu"), 
                        rs.getBoolean("termine"));
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
        if (!(object instanceof PartieModel)) {
            throw new DAOException("Wrong object parameter in insert, require PartieModel");
        }
        int affectedRows = 0;
        PartieModel partie = (PartieModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("INSERT INTO Partie (titrePartie ,resumePartie, datePartie, lieu, termine, idUnivers, loginMJ) VALUES (?,?,?)");
            st.setString(1, partie.getTitrePartie());
            st.setString(2, partie.getRésumé());
            st.setDate(3, partie.getDate());
            st.setString(4, partie.getLieu());
            st.setBoolean(5, partie.isPartieFinie());
            st.setInt(6, partie.getUnivers().getId());
            st.setInt(7, partie.getMJ().getId());
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
        if (!(object instanceof PartieModel)) {
            throw new DAOException("Wrong object parameter in update, require PartieModel");
        }
        int affectedRows = 0 ; 
        PartieModel partie = (PartieModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("UPDATE Partie SET titrePartie=?, resumePartie=?, datePartie=?, lieu=?, termine=?, idUnivers=?, loginMJ=? WHERE idPartie=?");
            st.setString(1, partie.getTitrePartie());
            st.setString(2, partie.getRésumé());
            st.setDate(3, partie.getDate());
            st.setString(4, partie.getLieu());
            st.setBoolean(5, partie.isPartieFinie());
            st.setInt(6, partie.getUnivers().getId());
            st.setInt(7, partie.getMJ().getId());
            st.setInt(8, partie.getId());
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
        if (!(object instanceof PartieModel)) {
            throw new DAOException("Wrong object parameter in delete, require PartieModel");
        }
        int affectedRows = 0;
        PartieModel partie = (PartieModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("DELETE FROM Partie WHERE idPartie=?");
            st.setInt(1, partie.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return affectedRows ;
    }
}
