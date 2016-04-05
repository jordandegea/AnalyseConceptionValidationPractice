package controleur;

import dao.DAOException;
import dao.OuvrageDAO;
import java.io.*;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import modele.Ouvrage;

/**
 * Le contrôleur de l'application.
 */
@WebServlet(name = "Controleur", urlPatterns = {"/controleur"})
public class Controleur extends HttpServlet {

    @Resource(name = "jdbc/bibliography")
    private DataSource ds;

    /* pages d’erreurs */
    private void invalidParameters(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/controleurErreur.jsp").forward(request, response);
    }

    private void erreurBD(HttpServletRequest request,
            HttpServletResponse response, DAOException e)
            throws ServletException, IOException {
        request.setAttribute("erreurMessage", e.getMessage());
        request.getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
    }

    /**
     * Actions possibles en GET : afficher (correspond à l’absence du param),
     * getOuvrage.
     */
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        OuvrageDAO ouvrageDAO = new OuvrageDAO(ds);

        try {
            if (action == null) {
                actionAfficher(request, response, ouvrageDAO);
            } else if (action.equals("getOuvrage")) {
                actionGetOuvrage(request, response, ouvrageDAO);
            } else {
                invalidParameters(request, response);
            }
        } catch (DAOException e) {
            erreurBD(request, response, e);
        }
    }

    /**
     *
     * Affiche la page d’accueil avec la liste de tous les ouvrages.
     */
    private void actionAfficher(HttpServletRequest request,
            HttpServletResponse response,
            OuvrageDAO ouvrageDAO) throws DAOException, ServletException, IOException {
        /* On interroge la base de données pour obtenir la liste des ouvrages */
        List<Ouvrage> ouvrages = ouvrageDAO.getListeOuvrages();
        /* On ajoute cette liste à la requête en tant qu’attribut afin de la transférer à la vue
         * Rem. : ne pas confondre attribut (= objet ajouté à la requête par le programme
         * avant un forward, comme ici)
         * et paramètre (= chaîne représentant des données de formulaire envoyées par le client) */
        request.setAttribute("ouvrages", ouvrages);
        /* Enfin on transfère la requête avec cet attribut supplémentaire vers la vue qui convient */
        request.getRequestDispatcher("/WEB-INF/listAll.jsp").forward(request, response);
    }

    /**
     *
     * Récupère les informations sur un ouvrage donné par son identifiant.
     * Ajoute cet ouvrage comme attribut à la requête puis appelle la vue
     * demandée. La requête doit comprendre les paramètres : -- id,
     * l’identifiant de l’ouvrage à récupérer -- view, le nom de la vue à
     * afficher ("modifier" ou "supprimer") Sinon, on appelle invalidParameters.
     */
    private void actionGetOuvrage(HttpServletRequest request,
            HttpServletResponse response,
            OuvrageDAO ouvrageDAO) throws DAOException, ServletException, IOException {
        String view = request.getParameter("view");
        if (view == null)
            invalidParameters(request, response);
        int id = Integer.parseInt(request.getParameter("id"));
        Ouvrage ouvrage = ouvrageDAO.getOuvrage(id);
        request.setAttribute("ouvrage", ouvrage);
        
        if (view.equals("modifier")) {
            request.getRequestDispatcher("/WEB-INF/modifier.jsp").forward(request, response);
        } else if (view.equals("supprimer")) {
            request.getRequestDispatcher("/WEB-INF/supprimer.jsp").forward(request, response);
        } else {
            invalidParameters(request, response);
        }
    }

    /**
     * Actions possibles en POST : ajouter, supprimer, modifier. Une fois
     * l’action demandée effectuée, on retourne à la page d’accueil avec
     * actionAfficher(...)
     */
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        // le paramètre "action" est obligatoire en POST
        if (action == null) {
            invalidParameters(request, response);
            return;
        }
        OuvrageDAO ouvrageDAO = new OuvrageDAO(ds);

        try {
            if (action.equals("ajouter")) {
                actionAjouter(request, response, ouvrageDAO);
            } else if (action.equals("supprimer")) {
                actionSupprimer(request, response, ouvrageDAO);
            } else if (action.equals("modifier")) {
                actionModifier(request, response, ouvrageDAO);
            } else {
                invalidParameters(request, response);
                return;
            }
            /* Une fois l’action effectuée, on revient à la page d’accueil */
            actionAfficher(request, response, ouvrageDAO);

        } catch (DAOException e) {
            erreurBD(request, response, e);
        }
    }

    /**
     * Ajout d'un ouvrage.
     */
    private void actionAjouter(HttpServletRequest request,
            HttpServletResponse response,
            OuvrageDAO ouvrageDAO)
            throws IOException, ServletException, DAOException {
        String auteur = request.getParameter("auteur");
        String titre = request.getParameter("titre");

        ouvrageDAO.ajouterOuvrage(auteur, titre);
    }

    /**
     * Suppression d'un ouvrage.
     */
    private void actionSupprimer(HttpServletRequest request,
            HttpServletResponse response,
            OuvrageDAO ouvrageDAO)
            throws IOException, ServletException, DAOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ouvrageDAO.supprimerOuvrage(id);
    }

    /**
     * Modification d'un ouvrage.
     */
    private void actionModifier(HttpServletRequest request,
            HttpServletResponse response,
            OuvrageDAO ouvrageDAO)
            throws IOException, ServletException, DAOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String auteur = request.getParameter("auteur");
        String titre = request.getParameter("titre");
        ouvrageDAO.modifierOuvrage(id, auteur, titre);
    }

}
