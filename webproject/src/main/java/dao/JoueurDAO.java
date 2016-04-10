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
import java.util.HashSet;
import java.util.Set;
import java.util.Set;
import java.util.TreeSet;
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
    public Set<PartieModel> getParties(JoueurModel joueur) throws DAOException{
        Set<PartieModel> result = new HashSet<>();
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Partie WHERE idMJ="+joueur.getId());
            while (rs.next()) {
                PartieModel partie
                        = new PartieModel(
                        rs.getInt("idPartie"),
                        rs.getString("titrePartie"), 
                        rs.getString("resumePartie"), 
                        rs.getString("datePartie"), 
                        rs.getString("lieu"), 
                        rs.getBoolean("termine")
                     );
                result.add(partie);
            }
        } catch (SQLException e) {
            throw new DAOException("DBError JoueurDAO.getParties() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
    
    public Set<PersonnageModel> getPersonnagesOwned(JoueurModel joueur) throws DAOException{
        Set<PersonnageModel> result = new HashSet<>();
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Personnage WHERE idJoueur="+joueur.getId());
            while (rs.next()) {
                PersonnageModel perso
                        = new PersonnageModel(rs.getInt("idPersonnage"), rs.getString("nomPerso"), rs.getString("dateNaissance"), rs.getString("profession"), rs.getString("portrait"));
                result.add(perso);
            }
        } catch (SQLException e) {
            throw new DAOException("DBError JoueurDAO.getPersonnagesOwned() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
    public Set<PersonnageModel> getPersonnagesManaged(JoueurModel joueur) throws DAOException{
        Set<PersonnageModel> result = new HashSet<>();
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * "
                    + "FROM Personnage p JOIN MJ m ON p.idPersonnage=m.idPersonnage "
                    + "WHERE m.idMJ="+joueur.getId());
            while (rs.next()) {
                PersonnageModel perso
                        = new PersonnageModel(
                                rs.getInt("p.idPersonnage"), 
                                rs.getString("p.nomPerso"), 
                                rs.getString("p.dateNaissance"), 
                                rs.getString("p.profession"), 
                                rs.getString("p.portrait"));
                result.add(perso);
            }
        } catch (SQLException e) {
            throw new DAOException("DBError JoueurDAO.getPersonnagesOwned() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
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
            throw new DAOException("DBError JoueurDAO.get() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
    
    public Set<JoueurModel> getPotentialMJ(JoueurModel joueur) throws DAOException {
        Set<JoueurModel> result = new HashSet<>();
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Joueur WHERE idJoueur<>"+joueur.getId());
            while (rs.next()) {
                JoueurModel mj
                     = new JoueurModel(rs.getInt("idJoueur"), rs.getString("login"),rs.getString("mdp"),rs.getString("email"));
                result.add(mj);
            }
        } catch (SQLException e) {
            throw new DAOException("DBError JoueurDAO.getAll() " + e.getMessage(), e);
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
            throw new DAOException("DBError JoueurDAO.get() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    @Override
    public Set<JoueurModel> getAll() throws DAOException {
        Set<JoueurModel> result = new HashSet<>();
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
            throw new DAOException("DBError JoueurDAO.getAll() " + e.getMessage(), e);
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
        int id = this.getId();
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
            throw new DAOException("DBError JoueurDAO.insert() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        joueur.setId(id);
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
            throw new DAOException("DBError JoueurDAO.update() " + e.getMessage(), e);
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
            throw new DAOException("DBError JoueurDAO.delete() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return affectedRows ;
    }
}
