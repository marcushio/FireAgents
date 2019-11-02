import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author: Marcus Trujillo
 * @version:
 * brief class description
 */
public class Fire implements Runnable{
    private List<Node> hosts = new ArrayList<>();

    public Fire(Node host){
        hosts.add(host);
    }

    @Override
    public void run(){
        Random random = new Random();
        while(true){ //run while the program is open
            Node fireSource = null;
            List<Node> yellowNeighbors = null;
            Node fireTarget = null;
            while (fireTarget == null) {
                fireSource = hosts.get(random.nextInt(hosts.size()));
                yellowNeighbors = fireSource.getYellowNeighbors();
                if(yellowNeighbors.size()>0) fireTarget = yellowNeighbors.get(0);
            }

            try {
                Thread.sleep(3000); //spread fire every however long
            } catch (InterruptedException ex){
                ex.printStackTrace();
            }
            if(fireTarget.ignite()){ //if we light a neighbor on fire
                hosts.add(fireTarget);   //the fire now lives on the thing we set fire to
               // System.out.println("I'm the fire I just arrived at " + host.getCoordinate().toString());
            }

        }
    }
}
