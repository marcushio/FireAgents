import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

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
    ListProperty<String> entries = new SimpleListProperty<>();

    /**
     * Add an entry into our log
     * @param entry
     */
    public void addEntry(String entry){
        entries.add(entry);
    }

    public ListProperty<String> getEntriesProperty(){
        return entries;
    }

}
