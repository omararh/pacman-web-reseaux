package fr.angers.m1.forms;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import fr.angers.m1.beans.Utilisateur;
import fr.angers.m1.dao.UtilisateurDao;

/**
 * CredentialsValidator s'occupe de la validation des identifiants
 * d'un utilisateur tentant de se connecter.
 */
public class CredentialsValidator {
    private static final String CHAMP_PSEUDO = "pseudo";
    private static final String CHAMP_PASSWORD = "password";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<>();

    private UtilisateurDao utilisateurDao;

    /**
     * Constructeur de la classe CredentialsValidator.
     *
     * @param utilisateurDao DAO pour interagir avec les utilisateurs en base de données.
     */
    public CredentialsValidator(UtilisateurDao utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }

    /**
     * Valide les informations de connexion fournies par l'utilisateur.
     *
     * @param request La requête HTTP contenant les données de connexion.
     * @return Un objet Utilisateur si les informations sont valides, sinon null.
     */
    public Utilisateur connexionUtilisateur(HttpServletRequest request) {
        String pseudo = getValeurChamp(request, CHAMP_PSEUDO);
        String password = getValeurChamp(request, CHAMP_PASSWORD);

        Utilisateur utilisateur = utilisateurDao.trouver(pseudo);

        if (utilisateur != null) {
            if (!utilisateur.getPassword().equals(password)) {
                setErreur(CHAMP_PASSWORD, "Mot de passe incorrect");
                utilisateur = null;
            }
        } else {
            setErreur(CHAMP_PSEUDO, "Identifiant indiqué n'existe pas");
        }

        if (erreurs.isEmpty()) {
            resultat = "Connexion réussie";
        } else {
            resultat = "Échec de connexion. Veuillez réessayer";
        }

        return utilisateur;
    }

    /**
     * Ajoute un message d'erreur associé à un champ spécifique.
     *
     * @param champ Le nom du champ lié à l'erreur.
     * @param message Le message d'erreur à ajouter.
     */
    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }

    /**
     * Récupère la valeur d'un champ de formulaire spécifique.
     *
     * @param request La requête HTTP contenant le formulaire.
     * @param nomChamp Le nom du champ à récupérer.
     * @return La valeur du champ, ou null si le champ est vide ou non présent.
     */
    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        return (valeur == null || valeur.trim().length() == 0) ? null : valeur;
    }

    // Getters

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public static String getChampPseudo() {
        return CHAMP_PSEUDO;
    }

    public static String getChampPassword() {
        return CHAMP_PASSWORD;
    }
}
