/**
 * @author: Marcus Trujillo
 * @version: 10/21/19
 * Log entry is what populates the log. It's made by the agent whenever it makes a copy and includes the name of the agent
 * that copied it and the location of the node the agent was on when it made the copy.
 */
public class LogEntry {
    String id;
    String location;
    //Node sender; might be useful so that nodes know not to send it back to it's sender

    /**
     *
     * @param id
     * @param location
     */
    public LogEntry(String id, String location){
        this.id = id;
        this.location = location;
    }

    @Override
    public String toString(){
        return "Agent ID: " + id + "\nAgent Location: " + location + "\n" ;
    }
}
