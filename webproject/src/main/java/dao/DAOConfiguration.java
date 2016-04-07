/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 *
 * @author JordanLeMagnifique
 */
public class DAOConfiguration {
    
    @Resource(name = "jdbc/bibliography")
    public static DataSource dataSource;
    
}
