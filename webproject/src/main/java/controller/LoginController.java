/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAOException;
import dao.JoueurDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.JoueurModel;
import validator.JoueurValidator;
import validator.ValidatorException;

/**
 *
 * @author william
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends AbstractControllerBase {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("idUser") != null) {
            int userId = Integer.parseInt((String) session.getAttribute("idUser"));
            response.sendRedirect("joueur?action=SHOW");
        } else {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }

    /**
     * Get the user corresponding to the login & password
     *
     * @param request
     * @return
     * @throws DAOException
     */
    private JoueurModel getJoueur(HttpServletRequest request) throws DAOException {
        String login = (String) request.getParameter("login");
        JoueurModel joueur = JoueurDAO.instance().get(login);

        return joueur;
    }

    /**
     * Try to find the user corresponding to the login & password Redirect to
     * showJoueur.jsp if success, reload login page with error otherwise
     *
     * @param request
     * @param response
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JoueurModel joueur = getJoueur(request);
            try {
                JoueurValidator.instance().loginValidate(joueur, (String) request.getParameter("password"));
                request.getSession().setAttribute("idUser", joueur.getId());
                response.sendRedirect("joueur?action=SHOW");
            } catch (ValidatorException ex) {
                request.setAttribute("error", ex.getMessage());
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
        } catch (DAOException ex) {
            super.erreurBD(request, response, ex);
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        processRequest(request, response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        login(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
