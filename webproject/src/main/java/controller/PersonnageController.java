/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAOException;
import dao.JoueurDAO;
import dao.PersonnageDAO;
import dao.UniversDAO;
import java.io.IOException;
import java.util.ArrayList;
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
            Set<UniversModel> univers = UniversDAO.instance().getAll();
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
        int i = 0;
        ArrayList<String> textareaList = new ArrayList<String>();
        ArrayList<Integer> checkList = new ArrayList<Integer>();
        while ((String) request.getParameter("textareaname"+i) != null) {
               textareaList.add((String) request.getParameter("textareaname"+i));
           //    checkList.add( Integer.parseInt( (String)request.getParameter("checkname"+i)));
               i++;
        }
        int idUnivers = Integer.parseInt(request.getParameter("univers"));

        try {
            UniversModel univers = UniversDAO.instance().get(idUnivers);

            JoueurModel joueur = this.getUser(request, response);
            BioInitialeModel bioInitiale = new BioInitialeModel(textareaList,checkList);
            PersonnageModel perso = new PersonnageModel(nom, dateNaiss, profession, portrait, bioInitiale, joueur, univers);
            try {
                PersonnageValidator.instance().createValidate(perso);
                PersonnageDAO.instance().insert(perso);
                String contextPath = request.getContextPath();
                response.sendRedirect(response.encodeRedirectURL(contextPath + "/personnage?action=SHOW&idPerso=" + perso.getId()));
            } catch (ValidatorException ex) {
                request.setAttribute("error", ex.getMessage());
                request.setAttribute("personnage", perso);
                request.getRequestDispatcher("/WEB-INF/personnage/newPersonnage.jsp").forward(request, response);
            }
        } catch (DAOException ex) {
            super.erreurBD(request, response, ex);
        }
    }
    
    private void updatePersonnage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String profession = (String) request.getParameter("profession");
        try {
            int idPerso = Integer.parseInt(request.getParameter("idPerso"));
            PersonnageModel perso = PersonnageDAO.instance().get(idPerso);
            if (perso != null) {
                perso.setProfession(profession);
                PersonnageDAO.instance().update(perso);
                String contextPath = request.getContextPath();
                response.sendRedirect(response.encodeRedirectURL(contextPath + "/personnage?action=SHOW&idPerso=" + perso.getId()));
            } else {
                super.error404(request, response);
            }
        } catch (DAOException ex) {
            super.erreurBD(request, response, ex);
        }
        
    }
    
    private void editPersonnage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idPerso = Integer.parseInt(request.getParameter("idPerso"));
            PersonnageModel perso = PersonnageDAO.instance().get(idPerso);
            if (perso != null) {
                request.setAttribute("personnage", perso);
                request.setAttribute("idPerso",idPerso);
                request.getRequestDispatcher("/WEB-INF/personnage/editPersonnage.jsp").forward(request, response);
            } else {
                super.error404(request, response);
            }
        } catch (DAOException ex) {
            super.erreurBD(request, response, ex);
        }
        
    }

    private void showPersonnage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idPerso = Integer.parseInt(request.getParameter("idPerso"));
            PersonnageModel perso = PersonnageDAO.instance().get(idPerso);
            JoueurModel j = super.getUser(request, response);
            // Si c'est un simple joueur
            if (!j.equals(perso.getOwner())) {
                request.setAttribute("personnage", perso);
                request.getRequestDispatcher("/WEB-INF/personnage/showPersonnageMJ.jsp").forward(request, response);
            } 
            // Si c'est le maitre du jeu.
            else if (perso != null){
                request.setAttribute("personnage", perso);
                request.getRequestDispatcher("/WEB-INF/personnage/showPersonnage.jsp").forward(request, response);
            } 
            else {
                super.error404(request, response);
            }
        } catch (DAOException ex) {
            super.erreurBD(request, response, ex);
        }
    }

    private void findMJ(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JoueurModel joueur = this.getUser(request, response);
            Set<JoueurModel> potentialMJ = JoueurDAO.instance().getPotentialMJ(joueur);
            request.setAttribute("potentialMJ", potentialMJ);
            request.setAttribute("idPerso", Integer.parseInt(request.getParameter("idPerso")));
            request.getRequestDispatcher("/WEB-INF/personnage/findMJ.jsp").forward(request, response);
        } catch (DAOException ex) {
            super.erreurBD(request, response, ex);
        }

    }

    private void askMJ(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idPerso = Integer.parseInt(request.getParameter("idPerso"));
            int idMJ = Integer.parseInt(request.getParameter("idMJ"));
            PersonnageModel perso = PersonnageDAO.instance().get(idPerso);
            JoueurModel mj = JoueurDAO.instance().get(idMJ);
            PersonnageValidator.instance().askMJValidate(perso, mj);
            PersonnageDAO.instance().askMJ(perso, mj);
            String contextPath = request.getContextPath();
            response.sendRedirect(response.encodeRedirectURL(contextPath + "/personnage?action=SHOW&idPerso=" + perso.getId()));
        } catch (ValidatorException ex) {
            try {
                JoueurModel joueur = this.getUser(request, response);
                Set<JoueurModel> potentialMJ = JoueurDAO.instance().getPotentialMJ(joueur);
                request.setAttribute("error", ex.getMessage());
                request.setAttribute("potentialMJ", potentialMJ);
                request.getRequestDispatcher("/WEB-INF/personnage/findMJ.jsp").forward(request, response);
            } catch (DAOException ex1) {
                super.erreurBD(request, response, ex1);
            }
        } catch (DAOException ex) {
            super.erreurBD(request, response, ex);
        }
    }

    public void leaveMJ(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idPerso = Integer.parseInt(request.getParameter("idPerso"));
        try {
            PersonnageModel perso = PersonnageDAO.instance().get(idPerso);
            PersonnageDAO.instance().leaveMJ(perso);
            String contextPath = request.getContextPath();
            response.sendRedirect(response.encodeRedirectURL(contextPath + "/personnage?action=SHOW&idPerso=" + perso.getId()));
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
            editPersonnage(request, response);
        } else if (action.equals("SHOW")) {
            this.showPersonnage(request, response);
        } else if (action.equals("FINDMJ")) {
            this.findMJ(request, response);
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
            createPersonnage(request, response);
        } else if (action.equals("UPDATE")) {
            updatePersonnage(request, response);
        } else if (action.equals("DELETE")) {

        } else if (action.equals("ASKMJ")) {
            this.askMJ(request, response);
        } else if (action.equals("LEAVEMJ")) {
            this.leaveMJ(request, response);
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
