package fr.angers.m1.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.angers.m1.beans.Utilisateur;
import fr.angers.m1.dao.DAOException;
import fr.angers.m1.dao.UtilisateurDao;

public class InscriptionForm {
    public static final String CHAMP_PSEUDO = "pseudo";
    public static final String CHAMP_EMAIL = "email";
    public static final String CHAMP_PASSWORD = "password";
    public static final String CHAMP_CONF_PASSWORD = "conf_password";
    private String resultat;

    private Map <String, String> erreurs = new HashMap <String, String> ();

    private UtilisateurDao utilisateurDao;

    // Constructeur
    public InscriptionForm( UtilisateurDao utilisateurDao ) {
        this.utilisateurDao = utilisateurDao;
    }

    public String getResultat() {
        return resultat;
    }

    public Map <String, String> getErreurs() {
        return erreurs;
    }

    public Utilisateur inscriptionUtilisateur (HttpServletRequest request) {
        String pseudo = getValeurChamp(request, CHAMP_PSEUDO);
        String email = getValeurChamp(request, CHAMP_EMAIL);
        String password = getValeurChamp(request, CHAMP_PASSWORD);
        String conf_password = getValeurChamp(request, CHAMP_CONF_PASSWORD);

        Utilisateur utilisateur = new Utilisateur();

        try {
            traiterEmail(email, utilisateur);
            traiterPseudo(pseudo, utilisateur);
            traiterPassword(password, conf_password, utilisateur);

            if (erreurs.isEmpty()) {
                utilisateurDao.inserer(utilisateur);
                resultat = "Inscription effectuée avec succès";
            } else {
                resultat = "Échec de l'inscription";
            }

        } catch (DAOException e) {
            resultat = "Une erreur est survenue, merci de réessayer";
            e.printStackTrace();
        }

        return utilisateur;
    }

    // appel à la méthode validation email
    private void traiterEmail(String email, Utilisateur utilisateur) {
        try {
            validationEmail (email);
        } catch (FormValidationException e) {
            setErreur(CHAMP_EMAIL, e.getMessage());
        }
        utilisateur.setEmail(email);
    }

    private void traiterPseudo (String pseudo, Utilisateur utilisateur) {
        try {
            validationPseudo (pseudo);
        } catch (FormValidationException e) {
            setErreur(CHAMP_PSEUDO, e.getMessage());
        }
        utilisateur.setPseudo(pseudo);
    }

    private void traiterPassword (String password, String conf_password, Utilisateur utilisateur) {
        try {
            validationPassword(password, conf_password);
        } catch (FormValidationException e) {
            setErreur(CHAMP_PASSWORD, e.getMessage());
            setErreur(CHAMP_CONF_PASSWORD, null);
        }
        utilisateur.setPassword(password);
    }


    private void validationEmail(String email) throws FormValidationException {
        if ( email != null ) {
            if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
                throw new FormValidationException( "Merci de saisir une adresse mail valide" );
            }
        } else {
            throw new FormValidationException( "Merci de saisir une adresse mail" );
        }

    }

    private void validationPassword(String password, String conf_password) throws FormValidationException {
        if (password != null && conf_password != null ) {
            if ( !password.equals( conf_password ) ) {
                throw new FormValidationException("Les mots de passe entrés sont différents, merci de les saisir à nouveau" );
            } else if ( password.length() < 4 ) {
                throw new FormValidationException("Les mots de passe doivent contenir au moins 4 caractères" );
            }
        } else {
            throw new FormValidationException("Merci de saisir et confirmer votre mot de passe" );
        }

    }

    private void validationPseudo(String pseudo) throws FormValidationException {
        if (pseudo != null && pseudo.length() < 3) {
            throw new FormValidationException("Le nom d'utilisateur doit contenir au moins 3 caractères" );
        }

    }

    //Ajoute un message correspondant au champ spécifié à la map des erreurs.
    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }

    private static String getValeurChamp (HttpServletRequest request, String nomChamp) {
        //recuperer ce que l'utilisateur a saisi dans le champ
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur;
        }
    }

}