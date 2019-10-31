import jdk.nashorn.internal.ir.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author: Marcus Trujillo
 * @author: Colton Trujillo
 * @version:
 * Represents the node sensors in the network that can change color.
 */
enum COLOR{BLUE, YELLOW, RED}

public class Node extends Observable implements Runnable{
    //Do we need locks and conditions?
    private COLOR color = COLOR.BLUE;
    private Coordinate location;
    private List<Node> neighbors = new ArrayList<Node>();
    private BlockingQueue<LogEntry> messageBuffer = new ArrayBlockingQueue<LogEntry>(1000);
    private Agent agent;


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
        if(neighbors.contains(neighbor)){ return false; }
        neighbors.add(neighbor);
        return true;
    }

    public int numNeighbors(){
        return neighbors.size();
    }

    public List<Node> getNeighbors(){
        return neighbors;
    }

    public COLOR getColor(){
        return color;
    }

    public Coordinate getCoordinate(){
        return location;
    }

    public void setCoordinate(Coordinate coordinate){
        this.location = coordinate;
    }

    public boolean hasAgent(){
        if(this.agent != null) { return true;}
        else { return false; }
    }

    /**
     * Allows an agent to travel to node if node does not currently have an agent
     */
    public synchronized boolean acceptAgent(Agent agent){ //is this going to be an issue ?? yeah I should lock this down so it's not interrupted and have some other agent gank the spot
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
        //send text if ya got it. There's no reason for these things to ever finish... so let's just have them loop til
        //the program closes. I mean that's really all this thing actively does... everything else is changed from the outside
        while(true) {
            if (!messageBuffer.isEmpty()) {
                LogEntry entryToSend = messageBuffer.remove();
            }
        }
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
        else if(color.equals(COLOR.RED)) { return false; }
        else {
            color = COLOR.YELLOW;
            if(this.agent != null){
                LogEntry newEntry  = agent.createLogEntry();
                sendLogEntry(newEntry);
            }
        }
        return true;
    }

    /**
     * Receives a log entry and if it gets in return true.
     * @return
     */
    public boolean receiveLogEntry(LogEntry newEntry){
        if( messageBuffer.add(newEntry) ) { return true; }
        return false;
    }

    /**
     *
     * @return
     */
    private boolean sendLogEntry(){
        //we have to discuss routing protocol for now just have a node send message to neighbors but maybe not from sending neighbor
        try {
            for (Node node : neighbors) {
                node.receiveLogEntry(messageBuffer.take());
            }
        } catch (InterruptedException ex){
            ex.printStackTrace();
        }
        return true;
    }

    /**
     * for sending a specific log entry that this particular node created rather than passing along a message it recieved
     * @param
     */
    private boolean sendLogEntry(LogEntry newEntry){ // I feel like there needs to be more to this.
            for (Node node : neighbors) {
                node.receiveLogEntry(newEntry);
            }
        return true;
    }

    public static void main(String[] args){
        Random random = new Random();
        System.out.println(random.nextInt(10));
    }
}
