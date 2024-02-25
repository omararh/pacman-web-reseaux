package fr.angers.m1.controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import fr.angers.m1.client.ClientEnvoi;
import fr.angers.m1.modele.Maze;

public  class GestionToucheClavier extends JFrame implements KeyListener{
    private static final long serialVersionUID = 1L;

    @Override
    public void keyPressed(KeyEvent arg0) {
        switch (arg0.getKeyCode()) {
            // Touche pour faire marcher Pacman manuellement
            case KeyEvent.VK_UP:
                //ClientEnvoi.setMessage("touche:" + Maze.NORTH);
                ClientEnvoi.setMessage("direction:" + Maze.NORTH);
                System.out.println("Client > direction:"+Maze.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                ClientEnvoi.setMessage("direction:" + Maze.SOUTH);
                System.out.println("Client > direction:"+Maze.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                ClientEnvoi.setMessage("direction:" + Maze.WEST);
                System.out.println("Client > direction:"+Maze.WEST);
                break;
            case KeyEvent.VK_RIGHT:
                ClientEnvoi.setMessage("direction:" + Maze.EAST);
                System.out.println("Client > direction:"+Maze.EAST);
                break;

            default:
                ClientEnvoi.setMessage("direction:" + Maze.STOP);
                System.out.println("Client > direction:"+Maze.STOP);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

}
