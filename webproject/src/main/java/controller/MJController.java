/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAOException;
import dao.PersonnageDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.JoueurModel;
import model.PersonnageModel;

/**
 *
 * @author william
 */
@WebServlet(name = "MJController", urlPatterns = {"/mj"})
public class MJController extends AbstractControllerBase {

    private void acceptDemandeMJ(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int idPerso = Integer.parseInt(request.getParameter("idPerso"));
        JoueurModel joueur = super.getUser(request, response);
        try {
            PersonnageModel perso = PersonnageDAO.instance().get(idPerso);
            PersonnageDAO.instance().acceptDemandeMJ(perso, joueur);
            String contextPath = request.getContextPath();
            response.sendRedirect(response.encodeRedirectURL(contextPath + "/joueur?action=SHOW"));
        } catch (DAOException ex) {
            super.erreurBD(request, response, ex);
        }
    }

    private void rejectDemandeMJ(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idPerso = Integer.parseInt(request.getParameter("idPerso"));
        JoueurModel joueur = super.getUser(request, response);
        
        try {
            PersonnageModel perso = PersonnageDAO.instance().get(idPerso);
            PersonnageDAO.instance().rejectDemandeMJ(perso, joueur);
            String contextPath = request.getContextPath();
            response.sendRedirect(response.encodeRedirectURL(contextPath + "/joueur?action=SHOW"));
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
        String action = request.getParameter("action");

        if (action == null) {
            super.invalidParameters(request, response);
        } else {
            super.invalidParameters(request, response);
        }
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
        String action = request.getParameter("action");

        if (action == null) {
            super.invalidParameters(request, response);
        } else if (action.equals("ACCEPT")) {
            this.acceptDemandeMJ(request, response);
        } else if (action.equals("REJECT")) {
            this.rejectDemandeMJ(request, response);
        } else {
            super.invalidParameters(request, response);
        }
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
