/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAOException;
import dao.JoueurDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.EpisodeModel;
import model.JoueurModel;
import model.PersonnageModel;

/**
 *
 * @author william
 */
@WebServlet(name = "JoueurController", urlPatterns = {"/joueur"})
public class JoueurController extends AbstractControllerBase {

    private void showJoueur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JoueurModel joueur = getUser(request, response);
            Set<PersonnageModel> demandeursMJ = JoueurDAO.instance().getDemandeursMJ(joueur);
            Set<PersonnageModel> persosMJ = JoueurDAO.instance().getPersonnagesManaged(joueur);
            Set<EpisodeModel> episodesMJ = JoueurDAO.instance().getEpisodeDemande(joueur);
            Set<PersonnageModel> episodesPersoMJ = JoueurDAO.instance().getEpisodePersonnageDemande(joueur);
            LinkedList demandeEP = new LinkedList(episodesMJ);
            LinkedList demandePerso = new LinkedList(episodesPersoMJ);
            LinkedList demandes = new LinkedList();
            for (int i = 0; i < episodesMJ.size(); i++) {
                demandes.add(demandePerso.get(i));
                demandes.add(demandeEP.get(i));
            }
            request.setAttribute("joueur", joueur);
            request.setAttribute("demandeursMJ", demandeursMJ);
            request.setAttribute("persosMJ", persosMJ);
            request.setAttribute("demandesEpisode", demandes);

            request.getRequestDispatcher("/WEB-INF/joueur/showJoueur.jsp").forward(request, response);
        } catch (DAOException ex) {
            super.erreurBD(request, response, ex);
        }
    }

    private void newJoueur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    private void editJoueur(HttpServletRequest request, HttpServletResponse response) {

    }

    private void deleteJoueur(HttpServletRequest request, HttpServletResponse response) {

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
            this.showJoueur(request, response);
        } else if (action.equals("NEW")) {
            newJoueur(request, response);
        } else if (action.equals("EDIT")) {

        } else if (action.equals("SHOW")) {
            this.showJoueur(request, response);
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
        } else if (action.equals("CREATE")) {
            // Traitement spécifique modifier
        } else if (action.equals("EDIT")) {
            //Traitement spécifique supprimer 
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
