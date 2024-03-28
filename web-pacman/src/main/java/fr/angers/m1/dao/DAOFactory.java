package fr.angers.m1.dao;

import fr.angers.m1.constants.BaseDeDonnesConf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DAOFactory {
    private String url;
    private String username;
    private String password;

    DAOFactory (String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }


    /**
     * @throws DAOConfigurationException
     */
    public static DAOFactory getInstance() throws DAOConfigurationException {
        String url = BaseDeDonnesConf.url + "/" + BaseDeDonnesConf.dataBaseName;
        String driver = BaseDeDonnesConf.className;
        String username = BaseDeDonnesConf.login;
        String password = BaseDeDonnesConf.password;

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DAOConfigurationException("Le driver est introuvable dans le classpath.", e);
        }

        DAOFactory instance = new DAOFactory(url, username, password);
        return instance;
    }

    // Méthode chargée de fournir une connexion à la base de données
    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // Méthodes de récupération de l'implémentation des différents DAO
    public UtilisateurDao getUtilisateurDao() {
        return new UtilisateurDaoImpl(this);
    }

    public PartieDao getPartieDao() {
        return new PartieDaoImpl(this);
    }
}
