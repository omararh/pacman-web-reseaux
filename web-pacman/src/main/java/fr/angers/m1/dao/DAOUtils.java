package fr.angers.m1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * permet de simplifier l'initialisation des requêtes préparées et assure la gestion sécurisée
 * des fermetures de Connection, Statement, et ResultSet dans les opérations JDBC.
 */
public class DAOUtils {

    private DAOUtils() {

    }

    public static PreparedStatement initRequetePreparee(Connection connexion, String sql, boolean returnGeneratedKeys, Object... objets) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement(sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
        for (int i = 0; i < objets.length; i++) {
            preparedStatement.setObject(i + 1, objets[i]);
        }
        return preparedStatement;
    }


    // Fermeture silencieuse du resultset
    public static void fermetureSilencieuse(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("Échec de la fermeture du ResultSet : " + e.getMessage());
            }
        }
    }


    // Fermeture silencieuse du statement
    public static void fermetureSilencieuse(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Échec de la fermeture du Statement : " + e.getMessage());
            }
        }
    }


    // Fermeture silencieuse de la connexion
    public static void fermetureSilencieuse(Connection connexion) {
        if (connexion != null) {
            try {
                connexion.close();
            } catch (SQLException e) {
                System.out.println("Échec de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }


    // Fermetures silencieuses du statement & connexion
    public static void fermeturesSilencieuses(Statement statement, Connection connexion) {
        fermetureSilencieuse(statement);
        fermetureSilencieuse(connexion);
    }


    // Fermetures silencieuses du resultset & statement & connexion
    public static void fermeturesSilencieuses(ResultSet resultSet, Statement statement, Connection connexion) {
        fermetureSilencieuse(resultSet);
        fermetureSilencieuse(statement);
        fermetureSilencieuse(connexion);
    }

}
