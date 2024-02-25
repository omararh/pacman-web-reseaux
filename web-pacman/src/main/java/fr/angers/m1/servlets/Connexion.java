package fr.angers.m1.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.angers.m1.beans.Utilisateur;
import fr.angers.m1.dao.UtilisateurDao;
import fr.angers.m1.forms.ConnexionForm;
import fr.angers.m1.dao.DAOFactory;

@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String ATT_SESSION_UTILISATEUR = "sessionUtilisateur";
    private static final String CONF_DAO_FACTORY = "daoFactory";
    private static final String ATT_FORM = "form";
    private static final String ATT_UTILISATEUR = "utilisateur";
    private static final String VUE = "/WEB-INF/connexion.jsp";

    private UtilisateurDao utilisateurDao ;

    public void init() throws ServletException {
        // récupération d'une instance de notre dao utilisateur
        this.utilisateurDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUtilisateurDao();
    }

    public Connexion() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération de la session depuis la requête
        HttpSession session = request.getSession();

        if ( session.getAttribute( ATT_SESSION_UTILISATEUR ) == null ) {
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        } else {
            response.sendRedirect("/web-pacman/accueil");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Préparation de l'objet formulaire
        ConnexionForm form = new ConnexionForm(utilisateurDao);

        // Traitement de la requête et récupération du bean en résultant
        Utilisateur utilisateur = form.connexionUtilisateur(request);

        HttpSession session = request.getSession();

        // Stockage du formulaire et du bean dans l'objet request
        request.setAttribute(ATT_FORM, form );
        request.setAttribute(ATT_UTILISATEUR, utilisateur);

        // Si aucune erreur de validation n'a eu lieu, alors ajout du bean
        // Utilisateur à la session, sinon suppression du bean de la session.
        if (form.getErreurs().isEmpty()) {
            session.setAttribute(ATT_SESSION_UTILISATEUR, utilisateur);
            response.sendRedirect("/web-pacman/accueil");
        } else {
            session.setAttribute(ATT_SESSION_UTILISATEUR, null);
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }
    }

}
