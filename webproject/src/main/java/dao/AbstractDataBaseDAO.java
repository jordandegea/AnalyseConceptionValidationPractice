package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.sql.DataSource;
import model.AbstractBaseModel;

/**
 * Classe abstraite permettant de factoriser du code pour les DAO
 * basées sur JDBC
 * @author Philippe.Genoud@imag.fr
 */
public abstract class AbstractDataBaseDAO {

    
    protected AbstractDataBaseDAO(/*DataSource ds*/) {
        /*this.dataSource = ds;*/
    }

    protected Connection getConnection() throws SQLException {
        return DAOConfiguration.dataSource.getConnection();
    }
    /**
     * fermeture d'une connexion
     * @param c la connexion à fermer
     * @throws DAOException si problème lors de la fermeture de la connexion
     */
    protected void closeConnection(Connection c) throws DAOException {
        if (c != null) {
            try {
                c.close();
            } catch (SQLException sqle) {
                throw new DAOException("Problème fermeture de connexion avec la BD ", sqle);
            }
        }

    }
    
    protected int getId() throws DAOException {
        int id = 0;
        
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT id.nextval FROM DUAL");
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("DBError " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        
        return id;
    }
    
    public abstract     AbstractBaseModel           get(int id)             throws DAOException;
    public abstract     Set<? extends AbstractBaseModel>     getAll()                throws DAOException;
    public abstract     int                         insert(Object object)   throws DAOException;
    public abstract     int                         update(Object object)   throws DAOException;
    public abstract     int                         delete(Object object)   throws DAOException;
    
    
}
