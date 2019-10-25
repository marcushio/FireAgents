import Math.random;

import java.util.Random;

/**
 * @author: Marcus Trujillo
 * @version:
 * brief class description
 */
public class Agent implements Runnable{ //should these guys be observable? I dont think we need to...

    private Coordinate location;
    private String name;
    private boolean hasFoundFire;
    private Node host;

    public Agent(Agent parentAgent){
        hasFoundFire = parentAgent.hasFoundFire();
        //make a new name based on the location this was cloned from
        //
    }
    @Override
    public void run(){
        while(!hasFoundFire){
            //move();
        }
            //if the node that we're on catches fire the agent dies....
            //when the node we're on catches fire then we can go ahead and
            //we haven't found fire yet so we're just cloning
            //clone if near fire
    }

    public boolean setLocation(Coordinate newLocation){
        this.location = newLocation;
        return true;
    }

    /**
     * @return whether this agent has gotten near a node that has fire or not because this effects our behavior.
     */
    public boolean hasFoundFire(){
        return hasFoundFire;
    }

    /**
     *
     * @return
     */
    private Agent copy(){
        Agent newAgent = new Agent(this);
        return newAgent;
    }

    /**
     *
     */
    private boolean walk(){
        Random random = new Random();
        this.host = host.getNeighbors().get( random.nextInt(host.numNeighbors()) ); //set 
    }
}
