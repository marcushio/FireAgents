import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: Marcus Trujillo
 * @version: 10/21/19
 * Log entry is what populates the log. It's made by the agent whenever it makes a copy and includes the name of the agent
 * that copied it and the location of the node the agent was on when it made the copy.
 */
public class LogEntry {
    String id;
    String location;
    Set<Coordinate> placesVisited = new HashSet<Coordinate>();
    //Node sender; might be useful so that nodes know not to send it back to it's sender

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

    public boolean addVisited(Coordinate newCoordinate){
        if(placesVisited.add(newCoordinate)){ return true; }
        return false;
    }

    public boolean hasVisitedCoordinate(Coordinate coordinate){
        if(placesVisited.contains(coordinate)){return true;}
        return false;
    }

    @Override
    public String toString(){
        return "Agent ID: " + id + "\nAgent Location: " + location + "\n" ;
    }
}
