package fr.angers.m1.servlets;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.angers.m1.beans.Partie;
import fr.angers.m1.dao.DAOFactory;
import fr.angers.m1.dao.PartieDao;

@WebServlet("/Index")
public class Index extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VUE = "/index.jsp";
    private static final String CONF_DAO_FACTORY = "daoFactory";
    private static final String ATT_TOP10PARTIES = "partie";

    private PartieDao partieDao ;

    public void init() throws ServletException {
        // récupération d'une instance de notre dao partie
        this.partieDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getPartieDao();
    }

    public Index() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Partie> top10Parties = partieDao.getTop10Parties();
        request.setAttribute(ATT_TOP10PARTIES, top10Parties);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
