import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: Marcus Trujillo
 * @version: 10/21/19
 *
 * Log entry is what populates the log. It's made by the agent whenever it makes a copy and includes the name of the agent
 * that copied it and the location of the node the agent was on when it made the copy.
 */
public class LogEntry {
    String id;
    String location;
    Set<Coordinate> placesVisited = new HashSet<Coordinate>();

    /**
     *
     * @param id
     * @param location
     */
    public LogEntry(String id, Coordinate location){
        this.id = id;
        this.location = location.toString();
        placesVisited.add(location);
    }

    /**
     * adds a coordinate to the list of places this node has been.
     * @param newCoordinate
     * @return
     */
    public boolean addVisited(Coordinate newCoordinate){
        if(placesVisited.add(newCoordinate)){ return true; }
        return false;
    }

    /**
     * checks to see if this entry has been to a particular location
     * @param coordinate
     * @return
     */
    public boolean hasVisitedCoordinate(Coordinate coordinate){
        if(placesVisited.contains(coordinate)){return true;}
        return false;
    }

    /**
     * checks to see if a message has already visited all neighboring nodes
     * @return
     */
    public boolean hasVisitedNeighbors(Node currentHost){
        if( currentHost.getNeighbors().containsAll(placesVisited) ) return true;
        return false;
    }

    /**
     * clears the record of all places this entry has been.
     */
    public void emptyPlacesVisited(){
        placesVisited.clear();
    }

    @Override
    public String toString(){
        return "Agent ID: " + id + "\nAgent Location: " + location + "\n" ;
    }
}
