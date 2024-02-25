package fr.angers.m1.controleur;

import com.google.gson.Gson;

import fr.angers.m1.modele.Game;

public class ControleurServeur implements ControleurGame {
    private Game game;
    private static int touchePacman;

    // constructeur
    public ControleurServeur(Game game) {
        this.game = game;
    }

    public void init() {
        game.init();
    }

    public void start() {
        game.start();
    }

    public void step() {
        game.step();
    }

    public void stop() {
        game.stop();
    }

    public void nb_tour_sec(int nb) {
        game.nb_tour_sec(nb);
    }

    public void chargement(String filename) {
        game.actualiser(filename);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getScore() {
        return game.getScore();
    }

    // retourner le maze en Json
    public String maze() {
        Gson gson = new Gson();
        String json = gson.toJson(game.getMaze());

        return json;
    }

    public static int getTouchePacman() {
        return touchePacman;
    }

    public static void setTouchePacman(int touchePacman) {
        ControleurServeur.touchePacman = touchePacman;
    }
}
