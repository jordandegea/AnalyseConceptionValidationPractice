/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAOConfiguration;
import dao.DAOException;
import dao.JoueurDAO;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import model.JoueurModel;
/**
 *
 * @author JordanLeMagnifique
 */
public abstract class AbstractControllerBase extends HttpServlet{
    
    @Resource(name = "jdbc/roleplay")
    public static DataSource dataSource;
    
    public AbstractControllerBase() {
        DAOConfiguration.dataSource = dataSource ;    
    }

    /* pages d’erreurs */
    protected void invalidParameters(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/controleurErreur.jsp").forward(request, response);        
    }

    protected void erreurBD(HttpServletRequest request,
                HttpServletResponse response, DAOException e)
            throws ServletException, IOException {
        request.setAttribute("erreurMessage", e.getMessage());
        request.getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
    }
  
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
    }
    
    /**
     * Get the user corresponding to the login & password
     *
     * @param request
     * @return
     * @throws DAOException
     */
    private JoueurModel getUser(HttpServletRequest request) throws DAOException {
        JoueurModel joueur = JoueurDAO.instance().get(Integer.parseInt((String) request.getSession().getAttribute("idUser")));

        return joueur;
    }
    
}
