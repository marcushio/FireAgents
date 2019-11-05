import javafx.scene.paint.Color;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * @author: Marcus Trujillo
 * @version: 10/25/2019
 *
 * An agent runs on it's own thread. Before any fire is discovered it randomly traverses the network looking for fire.
 * After this, it makes new agents in order to surround the fire and monitor it's spread.
 */
public class Agent implements Runnable{
    private String id; //composed of creation of time, name, and location it was made
    private boolean hasFoundFire; //need some kind of flag so we can walk at appropriate times.
    private Node host; //it has to know which Node it's on and figure out what neighbors it can visit.
    private boolean alive = false;

    /**
     * this is used for making an agent that didn't come from another agent, in this context it's our original agent.
     * @param newHost
     */
    public Agent(Node newHost){
        this.host = newHost;
        this.id = LocalDateTime.now() + " " + newHost.getCoordinate().toString();
        hasFoundFire = false;
    }

    /**
     * makes agent from a parent and places it on a new host.
     * @param parentAgent
     * @param newHost
     */
    public Agent(Agent parentAgent, Node newHost){
        host = newHost;
        hasFoundFire = true;
        id = LocalDateTime.now()  + " " + newHost.getCoordinate().toString();
    }

    /**
     * Agent copies itself and spreads copies to all neighbor nodes. Host nodes are in charge of calling this.
     */
    public void cloneToNeighbors(){ //this might have to be synchronized, it used to be.
        for(Node node : host.getNeighbors() ){
            node.acceptAgent( new Agent(this, node) );
        }
    }

    /**
     * kill this particular agent by setting alive to false so it's run() finishes and the thread terminates.
     */
    public void kill(){
        alive = false;
    }

    public String getId(){ return id; }

    public void findFire(){ hasFoundFire = true; }

    /**
     * @return whether this agent or it's parent has gotten near a node that has fire or not because this effects our behavior.
     */
    public boolean hasFoundFire(){ return hasFoundFire;  }


    @Override
    public void run(){
        alive = true;
        while(alive) {
            while (!hasFoundFire) {
                step();
                try{ Thread.sleep(3000); } catch (InterruptedException ex){ ex.printStackTrace();} //this is just here for viewability
            }
           if(host.getColor().get().equals(Color.RED)){
                alive = false;
           }
        }

    }
    /**
     * this is how the agent steps from node to node as it traverses the network
     */
    private synchronized boolean step() {
        Random random = new Random();
        Node newHost = this.host.getNeighbors().get(random.nextInt(host.numNeighbors()));
        if(!newHost.getColor().get().equals(Color.RED) && !newHost.hasAgent()){
            newHost.acceptAgent(this);
            host.setNullAgent(); //set the last host's agent to null
            host = newHost; //set this agents host to our new host.
            if (host.getColor().get().equals(Color.YELLOW)) {
                hasFoundFire = true;
                cloneToNeighbors();
            }
            return true;
        }

        return false;
    }


}
