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
    List<String> log;

    public Log(){
        this.log = new ArrayList<String>();
    }

    public Log(List<String> log){
        this.log = log;
    }

    /**
     * Add an entry into our log
     * @param entry
     */
    public void addEntry(String entry){
        this.log.add(entry);
    }

}
