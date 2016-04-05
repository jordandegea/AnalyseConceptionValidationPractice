package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
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

    @Resource(name = "jdbc/bibliography")
    protected DataSource dataSource;
    
    protected AbstractDataBaseDAO(/*DataSource ds*/) {
        /*this.dataSource = ds;*/
    }

    protected Connection getConnection() throws SQLException {
        return dataSource.getConnection();
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
    
    public abstract     AbstractBaseModel           get(int id)             throws DAOException;
    public abstract     List<AbstractBaseModel>     getAll()                throws DAOException;
    public abstract     void                        insert(Object object)   throws DAOException;
    public abstract     void                        update(Object object)   throws DAOException;
    public abstract     void                        delete(Object object)   throws DAOException;
    
    
}
