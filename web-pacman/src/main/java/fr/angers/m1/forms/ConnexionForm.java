package fr.angers.m1.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.angers.m1.beans.Utilisateur;
import fr.angers.m1.dao.UtilisateurDao;

public class ConnexionForm {
    private static final String CHAMP_PSEUDO  = "pseudo";
    private static final String CHAMP_PASSWORD   = "password";
    private String resultat;
    private Map <String, String> erreurs = new HashMap <String, String> ();

    UtilisateurDao utilisateurDao;

    public ConnexionForm (UtilisateurDao utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }

    public Utilisateur connexionUtilisateur (HttpServletRequest request) {
        String id = getValeurChamp(request, CHAMP_PSEUDO);
        String password = getValeurChamp(request, CHAMP_PASSWORD);

        Utilisateur utilisateur = utilisateurDao.trouver(id);

        if (utilisateur != null) {

            if (!utilisateur.getPassword().equals(password)) {
                setErreur(CHAMP_PASSWORD, "Mot de passe incorrect");
                utilisateur = null;
            }
        } else {
            setErreur(CHAMP_PSEUDO, "Identifiant indiqué n'existe pas");
        }

        if(erreurs.isEmpty()) {
            resultat = "Connexion réussie";
        } else {
            resultat = "Échec de connexion. Veuillez réessayer";
        }

        return utilisateur;
    }


    // Ajoute un message correspondant au champ spécifié à la map des erreurs
    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }

    public Map <String, String> getErreurs() {
        return erreurs;
    }

    // méthode pour récuperer les entrées utilisateur
    private static String getValeurChamp (HttpServletRequest request, String nomChamp) {
        //recuperer ce que l'utilisateur a saisi dans le champs
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur;
        }
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