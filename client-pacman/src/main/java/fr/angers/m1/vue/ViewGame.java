package fr.angers.m1.vue;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fr.angers.m1.controleur.GestionToucheClavier;
import fr.angers.m1.controleur.ControleurClient;
import fr.angers.m1.modele.Maze;

public class ViewGame {

    private JFrame fenetreMaze, fenetreCommande;
    private PanelPacmanGame panelPacmanGame;
    private Maze maze;
    private ViewCommande view_commande;
    private FenetreResultat view_resultat;
    private JLabel nb_tour_per_seconde;
    private JSlider slider;
    private JLabel jLabelTurn;
    private JButton btn_choix_maze;
    private JPanel conteneurPrincipaleMaze;
    private GestionToucheClavier toucheClavier;

    public ViewGame(String chemin){
        super();

        this.view_resultat = new FenetreResultat();
        this.view_commande = new ViewCommande();

        try {
            this.maze = new Maze(chemin);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        this.affichageFrame(this.maze);
    }

    // get et set
    public ViewCommande getViewCommande(){
        return this.view_commande;
    }

    public void setViewCommande(ViewCommande view_commande) {
        this.view_commande = view_commande;
    }

    public Maze getMaze() {
        return this.maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    // Affichage des fenetres
    public void affichage(boolean b){
        view_resultat.affichageResultat(b);
        fenetreMaze.setVisible(b);
        fenetreCommande.setVisible(b);
    }

    public void affichageFrame(Maze maze){
        // chargement du maze dans le panelPacmanGame
        this.panelPacmanGame = new PanelPacmanGame(maze);

        fenetreMaze = new JFrame();
        fenetreCommande = new JFrame();

        // fenetre contenant le Maze

        fenetreMaze.setTitle("Pacman");
        fenetreMaze.setSize(700,600);
        //pour quelle soit collée avec la fenetre des resultats
        fenetreMaze.setLocation(500,128);
        fenetreMaze.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fenetreCommande.setTitle("Commande");
        fenetreCommande.setSize(500, 190);
        fenetreCommande.setLocation(10,10);
        fenetreCommande.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Reste de l'affichage : les deux JLabel, JSlider et JButton (choix du maze)
        nb_tour_per_seconde = new JLabel("Number of turns per second",SwingConstants.CENTER);
        nb_tour_per_seconde.setFont(new Font("Serif", Font.PLAIN, 16)); //20
        slider = new JSlider(1,10,1);
        slider.setMajorTickSpacing(1);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(1);
        jLabelTurn = new JLabel("Turn : 0",SwingConstants.CENTER);
        jLabelTurn.setFont(new Font("Serif", Font.PLAIN, 16));
        btn_choix_maze = new JButton("Sélectionner le maze");
        btn_choix_maze.setFont(new Font("Serif", Font.PLAIN, 16));
        btn_choix_maze.setPreferredSize(new Dimension(20,10));

        //Gestion du reste de l'affichage
        JPanel conteneurFooter = new JPanel();
        conteneurFooter.setLayout(new GridLayout(1,2));
        //Ce conteneur contient le JLabel "Number of turns" et le JSlider
        JPanel conteneurNbTour = new JPanel();
        conteneurNbTour.setLayout(new GridLayout(2,1));
        conteneurNbTour.add(nb_tour_per_seconde);
        conteneurNbTour.add(slider);
        //Ce conteneur contient le JLabel "Turn : 0" et le JButton (choix du maze)
        JPanel conteneurTurnMaze = new JPanel();
        conteneurTurnMaze.setLayout(new GridLayout(2,1));
        conteneurTurnMaze.add(jLabelTurn);
        conteneurTurnMaze.add(btn_choix_maze);

        conteneurFooter.add(conteneurNbTour);
        conteneurFooter.add(conteneurTurnMaze);

        // Utilisation d'un JPanel contenant tout les composants nécessaire à l'affichage
        toucheClavier = new GestionToucheClavier();
        fenetreMaze.addKeyListener(toucheClavier);
        fenetreMaze.setFocusable(true); //fenetreCommande

        conteneurPrincipaleMaze = new JPanel();
        conteneurPrincipaleMaze.setLayout(new GridLayout(1,1));
        conteneurPrincipaleMaze.add(this.panelPacmanGame);

        JPanel conteneurPricipalCommande = new JPanel();
        conteneurPricipalCommande.setLayout(new GridLayout(2,1));
        conteneurPricipalCommande.add(this.view_commande);
        conteneurPricipalCommande.add(conteneurFooter);

        fenetreMaze.setContentPane(conteneurPrincipaleMaze);
        fenetreCommande.setContentPane(conteneurPricipalCommande);

        // Appel de méthode permettant de gérer les commandes
        this.init();
        this.start();
        this.stop();
        this.step();
        this.nb_tour_sec();
        this.changeMaze();
    }

    // Méthode permettant de mettre à jour mes frames
    public void update(){
        jLabelTurn.setText("Turn : " + ControleurClient.getCompteurTour());
        view_resultat.setScore("" + ControleurClient.getScore());

        if(ControleurClient.getCompteurTour() >= ControleurClient.getNbTourMax()) {
            this.view_commande.getBtnInit().setEnabled(true);
            this.view_commande.getBtnStart().setEnabled(false);
            this.view_commande.getBtnStep().setEnabled(false);
            this.view_commande.getBtnStop().setEnabled(false);

            this.affichage(true);
        }

        switch (ControleurClient.getViePacman()) {
            case 1:
                this.view_resultat.setViePacman1(true);
                this.view_resultat.setViePacman2(false);
                this.view_resultat.setViePacman3(false);
                break;
            case 2:
                this.view_resultat.setViePacman1(true);
                this.view_resultat.setViePacman2(true);
                this.view_resultat.setViePacman3(false);
                break;
            case 3:
                this.view_resultat.setViePacman1(true);
                this.view_resultat.setViePacman2(true);
                this.view_resultat.setViePacman3(true);
                break;
            default:
                this.view_resultat.setViePacman1(false);
                this.view_resultat.setViePacman2(false);
                this.view_resultat.setViePacman3(false);
                this.view_resultat.gameOver();
                break;
        }

        this.panelPacmanGame.setGhostsScarred(ControleurClient.isGhostScare());
        //Cela permet de supprimer tous les composants existants sur le JFrame "fenetreMaze"
        fenetreMaze.getContentPane().removeAll();
        fenetreMaze.validate();

        this.panelPacmanGame = new PanelPacmanGame(ControleurClient.getMaze());
        this.conteneurPrincipaleMaze.add(this.panelPacmanGame);
        //Cette méthode permet de valider l'ajout des nouveauc composants après le removeAll()
        fenetreMaze.revalidate();
        panelPacmanGame.repaint();
    }

    // Méthode permettant de gérer l'action clic sur le bouton init
    public void init(){
        this.view_commande.getBtnInit().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evenement) {
                ControleurClient.init();
                btn_choix_maze.setEnabled(true);
            }
        });
    }

    // Méthode permettant de gérer l'action clic sur le bouton start
    public void start(){
        this.view_commande.getBtnStart().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evenement) {
                ControleurClient.start();
                btn_choix_maze.setEnabled(false);
            }
        });
    }

    //Méthode permettant de gérer l'action clic sur le bouton step
    public void step(){
        this.view_commande.getBtnStep().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evenement) {
                ControleurClient.step();
                btn_choix_maze.setEnabled(true);
            }
        });
    }

    //Méthode permettant de gérer l'action clic sur le bouton stop
    public void stop(){
        this.view_commande.getBtnStop().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evenement) {
                ControleurClient.stop();
                btn_choix_maze.setEnabled(true);
            }
        });
    }

    // Méthode permettant de donner le temps de step par seconde
    public void nb_tour_sec(){
        this.slider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent evenement) {
                ControleurClient.nb_tour_sec(slider.getValue());
            }
        });
    }

    // Méthode permettant de changer de maze lorsque l'on clique sur le boutton "choix_maze"
    public void changeMaze() {
        this.btn_choix_maze.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evenement) {
                JFileChooser choose = new JFileChooser();
                choose.setCurrentDirectory(new File("src/main/resources/layouts"));
                int retour = choose.showOpenDialog(null);

                if(retour == JFileChooser.APPROVE_OPTION) {
                    chargerMaze(choose.getSelectedFile().getAbsolutePath());
                    ControleurClient.init();
                }
                else {
                    System.out.println("Pas de fichier choisi");
                }
            }
        });
    }

    // Méthode permettant de charger le maze
    public void chargerMaze(String file) {
        try {
            Maze maze = new Maze(file);
            this.setMaze(maze);
            ControleurClient.chargement(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
