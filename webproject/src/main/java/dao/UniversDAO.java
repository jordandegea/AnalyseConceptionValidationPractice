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
import javax.sql.DataSource;
import model.AbstractBaseModel;
import model.UniversModel;

/**
 *
 * @author JordanLeMagnifique
 */
public class UniversDAO extends AbstractDataBaseDAO {

    final private static UniversDAO instanceUnique = new UniversDAO();
    
    
    static UniversDAO instance() {
        return instanceUnique;
    }
    
    private UniversDAO(/*DataSource ds*/) {
        super(/*ds*/);
    }

    @Override
    public AbstractBaseModel get(int id) throws DAOException {
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
    
    public AbstractBaseModel get(String nom) throws DAOException {
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
    public List<AbstractBaseModel> getAll() throws DAOException {
        List<AbstractBaseModel> result = new ArrayList<>();
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
    public void insert(Object object) throws DAOException {
        if (!(object instanceof UniversModel)) {
            throw new DAOException("Wrong object parameter in insert, require UniversModel");
        }
        UniversModel univers = (UniversModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("INSERT INTO Univers (nomUnivers) VALUES (?)");
            st.setString(1, univers.getNom());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }
    

    @Override
    public void update(Object object) throws DAOException {
        if (!(object instanceof UniversModel)) {
            throw new DAOException("Wrong object parameter in update, require UniversModel");
        }
        UniversModel univers = (UniversModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("UPDATE Univers SET nomUnivers=? WHERE idUnivers=?");
            st.setString(1, univers.getNom());
            st.setInt(2, univers.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }

    @Override
    public void delete(Object object) throws DAOException {
        if (!(object instanceof UniversModel)) {
            throw new DAOException("Wrong object parameter in delete, require UniversModel");
        }
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
    }


}
