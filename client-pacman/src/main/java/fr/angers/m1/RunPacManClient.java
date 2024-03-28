package fr.angers.m1;

import fr.angers.m1.client.ClientEnvoi;
import fr.angers.m1.client.ClientReception;
import fr.angers.m1.configuration.PacManConf;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class RunPacManClient {
    private static String adrIP = PacManConf.url;
    private static int port= PacManConf.port;
    private static Socket clientSocket;

    public static void main(String[] args) {

        try {
            clientSocket = new Socket(adrIP,port);
            // lancer le thread reception et envoi
            new ClientReception(clientSocket).start();
            new ClientEnvoi(clientSocket).start();

        } catch (UnknownHostException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
