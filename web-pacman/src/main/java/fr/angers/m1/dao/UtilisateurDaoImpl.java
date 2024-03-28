package fr.angers.m1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.angers.m1.beans.Utilisateur;
import java.sql.Statement;

public class UtilisateurDaoImpl implements UtilisateurDao {
    private DAOFactory daoFactory;
    private static final String SQL_TROUVER = "SELECT id, email, pseudo, password, date_inscription FROM utilisateur WHERE utilisateur.pseudo = ?";
    private static final String SQL_INSERER = "INSERT INTO utilisateur (email, password, pseudo, date_inscription) VALUES (?, ?, ?, current_timestamp())";
    private static final String SQL_SUPPRIMER = "DELETE FROM utilisateur WHERE utilisateur.pseudo = ?";
    private static final String SQL_MODIFIER_COMPTE = "UPDATE utilisateur SET pseudo = ?, email = ?, password = ? WHERE id = ?";

    public UtilisateurDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void inserer(Utilisateur utilisateur) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet autoGeneratedValues = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtils.initRequetePreparee(connexion, SQL_INSERER, true, utilisateur.getEmail(), utilisateur.getPassword(), utilisateur.getPseudo());
            int statut = preparedStatement.executeUpdate();

            if(statut == 0) {
                throw new DAOException("Echec de l'ajout de l'utilisateur , aucune ligne ajoutée dans la table.");
            }

            autoGeneratedValues = preparedStatement.getGeneratedKeys();

            if(autoGeneratedValues.next()) {
                utilisateur.setId(autoGeneratedValues.getLong(1));
            } else {
                throw new DAOException("Échec de l'ajout de l'utilisateur en BDD, aucun ID auto-généré retourné.");
            }

        }catch(SQLException e) {
            throw new DAOException(e);
        }finally {
            DAOUtils.fermeturesSilencieuses(autoGeneratedValues, preparedStatement, connexion);
        }
    }


    /**
     * @param identifiant
     * @return
     * @throws DAOException
     */
    @Override
    public Utilisateur trouver (String identifiant) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resSet = null;
        Utilisateur utilisateur = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtils.initRequetePreparee(connexion, SQL_TROUVER, false, identifiant);
            resSet = preparedStatement.executeQuery();
            if(resSet.next()) {
                utilisateur = map(resSet);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
        }

        return utilisateur;
    }

    /**
     * @param sql
     * @param objets
     * @return
     * @throws DAOException
     */
    @Override
    public Utilisateur verifier(String sql, Object... objets) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Utilisateur utilisateur = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtils.initRequetePreparee(connexion, sql, false, objets );
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
                utilisateur = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            DAOUtils.fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return utilisateur;
    }

    /**
     *
     * @param identifiant
     */
    @Override
    public void supprimerCompte(String identifiant) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet autoGeneratedValues = null;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = DAOUtils.initRequetePreparee(connexion, SQL_SUPPRIMER, true, identifiant);
            int statut = preparedStatement.executeUpdate();
            if(statut == 0)
                throw new DAOException("Echec suppression du compte");
        }catch(SQLException e) {
            throw new DAOException(e);
        }finally {
            DAOUtils.fermeturesSilencieuses(autoGeneratedValues, preparedStatement, connexion);
        }
    }

    /**
     *
     * @param utilisateur
     * @throws DAOException
     */
    @Override
    public void modifierCompte(Utilisateur utilisateur) throws DAOException {
        Connection connexion = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();

            statement = connexion.createStatement();
            statement.execute("SET foreign_key_checks = 0");

            preparedStatement = DAOUtils.initRequetePreparee(connexion, SQL_MODIFIER_COMPTE, true, utilisateur.getPseudo(), utilisateur.getEmail(), utilisateur.getPassword(), utilisateur.getId());
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException("Échec de la modification de l'utilisateur, aucune ligne modifiée dans la table." );
            }

            statement.execute("SET foreign_key_checks = 1");
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            DAOUtils.fermeturesSilencieuses(preparedStatement, connexion);
        }
    }

    /**
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private static Utilisateur map (ResultSet resultSet) throws SQLException {

        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setId(resultSet.getLong(COLONNE_ID));
        utilisateur.setEmail(resultSet.getString(COLONNE_EMAIL));
        utilisateur.setPassword(resultSet.getString(COLONNE_PASSWORD));
        utilisateur.setPseudo(resultSet.getString(COLONNE_PSEUDO));
        utilisateur.setDate_inscription(resultSet.getTimestamp(COLONNE_DATE_INS));

        return utilisateur;
    }


}
