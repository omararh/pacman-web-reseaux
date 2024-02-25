package fr.angers.m1.vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FenetreResultat {
    private JFrame frame_resultat;
    private JPanel panel_resultat1, panel_resultat2;
    private JLabel label_score_text, label_score_resultat, label_vies, label_gameOver;
    private JLabel label_pacman_vie_1, label_pacman_vie_2, label_pacman_vie_3;
    private JLabel label_pacman_mort_1, label_pacman_mort_2, label_pacman_mort_3;

    public FenetreResultat() {
        super();

        frame_resultat = new JFrame();
        panel_resultat1 = new JPanel();

        label_pacman_vie_1 = new JLabel(" I ");
        label_pacman_vie_2 = new JLabel(" II " );
        label_pacman_vie_3 = new JLabel(" III ");
        label_pacman_mort_1 = new JLabel("   ");
        label_pacman_mort_2 = new JLabel("   ");
        label_pacman_mort_3 = new JLabel("   ");

        label_score_text = new JLabel("SCORE ");
        label_score_resultat = new JLabel("0");
        label_vies = new JLabel("          CHANCES :  ");
        label_gameOver = new JLabel("    GAME OVER");
        label_gameOver.setFont(new Font("Serif", Font.BOLD, 38));
        panel_resultat1.setLayout(new FlowLayout());
        panel_resultat1.add(label_score_text);
        panel_resultat1.add(label_score_resultat);
        panel_resultat1.add(label_gameOver);
        panel_resultat1.add(label_vies);
        panel_resultat1.add(label_pacman_vie_1);
        panel_resultat1.add(label_pacman_vie_2);
        panel_resultat1.add(label_pacman_vie_3);
        panel_resultat1.add(label_pacman_mort_1);
        panel_resultat1.add(label_pacman_mort_2);
        panel_resultat1.add(label_pacman_mort_3);

        frame_resultat.setTitle("Résultats de la partie en cous");
        frame_resultat.setSize(700,100);
        frame_resultat.setLocation(500,10);
        frame_resultat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel_resultat2 = new JPanel();
        panel_resultat2.setLayout(new BorderLayout());
        panel_resultat2.add(panel_resultat1);

        frame_resultat.setContentPane(panel_resultat2);

        this.label_pacman_mort_1.setVisible(false);
        this.label_pacman_mort_2.setVisible(false);
        this.label_pacman_mort_3.setVisible(false);
        this.label_gameOver.setVisible(false);
    }

    public void affichageResultat(boolean b) {
        frame_resultat.setVisible(b);
    }

    public void setScore(String score) {
        label_score_resultat.setText(score);
    }

    public void setViePacman1(boolean vie) {
        this.label_pacman_vie_1.setVisible(vie);
        this.label_pacman_mort_1.setVisible(!vie);
    }

    public void setViePacman2(boolean vie) {
        this.label_pacman_vie_2.setVisible(vie);
        this.label_pacman_mort_2.setVisible(!vie);
    }

    public void setViePacman3(boolean vie) {
        this.label_pacman_vie_3.setVisible(vie);
        this.label_pacman_mort_3.setVisible(!vie);
    }

    public void gameOver() {
        this.label_score_text.setVisible(false);
        this.label_score_resultat.setVisible(false);
        label_vies.setVisible(false);
        this.label_gameOver.setVisible(true); //Cacher score et afficher game over
    }
}
