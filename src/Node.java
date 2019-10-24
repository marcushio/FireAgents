import java.util.Observable;
import java.util.concurrent.BlockingQueue;

/**
 * @author: Marcus Trujillo
 * @author: Colton Trujillo
 * @version:
 * Represents the node sensors in the network that can change color.
 */
enum COLOR{RED, YELLOW, BLUE}

public class Node extends Observable implements Runnable{
    COLOR color;
    BlockingQueue messageBuffer;

    public boolean ignite(){
        if(color.equals(COLOR.RED)) return false;
        else{
            sendDeathMessage();
            color = COLOR.RED;
            //set Agent to null? somehow remove agent
        }
        return true;
    }

    public boolean addNeighbor(Node neighbor){

        return false;
    }

    @Override
    public void run(){
        //change color
        //spread message to neighbors if it has it.
    }

    private boolean sendDeathMessage(){
        //tell all neighbors i'm dying :(
        return false;
    }


}
