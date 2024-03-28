package fr.angers.m1.servlets;

import java.io.IOException;
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
        this.partieDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getPartieDao();
    }

    public Accueil() {
        super();
    }

    /**
     *
     * @param request   an {@link HttpServletRequest} object that
     *                  contains the request the client has made
     *                  of the servlet
     *
     * @param response  an {@link HttpServletResponse} object that
     *                  contains the response the servlet sends
     *                  to the client
     *
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute(ATT_SESSION_UTILISATEUR) == null) {
            response.sendRedirect("/web-pacman/connexion");

        } else {
            String pseudo = ((Utilisateur) session.getAttribute(ATT_SESSION_UTILISATEUR)).getPseudo();
            System.out.println("session for the user : " + pseudo);

            List<Partie> parties = partieDao.getMesParties(pseudo);

            request.setAttribute(ATT_PARTIES, parties);
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
