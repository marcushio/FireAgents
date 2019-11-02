import javafx.beans.property.ListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Marcus Trujillo
 * @author: Colton Trujillo
 * @version: 10/31/19
 * brief class description
 */
public class Network {
    private Map<Coordinate, Node> coordinateNodeMap = new HashMap<>();
    private Station station;
    private Fire fire;
    private ExecutorService service = Executors.newCachedThreadPool();

    public void put(Coordinate coordinate, Node node){
        coordinateNodeMap.put(coordinate,node);
    }

    public boolean setFire(Coordinate coordinate){
        Node node;
        if((node = coordinateNodeMap.get(coordinate))!= null){
            node.ignite();
            fire = new Fire(node);
            return true;
        }
        return false;
    }

    public boolean setStation(Station station){
        if (this.station!=null) return false;
        this.station = station;
        station.addToLog(new LogEntry("FIRE FIRE", "BIATCH"));
        return true;
    }

    public void addEdge(Coordinate node1Coordinate, Coordinate node2Coordinate){
        Node node1 = coordinateNodeMap.get(node1Coordinate);
        Node node2 = coordinateNodeMap.get(node2Coordinate);
        node1.addNeighbor(node2);
        node2.addNeighbor(node1);
    }

    public Map<Coordinate, Node> getCoordinateNodeMap() {
        return coordinateNodeMap;
    }

    public Station getStation() {
        return station;
    }

    /**
     * Return coordinate object for station. This is used to set the color of the station node in the GUI.
     * @return Coordinate of station
     */
    public Coordinate getStationCoordinate(){
        return station.getCoordinate();
    }
    /**
     * start our network by putting all node and agent threads into the runnable state
     * @return
     */
    public boolean startNetwork(){
        Set<Coordinate> keys = coordinateNodeMap.keySet();
        for(Coordinate coordinate : keys){
            service.execute( coordinateNodeMap.get(coordinate) ); //as we iterate through, start the node thread
        }
        Node stationNode = coordinateNodeMap.get( station.getCoordinate() ); //get node at station coords
        Agent firstAgent = new Agent(stationNode); //make our first agent
        stationNode.acceptAgent(firstAgent);  //place it on the station node
        service.execute(firstAgent); //execute the Agent's run()
        return true; //this is not currently informative because it never returns false
    }

    /**
     * start our fire.
     * @return
     */
    public boolean startFire(){
        if(fire != null){ //if setFire successfully set the fire
            service.execute(fire);
            return true;
        }
        return false;
    }

    public StringProperty getEntriesProperty(){
        return station.getEntriesProperty();
    }


}
