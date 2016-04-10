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
import java.util.Set;
import javax.sql.DataSource;
import model.AbstractBaseModel;
import model.JoueurModel;
import model.PartieModel;
import model.PersonnageModel;
import model.UniversModel;

/**
 *
 * @author JordanLeMagnifique
 */
public class UniversDAO extends AbstractDataBaseDAO {

    final private static UniversDAO instanceUnique = new UniversDAO();

    public static UniversDAO instance() {
        return instanceUnique;
    }

    private UniversDAO(/*DataSource ds*/) {
        super(/*ds*/);
    }

    // Personal DAOs Methods
    public Set<PersonnageModel> getPersonnages(UniversModel univers) throws DAOException{
        // TODO: complete that
        throw new DAOException("Not Implemented Yet");
    }
    
    // Override Methods
    @Override
    public UniversModel get(int id) throws DAOException {
        UniversModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Univers WHERE idUnivers='" + id + "'");
            if (rs.next()) {
                result = new UniversModel(id, rs.getString("nomUnivers"));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    public UniversModel get(String nom) throws DAOException {
        UniversModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Univers WHERE nomUnivers='" + nom + "'");
            if (rs.next()) {
                result = new UniversModel(rs.getInt("idUnivers"), rs.getString("nomUnivers"));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    @Override
    public Set<UniversModel> getAll() throws DAOException {
        Set<UniversModel> result = new HashSet<>();
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Univers");
            while (rs.next()) {
                UniversModel ouvrage
                        = new UniversModel(rs.getInt("idUnivers"), rs.getString("nomUnivers"));
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
        if (!(object instanceof UniversModel)) {
            throw new DAOException("Wrong object parameter in insert, require UniversModel");
        }
        int affectedRows = 0;
        int id = this.getId();
        UniversModel univers = (UniversModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("INSERT INTO Univers (nomUnivers, idUnivers) VALUES (?,?)");
            affectedRows = st.executeUpdate();
            st.setString(1, univers.getNom());
            st.setInt(2, id);
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        univers.setId(id);
        return affectedRows ;
    }

    @Override
    public int update(Object object) throws DAOException {
        if (!(object instanceof UniversModel)) {
            throw new DAOException("Wrong object parameter in update, require UniversModel");
        }
        int affectedRows = 0 ; 
        UniversModel univers = (UniversModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("UPDATE Univers SET nomUnivers=? WHERE idUnivers=?");
            st.setString(1, univers.getNom());
            st.setInt(2, univers.getId());
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
        if (!(object instanceof UniversModel)) {
            throw new DAOException("Wrong object parameter in delete, require UniversModel");
        }
        int affectedRows = 0;
        UniversModel univers = (UniversModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("DELETE FROM Univers  WHERE id=?");
            st.setInt(1, univers.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return affectedRows ;
    }

}
