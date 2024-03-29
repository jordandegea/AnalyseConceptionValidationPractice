/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BiographieDAO;
import dao.DAOException;
import dao.EpisodeDAO;
import dao.JoueurDAO;
import dao.ParagrapheDAO;
import dao.PersonnageDAO;
import dao.TransitionDAO;
import dao.UniversDAO;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.BioInitialeModel;
import model.BiographieModel;
import model.EpisodeModel;
import model.JoueurModel;
import model.ParagrapheModel;
import model.PersonnageModel;
import model.TransitionModel;
import model.UniversModel;
import validator.EpisodeValidator;
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
        int i = 1;
        ArrayList<String> textareaList = new ArrayList<String>();
        ArrayList<Integer> checkList = new ArrayList<Integer>();
        while ((String) request.getParameter("paragraphe" + i) != null) {
            textareaList.add((String) request.getParameter("paragraphe" + i));
            if (request.getParameter("isPrivate" + i) != null) {
                checkList.add(1);
            } else {
                checkList.add(0);
            }
            i++;
        }
        int idUnivers = Integer.parseInt(request.getParameter("univers"));

        try {
            UniversModel univers = UniversDAO.instance().get(idUnivers);

            JoueurModel joueur = this.getUser(request, response);
            BioInitialeModel bioInitiale = new BioInitialeModel(textareaList, checkList);
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

    private void addTransition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<String> textareaList = new ArrayList<String>();
        ArrayList<Integer> checkList = new ArrayList<Integer>();
        int i = 1;

        while ((String) request.getParameter("paragraphe" + i) != null) {
            System.out.println((String) request.getParameter("paragraphe" + i));
            textareaList.add((String) request.getParameter("paragraphe" + i));
            if (request.getParameter("isPrivate" + i) != null) {
                checkList.add(1);
            } else {
                checkList.add(0);
            }
            i++;
        }
        int idPerso = Integer.parseInt(request.getParameter("idPerso"));
        boolean validate = false;
        if (request.getParameter("validationEpisode") != null) {
            validate = true;
        }
        try {
            PersonnageModel perso = PersonnageDAO.instance().get(idPerso);
            try {
                EpisodeValidator.instance().dateValidate(request.getParameter("dateEpisode"));
                Date date = Date.valueOf(request.getParameter("dateEpisode"));
                EpisodeModel tm = new EpisodeModel(date, false, false, textareaList, checkList);
                tm.setValidJoueur(validate);
                BiographieModel bm = perso.getBiographie();
                EpisodeDAO.instance().insert(tm);
                EpisodeDAO.instance().insertEpisodeBiographie(tm, bm.getId());
                String contextPath = request.getContextPath();
                response.sendRedirect(response.encodeRedirectURL(contextPath + "/personnage?action=SHOW&idPerso=" + idPerso));
            } catch (ValidatorException ex) {
                request.setAttribute("error", ex.getMessage());
                request.setAttribute("personnage", perso);
                request.setAttribute("idPerso", idPerso);
                request.getRequestDispatcher("/WEB-INF/personnage/rajouterEpisode.jsp").forward(request, response);
            }
        } catch (DAOException ex) {
            Logger.getLogger(PersonnageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updatePersonnage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String profession = (String) request.getParameter("profession");
        try {
            int idPerso = Integer.parseInt(request.getParameter("idPerso"));
            PersonnageModel perso = PersonnageDAO.instance().get(idPerso);
            if (perso != null) {
                try {
                    perso.setProfession(profession);
                    PersonnageDAO.instance().update(perso);
                    PersonnageValidator.instance().editValidate(perso);
                    String contextPath = request.getContextPath();
                    response.sendRedirect(response.encodeRedirectURL(contextPath + "/personnage?action=SHOW&idPerso=" + perso.getId()));
                } catch (ValidatorException ex) {
                    request.setAttribute("error", ex.getMessage());
                    request.setAttribute("personnage", perso);
                    request.setAttribute("idPerso", idPerso);
                    request.getRequestDispatcher("/WEB-INF/personnage/editPersonnage.jsp").forward(request, response);
                }
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
                request.setAttribute("idPerso", idPerso);
                request.getRequestDispatcher("/WEB-INF/personnage/editPersonnage.jsp").forward(request, response);
            } else {
                super.error404(request, response);
            }
        } catch (DAOException ex) {
            super.erreurBD(request, response, ex);
        }

    }

    private void showEpisode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idEpisode = Integer.parseInt(request.getParameter("idEpisode"));
        int idPerso = Integer.parseInt(request.getParameter("idPerso"));
        try {
            PersonnageModel pers = PersonnageDAO.instance().get(idPerso);
            EpisodeModel ep = (EpisodeModel) EpisodeDAO.instance().get(idEpisode);
            request.setAttribute("episode", ep);
            request.setAttribute("personnage", pers);
            JoueurModel j = super.getUser(request, response);
            request.getRequestDispatcher("/WEB-INF/personnage/presenterEpisode.jsp").forward(request, response);
        } catch (DAOException ex) {
            Logger.getLogger(PersonnageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void supprimerEpisode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idEpisode = Integer.parseInt(request.getParameter("idEpisode"));
        try {
            EpisodeModel ep = (EpisodeModel) EpisodeDAO.instance().get(idEpisode);
            EpisodeDAO.instance().delete(ep);
            String contextPath = request.getContextPath();
            response.sendRedirect(response.encodeRedirectURL(contextPath + "/personnage?action=SHOW"));        } catch (DAOException ex) {
            Logger.getLogger(PersonnageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      private void showEpisodeSauvegarde(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idEpisode = Integer.parseInt(request.getParameter("idEpisode"));
        try {
            EpisodeModel ep = (EpisodeModel) EpisodeDAO.instance().get(idEpisode);
            request.setAttribute("episode", ep);
            JoueurModel j = super.getUser(request, response);
            request.getRequestDispatcher("/WEB-INF/personnage/modifierEpisode.jsp").forward(request, response);
        } catch (DAOException ex) {
            Logger.getLogger(PersonnageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showPersonnage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            int idPerso = Integer.parseInt(request.getParameter("idPerso"));
            PersonnageModel perso = PersonnageDAO.instance().get(idPerso);
            Set<EpisodeModel> modifiables = EpisodeDAO.instance().getEpModifiables(idPerso);
            JoueurModel j = super.getUser(request, response);
            // Si c'est le maitre du jeu.
            request.setAttribute("epModif", modifiables);
            if (j.equals(perso.getMJ())) {
                request.setAttribute("personnage", perso);
                request.getRequestDispatcher("/WEB-INF/personnage/showPersonnageMJ.jsp").forward(request, response);
            } // Si c'est un simple joueur
            else if (!j.equals(perso.getOwner())) {
                request.setAttribute("personnage", perso);
                request.getRequestDispatcher("/WEB-INF/personnage/showPersonnageOther.jsp").forward(request, response);
            } // Si le personnage est au joueur
            else if (perso != null) {
                if (PersonnageDAO.instance().peutDemanderChangementMJ(perso)) {
                    request.setAttribute("peutchangerdemj", "oui");
                }
                request.setAttribute("personnage", perso);
                request.getRequestDispatcher("/WEB-INF/personnage/showPersonnage.jsp").forward(request, response);
            } else {
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

    private void transferPerso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JoueurModel joueur = this.getUser(request, response);
            PersonnageModel pm = PersonnageDAO.instance().get(Integer.parseInt(request.getParameter("idPerso")));
            Set<JoueurModel> potentialJoueur = JoueurDAO.instance().getPotentialJoueur(pm);
            request.setAttribute("potentialJoueur", potentialJoueur);
            request.setAttribute("idPerso", Integer.parseInt(request.getParameter("idPerso")));
            request.getRequestDispatcher("/WEB-INF/personnage/findJoueurTransfer.jsp").forward(request, response);
        } catch (DAOException ex) {
            super.erreurBD(request, response, ex);
        }

    }

    private void doUpdateEpisode(HttpServletRequest request, HttpServletResponse response) {
        int idEpisode = Integer.parseInt((String) request.getParameter("idEp"));
        int i = 1;
        LinkedList<String> textareaList = new LinkedList<String>();
        LinkedList<Integer> checkList = new LinkedList<Integer>();
        while ((String) request.getParameter(Integer.toString(i)) != null) {
            textareaList.add((String) request.getParameter(Integer.toString(i)));
            if (request.getParameter(Integer.toString(i + 1)) != null) {
                checkList.add(1);
            } else {
                checkList.add(0);
            }
            i = i + 2;
        }
        boolean validate = false;
        if (request.getParameter("validationEpisode") != null) {
            validate = true;
        }
        try {
            EpisodeModel epm = (EpisodeModel) EpisodeDAO.instance().get(idEpisode);
            i = 0;
            for (ParagrapheModel pm : epm.getParagraphes()) {
                pm.setContenu(textareaList.get(i));
                pm.setEpisode(epm);
                pm.setSecret((checkList.get(i) == 1) ? true : false);
                ParagrapheDAO.instance().update(pm);
                i++;
            }
            epm.setValidJoueur(validate);
            EpisodeDAO.instance().update(epm);
            try {
                String contextPath = request.getContextPath();
                response.sendRedirect(response.encodeRedirectURL(contextPath + "/joueur?action=SHOW"));
            } catch (IOException ex) {
                Logger.getLogger(PersonnageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (DAOException ex) {
            Logger.getLogger(PersonnageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void doValidMJ(HttpServletRequest request, HttpServletResponse response) {
        int idEpisode = Integer.parseInt((String) request.getParameter("idEp"));
        int i = 1;
        ArrayList<String> textareaList = new ArrayList<String>();
        while ((String) request.getParameter(Integer.toString(i)) != null) {
            textareaList.add((String) request.getParameter(Integer.toString(i)));
            i++;
        }

        try {
            EpisodeModel epm = (EpisodeModel) EpisodeDAO.instance().get(idEpisode);
            i = 0;
            for (ParagrapheModel pm : epm.getParagraphes()) {
                pm.setContenu(textareaList.get(i));
                pm.setEpisode(epm);
                ParagrapheDAO.instance().update(pm);
                i++;
            }
            epm.setValidMJ(true);
            EpisodeDAO.instance().update(epm);
            try {
                String contextPath = request.getContextPath();
                response.sendRedirect(response.encodeRedirectURL(contextPath + "/joueur?action=SHOW"));
            } catch (IOException ex) {
                Logger.getLogger(PersonnageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (DAOException ex) {
            Logger.getLogger(PersonnageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void doRefuseMJ(HttpServletRequest request, HttpServletResponse response) {
        int idEpisode = Integer.parseInt((String) request.getParameter("idEp"));
        try {
            EpisodeModel epm = (EpisodeModel) EpisodeDAO.instance().get(idEpisode);
            epm.setValidMJ(false);
            epm.setValidJoueur(false);

            EpisodeDAO.instance().update(epm);
            try {
                String contextPath = request.getContextPath();
                response.sendRedirect(response.encodeRedirectURL(contextPath + "/joueur?action=SHOW"));
            } catch (IOException ex) {
            }
        } catch (DAOException ex) {
            Logger.getLogger(PersonnageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void revealParagraph(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idPar = Integer.parseInt((String) request.getParameter("idPar"));
        try {
            ParagrapheModel p = ParagrapheDAO.instance().get(idPar);
            int idPerso = ParagrapheDAO.instance().getNumPerso(idPar);
            ParagrapheDAO.instance().updateVisibility(p);
            String contextPath = request.getContextPath();
            response.sendRedirect(response.encodeRedirectURL(contextPath + "/personnage?action=SHOW&idPerso=" + idPerso));
        } catch (DAOException ex) {
            super.erreurBD(request, response, ex);
        }
    }

    private void saisiEpisode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idPerso = Integer.parseInt(request.getParameter("idPerso"));
            PersonnageModel perso = PersonnageDAO.instance().get(idPerso);
            if (perso != null) {
                request.setAttribute("personnage", perso);
                request.setAttribute("idPerso", idPerso);
                request.getRequestDispatcher("/WEB-INF/personnage/rajouterEpisode.jsp").forward(request, response);
            } else {
                super.error404(request, response);
            }
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

    private void askJoueurTransfer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idPerso = Integer.parseInt(request.getParameter("idPerso"));
            int idJoueur = Integer.parseInt(request.getParameter("idJoueur"));
            PersonnageModel perso = PersonnageDAO.instance().get(idPerso);
            JoueurModel j = JoueurDAO.instance().get(idJoueur);
            PersonnageValidator.instance().askJoueurTransferValidate(perso, j);
            PersonnageDAO.instance().askJoueurTransfer(perso, j);
            String contextPath = request.getContextPath();
            response.sendRedirect(response.encodeRedirectURL(contextPath + "/personnage?action=SHOW&idPerso=" + perso.getId()));
        } catch (ValidatorException ex) {
            try {
                int idPerso = Integer.parseInt(request.getParameter("idPerso"));
                PersonnageModel perso;
                perso = PersonnageDAO.instance().get(idPerso);
                Set<JoueurModel> potentialJ = JoueurDAO.instance().getPotentialJoueur(perso);
                request.setAttribute("error", ex.getMessage());
                request.setAttribute("potentialMJ", potentialJ);
                request.getRequestDispatcher("/WEB-INF/personnage/findJoueurTransfer.jsp").forward(request, response);
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
        } else if (action.equals("TRANSFER")) {
            this.transferPerso(request, response);
        } else if (action.equals("FINDMJ")) {
            this.findMJ(request, response);
        } else if (action.equals("REVEAL")) {
            this.revealParagraph(request, response);
        } else if (action.equals("NEWTRANSI")) {
            this.addTransition(request, response);
        } else if (action.equals("VALIDERTRANSI")) {
            this.doValidMJ(request, response);
        } else if (action.equals("UPDATETRANSI")) {
            this.doUpdateEpisode(request, response);
        } else if (action.equals("REFUSERTRANSI")) {
            this.doRefuseMJ(request, response);
        } else if (action.equals("NEWEP")) {
            this.saisiEpisode(request, response);
        } else if (action.equals("VOIREP")) {
            this.showEpisode(request, response);
        } else if (action.equals("MODIFEPI")) {
            this.showEpisodeSauvegarde(request, response);
        } else if (action.equals("SUPPEPI")) {
            this.supprimerEpisode(request, response);
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
        } else if (action.equals("ASKJOUEURTRANSFER")) {
            this.askJoueurTransfer(request, response);
        } else if (action.equals("LEAVEMJ")) {
            this.leaveMJ(request, response);
        } else if (action.equals("MODIFEPI")) {
            this.showEpisodeSauvegarde(request, response);
        } else if (action.equals("SUPPEPI")) {
            this.supprimerEpisode(request, response);
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
