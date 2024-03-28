package fr.angers.m1.beans;


import java.sql.Timestamp;

public class Partie {
    private Long id;
    private int score;
    private Timestamp date;
    private String joueur;

    public Partie(Long id, int score, Timestamp date, String joueur) {
        this.id = id;
        this.score = score;
        this.date = date;
        this.setJoueur(joueur);
    }

    public Partie() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getJoueur() {
        return joueur;
    }

    public void setJoueur(String joueur) {
        this.joueur = joueur;
    }

}
