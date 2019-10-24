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
    BlockingQueue messageBuffer;

    private void lastMessage(){
        //tell all neighbors i'm dying :(
    }

    @Override
    public void run(){
        //change color
        //spread message to neighbors if it has it.
    }
}
