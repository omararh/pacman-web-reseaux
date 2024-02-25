package fr.angers.m1.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.angers.m1.beans.Utilisateur;
import fr.angers.m1.dao.DAOFactory;
import fr.angers.m1.dao.UtilisateurDao;
import fr.angers.m1.forms.InscriptionForm;

@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String ATT_UTILISATEUR = "utilisateur";
    public static final String ATT_SESSION_UTILISATEUR = "sessionUtilisateur";
    public static final String ATT_FORM = "form";
    private static final String VUE = "/WEB-INF/inscription.jsp";
    public static final String CONF_DAO_FACTORY = "daofactory";

    private UtilisateurDao utilisateurDao;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
    }

    public Inscription () {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération de la session depuis la requête
        HttpSession session = request.getSession();

        if (session.getAttribute( ATT_SESSION_UTILISATEUR ) == null) {
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        } else {
            // Affichage de la page restreinte
            response.sendRedirect("/web-pacman/accueil");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        InscriptionForm form = new InscriptionForm(utilisateurDao);

        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        Utilisateur utilisateur = form.inscriptionUtilisateur(request);

        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute(ATT_FORM, form);
        request.setAttribute(ATT_UTILISATEUR, utilisateur);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

}
