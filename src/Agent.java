import java.util.Random;

/**
 * @author: Marcus Trujillo
 * @version: 10/25/2019
 *
 * An agent runs on it's own thread. Before any fire is discovered it randomly traverses the network looking for fire.
 * After this, it makes new agents in order to surround the fire and monitor it's spread. 
 */
public class Agent implements Runnable{ //should these guys be observable? I dont think we need to...

    private Coordinate location;
    private String id;
    private boolean hasFoundFire; //need some kind of flag so we can walk at appropriate times.
    private Node host; //it has to know which Node it's on and figure out what neighbors it can visit.

    public Agent(Agent parentAgent){
        hasFoundFire = parentAgent.hasFoundFire();
        //make a new name based on the location this was cloned from and the time

    }
    @Override
    public void run(){
        while(!hasFoundFire){
            step();
        }
        if(host.getColor() == COLOR.YELLOW){
            cloneToNeighbors();
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

    private LogEntry createLogEntry(){
        return new LogEntry(this.id, host.getCoordinate().toString());
    }

    /**
     * Agent copies itself and spreads copies to all neighbor nodes
     */
    private void cloneToNeighbors(){
        for(Node node : host.getNeighbors() ){
            if( !node.hasAgent() ){
                Agent newAgent = new Agent(this);
                node.acceptAgent(newAgent);
                host.receiveLogEntry( createLogEntry() ); //createLogEntry returns boolean do we have this do something if that fails?
            }
        }
    }
}
