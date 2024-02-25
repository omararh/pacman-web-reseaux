package fr.angers.m1.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientEnvoi extends Thread {
    private Socket clientSocket;
    private static String message="", messageAncien="";
    //Scanner scanner = new Scanner(System.in);

    public ClientEnvoi(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            PrintWriter sortie = new PrintWriter(clientSocket.getOutputStream(),true);
            while(true) {
                if(!message.isEmpty() && !message.equals(messageAncien) && !message.equals("null")) {
                    sortie.println(message);
                    messageAncien = message;
                }
                sortie.flush();
            }

        } catch(NullPointerException e){
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        ClientEnvoi.message = message;
    }
}
