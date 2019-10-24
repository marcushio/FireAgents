import java.util.List;

/**
 * @author: Marcus Trujillo
 * @version: brief class description
 */
public class NetworkFactory {
    String configPath;


    public NetworkFactory(String configPath) {
        this.configPath = configPath;
        //make fileHandler
    }

    public Network buildNetwork(List<String> config){

        return new Network(); 
    }
}
