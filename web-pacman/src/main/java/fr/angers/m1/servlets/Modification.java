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
import fr.angers.m1.forms.FormValidationException;
import fr.angers.m1.forms.ModificationForm;

@WebServlet("/Modification")
public class Modification extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String ATT_FORM = "form";
    private static final String VUE = "/WEB-INF/modification.jsp";
    private static final String ATT_SESSION_UTILISATEUR = "sessionUtilisateur";
    public static final String CONF_DAO_FACTORY = "daofactory";

    private UtilisateurDao utilisateurDao;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
    }

    public Modification() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if ( session.getAttribute( ATT_SESSION_UTILISATEUR ) == null ) {
            response.sendRedirect("/web-pacman/connexion");
        } else {
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        ModificationForm form = new ModificationForm(this.utilisateurDao);
        Utilisateur newUtilisateur = null;
        try {
            newUtilisateur = form.modificationCompte((Utilisateur) session.getAttribute(ATT_SESSION_UTILISATEUR), request);
        } catch (FormValidationException e) {
            e.printStackTrace();
        }
        session.setAttribute( ATT_SESSION_UTILISATEUR, newUtilisateur );
        request.setAttribute(ATT_FORM, form);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

}