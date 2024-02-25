package fr.angers.m1.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReception extends Thread {
    private Socket clientSocket;

    public ClientReception(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while(true) {
                String msgServeur;
                while((msgServeur = in.readLine()) != null) {
                    System.out.println("Serveur > "+msgServeur);
                    GestionMsgRecu.gestionMsgServeur(msgServeur);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}