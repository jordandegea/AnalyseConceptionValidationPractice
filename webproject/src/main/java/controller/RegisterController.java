/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAOException;
import dao.JoueurDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
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
@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends AbstractControllerBase {

    /**
     * Create a user. Redirect to showJoueur.jsp if success, reload register
     * page with error otherwise
     *
     * @param request
     * @param response
     */
    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getParameter("login");
        String password = (String) request.getParameter("password");
        String confirm = (String) request.getParameter("confirm");
        String email = (String) request.getParameter("email");

        JoueurModel joueur = new JoueurModel(login, password, email);
        try {
            JoueurValidator.instance().createValidate(joueur, confirm);
            JoueurDAO.instance().insert(joueur);
            request.getSession().setAttribute("idUser", joueur.getId());
            response.sendRedirect("joueur?action=SHOW");
        } catch (ValidatorException ex) {
            request.setAttribute("error", ex.getMessage());
            request.setAttribute("joueur", joueur);
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
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
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
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
        registerUser(request, response);
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
