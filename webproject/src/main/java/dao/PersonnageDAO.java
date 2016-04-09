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
import model.UniversModel;

/**
 *
 * @author JordanLeMagnifique
 */
public class PersonnageDAO extends AbstractDataBaseDAO{
    final private static PersonnageDAO instanceUnique = new PersonnageDAO();

    public static PersonnageDAO instance() {
        return instanceUnique;
    }

    private PersonnageDAO(/*DataSource ds*/) {
        super(/*ds*/);
    }

            
    
    // Personal DAOs Methods
    
    public UniversModel getUnivers(PersonnageModel personnage) throws DAOException{
        UniversModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT u.idUnivers, u.nomUnivers FROM Univers u, Personnage p WHERE p.idPersonnage="+personnage.getId()+" AND u.idUnivers=p.idUnivers");
            if (rs.next()) {
                result = new UniversModel(rs.getInt("idUnivers"), rs.getString("nomUnivers"));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError UniversDAO.getUnivers() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
    
    public PartieModel getPartieEnCours(PersonnageModel personnage) throws DAOException{
        throw new DAOException("Not Implemented Yet");
    }
    public BiographieModel getBiographie(PersonnageModel personnage) throws DAOException{
        BiographieModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT idBiographie FROM Personnage p WHERE idPersonnage="+personnage.getId());
            if (rs.next()) {
                result = new BiographieModel(rs.getInt("idBiographie"));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError UniversDAO.getUnivers() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
    public JoueurModel getMJ(PersonnageModel personnage) throws DAOException{
        // TODO: complete that
        throw new DAOException("Not Implemented Yet");
    }
    public JoueurModel getOwner(PersonnageModel personnage) throws DAOException{
        // TODO: complete that
        throw new DAOException("Not Implemented Yet");
    }
    public Set<PartieModel> getParties(PersonnageModel personnage) throws DAOException{
        // TODO: complete that
        throw new DAOException("Not Implemented Yet");
    }
    
    
    
    // Override Methods    
    @Override
    public PersonnageModel get(int id) throws DAOException {
        PersonnageModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Personnage WHERE idPersonnage='" + id + "'");
            if (rs.next()) {
                result = new PersonnageModel(
                        id,
                        rs.getString("nomPerso"), 
                        rs.getString("dateNaissance"), 
                        rs.getString("profession"), 
                        rs.getString("portrait")
                );
            }
        } catch (SQLException e) {
            throw new DAOException("DBError PersonnageDAO.get() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    @Override
    public List<PersonnageModel> getAll() throws DAOException {
        List<PersonnageModel> result = new ArrayList<>();
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Personnage");
            while (rs.next()) {
                PersonnageModel ouvrage
                     = new PersonnageModel(
                        rs.getInt("idPersonnage"),
                        rs.getString("nomPerso"), 
                        rs.getString("dateNaissance"), 
                        rs.getString("profession"), 
                        rs.getString("portrait")
                     );
                result.add(ouvrage);
            }
        } catch (SQLException e) {
            throw new DAOException("DBError PersonnageDAO.getAll() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    @Override
    public int insert(Object object) throws DAOException {
        if (!(object instanceof PersonnageModel)) {
            throw new DAOException("Wrong object parameter in insert, require PersonnageModel");
        }
        int affectedRows = 0;
        int id = this.getId();
        PersonnageModel personnage = (PersonnageModel) object;
        BiographieDAO.instance().insert(personnage.getBiographie());
        
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("INSERT INTO Personnage (nomPerso, dateNaissance, profession, portrait, idJoueur, idUnivers, idBiographie, idPersonnage) VALUES (?,?,?,?,?,?,?,?)");
            st.setString(1, personnage.getNomPerso());
            st.setString(2, personnage.getDateNaiss());
            st.setString(3, personnage.getProfession());
            st.setString(4, personnage.getPortrait());
            st.setInt(5, personnage.getOwner().getId());
            st.setInt(6, personnage.getUnivers().getId());
            st.setInt(7, personnage.getBiographie().getId());
            st.setInt(8, id);
            affectedRows = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError PersonnageDAO.insert() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        personnage.setId(id);
        return affectedRows ;
    }

    @Override
    public int update(Object object) throws DAOException {
        if (!(object instanceof PersonnageModel)) {
            throw new DAOException("Wrong object parameter in update, require PersonnageModel");
        }
        int affectedRows = 0 ; 
        PersonnageModel personnage = (PersonnageModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("UPDATE Personnage SET "
                            + "nomPerso=?, dateNaissance=?, profession=?, portrait=?, idJoueur=?, idUnivers=?, idPartie=?, idBiographie=? "
                            + "WHERE idPersonnage=?");
            st.setString(1, personnage.getNomPerso());
            st.setString(2, personnage.getDateNaiss());
            st.setString(3, personnage.getProfession());
            st.setString(4, personnage.getPortrait());
            st.setInt(5, personnage.getOwner().getId());
            st.setInt(6, personnage.getUnivers().getId());
            st.setInt(7, personnage.getPartieEnCours().getId());
            st.setInt(8, personnage.getBiographie().getId());
            st.setInt(9, personnage.getId());
            affectedRows = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError PersonnageDAO.update() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return affectedRows ;
    }

    @Override
    public int delete(Object object) throws DAOException {
        if (!(object instanceof PersonnageModel)) {
            throw new DAOException("Wrong object parameter in delete, require PersonnageModel");
        }
        int affectedRows = 0;
        PersonnageModel personnage = (PersonnageModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("DELETE FROM Personnage WHERE idPersonnage=?");
            st.setInt(1, personnage.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError PersonnageDAO.delete() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return affectedRows ;
    }
}
