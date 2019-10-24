import java.util.List;
import java.util.Observable;
import java.util.concurrent.BlockingQueue;

/**
 * @author: Marcus Trujillo
 * @author: Colton Trujillo
 * @version:
 * Represents the node sensors in the network that can change color.
 */
enum COLOR{RED, YELLOW, BLUE}

public class Node extends Observable implements Runnable{
    private COLOR color;
    private List<Node> neighbors;
    private BlockingQueue messageBuffer;
    private Agent agent;

    //Constructor that sets initial conditions?

    /**
     * Called when this node is on fire. It has to be called from another node because fire travels along communication lines
     * @return
     */
    public boolean ignite(){
        if(color.equals(COLOR.RED)) return false;
        else{
            sendDeathMessage();
            color = COLOR.RED;
            agent = null;
        }
        return true;
    }

    /**
     * adds a node to this node's list of neighbors if it's not already there
     * @param neighbor
     * @return true if a node was added, else false
     */
    public boolean addNeighbor(Node neighbor){
        for(Node node : neighbors){
            if(neighbors.contains(node)){ return false; }
        }
        neighbors.add(neighbor);
        return true;
    }

    /**
     * Allows an agent to travel to it if it does not currently have an agent
     */
    public boolean acceptAgent(Agent agent){ //is this going to be an issue ??
        if(!color.equals(COLOR.RED) || this.agent == null){
            this.agent = agent;
            return true;
        }
        return false;
    }

    /**
     *
     */
    @Override
    public void run(){
        //change color
        //spread message to neighbors if it has it.
    }

    /**
     * The last message a node sends off when it dies.
     * @return
     */
    private boolean sendDeathMessage(){
        //tell all neighbors i'm dying :(
        for(Node node: neighbors){
            node.setToYellow();
        }
        return false;
    }

    /**
     * this method is called by a neighbor if that neighbor catches fire.
     * @return true if the color is actually changed to yellow false if it was already yellow
     */
    private boolean setToYellow(){
        if(color.equals(COLOR.YELLOW)){ return false; }
        else { color = COLOR.YELLOW; }
        return true;
    }

    private boolean sendLogEntry(){
        //we have to discuss routing protocol 
        return true;
    }

}
