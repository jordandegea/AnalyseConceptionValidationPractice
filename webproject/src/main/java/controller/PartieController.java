/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAOException;
import dao.PartieDAO;
import dao.UniversDAO;
import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import model.JoueurModel;
import model.PartieModel;
import model.UniversModel;
import validator.PartieValidator;
import validator.ValidatorException;

/**
 *
 * /**
 *
 * @author JordanLeMagnifique
 */
@WebServlet(name = "PartieController", urlPatterns = {"/partie"})
public class PartieController extends AbstractControllerBase {

    private void newPartie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<UniversModel> univers = UniversDAO.instance().getAll();
            request.setAttribute("univers", univers);
            request.getRequestDispatcher("/WEB-INF/partie/newPartie.jsp").forward(request, response);
        } catch (DAOException ex) {
            super.erreurBD(request, response, ex);
        }
    }

    private void createPartie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String titre = (String) request.getParameter("titre");
        String date = (String) request.getParameter("date");
        String lieu = (String) request.getParameter("lieu");
        String resumeInitial = (String) request.getParameter("resumeInitial");
        int idUnivers = Integer.parseInt(request.getParameter("univers"));

        try {
            UniversModel univers = UniversDAO.instance().get(idUnivers);
            JoueurModel joueur = this.getUser(request, response);
            PartieModel partie = new PartieModel(titre, date, lieu, resumeInitial, joueur, univers);

            try {
                PartieValidator.instance().createValidate(partie);
                PartieDAO.instance().insert(partie);
                String contextPath = request.getContextPath();
                response.sendRedirect(response.encodeRedirectURL(contextPath + "/partie?action=SHOW&idPartie=" + partie.getId()));
            } catch (ValidatorException ex) {
                request.setAttribute("error", ex.getMessage());
                request.setAttribute("partie", partie);
                request.getRequestDispatcher("/WEB-INF/partie/newPartie.jsp").forward(request, response);
            }
        } catch (DAOException ex) {
            super.erreurBD(request, response, ex);
        }
    }

    private void showPartie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idPartie = Integer.parseInt(request.getParameter("idPartie"));
            PartieModel partie = PartieDAO.instance().get(idPartie);
            if (partie != null) {
                request.setAttribute("personnage", partie);
                request.getRequestDispatcher("/WEB-INF/partie/showPartie.jsp").forward(request, response);
            } else {
                super.error404(request, response);
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
        String action = request.getParameter("action");

        if (action == null) {
            super.invalidParameters(request, response);
        } else if (action.equals("NEW")) {
            this.newPartie(request, response);
        } else if (action.equals("EDIT")) {
        } else if (action.equals("SHOW")) {
            this.showPartie(request, response);
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
        } else if (action.equals("CREATE")) {
            this.createPartie(request, response);
        } else if (action.equals("UPDATE")) {
        } else if (action.equals("DELETE")) {
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
