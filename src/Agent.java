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
    private String name;
    private boolean hasFoundFire; //need some kind of flag so we can walk at appropriate times.
    private Node host; //it has to know which Node it's on and figure out what neighbors it can visit.
    private boolean alive = false;

    /**
     * this is used for making an agent that didn't come from another agent, in this context it's our original agent.
     * @param newHost
     */
    public Agent(Node newHost){
        this.host = newHost;
        this.name = "0 1";
        this.id = LocalDateTime.now() + " " + this.name + " " + newHost.getCoordinate().toString();
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
       // parentAgent.name.charAt()
        id = LocalDateTime.now()  + newHost.getCoordinate().toString(); //probably increment the name somehow so it's not identical to parent
        //make a new name based on the location this was cloned from and the time
    }

    /**
     * Agent copies itself and spreads copies to all neighbor nodes
     */
    public void cloneToNeighbors(){ //this might have to be synchronized, it used to be.
        for(Node node : host.getNeighbors() ){
            node.acceptAgent( new Agent(this, node) ) ;
        }
    }

    /**
     * kill this particular agent by setting alive to false so it's run() finishes and the thread terminates.
     */
    public void kill(){
        alive = false;
    }

    public String getId(){ return id; }

    /**
     * @return whether this agent or it's parent has gotten near a node that has fire or not because this effects our behavior.
     */
    public boolean hasFoundFire(){ return hasFoundFire;  }

    /**
     * @return host
     */
    public Node getHost(){ return host; }

    @Override
    public void run(){
        alive = true;
        while(alive) {
            while (!hasFoundFire) {
                step();
                if (host.getColor().get().equals(Color.YELLOW)) {
                    hasFoundFire = true;
                    // System.out.println("I found fire ");
                    cloneToNeighbors();
                } else if(host.getColor().get().equals(Color.RED)){
                    alive = false;
                }
                try{ Thread.sleep(3000); } catch (InterruptedException ex){ ex.printStackTrace();} //this is mostly just here for readability probs delete later.
            }
            if (host.getColor().get().equals(Color.YELLOW)) {
                cloneToNeighbors();
            } else if(host.getColor().get().equals(Color.RED)){
                alive = false;
            }
        }

    }
    /**
     * this is how the agent steps from node to node as it traverses the network
     */
    private boolean step() {
        Random random = new Random();
        Node newHost = this.host.getNeighbors().get(random.nextInt(host.numNeighbors()));
        if (newHost.acceptAgent(this)) {
            host.setNullAgent(); //set the last host's agent to null
            host = newHost; //set this agents host to our new host.
            //System.out.println("Agent " + id + " Stepped to " + host.getCoordinate().toString());
            return true;
        }
        return false;
    }


}
