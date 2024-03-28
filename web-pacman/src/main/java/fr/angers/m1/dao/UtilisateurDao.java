package fr.angers.m1.dao;

import fr.angers.m1.beans.Utilisateur;

public interface UtilisateurDao {
    public static String TABLE_UTILISATEUR = "utilisateur";
    public static String COLONNE_ID = "id";
    public static String COLONNE_EMAIL = "email";
    public static String COLONNE_PASSWORD = "password";
    public static String COLONNE_PSEUDO = "pseudo";
    public static String COLONNE_DATE_INS = "date_inscription";


    public void inserer(Utilisateur utilisateur) throws DAOException;
    public Utilisateur trouver(String identifiant) throws DAOException;
    public void supprimerCompte(String identifiant) throws DAOException;
    public void modifierCompte(Utilisateur utilisateur) throws DAOException;
    Utilisateur verifier(String sql, Object ... objets) throws DAOException;

}
