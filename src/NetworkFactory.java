import java.util.List;
import java.util.Scanner;

/**
 * This class is responsible for creating the initial state of a Network.
 * To use it, call the public methods build method with a complete path specified by a string to the config file
 */
public class NetworkFactory {
    Network network = new Network();
    FileHandler handler = new FileHandler();
    String pathToConfigFile;

    public NetworkFactory(String pathToConfigFile){
        this.pathToConfigFile = pathToConfigFile;
    }

    public Network build(){
        handler.setSpecs(pathToConfigFile);
        setNodes(handler.getNodeSpecs());
        setEdges(handler.getEdgeSpecs());
        setStation(handler.getStationSpec());
        setFire(handler.getFireSpec());
        return network;
    }

    private void setFire(String fireSpec) {
        Scanner scanner = new Scanner(fireSpec);
        scanner.next();
        Coordinate coordinate = new Coordinate(scanner.nextInt(),scanner.nextInt());
        network.setFire(coordinate);
    }

    private void setStation(String stationSpec) {
        Scanner scanner = new Scanner(stationSpec);
        scanner.next();
        Coordinate coordinate = new Coordinate(scanner.nextInt(), scanner.nextInt());
        Station station = new Station();
        station.setCoordinate(coordinate);
        network.setStation(station);
    }

    private void setNodes(List<String> nodeSpecs){
        for(String nodeSpec : nodeSpecs){
            Scanner scanner = new Scanner(nodeSpec);
            scanner.next();
            Coordinate coordinate = new Coordinate(scanner.nextInt(), scanner.nextInt());
            Node node = new Node();
            node.setCoordinate(coordinate);
            network.put(coordinate,node);

        }
    }
    private void setEdges(List<String> edgeSpecs){
        for(String edgeSpec : edgeSpecs){
            Scanner scanner = new Scanner(edgeSpec);
            scanner.next();
            Coordinate coordinateA = new Coordinate(scanner.nextInt(), scanner.nextInt());
            Coordinate coordinateB = new Coordinate(scanner.nextInt(), scanner.nextInt());
            network.addEdge(coordinateA,coordinateB);
        }
    }
}
