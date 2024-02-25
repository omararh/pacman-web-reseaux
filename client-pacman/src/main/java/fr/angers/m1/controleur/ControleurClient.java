package fr.angers.m1.controleur;

import fr.angers.m1.client.ClientEnvoi;
import fr.angers.m1.modele.Maze;
import fr.angers.m1.vue.ViewGame;

public class ControleurClient {
    private static ViewGame view_game;
    private static int compteurTour;
    private static int score;
    private static int nbTourMax;
    private static int viePacman;
    private static boolean ghostScare;
    private static Maze maze;

    public ControleurClient(ViewGame viewGame) {
        view_game = viewGame;

        view_game.getViewCommande().getBtnInit().setEnabled(true);
        view_game.getViewCommande().getBtnStart().setEnabled(false);
        view_game.getViewCommande().getBtnStep().setEnabled(false);
        view_game.getViewCommande().getBtnStop().setEnabled(false);

        view_game.affichage(true);
    }

    public static void init() {
        ClientEnvoi.setMessage("commande:init");
        view_game.getViewCommande().getBtnInit().setEnabled(false);
        view_game.getViewCommande().getBtnStart().setEnabled(true);
        view_game.getViewCommande().getBtnStep().setEnabled(true);
        view_game.getViewCommande().getBtnStop().setEnabled(false);
    }

    public static void start() {
        ClientEnvoi.setMessage("commande:start");
        view_game.getViewCommande().getBtnInit().setEnabled(false);
        view_game.getViewCommande().getBtnStart().setEnabled(false);
        view_game.getViewCommande().getBtnStep().setEnabled(false);
        view_game.getViewCommande().getBtnStop().setEnabled(true);
    }

    public static void step() {
        ClientEnvoi.setMessage("commande:step");
        view_game.getViewCommande().getBtnInit().setEnabled(false);
        view_game.getViewCommande().getBtnStart().setEnabled(true);
        view_game.getViewCommande().getBtnStep().setEnabled(true);
        view_game.getViewCommande().getBtnStop().setEnabled(false);
    }

    public static void stop() {
        ClientEnvoi.setMessage("commande:stop");
        view_game.getViewCommande().getBtnInit().setEnabled(true);
        view_game.getViewCommande().getBtnStart().setEnabled(true);
        view_game.getViewCommande().getBtnStep().setEnabled(false);
        view_game.getViewCommande().getBtnStop().setEnabled(false);
    }

    public static void nb_tour_sec(int nb) {
        ClientEnvoi.setMessage("commande:nb_tour_sec;" + nb);
    }

    // a revoir
    public static void chargement(String filename) {
        ClientEnvoi.setMessage("commande:actualiser;" + filename);
    }


    //compteurTour
    public static int getCompteurTour() {
        return compteurTour;
    }

    public static void setCompteurTour(int compteurTour) {
        ControleurClient.compteurTour = compteurTour;
    }

    //score
    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        ControleurClient.score = score;
    }

    //nbTourMax
    public static int getNbTourMax() {
        return nbTourMax;
    }

    public static void setNbTourMax(int nbTourMax) {
        ControleurClient.nbTourMax = nbTourMax;
    }

    //Vie Pacman
    public static int getViePacman() {
        return viePacman;
    }

    public static void setViePacman(int viePacman) {
        ControleurClient.viePacman = viePacman;
    }

    //Ghost Scare
    public static boolean isGhostScare() {
        return ghostScare;
    }

    public static void setGhostScare(boolean ghostScare) {
        ControleurClient.ghostScare = ghostScare;
    }

    //Maze
    public static Maze getMaze() {
        return maze;
    }

    public static void setMaze(Maze maze) {
        ControleurClient.maze = maze;
    }
}
