/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import javax.sql.DataSource;
import model.AbstractBaseModel;
import model.UniversModel;

/**
 *
 * @author JordanLeMagnifique
 */
public class UniversDAO extends AbstractDataBaseDAO{

    public UniversDAO(DataSource ds) {
        super(ds);
    }

    @Override
    public AbstractBaseModel get(int id) throws DAOException{
        UniversModel result = null;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM bibliographie WHERE id='"+id+"'");
            if(rs.next()) {
                result = new UniversModel(rs.getInt("id"), rs.getString("auteur"));
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    @Override
    public Set<AbstractBaseModel> getAll() throws DAOException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(Object object) throws DAOException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Object object) throws DAOException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object object) throws DAOException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
