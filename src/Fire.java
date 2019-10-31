import java.util.Random;

/**
 * @author: Marcus Trujillo
 * @version:
 * brief class description
 */
public class Fire implements Runnable{
    private Node host;

    public Fire(Node host){
        this.host = host;
        host.ignite(); //this might be redundant cuz I'm not sure if you've set fire in the initial setup of the network 
    }

    @Override
    public void run(){
        Random random = new Random();
        while(true){ //run while the program is open
            Node fireTarget = host.getNeighbors().get(random.nextInt(host.numNeighbors()));
            if(fireTarget.ignite()){ //if we light a neighbor on fire
                host = fireTarget;   //the fire now lives on the thing we set fire to
            }
            try {
                Thread.sleep(3000); //spread fire every 3 seconds
            } catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }
}
