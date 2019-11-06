import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Marcus Trujillo
 * @author: Colton Trujillo
 * @version: 10/28/2019
 * Our log for our simulation. Entries are created whenever an agent makes a copy of itself. This is the collection of
 * all our entries.
 */
public class Log {
    List<String> entries = new ArrayList<>();
    SimpleStringProperty lastEntry = new SimpleStringProperty();

    /**
     * Add an entry into our log
     * @param entry
     */
    public void addEntry(String entry){
        lastEntry.set(entry);
        entries.add(entry);
    }

    /**
     *
     * @return the last entry
     */
    public StringProperty getEntriesProperty(){
        return lastEntry;
    }

}
