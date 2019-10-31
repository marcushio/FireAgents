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
public class Agent implements Runnable{ //should these guys be observable? I dont think we need to...
//Do I need to add locks and conditions?
    private String id; //composed of creation of time, name, and location it was made
    private String name;
    private boolean hasFoundFire; //need some kind of flag so we can walk at appropriate times.
    private Node host; //it has to know which Node it's on and figure out what neighbors it can visit.

    public Agent(Node newHost){
        this.host = newHost;
        this.name = "AA";
        this.id = LocalDateTime.now() + " " + this.name + " " + newHost.getCoordinate().toString();
        hasFoundFire = false;
    }

    public Agent(Agent parentAgent, Node newHost){
        hasFoundFire = parentAgent.hasFoundFire();
        id = LocalDateTime.now() + " " + parentAgent.name + " " + newHost.getCoordinate().toString(); //probably increment the name somehow so it's not identical to parent
        //make a new name based on the location this was cloned from and the time

    }

    @Override
    public void run(){
        boolean alive = true;
        while(alive) {
            while (!hasFoundFire) {
                step();
            }
            if (host.getColor() == COLOR.YELLOW) {
                cloneToNeighbors();
            } else if(host.getColor() == COLOR.RED){
                alive = false;
            }
        }
    }


    /**
     * @return whether this agent or it's parent has gotten near a node that has fire or not because this effects our behavior.
     */
    public boolean hasFoundFire(){
        return hasFoundFire;
    }

    /**
     * @return host
     */
    public Node getHost(){ return host; }

    /**
     *
     */
    private boolean step() {
        Random random = new Random();
        Node newHost = host.getNeighbors().get(random.nextInt(host.numNeighbors()));
        if (newHost.acceptAgent(this)) {
            this.host = newHost;

            return true;
        }
        return false;
    }

    public LogEntry createLogEntry(){
        return new LogEntry(this.id, host.getCoordinate().toString());
    }

    /**
     * Agent copies itself and spreads copies to all neighbor nodes
     */
    private synchronized void cloneToNeighbors(){ //this might have to
        for(Node node : host.getNeighbors() ){
            if( !node.hasAgent() ){
                Agent newAgent = new Agent(this, node);
                node.acceptAgent(newAgent);
                host.receiveLogEntry( createLogEntry() ); //createLogEntry returns boolean do we have this do something if that fails?
            }
        }
    }


}
