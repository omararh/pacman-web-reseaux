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
import fr.angers.m1.dao.PartieDao;
import fr.angers.m1.dao.UtilisateurDao;

@WebServlet("/Supprimer")
public class Supprimer extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CONF_DAO_FACTORY = "daoFactory";
    private static final String ATT_SESSION_UTILISATEUR = "sessionUtilisateur";
    private static final String VUE = "/WEB-INF/supprimer.jsp";

    private UtilisateurDao utilisateurDao;
    private PartieDao partieDao;

    public void init() throws ServletException {
        this.utilisateurDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUtilisateurDao();
        this.partieDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getPartieDao();
    }

    public Supprimer() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute(ATT_SESSION_UTILISATEUR) == null) {
            response.sendRedirect("/web-pacman/connexion");
        } else {
            String pseudo = ((Utilisateur) session.getAttribute(ATT_SESSION_UTILISATEUR)).getPseudo();

            partieDao.deleteByPseudo(pseudo);
            utilisateurDao.supprimerCompte(pseudo);
            session.invalidate();
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
