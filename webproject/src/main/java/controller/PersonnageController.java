/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAOException;
import dao.PersonnageDAO;
import dao.UniversDAO;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.BioInitialeModel;
import model.JoueurModel;
import model.PersonnageModel;
import model.UniversModel;
import validator.PersonnageValidator;
import validator.ValidatorException;

/**
 *
 * @author william
 */
@WebServlet(name = "PersonnageController", urlPatterns = {"/personnage"})
public class PersonnageController extends AbstractControllerBase {

    private void newPersonnage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<UniversModel> univers = UniversDAO.instance().getAll();
            request.setAttribute("univers", univers);
            request.getRequestDispatcher("/WEB-INF/personnage/newPersonnage.jsp").forward(request, response);
        } catch (DAOException ex) {
            super.erreurBD(request, response, ex);
        }
    }

    private void createPersonnage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = (String) request.getParameter("nomPerso");
        String dateNaiss = (String) request.getParameter("dateNaiss");
        String profession = (String) request.getParameter("profession");
        String portrait = (String) request.getParameter("portrait");
        int idUnivers = Integer.parseInt(request.getParameter("univers"));

        try {
            UniversModel univers = UniversDAO.instance().get(idUnivers);

            JoueurModel joueur = this.getUser(request, response);
            BioInitialeModel bioInitiale = new BioInitialeModel();
            PersonnageModel perso = new PersonnageModel(nom, dateNaiss, profession, portrait, bioInitiale, joueur, univers);
            try {
                PersonnageValidator.instance().createValidate(perso);
                PersonnageDAO.instance().insert(perso);
                request.setAttribute("personnage", perso);
                request.getRequestDispatcher("/WEB-INF/personnage/showPersonnage.jsp").forward(request, response);
            } catch (ValidatorException ex) {
                request.setAttribute("error", ex.getMessage());
                request.setAttribute("personnage", perso);
                request.getRequestDispatcher("/WEB-INF/personnage/newPersonnage.jsp").forward(request, response);
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
            newPersonnage(request, response);
        } else if (action.equals("EDIT")) {

        } else if (action.equals("SHOW")) {

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
            createPersonnage(request, response);
        } else if (action.equals("UPDATE")) {
            //Traitement spécifique supprimer 
        } else if (action.equals("DELETE")) {

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