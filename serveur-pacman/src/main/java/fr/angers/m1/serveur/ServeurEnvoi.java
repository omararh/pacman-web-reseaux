package fr.angers.m1.serveur;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ServeurEnvoi extends Thread {
    private Socket socket_client;
    private static String message="";
    private static String message_old="";

    // Constructeur
    public ServeurEnvoi(Socket socket_client) {
        this.socket_client = socket_client;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket_client.getOutputStream(),true);

            while(true) {
                if(!message.isEmpty() && !message.equals(message_old) && !message.equals("null")) {
                    out.println(message);
                    // Pour eviter l'envoi sans fin d'un meme message
                    message_old = message;
                }
                out.flush();
            }

        } catch(NullPointerException e){
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                // Fermer le socket client
                socket_client.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        ServeurEnvoi.message = message;
    }
}
