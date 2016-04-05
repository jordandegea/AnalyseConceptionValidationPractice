package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import modele.Ouvrage;

public class OuvrageDAO extends AbstractDataBaseDAO {

    public OuvrageDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Renvoie la liste des ouvrages de la table bibliographie.
     */
    public List<Ouvrage> getListeOuvrages() throws DAOException {
        List<Ouvrage> result = new ArrayList<Ouvrage>();
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM bibliographie");
            while (rs.next()) {
                Ouvrage ouvrage =
                    new Ouvrage(rs.getInt("id"), rs.getString("auteur"), rs.getString("titre"));
                result.add(ouvrage);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    /**
     * Ajoute l'ouvrage d'auteur et de titre spécifié dans la table
     * bibliographie.
     */
    public void ajouterOuvrage(String auteur, String titre) throws DAOException {
        Connection conn = null ;
        try {
            conn = getConnection();
            PreparedStatement st =
                conn.prepareStatement("INSERT INTO bibliographie (auteur, titre) VALUES (?, ?)");
            st.setString(1, auteur);
            st.setString(2, titre);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * Récupère l'ouvrage d'identifiant id dans la table bibliographie.
     */
    public Ouvrage getOuvrage(int id) throws DAOException {
        Ouvrage result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM bibliographie WHERE id='"+id+"'");
            if(rs.next()) {
                result = new Ouvrage(rs.getInt("id"), rs.getString("auteur"), rs.getString("titre"));
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    /**
     * Modifie l'ouvrage d'identifiant id avec le nouvel auteur et le nouveau
     * titre spécifiés dans la table bibliographie.
     */
    public void modifierOuvrage(int id, String auteur, String titre) throws DAOException {
        Connection conn = null ;
        try {
            conn = getConnection();
            PreparedStatement st =
                conn.prepareStatement("UPDATE bibliographie SET auteur=? , titre=? WHERE id=?");
            st.setString(1, auteur);
            st.setString(2, titre);
            st.setInt(3, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * Supprime l'ouvrage d'identifiant id dans la table bibliographie.
     */
    public void supprimerOuvrage(int id) throws DAOException {
         Connection conn = null ;
        try {
            conn = getConnection();
            PreparedStatement st =
                conn.prepareStatement("DELETE FROM bibliographie  WHERE id=?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }
}
