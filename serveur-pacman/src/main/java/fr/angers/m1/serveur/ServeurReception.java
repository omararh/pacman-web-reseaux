package fr.angers.m1.serveur;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import fr.angers.m1.RunPacManServeur;
import fr.angers.m1.baseDeDonnee.BaseDeDonnee;
import fr.angers.m1.controleur.ControleurServeur;
import fr.angers.m1.modele.*;


public class ServeurReception extends Thread {
    private static Socket clientSocket;
    private static PacmanGame pacmanGame;
    private static ControleurServeur controleurServeur;

    public ServeurReception(Socket clientSocket) {
        ServeurReception.clientSocket = clientSocket;
        pacmanGame = new PacmanGame(1000, RunPacManServeur.chemin);
    }

    public static void traitementMsgClient(String msgClient) {
        String[] parseGenerale = msgClient.split(":");

        if(parseGenerale[0].equals("authentification")) {
            String[] authentification = parseGenerale[1].split(";");
            String username = authentification[0];
            String password = authentification[1];
            int id = BaseDeDonnee.verificationID(username, password);

            if(id == -1) {
                System.out.println("Le client n'a pas pu s'authentifier");
            } else {
                System.out.println("Authentification du "+username+" réussi");
                Game.setIdentifiantUser(id);
                ServeurEnvoi.setMessage("choix:");
            }

        } else

        if(parseGenerale[0].equals("choix")) {
            int choix = Integer.parseInt(parseGenerale[1]);
            pacmanGame.choiceUser(choix);
            controleurServeur = new ControleurServeur(pacmanGame);
            ServeurEnvoi.setMessage("chemin:" + RunPacManServeur.chemin);
        } else if(parseGenerale[0].equals("commande")) {
            String[] commande = parseGenerale[1].split(";");

            switch(commande[0]) {
                case "init":
                    controleurServeur.init();
                    break;

                case "start":
                    pacmanGame.start();
                    break;

                case "step":
                    pacmanGame.step();
                    break;

                case "stop":
                    pacmanGame.stop();
                    break;

                case "nb_tour_sec":
                    int nb = Integer.parseInt(commande[1]);
                    pacmanGame.nb_tour_sec(nb);
                    break;

                case "actualiser":
                    pacmanGame.actualiser(commande[1]);
                    break;

                default:
                    break;
            }
        } else if(parseGenerale[0].equals("direction")) {
            int touchePacman = Integer.parseInt(parseGenerale[1]);
            ControleurServeur.setTouchePacman(touchePacman);
        }
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String msg_client;
            while((msg_client=in.readLine())!=null) {
                System.out.println("Client > "+msg_client);
                ServeurReception.traitementMsgClient(msg_client);
            }
            System.out.println("Client déconnecté !");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
