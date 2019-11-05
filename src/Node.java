//import jdk.nashorn.internal.ir.Block;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author: Marcus Trujillo
 * @author: Colton Trujillo
 * @version:
 * Represents the node sensors in the network that can change color.
 */

public class Node extends Observable implements Runnable{
    //Do we need locks and conditions?
    private ObjectProperty<Color> color = new ColorPicker(Color.BLUE).valueProperty();
    private ObjectProperty<Color> strokeColor = new ColorPicker(Color.BLUE).valueProperty();
    private Coordinate location;
    private List<Node> neighbors = new ArrayList<Node>();
    private BlockingQueue<LogEntry> messageBuffer = new ArrayBlockingQueue<LogEntry>(1000);
    private Agent agent;
    public static Station station;
    /**
     * Return color integer property of this node so it may be bound to a property in the gui
     * @return This node's color integer property which will be 0,1, or 2 depending on if the node is
     * blue, yellow, or red respectively.
     */
    public ObjectProperty<Color> getColorIntegerProperty(){
        return color;
    }
    /**
     * Called when this node is on fire. It has to be called from another node because fire travels along communication lines
     * @return
     */
    public synchronized boolean ignite(){
        if(color.get().equals(Color.RED)) return false;
        else{
            color.set(Color.RED);
            strokeColor.set(Color.RED);
            sendDeathMessage();
            if(agent != null) {
                agent.kill();
                agent = null;
            }
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

    public void setStation(Station station){
        this.station = station;
    }

    public int numNeighbors(){
        return neighbors.size();
    }

    public List<Node> getNeighbors(){
        return neighbors;
    }

    public ObjectProperty<Color> getColor(){
        return color;
    }

    public Coordinate getCoordinate(){
        return location;
    }

    public void setCoordinate(Coordinate coordinate){
        this.location = coordinate;
    }

    public boolean setNullAgent(){
        agent = null;
        strokeColor.set(color.get());
        return true;
    }

    public boolean hasAgent(){
        if(this.agent != null) { return true;}
        else { return false; }
    }

    /**
     * Allows an agent to travel to node if node does not currently have an agent
     */
    public synchronized boolean acceptAgent(Agent agent){ //is this going to be an issue ?? it used to be synchronized
        if(!color.get().equals(Color.RED) && this.agent == null){
            this.agent = agent;
            strokeColor.set(Color.MEDIUMPURPLE);
            if(color.get().equals(Color.YELLOW)) {
                this.agent.findFire();
            }
            if (this.agent.hasFoundFire()){
                LogEntry entry = createLogEntry();
                messageBuffer.add( entry);
                station.addToLog(entry);
            }
            return true;
        }
        return false;
    }

    /**
     * Receives a log entry and if it gets in return true.
     * @return
     */
    public boolean receiveLogEntry(LogEntry newEntry){
        if( messageBuffer.add(newEntry) ) {
            messageBuffer.notifyAll();
            return true;
        }
        return false;
    }

    /**
     * create logEntry based on the agent that has been newly cloned onto this node.
     * @return new logEntry
     */
    public LogEntry createLogEntry(){
        String id = agent.getId();
        return new LogEntry(id, this.location);
    }

    /**
     *
     */
    @Override
    public void run() {
        //wait until something gets into the message buffer then send the message out. This will be more efficient
        //put in loop to be sure the condition is actually met...

        while (!messageBuffer.isEmpty()) {
            try {
                LogEntry pendingMessage = messageBuffer.take();
                station.addToLog(pendingMessage);
                handleLogEntry(pendingMessage);
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void handleLogEntry(LogEntry pendingMessage) {
        //this is a naive DFS I'm pretty sure I'm going to have to make it more robust afer
        for(Node node : neighbors){
            if(!pendingMessage.hasVisitedCoordinate( node.getCoordinate() )){
                node.receiveLogEntry(pendingMessage);
                break;
            }
    }

}

    /**
     * @return all neighbors of this node that are yellow.
     */
    public List<Node> getYellowNeighbors() {
        List<Node> yellowNodes = new ArrayList<>();
        for (Node node : neighbors) {
            if (node.getColor().get().equals(Color.YELLOW)) yellowNodes.add(node);
        }
        return yellowNodes;
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
    private synchronized boolean setToYellow(){
        if(color.get().equals(Color.YELLOW)){ return false; }
        if(color.get().equals(Color.RED)) { return false; }
        color.set(Color.YELLOW);
        if(this.agent != null){
            strokeColor.set(Color.PURPLE);
            this.agent.cloneToNeighbors();
        } else strokeColor.set(Color.YELLOW);
        return true;
    }


    public ObservableValue<? extends Paint> getStrokeColor() {
        return strokeColor;
    }
}
