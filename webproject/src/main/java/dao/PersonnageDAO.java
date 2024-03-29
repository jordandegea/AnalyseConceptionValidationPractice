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
import model.BiographieModel;
import model.JoueurModel;
import model.PartieModel;
import model.PersonnageModel;
import model.UniversModel;

/**
 *
 * @author JordanLeMagnifique
 */
public class PersonnageDAO extends AbstractDataBaseDAO {

    final private static PersonnageDAO instanceUnique = new PersonnageDAO();

    public static PersonnageDAO instance() {
        return instanceUnique;
    }

    private PersonnageDAO(/*DataSource ds*/) {
        super(/*ds*/);
    }

    // Personal DAOs Methods
    public UniversModel getUnivers(PersonnageModel personnage) throws DAOException {
        UniversModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT u.idUnivers, u.nomUnivers FROM Univers u, Personnage p WHERE p.idPersonnage=" + personnage.getId() + " AND u.idUnivers=p.idUnivers");
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

    public PartieModel getPartieEnCours(PersonnageModel personnage) throws DAOException {
        PartieModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT p.idPartie, p.titrePartie, p.resumePartie, p.datePartie, p.lieu, p.termine, p.idUnivers, p.idMJ FROM Partie p, PartieEnCours pc WHERE p.idPartie = pc.idPartie AND pc.idPersonnage =" + personnage.getId() + " AND p.termine = 0");
            if (rs.next()) {
                result = new PartieModel(rs.getInt("idPartie"), rs.getString("titrePartie"), rs.getString("resumePartie"), rs.getString("datePartie"), rs.getString("lieu"), ((rs.getInt("termine") == 1) ? true : false));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError PersonnageDAO.getPartieEnCours() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    public BiographieModel getBiographie(PersonnageModel personnage) throws DAOException {
        BiographieModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT idBiographie FROM Personnage p WHERE idPersonnage=" + personnage.getId());
            if (rs.next()) {
                result = new BiographieModel(rs.getInt("idBiographie"));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError PersonnageDAO.getBiographie() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    public JoueurModel getMJ(PersonnageModel personnage) throws DAOException {
        JoueurModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT j.* FROM MJ mj, Joueur j WHERE mj.idPersonnage=" + personnage.getId() + " AND mj.idMJ=j.idJoueur");
            if (rs.next()) {
                result = new JoueurModel(rs.getInt("idJoueur"), rs.getString("login"), rs.getString("mdp"), rs.getString("email"));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError PersonnageDAO.getMJ() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    public JoueurModel getOwner(PersonnageModel personnage) throws DAOException {
        JoueurModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT j.* FROM Personnage p, Joueur j WHERE p.idPersonnage=" + personnage.getId() + " AND p.idJoueur=j.idJoueur");
            if (rs.next()) {
                result = new JoueurModel(rs.getInt("idJoueur"), rs.getString("login"), rs.getString("mdp"), rs.getString("email"));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError PersonnageDAO.getOwner() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    public Set<PartieModel> getParties(PersonnageModel personnage) throws DAOException {

        Set<PartieModel> result = new HashSet<PartieModel>();
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT p.* "
                    + "FROM Partie p, PartieEnCours pc WHERE "
                    + "p.idPartie=pc.idPartie "
                    + "AND pc.idPersonnage=" + personnage.getId()
            );
            while (rs.next()) {
                result.add(new PartieModel(
                        rs.getInt("idPartie"),
                        rs.getString("titrePartie"),
                        rs.getString("resumePartie"),
                        rs.getString("datePartie"),
                        rs.getString("lieu"),
                        rs.getBoolean("termine")
                ));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError PersonnageDAO getParties() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    public boolean peutDemanderChangementMJ(PersonnageModel personnage) throws DAOException {
        boolean result = true;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT p.idPartie, p.titrePartie, p.resumePartie, p.datePartie, p.lieu, p.termine, p.idUnivers, p.idMJ "
                    + "FROM Partie p, PartieEnCours pc "
                    + "WHERE p.idPartie = pc.idPartie AND pc.idPersonnage =" + personnage.getId() + " AND p.termine = 0");
            if (rs.next()) {
                result = false;
            }
        } catch (SQLException e) {
            throw new DAOException("DBError PersonnageDAO peutDemanderChangementMJ() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
    
    public void askMJ(PersonnageModel perso, JoueurModel mj) throws DAOException {
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("INSERT INTO MJ (idPersonnage, idMJ) VALUES (?,?)");
            st.setInt(1, perso.getId());
            st.setInt(2, mj.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError PersonnageDAO.askMJ() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }

        perso.setDemandeMJ(true);
        this.update(perso);
    }

    public void askJoueurTransfer(PersonnageModel perso, JoueurModel j) throws DAOException {
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("UPDATE Personnage SET idJoueur = ?  WHERE idPersonnage = ? ");
            st.setInt(1, j.getId());
            st.setInt(2, perso.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError PersonnageDAO.askJoueurTransfer() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }

    public void leaveMJ(PersonnageModel perso) throws DAOException {
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("DELETE FROM MJ WHERE idPersonnage=?");
            st.setInt(1, perso.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError PersonnageDAO.delete() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }

        perso.setDemandeMJ(false);
        this.update(perso);
    }

    public void acceptDemandeMJ(PersonnageModel perso, JoueurModel mj) throws DAOException {
        perso.setDemandeMJ(false);
        this.update(perso);
    }

    public void rejectDemandeMJ(PersonnageModel perso, JoueurModel mj) throws DAOException {
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("DELETE FROM MJ WHERE idPersonnage=? AND idMJ=?");
            st.setInt(1, perso.getId());
            st.setInt(2, mj.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError PersonnageDAO.rejectDemandeMJ() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }

        perso.setDemandeMJ(false);
        this.update(perso);
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
                        rs.getString("portrait"),
                        rs.getBoolean("demandeMJ")
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
    public Set<PersonnageModel> getAll() throws DAOException {
        Set<PersonnageModel> result = new HashSet<>();
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
                                rs.getString("portrait"),
                                rs.getBoolean("demandeMJ")
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
                    = conn.prepareStatement("INSERT INTO Personnage (nomPerso, dateNaissance, profession, portrait, idJoueur, idUnivers, idBiographie, idPersonnage, demandeMJ) VALUES (?,?,?,?,?,?,?,?,?)");
            st.setString(1, personnage.getNomPerso());
            st.setString(2, personnage.getDateNaiss());
            st.setString(3, personnage.getProfession());
            st.setString(4, personnage.getPortrait());
            st.setInt(5, personnage.getOwner().getId());
            st.setInt(6, personnage.getUnivers().getId());
            st.setInt(7, personnage.getBiographie().getId());
            st.setInt(8, id);
            st.setBoolean(9, personnage.isDemandeMJ());
            affectedRows = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError PersonnageDAO.insert() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        personnage.setId(id);
        return affectedRows;
    }

    @Override
    public int update(Object object) throws DAOException {
        if (!(object instanceof PersonnageModel)) {
            throw new DAOException("Wrong object parameter in update, require PersonnageModel");
        }
        int affectedRows = 0;
        PersonnageModel personnage = (PersonnageModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("UPDATE Personnage SET "
                            + "nomPerso=?, dateNaissance=?, profession=?, portrait=?, idJoueur=?, demandeMJ=?  "
                            + "WHERE idPersonnage=?");
            st.setString(1, personnage.getNomPerso());
            st.setString(2, personnage.getDateNaiss());
            st.setString(3, personnage.getProfession());
            st.setString(4, personnage.getPortrait());
            st.setInt(5, personnage.getOwner().getId());
            st.setBoolean(6, personnage.isDemandeMJ());
            st.setInt(7, personnage.getId());
            affectedRows = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError PersonnageDAO.update() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return affectedRows;
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
        return affectedRows;
    }
}
