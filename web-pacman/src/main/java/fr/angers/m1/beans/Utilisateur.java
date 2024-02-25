package fr.angers.m1.beans;


import java.sql.Timestamp;

public class Utilisateur {
    private Long id;
    private String pseudo;
    private String email;
    private String password;
    private Timestamp date_inscription;


    public Utilisateur () {

    }

    /* GUETTER & SETTER */

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getPseudo() {
        return pseudo;
    }


    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public Timestamp getDate_inscription() {
        return date_inscription;
    }


    public void setDate_inscription(Timestamp date_inscription) {
        this.date_inscription = date_inscription;
    }

}
