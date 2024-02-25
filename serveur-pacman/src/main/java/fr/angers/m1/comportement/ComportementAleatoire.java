package fr.angers.m1.comportement;

import java.util.Random;

import fr.angers.m1.agent.Agent;
import fr.angers.m1.agent.PositionAgent;
import fr.angers.m1.modele.Maze;

public class ComportementAleatoire extends ComportementAgent {

    @Override
    public boolean getAction(Agent agent, Maze maze) {
        Random aleatoire = new Random();
        int direction = aleatoire.nextInt(4);
        PositionAgent positionAgent = agent.getPosition();
        int position_x = positionAgent.getX();
        int position_y = positionAgent.getY();

        switch(direction) {
            case Maze.NORTH:
                position_y--;
                break;
            case Maze.SOUTH:
                position_y++;
                break;
            case Maze.EAST:
                position_x++;
                break;
            case Maze.WEST:
                position_x--;
                break;
            default:
                break;
        }

        if(!maze.isWall(position_x, position_y)) {
            switch (direction){
                case Maze.NORTH:
                    agent.getPosition().setY(agent.getPosition().getY()-1);
                    agent.getPosition().setDir(Maze.NORTH);
                    break;
                case Maze.SOUTH:
                    agent.getPosition().setY(agent.getPosition().getY()+1);
                    agent.getPosition().setDir(Maze.SOUTH);
                    break;
                case Maze.EAST:
                    agent.getPosition().setX(agent.getPosition().getX()+1);
                    agent.getPosition().setDir(Maze.EAST);
                    break;
                case Maze.WEST:
                    agent.getPosition().setX(agent.getPosition().getX()-1);
                    agent.getPosition().setDir(Maze.WEST);
                    break;
                default:
                    break;
            }

            return true;
        }

        return false;
    }
}
