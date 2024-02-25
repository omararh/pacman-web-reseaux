package fr.angers.m1.modele;

import java.util.ArrayList;

import fr.angers.m1.agent.*;
import fr.angers.m1.baseDeDonnee.BaseDeDonnee;
import fr.angers.m1.comportement.*;;
import fr.angers.m1.serveur.ServeurEnvoi;

public class PacmanGame extends Game {
    private Maze maze;
    private ArrayList<Pacman> list_pacman = new ArrayList<>();
    private ArrayList<Fantome> list_fantome = new ArrayList<>();
    private String filename;
    private ComportementAgent deplacementManuelPacman, deplacementAleatoire;
    private int tempsInvincible, temps_pacman_invincible, score, nb_vie_pacman;
    private boolean ghostScare;
    private boolean pacmanManuel, fontomeManuel;
    private int choixPartie; // pour récuperer le choix du client pacman manuel & pacman aleatoire

    public PacmanGame(int nb_tour_max, String filename){
        super(nb_tour_max);
        this.filename = filename;
        try {
            this.maze = new Maze(this.filename);
        } catch (Exception e) {
            e.printStackTrace();
        }

        deplacementAleatoire = new ComportementAleatoire();
        deplacementManuelPacman = new DeplacementManuelPacman();

        tempsInvincible = 90;
        temps_pacman_invincible = 0;
        score = 0;
        nb_vie_pacman = 3;
        ghostScare = false;
        choixPartie = 0;
        // le pacman se deplace manuellement ou aleatoirement
        pacmanManuel = false;
        // les fontomes se deplacent tjr aleatoirement
        fontomeManuel = false;
    }

    // tester si l'action est autorisee ou pas
    public boolean isLegalMove(Agent agent,AgentAction agentAction) {
        PositionAgent positionAgent = agent.getPosition();
        int position_x = positionAgent.getX();
        int position_y = positionAgent.getY();

        switch (agentAction.getDirection()) {
            case Maze.NORTH:
                position_y--;
                break;
            case Maze.SOUTH:
                position_y++;
                break;
            case Maze.EAST:
                position_x++;
                break;
            case Maze.WEST:
                position_x--;
                break;
            case Maze.STOP:
                break;

            default:
                break;
        }
        //verifier si la position de l'agent n'est pas sur le mur
        return (!this.maze.isWall(position_x, position_y));
    }

    // permet de mettre à jour la nouvelle position de l'agent en fonction de l'action de l'agent action
    public void moveAgent(Agent agent,AgentAction agentAction) {
        if(this.isLegalMove(agent, agentAction)) {
            switch (agentAction.getDirection()) {
                case Maze.NORTH:
                    agent.getPosition().setY(agent.getPosition().getY()-1);
                    agent.getPosition().setDir(Maze.NORTH);
                    break;
                case Maze.SOUTH:
                    agent.getPosition().setY(agent.getPosition().getY()+1);
                    agent.getPosition().setDir(Maze.SOUTH);
                    break;
                case Maze.EAST:
                    agent.getPosition().setX(agent.getPosition().getX()+1);
                    agent.getPosition().setDir(Maze.EAST);
                    break;
                case Maze.WEST:
                    agent.getPosition().setX(agent.getPosition().getX()-1);
                    agent.getPosition().setDir(Maze.WEST);
                    break;
                case Maze.STOP:
                    break;

                default:
                    break;
            }
        }
    }


    // mort du pacman
    public void mortPacman() {
        for(int j = 0; j < list_fantome.size(); j++) {
            PositionAgent agent_fantome = list_fantome.get(j).getPosition();
            int x_f = agent_fantome.getX();
            int y_f = agent_fantome.getY();

            for(int i = 0; i < list_pacman.size(); i++) {
                PositionAgent agent_pacman = list_pacman.get(i).getPosition();
                int x_p = agent_pacman.getX();
                int y_p = agent_pacman.getY();

                if(x_p==x_f && y_p==y_f) {
                    list_pacman.remove(i);
                    this.maze.getPacman_start().remove(i);
                }
            }
        }
    }

    // mort d'un fantôme
    public void mortFantome() {
        for(int i = 0; i < list_pacman.size(); i++) {
            PositionAgent agent_pacman = list_pacman.get(i).getPosition();
            int x_p = agent_pacman.getX();
            int y_p = agent_pacman.getY();

            for(int j = 0; j < list_fantome.size(); j++) {
                PositionAgent agent_fantome = list_fantome.get(j).getPosition();
                int x_f = agent_fantome.getX();
                int y_f = agent_fantome.getY();

                if(x_p==x_f && y_p==y_f) {
                    list_fantome.remove(j);
                    this.maze.getGhosts_start().remove(j);
                    score += 10;
                }
            }
        }
    }

    //Controle des touches clavier selon le choix de l'utilisateur
    public void choixPartie() {
        switch (choixPartie) {
            // cas d'une partie aléatoire
            case 1:
                pacmanManuel = false; //pacman sera gere aleatoirement
                break;

            // cas d'une partie manuelle
            case 2:
                pacmanManuel = true; //pacman sera gere manuellement
                break;

            default:
                break;
        }
    }

    // Initialise le game en mettant à jour les positions des agents
    @Override
    public void initializeGame(){
        this.actualiser(filename);

        choixPartie();
    }

    @Override
    public void takeTurn(){
        if(this.getCompteurTour() <= this.temps_pacman_invincible)
            this.mortFantome();
        else {
            this.temps_pacman_invincible = 0;
            this.ghostScare = false;
            this.mortPacman();
        }

        choixPartie();

        if(!list_pacman.isEmpty()) {
            for(int i = 0; i < list_pacman.size(); i++) {
                // deplacement de pacman
                if(pacmanManuel) {
                    if(deplacementManuelPacman.getAction(list_pacman.get(i),this.maze))
                        System.out.print("");
                    pacmanManuel = false;
                }
                else {
                    if(deplacementAleatoire.getAction(list_pacman.get(i),this.maze))
                        System.out.print("");
                }

                // Mange les pacgommes
                if(this.maze.isFood(list_pacman.get(i).getPosition().getX(),list_pacman.get(i).getPosition().getY())) {
                    this.maze.setFood(list_pacman.get(i).getPosition().getX(),list_pacman.get(i).getPosition().getY(),false);
                    score += 1;
                }

                // Mange les capsule rouge et donc peut manger les fontomes
                if(this.maze.isCapsule(list_pacman.get(i).getPosition().getX(),list_pacman.get(i).getPosition().getY())) {
                    this.maze.setCapsule(list_pacman.get(i).getPosition().getX(),list_pacman.get(i).getPosition().getY(),false);
                    this.ghostScare = true;
                    this.temps_pacman_invincible = this.getCompteurTour() + tempsInvincible;
                }
            }
        }
        else {
            this.nb_vie_pacman -= 1;
        }

        if(!list_fantome.isEmpty()) {
            for(int i = 0; i < list_fantome.size(); i++) {
                // cette condition est la pour une eventuelle amelioration du jeu (multijoueur)
                if(deplacementAleatoire.getAction(list_fantome.get(i),this.maze))
                    System.out.print("");
            }
        }

        ServeurEnvoi.setMessage(this.donneesAuClient());
    }

    // Game over
    @Override
    public void gameOver(){
        if(this.nb_vie_pacman==0) {
            this.setCompteurTour(this.getNbTourMax());
            BaseDeDonnee.enregistrerScore(Game.getIdentifiantUser(), score);
            score = 0;
        }
    }

    // Actualise le maze quand on change de fichier
    @Override
    public void actualiser(String file) {
        this.filename = file;

        try {
            this.maze = new Maze(filename);
            list_pacman.clear();
            list_fantome.clear();

            for(int i = 0; i < this.maze.getInitNumberOfPacmans(); i++) {
                if(!this.maze.isWall(maze.getPacman_start().get(i).getX(),maze.getPacman_start().get(i).getY())) {
                    Pacman pacman_temp = new Pacman(maze.getPacman_start().get(i));
                    list_pacman.add(pacman_temp);
                }
            }

            for(int i = 0; i < this.maze.getInitNumberOfGhosts(); i++) {
                if(!this.maze.isWall(maze.getGhosts_start().get(i).getX(),maze.getGhosts_start().get(i).getY())) {
                    Fantome fantome_temp = new Fantome(maze.getGhosts_start().get(i));
                    list_fantome.add(fantome_temp);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }


    //Getteurs et Setteurs

    @Override
    public Maze getMaze(){
        return this.maze;
    }

    @Override
    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public ArrayList<Pacman> getListPacman(){
        return this.list_pacman;
    }

    @Override
    public ArrayList<Fantome> getListFantome(){
        return this.list_fantome;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public int getViePacman() {
        return this.nb_vie_pacman;
    }

    @Override
    public boolean getGhostScare() {
        return this.ghostScare;
    }

    @Override
    public void choiceUser(int choice) {
        this.choixPartie = choice;
    }
}
