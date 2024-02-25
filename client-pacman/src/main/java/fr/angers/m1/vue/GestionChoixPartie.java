package fr.angers.m1.vue;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.angers.m1.client.ClientEnvoi;

public class GestionChoixPartie {
    private JFrame frame_choix_partie;
    private JPanel panel_choix_partie;
    private JButton btn_aleatoire, btn_manuel;

    //Constructeur
    public GestionChoixPartie() {
        super();

        frame_choix_partie = new JFrame();
        panel_choix_partie = new JPanel();

        btn_aleatoire = new JButton("Pacman aléatoire");
        btn_manuel = new JButton("Pacman manuel");

        panel_choix_partie.setLayout(new GridLayout(1,2));
        panel_choix_partie.add(btn_aleatoire);
        panel_choix_partie.add(btn_manuel);

        frame_choix_partie.setTitle("Menu choix de jeu");
        frame_choix_partie.setSize(500,100);
        frame_choix_partie.setResizable(false);
        frame_choix_partie.setLocationRelativeTo(null);
        frame_choix_partie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame_choix_partie.setContentPane(panel_choix_partie);
        frame_choix_partie.setVisible(true);

        //aleatoire
        btn_aleatoire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame_choix_partie.setVisible(false);
                frame_choix_partie.dispose();
                //envoyer au serveur le choix sélectionné 1=aleatoire
                ClientEnvoi.setMessage("choix:" + 1);
                System.out.println("Client > choix:1 //aléatoire");
            }
        });

        // Manuel
        btn_manuel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame_choix_partie.setVisible(false);
                frame_choix_partie.dispose();
                //envoyer au serveur le choix sélectionné 2=manuel
                ClientEnvoi.setMessage("choix:" + 2);
                System.out.println("Client > choix:2 //manuel");
            }
        });
    }

    public void voir_frame_choix_partie(boolean b) {
        frame_choix_partie.setVisible(b);
    }

    public void dispose() {
        frame_choix_partie.dispose();
    }
}
