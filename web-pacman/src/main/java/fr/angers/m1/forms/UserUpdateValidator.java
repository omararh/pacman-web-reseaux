package fr.angers.m1.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.angers.m1.beans.Utilisateur;
import fr.angers.m1.dao.UtilisateurDao;

public class UserUpdateValidator {
    private static final String CHAMP_NEW_PSEUDO  = "new_pseudo";
    public static final String CHAMP_NEW_EMAIL = "new_email";
    public static final String CHAMP_NEW_PASSWORD = "new_password";
    public static final String CHAMP_CONF_NEW_PASSWORD = "conf_new_password";
    private static final String SQL_SELECT_PAR_PSEUDO = "SELECT * FROM utilisateur WHERE pseudo = ?";
    private static final String SQL_SELECT_PAR_EMAIL = "SELECT * FROM utilisateur WHERE email = ?";
    private String resultat;

    private Map <String, String> erreurs = new HashMap <String, String> ();

    private UtilisateurDao utilisateurDao;

    // Constructeur
    public UserUpdateValidator(UtilisateurDao utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }

    public String getResultat() {
        return resultat;
    }

    public Map <String, String> getErreurs() {
        return erreurs;
    }

    /**
     *
     * @param utilisateur
     * @param request
     * @return Utilisateur
     * @throws ValidatorException
     */
    public Utilisateur modificationCompte(Utilisateur utilisateur,HttpServletRequest request) throws ValidatorException {
        String new_pseudo = getValeurChamp(request, CHAMP_NEW_PSEUDO);
        String new_email = getValeurChamp(request, CHAMP_NEW_EMAIL);
        String new_password = getValeurChamp(request, CHAMP_NEW_PASSWORD);
        String new_conf_password = getValeurChamp(request, CHAMP_CONF_NEW_PASSWORD);

        Utilisateur newUtilisateur = new Utilisateur();
        newUtilisateur.setId(utilisateur.getId());
        newUtilisateur.setPseudo(utilisateur.getPseudo());
        newUtilisateur.setEmail(utilisateur.getEmail());
        newUtilisateur.setPassword(utilisateur.getPassword());

        if(new_pseudo != null && new_pseudo != "") {
            try {
                validationNewPseudo(new_pseudo);
            }
            catch(ValidatorException e) {
                erreurs.put(CHAMP_NEW_PSEUDO, e.getMessage());
            }
            newUtilisateur.setPseudo(new_pseudo);
        }

        if(new_email != null && new_email != "") {
            try {
                validationNewEmail(new_email);
            } catch (ValidatorException e) {
                erreurs.put(CHAMP_NEW_EMAIL, e.getMessage());
            }
            newUtilisateur.setEmail(new_email);
        }

        if(new_password != null && new_conf_password != null) {
            try {
                validation_new_password(new_password);
            } catch (ValidatorException e) {
                erreurs.put(CHAMP_NEW_PASSWORD, e.getMessage());
            }

            try {
                validation_conf_new_password(new_conf_password, new_password);
            } catch (ValidatorException e) {
                erreurs.put(CHAMP_CONF_NEW_PASSWORD, e.getMessage());
            }
            newUtilisateur.setPassword(new_password);
        }

        if(erreurs.isEmpty()) {
            utilisateurDao.modifierCompte( newUtilisateur );
            resultat = "Modification effectuée avec succès";
            return newUtilisateur;
        }

        resultat = "Modification échouée, merci de réessayer";
        return utilisateur;

    }

    /**
     *
     * @param new_email
     * @throws ValidatorException
     */
    private void validationNewEmail(String new_email) throws ValidatorException {
        if (new_email != null ) {
            if (!new_email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new ValidatorException( "Merci de saisir une adresse mail valide");
            } else if (utilisateurDao.verifier(SQL_SELECT_PAR_EMAIL, new_email) != null) {
                throw new ValidatorException("Cette adresse mail est déjà utilisée, merci d'en choisir une autre");
            }
        } else {
            throw new ValidatorException("Merci de saisir une adresse mail");
        }
    }

    /**
     *
     * @param new_conf_password
     * @param new_password
     * @throws ValidatorException
     */
    private void validation_conf_new_password(String new_conf_password, String new_password) throws ValidatorException {
        if (!new_conf_password.contentEquals(new_password)  ) {
            throw new ValidatorException("La confirmation doit être identique au nouveau mot de passe");
        }
    }

    /**
     *
     * @param new_password
     * @throws ValidatorException
     */
    private void validation_new_password(String new_password) throws ValidatorException {
        if (new_password.length() < 4 ) {
            throw new ValidatorException("Le nouveau mot de passe doit contenir au moins 4 caractères");
        }
    }

    /**
     *
     * @param new_pseudo
     * @throws ValidatorException
     */
    private void validationNewPseudo(String new_pseudo) throws ValidatorException {
        if (new_pseudo.length() < 3 ) {
            throw new ValidatorException("Le nouveau nom d'utilisateur doit contenir au moins 3 caractères");
        }else if (utilisateurDao.verifier(SQL_SELECT_PAR_PSEUDO, new_pseudo) != null) {
            throw new ValidatorException("Ce pseudo est déjà utilisé, merci d'en choisir un autre");
        }
    }

    /**
     *
     * @param request
     * @param nomChamp
     * @return
     */
    private static String getValeurChamp (HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur;
        }
    }

}
