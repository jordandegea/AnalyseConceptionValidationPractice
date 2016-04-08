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
import model.BiographieModel;
import model.JoueurModel;
import model.PartieModel;
import model.PersonnageModel;
import model.UniversModel;

/**
 *
 * @author JordanLeMagnifique
 */
public class JoueurDAO extends AbstractDataBaseDAO{
    final private static JoueurDAO instanceUnique = new JoueurDAO();

    public static JoueurDAO instance() {
        return instanceUnique;
    }

    private JoueurDAO(/*DataSource ds*/) {
        super(/*ds*/);
    }
    
    
    // Personal DAOs Methods
    public Set<PartieModel> getParties(JoueurModel personnage) throws DAOException{
        // TODO: complete that
        throw new DAOException("Not Implemented Yet");
    }
    public Set<PersonnageModel> getPersonnagesOwned(JoueurModel joueur) throws DAOException{
        // TODO: complete that
        throw new DAOException("Not Implemented Yet");
    }
    public Set<PersonnageModel> getPersonnagesManaged(JoueurModel joueur) throws DAOException{
        // TODO: complete that
        throw new DAOException("Not Implemented Yet");
    }
    
    
    public JoueurModel get(String login) throws DAOException {
        JoueurModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Joueur WHERE login='" + login + "'");
            if (rs.next()) {
                result = new JoueurModel(Integer.parseInt(rs.getString("idJoueur")), rs.getString("login"),rs.getString("mdp"),rs.getString("email"));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
    
    // Override Methods 
    @Override
    public JoueurModel get(int id) throws DAOException {
        JoueurModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Joueur WHERE idJoueur='" + id + "'");
            if (rs.next()) {
                result = new JoueurModel(id, rs.getString("login"),rs.getString("mdp"),rs.getString("email"));
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
            ResultSet rs = st.executeQuery("SELECT * FROM Joueur");
            while (rs.next()) {
                JoueurModel ouvrage
                     = new JoueurModel(rs.getInt("idJoueur"), rs.getString("login"),rs.getString("mdp"),rs.getString("email"));
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
        if (!(object instanceof JoueurModel)) {
            throw new DAOException("Wrong object parameter in insert, require JoueurModel");
        }
        int affectedRows = 0;
        JoueurModel joueur = (JoueurModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("INSERT INTO Joueur (idJoueur, login, mdp, email) VALUES (id.nextval, ?,?,?)");
            st.setString(1, joueur.getLogin());
            st.setString(2, joueur.getPassword());
            st.setString(3, joueur.getEmail());
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
        if (!(object instanceof JoueurModel)) {
            throw new DAOException("Wrong object parameter in update, require JoueurModel");
        }
        int affectedRows = 0 ; 
        JoueurModel joueur = (JoueurModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("UPDATE Joueur SET login=?, mdp=?, email=? WHERE idJoueur=?");
            st.setString(1, joueur.getLogin());
            st.setString(2, joueur.getPassword());
            st.setString(3, joueur.getEmail());
            st.setInt(4, joueur.getId());
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
        if (!(object instanceof JoueurModel)) {
            throw new DAOException("Wrong object parameter in delete, require JoueurModel");
        }
        int affectedRows = 0;
        JoueurModel joueur = (JoueurModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("DELETE FROM Joueur  WHERE idJoueur=?");
            st.setInt(1, joueur.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return affectedRows ;
    }
}
