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
import model.BioInitialeModel;
import model.BiographieModel;
import model.JoueurModel;
import model.PartieModel;
import model.PersonnageModel;
import model.ResumeModel;
import model.TransitionModel;

/**
 *
 * @author JordanLeMagnifique
 */
public class BiographieDAO extends AbstractDataBaseDAO{
    final private static BiographieDAO instanceUnique = new BiographieDAO();
    
    
    public static BiographieDAO instance() {
        return instanceUnique;
    }
    
    private BiographieDAO(/*DataSource ds*/) {
        super(/*ds*/);
    }
    
    // Personal DAOs Methods
    public BioInitialeModel getBioInitiale(BiographieModel bio) throws DAOException{
        throw new DAOException("Not Implemented Yet");
    }
     // Personal DAOs Methods
    public Set<ResumeModel> getResumes(BiographieModel bio) throws DAOException{
        // TODO: complete that
        throw new DAOException("Not Implemented Yet");
    }
     // Personal DAOs Methods
    public Set<TransitionModel> getTransitions(BiographieModel bio) throws DAOException{
        // TODO: complete that
        throw new DAOException("Not Implemented Yet");
    }
    
    
    // Override Methods
    @Override
    public AbstractBaseModel get(int id) throws DAOException {
        BiographieModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Biographie WHERE idEpisode='" + id + "' ");
            if (rs.next()) {
                result = new BiographieModel(id, rs.getInt("idBioInitiale"));
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
            ResultSet rs = st.executeQuery("SELECT * FROM Biographie");
            while (rs.next()) {
                BiographieModel ouvrage
                        = new BiographieModel(rs.getInt("idBiographie"), rs.getInt("idBioInitiale"));
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
        if (!(object instanceof BiographieModel)) {
            throw new DAOException("Wrong object parameter in insert, require BiographieModel");
        }
        int affectedRows = 0;
        int id = this.getId();
        BiographieModel biographie = (BiographieModel) object;
        BioInitialeDAO.instance().insert(biographie.getBioInitiale());
        
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("INSERT INTO Biographie (idBiographie, idBioInitiale) VALUES (id.nextval, ?)");
            st.setInt(1, biographie.getBioInitiale().getId());
            affectedRows = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        biographie.setId(id);
        return affectedRows ;
    }
    

    @Override
    public int update(Object object) throws DAOException {
        if (!(object instanceof BiographieModel)) {
            throw new DAOException("Wrong object parameter in update, require BiographieModel");
        }
        int affectedRows = 0 ;
        BiographieModel biographie = (BiographieModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("UPDATE Biographie SET idBioInitiale=? WHERE idBiographie=?");
            st.setInt(1, biographie.getBioInitiale().getId());
            st.setInt(2, biographie.getId());
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
        if (!(object instanceof BiographieModel)) {
            throw new DAOException("Wrong object parameter in delete, require BiographieModel");
        }
        int affectedRows = 0;
        BiographieModel biographie = (BiographieModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("DELETE FROM Biographie WHERE idBiographie=?");
            st.setInt(1, biographie.getId());
            affectedRows = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return affectedRows ;
    }
    
}
