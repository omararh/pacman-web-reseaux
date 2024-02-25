package fr.angers.m1.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.angers.m1.beans.Utilisateur;
import fr.angers.m1.beans.Partie;
import fr.angers.m1.dao.PartieDao;
import fr.angers.m1.dao.DAOFactory;

@WebServlet("/Accueil")
public class Accueil extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String ATT_SESSION_UTILISATEUR = "sessionUtilisateur";
    private static final String CONF_DAO_FACTORY = "daoFactory";
    private static final String ATT_PARTIES = "partie";
    private static final String VUE = "/WEB-INF/accueil.jsp";

    private PartieDao partieDao ;

    public void init() throws ServletException {
        // récupération d'une instance de notre dao partie
        this.partieDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getPartieDao();
    }

    public Accueil() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Récupération de la session depuis la requête
        HttpSession session = request.getSession();

        // Si l'objet utilisateur n'existe pas dans la session en cours, alors
        // l'utilisateur n'est pas connecté.
        if (session.getAttribute(ATT_SESSION_UTILISATEUR) == null) {
            response.sendRedirect("/web-pacman/connexion");
            // Redirection vers la page publique

        } else {
            String pseu = ((Utilisateur) session.getAttribute(ATT_SESSION_UTILISATEUR)).getPseudo();
            System.out.println("session : "+pseu);

            List<Partie> parties = partieDao.getMesParties(pseu);

            request.setAttribute(ATT_PARTIES, parties);
            // Affichage de la page restreinte
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
