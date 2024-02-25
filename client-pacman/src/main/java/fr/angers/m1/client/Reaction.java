package fr.angers.m1.client;

import fr.angers.m1.vue.Authentification;
import fr.angers.m1.controleur.ControleurClient;
import fr.angers.m1.vue.ViewGame;
import fr.angers.m1.vue.GestionChoixPartie;

public class Reaction {
    @SuppressWarnings("unused") // genere par eclipse !
    private static Authentification authentification;
    private static ViewGame viewGame;
    @SuppressWarnings("unused")
    private static GestionChoixPartie choixPartie;
    @SuppressWarnings("unused")
    private static ControleurClient controleurClient;

    // authentification du client
    public static void authentification() {
        authentification = new Authentification();
    }

    // type de la partie a jouer (aleatoire ou manuelle)
    public static void choixPartie() {
        choixPartie = new GestionChoixPartie();
    }

    // creer la vue game a partir du chemin envoye par le serveur
    public static void cheminMaze(String msgServeur) {
        String[] chemin = msgServeur.split(":");
        viewGame = new ViewGame(chemin[1]);
        controleurClient = new ControleurClient(viewGame);
    }

    public static void miseAjour(String msgServeur) {
        String[] donneeMiseAjour = msgServeur.split(";");

        // on vérifie d'abord si le message envoyé par le serveur est complet
        if(donneeMiseAjour.length == 7) {
            MiseAJour.majCompteurTour(donneeMiseAjour[1]);
            MiseAJour.majScore(donneeMiseAjour[2]);
            MiseAJour.majNombreTourMax(donneeMiseAjour[3]);
            MiseAJour.majViesPacman(donneeMiseAjour[4]);
            MiseAJour.majGhostScare(donneeMiseAjour[5]);
            MiseAJour.majMaze(donneeMiseAjour[6]);
            viewGame.update();
        }
        else {
            System.out.println("Le serveur n'a pas envoyé toutes les informations !");
        }
    }
}
