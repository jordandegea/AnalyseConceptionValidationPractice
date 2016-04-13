/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAOException;
import dao.PartieDAO;
import dao.PersonnageDAO;
import dao.ResumePartieDAO;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.JoueurModel;
import model.PartieModel;
import model.PersonnageModel;
import model.ResumePartieModel;
import validator.EpisodeValidator;
import validator.ValidatorException;

/**
 *
 * @author william
 */
@WebServlet(name = "ResumePartieController", urlPatterns = {"/resumePartie"})
public class ResumePartieController extends AbstractControllerBase {
    private void newResume(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idPartie = Integer.parseInt(request.getParameter("idPartie"));
        request.setAttribute("idPartie", idPartie);
        request.getRequestDispatcher("/WEB-INF/resumePartie/newResumePartie.jsp").forward(request, response);
    }

    private void createResume(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idPartie = Integer.parseInt(request.getParameter("idPartie"));
        try {
            EpisodeValidator.instance().dateValidate(request.getParameter("date"));
            Date date = Date.valueOf(request.getParameter("date"));
            PartieModel partie = PartieDAO.instance().get(idPartie);

            int i = 0;
            ArrayList<String> textareaList = new ArrayList<String>();
            while ((String) request.getParameter("paragraphe" + i) != null) {
                String paragraphe = (String) request.getParameter("paragraphe" + i);
                if (paragraphe.length()>0)
                    textareaList.add(paragraphe);
                i++;
            }

            ResumePartieModel resume = new ResumePartieModel(date, textareaList, partie);
            EpisodeValidator.instance().createValidate(resume);
            ResumePartieDAO.instance().insert(resume);
            partie.setResumePartie(resume);
            this.endPartie(request, response, partie);
        } catch (ValidatorException ex) {
            request.setAttribute("idPartie", idPartie);
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/resumePartie/newResumePartie.jsp").forward(request, response);
        } catch (DAOException ex) {
            super.erreurBD(request, response, ex);
        }

    }
    
    private void endPartie(HttpServletRequest request, HttpServletResponse response, PartieModel partie) throws IOException, ServletException {
        try {
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
            this.newResume(request, response);
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
            this.createResume(request, response);
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
