/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAOException;
import dao.PartieDAO;
import dao.PersonnageDAO;
import dao.UniversDAO;
import java.io.*;
import java.util.Set;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.JoueurModel;
import model.PartieModel;
import model.PersonnageModel;
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
            Set<UniversModel> univers = UniversDAO.instance().getAll();
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
                request.setAttribute("partie", partie);
                request.getRequestDispatcher("/WEB-INF/partie/showPartie.jsp").forward(request, response);
            } else {
                super.error404(request, response);
            }
        } catch (DAOException ex) {
            super.erreurBD(request, response, ex);
        }
    }

    private void addPersonnagePartie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idPartie = Integer.parseInt(request.getParameter("idPartie"));
            PartieModel partie = PartieDAO.instance().get(idPartie);
            if (partie != null) {
                Set<PersonnageModel> enrollable = PartieDAO.instance().getEnrollablePersonnages(partie);
                request.setAttribute("partie", partie);
                request.setAttribute("enrollable", enrollable);
                request.getRequestDispatcher("/WEB-INF/partie/enrollPersonnage.jsp").forward(request, response);
            } else {
                super.error404(request, response);
            }
        } catch (DAOException ex) {
            super.erreurBD(request, response, ex);
        }
    }

    private void enrollPersonnage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idPartie = Integer.parseInt(request.getParameter("idPartie"));
            int idPerso = Integer.parseInt(request.getParameter("idPerso"));
            PartieModel partie = PartieDAO.instance().get(idPartie);
            PersonnageModel perso = PersonnageDAO.instance().get(idPerso);

            if (partie != null && perso != null) {
                try {
                    PartieValidator.instance().enrollValidate(partie, perso);
                    PartieDAO.instance().enrollPersonnage(partie, perso);
                    String contextPath = request.getContextPath();
                    response.sendRedirect(response.encodeRedirectURL(contextPath + "/partie?action=SHOW&idPartie=" + partie.getId()));
                } catch (ValidatorException ex) {
                    Set<PersonnageModel> enrollable = PartieDAO.instance().getEnrollablePersonnages(partie);
                    request.setAttribute("error", ex.getMessage());
                    request.setAttribute("partie", partie);
                    request.setAttribute("enrollable", enrollable);
                    request.getRequestDispatcher("/WEB-INF/partie/enrollPersonnage.jsp").forward(request, response);
                }
            } else {
                super.error404(request, response);
            }
        } catch (DAOException ex) {
            super.erreurBD(request, response, ex);
        }
    }

    private void endPartie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idPartie = Integer.parseInt(request.getParameter("idPartie"));
        try {
            PartieModel partie = PartieDAO.instance().get(idPartie);
            PartieDAO.instance().endPartie(partie);
            String contextPath = request.getContextPath();
            response.sendRedirect(response.encodeRedirectURL(contextPath + "/partie?action=SHOW&idPartie=" + partie.getId()));
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
        } else if (action.equals("ADDPERSO")) {
            this.addPersonnagePartie(request, response);
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
        } else if (action.equals("END")) {
            this.endPartie(request, response);
        } else if (action.equals("ENROLL")) {
            this.enrollPersonnage(request, response);
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
