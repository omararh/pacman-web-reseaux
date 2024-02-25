package fr.angers.m1.vue;


import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ViewCommande extends JPanel{
    private static final long serialVersionUID = 1L;
    String path = "src/main/resources/icones";
    private JButton btnInit;
    private JButton btnStart;
    private JButton btnStep;
    private JButton btnStop;

    public ViewCommande(){
        super();

        this.setLayout(new GridLayout(1,4));


        Icon icon_init = new ImageIcon(path + "/icon_pause.png");
        btnInit = new JButton(icon_init);
        btnInit.setEnabled(true);
        this.add(btnInit);

        Icon icon_start = new ImageIcon(path + "/icon_run.png");
        btnStart = new JButton(icon_start);
        btnStart.setEnabled(true);
        this.add(btnStart);

        Icon icon_step = new ImageIcon(path + "/icon_step.png");
        btnStep = new JButton(icon_step);
        btnStep.setEnabled(true);
        this.add(btnStep);

        Icon icon_pause = new ImageIcon(path + "/icon_pause.png");
        btnStop = new JButton(icon_pause);
        btnStop.setEnabled(true);
        this.add(btnStop);
    }

    public JButton getBtnInit(){
        return this.btnInit;
    }

    public JButton getBtnStart(){
        return this.btnStart;
    }

    public JButton getBtnStep(){
        return this.btnStep;
    }

    public JButton getBtnStop(){
        return this.btnStop;
    }
}
