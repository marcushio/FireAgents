import java.util.ArrayList;
import java.util.List;

/**
 * @author: Marcus Trujillo
 * @author: Colton Trujillo
 * @version:
 * Our log for our simulation. Entries are created whenever an agent makes a copy of itself. This is the collection of
 * all our entries.
 */
public class Log {
    List<LogEntry> log;

    public Log(){
        this.log = new ArrayList<LogEntry>();
    }
    public Log(List<LogEntry> log){
        this.log = log;
    }
    /**
     * Add an entry into our log
     * @param entry
     */
    public void addEntry(LogEntry entry){
        this.log.add(entry);
    }

}
