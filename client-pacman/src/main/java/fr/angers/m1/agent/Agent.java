package fr.angers.m1.agent;

public class Agent {
    private PositionAgent position;

    public Agent(PositionAgent position){
        this.position = position;
    }

    //Getteurs et setteurs

    public PositionAgent getPosition(){
        return this.position;
    }

    public void setPosition(PositionAgent position){
        this.position = position;
    }
}
