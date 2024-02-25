package fr.angers.m1.agent;

public class AgentAction {
    private int direction;

    public AgentAction(int direction){
        if(direction >= 0 && direction <= 4){
            this.direction = direction;
        }
    }

    public void setDirection( int direction){
        if(direction >= 0 && direction <= 4){
            this.direction = direction;
        }
    }

    public int getDirection(){
        return this.direction;
    }
}
