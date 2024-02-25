package fr.angers.m1.client;

import com.google.gson.Gson;

import fr.angers.m1.controleur.ControleurClient;
import fr.angers.m1.modele.Maze;

public class MiseAJour {

    // constructeur
    public MiseAJour () {

    }

    public static void majCompteurTour(String msgServeur) {
        String[] compteur_tour = msgServeur.split(",");
        System.out.println("Compteur tour = " + compteur_tour[1]);
        int nb = Integer.parseInt(compteur_tour[1]);
        ControleurClient.setCompteurTour(nb);
    }

    public static void majScore(String msgServeur) {
        String[] score = msgServeur.split(",");
        System.out.println("Score = " + score[1]);
        int nb = Integer.parseInt(score[1]);
        ControleurClient.setScore(nb);
    }

    public static void majNombreTourMax(String msgServeur) {
        String[] nb_tour_max = msgServeur.split(",");
        System.out.println("Nombre Tour Max = " + nb_tour_max[1]);
        int nb = Integer.parseInt(nb_tour_max[1]);
        ControleurClient.setNbTourMax(nb);
    }

    public static void majViesPacman(String msgServeur) {
        String[] vie_pacman = msgServeur.split(",");
        System.out.println("Vie pacman = " + vie_pacman[1]);
        int nb = Integer.parseInt(vie_pacman[1]);
        ControleurClient.setViePacman(nb);
    }

    public static void majGhostScare(String msgServeur) {
        String[] ghostScare = msgServeur.split(",");
        System.out.println("Ghost Scare = " + ghostScare[1]);
        boolean bool = Boolean.parseBoolean(ghostScare[1]);
        ControleurClient.setGhostScare(bool);
    }

    // mettre à jour le maza à partir du message (Json) reçu
    public static void majMaze(String msgServeur) {
        System.out.println(msgServeur);
        Gson gson = new Gson();
        Maze maze = gson.fromJson(msgServeur, Maze.class);
        ControleurClient.setMaze(maze);
    }

}
