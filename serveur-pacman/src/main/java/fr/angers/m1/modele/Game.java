package fr.angers.m1.modele;

import com.google.gson.Gson;

import fr.angers.m1.agent.Fantome;
import fr.angers.m1.agent.Pacman;
import fr.angers.m1.serveur.ServeurEnvoi;

import java.util.ArrayList;

public abstract class Game implements Runnable{
    private int compteur_tour;
    private int nb_tour_max;
    private boolean isRunning;
    private Thread thread;
    private int sleep;
    private int nb_tour_per_seconde;
    private static int identifiantUser;

    public Game(int nb_tour_max){
        this.compteur_tour = 0; // Initialiser le compteur tour a 0
        this.nb_tour_max = nb_tour_max; // Nombre de tours max
        this.isRunning = false;
        this.sleep = 1000;
        this.nb_tour_per_seconde = 2;
    }

    // Méthode qui recolte toutes les données a envoyer au client
    public String donneesAuClient() {
        String messageAuClient = "update;";

        //compteur_tour
        messageAuClient += "compteur_tour," + compteur_tour + ";";

        //score
        messageAuClient += "score," + getScore() + ";";

        //nb_tour_max
        messageAuClient += "nb_tour_max," + nb_tour_max + ";";

        //vie Pacman
        messageAuClient += "vie_pacman," + getViePacman() + ";";

        //getGhostScare
        messageAuClient += "ghostScare," + getGhostScare() + ";";

        //maze
        Gson gson = new Gson();
        String maze = gson.toJson(this.getMaze());
        messageAuClient += maze;

        return messageAuClient;
    }

    // Tester si le jeu est terminé, si oui retourner true
    public boolean jeuTermine(){
        return (this.compteur_tour != this.nb_tour_max?false:true);
    }

    // setter & getter
    public int getCompteurTour(){
        return this.compteur_tour;
    }

    public void setCompteurTour(int compteur_tour){
        this.compteur_tour = compteur_tour;
    }

    public int getNbTourMax(){
        return this.nb_tour_max;
    }

    public void setNbTourMax(int nb_tour_max){
        this.nb_tour_max = nb_tour_max;
    }

    public boolean getIsRunning(){
        return this.isRunning;
    }

    public void setIsRunning(boolean isRunning){
        this.isRunning = isRunning;
    }

    public int getNb_tour_per_seconde() {
        return this.nb_tour_per_seconde;
    }

    public void setNb_tour_per_seconde(int nb_tour_per_seconde) {
        this.nb_tour_per_seconde = nb_tour_per_seconde;
    }

    public static int getIdentifiantUser() {
        return identifiantUser;
    }

    public static void setIdentifiantUser(int identifiantUser) {
        Game.identifiantUser = identifiantUser;
    }

    public abstract void initializeGame();
    public abstract void takeTurn();
    public abstract void gameOver();
    public abstract void actualiser(String filename);
    public abstract Maze getMaze();
    public abstract void setMaze(Maze maze);
    public abstract ArrayList<Pacman> getListPacman();
    public abstract ArrayList<Fantome> getListFantome();
    public abstract int getScore();
    public abstract int getViePacman();
    public abstract boolean getGhostScare();
    public abstract void choiceUser(int choice);

    // Initialiser le jeu
    public void init(){
        this.compteur_tour = 0;
        initializeGame();
        ServeurEnvoi.setMessage(this.donneesAuClient());
    }

    public void run(){
        do{
            step();

            try {
                Thread.sleep(sleep/this.nb_tour_per_seconde);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while(this.isRunning==true & (this.compteur_tour != this.nb_tour_max));
    }

    public void start(){
        this.isRunning = true;
        this.thread = new Thread(this);
        thread.start();
    }

    public void step(){
        if(this.compteur_tour <= this.nb_tour_max && !this.getListPacman().isEmpty() && !this.getListFantome().isEmpty()){
            this.setCompteurTour(this.getCompteurTour() + 1);
            takeTurn();
            ServeurEnvoi.setMessage(this.donneesAuClient());
        }
        else{
            gameOver();
            ServeurEnvoi.setMessage(this.donneesAuClient());
        }
    }

    public void stop(){
        if(!this.jeuTermine()){
            this.setIsRunning(false);
            ServeurEnvoi.setMessage(this.donneesAuClient());
        }
    }

    // Nombre de tours / seconde lors de changement sur slider
    public void nb_tour_sec(int nb){
        this.nb_tour_per_seconde = nb;
        ServeurEnvoi.setMessage(this.donneesAuClient());
    }
}
