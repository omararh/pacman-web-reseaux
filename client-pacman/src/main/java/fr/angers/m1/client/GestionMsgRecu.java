package fr.angers.m1.client;

public class GestionMsgRecu {

    // Constructeur
    public GestionMsgRecu () {}

    public static void gestionMsgServeur (String message) {

        if(message.startsWith("authentification:")) {
            Reaction.authentification();

        } else if(message.startsWith("choix:")) {
            Reaction.choixPartie();

        } else if(message.startsWith("chemin:")) {
            Reaction.cheminMaze(message);

        } else if(message.startsWith("update;")) {
            Reaction.miseAjour(message);
        }
    }

}
