import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author: Marcus Trujillo
 * @version: 11/01/2019
 *
 * Spreads after a fixed interval to other nodes, igniting them.
 */

public class Fire implements Runnable{
    private List<Node> hosts = new ArrayList<>();

    /**
     * add a host for this fire
     * @param host
     */
    public Fire(Node host){
        hosts.add(host);
    }

    @Override
    public void run(){
        while(true){ //run while the program is open
            try {
                Thread.sleep(2539); //spread fire every however long
            } catch (InterruptedException ex){
                ex.printStackTrace();
            }
            Node fireTarget = chooseTarget();
            if(fireTarget.ignite()){ //if we light a neighbor on fire
                hosts.add(fireTarget);   //the fire now lives on the thing we set fire to
            }
        }
    }

    private Node chooseTarget(){
        Random random = new Random();
        Node fireSource = null;
        List<Node> yellowNeighbors = null;
        Node fireTarget = null;
        while (fireTarget == null) {
            fireSource = hosts.get(random.nextInt(hosts.size()));
            yellowNeighbors = fireSource.getYellowNeighbors();
            if(yellowNeighbors.size()>0) fireTarget = yellowNeighbors.get(0);
        }
        return fireTarget;
    }

}
