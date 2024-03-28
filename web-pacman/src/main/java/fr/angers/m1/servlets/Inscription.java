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
import fr.angers.m1.forms.SigningUpValidator;

@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String ATT_UTILISATEUR = "utilisateur";
    public static final String ATT_SESSION_UTILISATEUR = "sessionUtilisateur";
    public static final String ATT_FORM = "form";
    private static final String VUE = "/WEB-INF/inscription.jsp";

    private UtilisateurDao utilisateurDao;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
    }

    public Inscription () {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute( ATT_SESSION_UTILISATEUR ) == null) {
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        } else {
            response.sendRedirect("/web-pacman/accueil");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SigningUpValidator signingUpValidator = new SigningUpValidator(utilisateurDao);

        Utilisateur utilisateur = signingUpValidator.inscriptionUtilisateur(request);

        request.setAttribute(ATT_FORM, signingUpValidator);
        request.setAttribute(ATT_UTILISATEUR, utilisateur);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

}
