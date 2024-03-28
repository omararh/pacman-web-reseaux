package fr.angers.m1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.angers.m1.beans.Partie;

public class PartieDaoImpl implements PartieDao {

    private DAOFactory daoFactory;
    private static final String SQL_SELECTMESPARTIES = "SELECT pseudo, id, score, date FROM utilisateur NATURAL JOIN partie WHERE pseudo=? ORDER BY date DESC";
    private static final String SQL_SELECTTOP10 = "SELECT pseudo , id, score, date FROM utilisateur NATURAL JOIN partie ORDER BY score DESC LIMIT 10";
    private static final String SQL_DELETE_PARTI_BY_PSEUDO = "DELETE FROM partie WHERE pseudo=?";
    private static final String SQL_UPDATE_PSEUDO =  "UPDATE partie SET pseudo=? where pseudo=?;";

    public PartieDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
    }

    @Override
    public ArrayList<Partie> getMesParties(String pseudo) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Partie> parties = new ArrayList<Partie>();

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtils.initRequetePreparee(connexion, SQL_SELECTMESPARTIES, false, pseudo);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                parties.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtils.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
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
            preparedStatement = DAOUtils.initRequetePreparee(connexion, SQL_SELECTTOP10, true);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                top10Parties.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtils.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return top10Parties;
    }

    /**
     * Supprime toutes les parties associées à un pseudo d'utilisateur.
     * Cela est utilisé lors de la suppression d'un compte utilisateur pour maintenir l'intégrité de la base de données.
     * @param pseudo Le pseudo de l'utilisateur dont les parties doivent être supprimées.
     * @throws DAOException Si une erreur de base de données survient.
     */
    public void deleteByPseudo(String pseudo) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtils.initRequetePreparee(connexion, SQL_DELETE_PARTI_BY_PSEUDO, false, pseudo);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtils.fermeturesSilencieuses(preparedStatement, connexion);
        }
    }

    /**
     * modifier le pseudo quand le fais dans la table user
     * @param pseudo
     * @param newPseudo
     * @throws DAOException
     */
    public void updatePseudo(String pseudo, String newPseudo) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtils.initRequetePreparee(connexion, SQL_UPDATE_PSEUDO, true, newPseudo, pseudo);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOUtils.fermeturesSilencieuses(preparedStatement, connexion);
        }
    }

    /**
     *
     * @param resSet
     * @return
     * @throws SQLException
     */
    private Partie map(ResultSet resSet) throws SQLException {
        Partie partie = new Partie();

        partie.setId(resSet.getLong(COLONNE_ID));
        partie.setScore(resSet.getInt(COLONNE_SCORE));
        partie.setJoueur(resSet.getString(COLONNE_JOUEUR));
        partie.setDate(resSet.getTimestamp(COLONNE_DATE));

        return partie;
    }
}
