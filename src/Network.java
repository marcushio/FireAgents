import java.util.Map;

/**
 * @author: Marcus Trujillo
 * @version:
 * brief class description
 */
public class Network {
    private Map<Coordinate, Node> coordinateNodeMap;
    private Station station;

    public void put(Coordinate coordinate, Node node){
        coordinateNodeMap.put(coordinate,node);
    }
    public boolean setFire(Coordinate coordinate){
        Node node;
        if((node = coordinateNodeMap.get(coordinate))!= null){
            node.ignite();
            return true;
        }
        return false;
    }

    public boolean setStation(Station station){
        if (this.station!=null) return false;
        this.station = station;
        return true;
    }

    public void addEdge(Coordinate node1Coordinate, Coordinate node2Coordinate){
        Node node1 = coordinateNodeMap.get(node1Coordinate);
        Node node2 = coordinateNodeMap.get(node2Coordinate);
        node1.addNeighbor(node2);
        node2.addNeighbor(node1);
    }
    //this is probably going to be the home of our threadpool cached is likely going to be our choice


}
