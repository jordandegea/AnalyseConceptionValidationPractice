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
import java.util.Map;
import java.util.Set;
import java.lang.String;
import model.AbstractBaseModel;
import model.EpisodeModel;
import model.JoueurModel;
import model.ParagrapheModel;
import model.PartieModel;
import model.PersonnageModel;
import model.ResumePartieModel;
import model.ResumePersonnageModel;
import model.UniversModel;

/**
 *
 * @author JordanLeMagnifique
 */
public class PartieDAO extends AbstractDataBaseDAO {

    final private static PartieDAO instanceUnique = new PartieDAO();

    public static PartieDAO instance() {
        return instanceUnique;
    }

    private PartieDAO(/*DataSource ds*/) {
        super(/*ds*/);
    }

    // Personal DAOs Methods
    public JoueurModel getJoueur(PartieModel partie) throws DAOException {
        JoueurModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT j.* FROM Joueur j, Partie p WHERE p.idPartie=" + partie.getId() + " AND p.idMJ=j.idJoueur");
            if (rs.next()) {
                result = new JoueurModel(rs.getInt("idJoueur"), rs.getString("login"), rs.getString("mdp"), rs.getString("email"));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError PartieDAO.getJoueur() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    public Set<ResumePersonnageModel> getResumesPersonnage(PartieModel partie) throws DAOException {
        Set<ResumePersonnageModel> result = new HashSet<>();
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * "
                    + "FROM Partie p JOIN Resume r ON p.idPartie=r.idPartie "
                    + "JOIN Episode e ON r.idEpisode=e.idEpisode "
                    + "WHERE p.idPartie='" + partie.getId() + "' AND typeEpisode='ResumePersonnage' ");
            while (rs.next()) {
                result.add(new ResumePersonnageModel(rs.getInt("e.idEpisode"), rs.getDate("dateEpisode"), rs.getBoolean("ecritureEnCours")));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    public ResumePartieModel getResumePartie(PartieModel partie) throws DAOException {
        ResumePartieModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * "
                    + "FROM Partie p JOIN Resume r ON p.idPartie=r.idPartie "
                    + "JOIN Episode e ON r.idEpisode=e.idEpisode "
                    + "WHERE p.idPartie='" + partie.getId() + "' AND typeEpisode='ResumePartie' ");
            if (rs.next()) {
                result = new ResumePartieModel(rs.getInt("e.idEpisode"), rs.getDate("dateEpisode"), rs.getBoolean("ecritureEnCours"));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    public UniversModel getUnivers(PartieModel partie) throws DAOException {
        UniversModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT u.idUnivers, u.nomUnivers FROM Univers u, Partie p WHERE p.idPartie=" + partie.getId() + " AND u.idUnivers=p.idUnivers");
            if (rs.next()) {
                result = new UniversModel(rs.getInt("idUnivers"), rs.getString("nomUnivers"));
            }
        } catch (SQLException e) {
            throw new DAOException("DBError PartieDAO.getUnivers() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    public Set<PersonnageModel> getPersonnages(PartieModel partie) throws DAOException {
        Set<PersonnageModel> result = new HashSet<>();
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs;
            if (!partie.isPartieFinie()) {
                rs = st.executeQuery("SELECT p.* FROM Personnage p, PartieEnCours pc WHERE pc.idPartie=" + partie.getId() + " AND p.idPersonnage=pc.idPersonnage");
            } else {
                rs = st.executeQuery("SELECT p.* FROM Personnage p, PartieTerminee pt WHERE pt.idPartie=" + partie.getId() + " AND p.idPersonnage=pt.idPersonnage");
            }
            while (rs.next()) {
                PersonnageModel perso
                        = new PersonnageModel(rs.getInt("idPersonnage"), rs.getString("nomPerso"), rs.getString("dateNaissance"), rs.getString("profession"), rs.getString("portrait"), rs.getBoolean("demandeMJ"));
                result.add(perso);
            }
        } catch (SQLException e) {
            throw new DAOException("DBError PartieDAO.getPersonnages() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    public Set<PersonnageModel> getEnrollablePersonnages(PartieModel partie) throws DAOException {
        Set<PersonnageModel> result = new HashSet<>();

        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement query = conn.prepareStatement(
                    "SELECT p.* FROM Personnage p, MJ mj "
                    + "WHERE p.idJoueur<>? "
                    + "AND p.idJoueur NOT IN ("
                    + "SELECT DISTINCT idJoueur FROM Personnage "
                    + "WHERE idPersonnage IN ("
                    + "SELECT idPersonnage FROM PartieEnCours "
                    + "WHERE idPartie=?"
                    + ")"
                    + ")"
                    + "AND p.idUnivers=? "
                    + "AND p.idPersonnage NOT IN ("
                    + "SELECT idPersonnage FROM PartieEnCours"
                    + ") "
                    + "AND mj.idMJ=? "
                    + "AND mj.idPersonnage=p.idPersonnage "
                    + "AND p.demandeMJ=?");
            query.setInt(1, partie.getMJ().getId());
            query.setInt(2, partie.getId());
            query.setInt(3, partie.getUnivers().getId());
            query.setInt(4, partie.getMJ().getId());
            query.setBoolean(5, false);
            ResultSet rs = query.executeQuery();

            while (rs.next()) {
                PersonnageModel perso
                        = new PersonnageModel(rs.getInt("idPersonnage"), rs.getString("nomPerso"), rs.getString("dateNaissance"), rs.getString("profession"), rs.getString("portrait"), rs.getBoolean("demandeMJ"));
                result.add(perso);
            }
        } catch (SQLException e) {
            throw new DAOException("DBError PartieDAO.getEnrollablePersonnages() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    public void enrollPersonnage(PartieModel partie, PersonnageModel perso) throws DAOException {
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("INSERT INTO PartieEnCours (idPersonnage, idPartie) VALUES (?,?)");
            st.setInt(1, perso.getId());
            st.setInt(2, partie.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError PartieDAO.enrollPersonnage() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }

    // Override Methods
    @Override
    public PartieModel get(int id) throws DAOException {
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
                        rs.getString("datePartie"),
                        rs.getString("lieu"),
                        rs.getBoolean("termine")
                );
            }
        } catch (SQLException e) {
            throw new DAOException("DBError PartieDAO.get() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    @Override
    public Set<PartieModel> getAll() throws DAOException {
        Set<PartieModel> result = new HashSet<>();
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Partie");
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
            throw new DAOException("DBError PartieDAO.getAll() " + e.getMessage(), e);
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
        int id = this.getId();
        PartieModel partie = (PartieModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("INSERT INTO Partie (titrePartie ,resumePartie, datePartie, lieu, termine, idUnivers, idMJ, idPartie) VALUES (?,?,?,?,?,?,?,?)");
            st.setString(1, partie.getTitrePartie());
            st.setString(2, partie.getResumeInitial());
            st.setString(3, partie.getDate());
            st.setString(4, partie.getLieu());
            st.setBoolean(5, partie.isPartieFinie());
            st.setInt(6, partie.getUnivers().getId());
            st.setInt(7, partie.getMJ().getId());
            st.setInt(8, id);
            affectedRows = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError PartieDAO.insert() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }

        partie.setId(id);
        return affectedRows;
    }

    @Override
    public int update(Object object) throws DAOException {
        if (!(object instanceof PartieModel)) {
            throw new DAOException("Wrong object parameter in update, require PartieModel");
        }
        int affectedRows = 0;
        PartieModel partie = (PartieModel) object;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st
                    = conn.prepareStatement("UPDATE Partie SET titrePartie=?, resumePartie=?, datePartie=?, lieu=?, termine=?, idUnivers=?, loginMJ=? WHERE idPartie=?");
            st.setString(1, partie.getTitrePartie());
            st.setString(2, partie.getResumeInitial());
            st.setString(3, partie.getDate());
            st.setString(4, partie.getLieu());
            st.setBoolean(5, partie.isPartieFinie());
            st.setInt(6, partie.getUnivers().getId());
            st.setInt(7, partie.getMJ().getId());
            st.setInt(8, partie.getId());
            affectedRows = st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("DBError PartieDAO.update() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return affectedRows;
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
            throw new DAOException("DBError PartieDAO.delete() " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return affectedRows;
    }
}
