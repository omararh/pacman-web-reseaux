package fr.angers.m1.comportement;

import fr.angers.m1.agent.Agent;
import fr.angers.m1.modele.Maze;

public abstract class ComportementAgent {
    public abstract boolean getAction(Agent agent,Maze maze);
}
