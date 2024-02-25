package fr.angers.m1.controleur;

import fr.angers.m1.modele.Game;

public interface ControleurGame {
    public void init();
    public void start();
    public void step();
    public void stop();
    public void nb_tour_sec(int nb);
    public void chargement(String filename);
    public Game getGame();
    public void setGame(Game game);
    public int getScore();
}
