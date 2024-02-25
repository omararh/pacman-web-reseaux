package fr.angers.m1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.angers.m1.beans.Partie;

public class PartieDaoImpl implements PartieDao {

    private DAOFactory daoFactory;
    //SELECT pseudo, id, score, date FROM partie NATURAL JOIN partie WHERE pseudo = ?
    private static final String SQL_SELECTMESPARTIES = "SELECT pseudo, id, score, date FROM utilisateur NATURAL JOIN partie WHERE pseudo=? ORDER BY date DESC";
    //private static final String SQL_SELECT = "SELECT pseudo , id, score, date FROM utilisateur NATURAL JOIN partie ORDER BY date DESC";
    //private static final String SQL_SELECTTOP10 = "SELECT id, score, date FROM partie ORDER BY score DESC LIMIT 10";
    private static final String SQL_SELECTTOP10 = "SELECT pseudo , id, score, date FROM utilisateur NATURAL JOIN partie ORDER BY score DESC LIMIT 10";
    //SELECT pseudo , score, date FROM utilisateur NATURAL JOIN partie
    //SELECT * FROM partie ORDER BY score DESC LIMIT 5

    // Constructeur
    public PartieDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
    }


//	@Override
//    public ArrayList<Partie> getParties() {
//        Connection connexion = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        ArrayList<Partie> parties = new ArrayList<Partie>();
//
//        try {
//            connexion = daoFactory.getConnection();
//            preparedStatement = DAOUtilitaire.initRequetePreparee(connexion, SQL_SELECT, true);
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                parties.add(map(resultSet));
//            }
//        } catch (SQLException e) {
//            throw new DAOException(e);
//        } finally {
//            DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
//        }
//
//        return parties;
//    }

    @Override
    public ArrayList<Partie> getMesParties(String pseudo) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Partie> parties = new ArrayList<Partie>();

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initRequetePreparee(connexion, SQL_SELECTMESPARTIES, false, pseudo);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                parties.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return parties;
    }


    @Override
    public ArrayList<Partie> getTop10Parties() throws DAOException {

        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Partie> top10Parties = new ArrayList<Partie>();

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtilitaire.initRequetePreparee(connexion, SQL_SELECTTOP10, true);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                top10Parties.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtilitaire.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return top10Parties;

    }


    private Partie map(ResultSet resSet) throws SQLException {
        Partie partie = new Partie();

        partie.setId(resSet.getLong(COLONNE_ID));
        partie.setScore(resSet.getInt(COLONNE_SCORE));
        partie.setJoueur(resSet.getString(COLONNE_JOUEUR));
        partie.setDate(resSet.getTimestamp(COLONNE_DATE));

        return partie;
    }


}
