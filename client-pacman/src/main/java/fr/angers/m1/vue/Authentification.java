package fr.angers.m1.vue;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.angers.m1.client.ClientEnvoi;

public class Authentification {
    private JFrame frame_authentification;
    private JPanel panel_authentification;
    private JLabel label_username, label_password;
    private JTextField text_field_username, text_field_password;
    private JButton btn_authentification;

    public Authentification() {
        super();

        frame_authentification = new JFrame();
        panel_authentification = new JPanel();

        // Username
        label_username = new JLabel("Username");
        text_field_username = new JTextField("anis");

        JPanel panel_username = new JPanel(new GridLayout(1,2));
        panel_username.add(label_username);
        panel_username.add(text_field_username);

        // Password
        label_password = new JLabel("Password");
        text_field_password = new JTextField("2004");

        JPanel panel_password = new JPanel(new GridLayout(1,2));
        panel_password.add(label_password);
        panel_password.add(text_field_password);

        // Boutton authentification
        btn_authentification = new JButton("S'authentifier");

        panel_authentification.setLayout(new GridLayout(3,1));
        panel_authentification.add(panel_username);
        panel_authentification.add(panel_password);
        panel_authentification.add(btn_authentification);

        frame_authentification.setTitle("Authentification");
        frame_authentification.setSize(400,140);
        frame_authentification.setResizable(false);
        frame_authentification.setLocationRelativeTo(null);
        frame_authentification.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_authentification.setContentPane(panel_authentification);
        frame_authentification.setVisible(true);

        //Lors du clique sur le btn s'authentifier
        this.btn_authentification.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verif_authentification();
            }
        });
    }


    //Vérification des champs remplis et envoi des données au serveur
    public boolean verif_authentification() {
        String username = this.text_field_username.getText();
        String password = this.text_field_password.getText();

        if(username.equals("") || username.equals("null")){
            this.text_field_username.setText("Votre username SVP");

            return false;
        } else 	if(password.equals("") || password.equals("null")) {
            this.text_field_password.setText("Votre password SVP");

            return false;
        } else {
            //Préparer le message à envoyer au serveur
            String message = "authentification:" + text_field_username.getText() + ";" + text_field_password.getText();
            ClientEnvoi.setMessage(message);
            frame_authentification.setVisible(false);
            System.out.println("Client > "+message);
            return true;
        }
    }
}
