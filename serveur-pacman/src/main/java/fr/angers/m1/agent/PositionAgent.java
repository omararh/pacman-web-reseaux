package fr.angers.m1.agent;

public class PositionAgent {

    private int x;
    private int y;
    private int dir;

    public PositionAgent(int x, int y, int dir) {
        this.x=x;
        this.y=y;
        this.dir=dir;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

}
