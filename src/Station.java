import javafx.beans.property.ListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.util.List;

public class Station extends Node {
    private Log log = new Log();
    private Coordinate coordinate;

    /**
     * Add entry to this Station's log
     * @param entry LogEntry instance containing log info
     */
    public void addToLog(LogEntry entry){
        log.addEntry(entry.toString());
    }

    /**
     * Set this instance's coordinate
     * @param coordinate
     */
    public void setCoordinate(Coordinate coordinate){
        this.coordinate = coordinate;
    }

    /**
     * get this station's location
     */
    public Coordinate getCoordinate(){ return coordinate; }

    public StringProperty getEntriesProperty(){
        return log.getEntriesProperty();
    }

    @Override
    public void run(){
        while(!super.getMessageBuffer().isEmpty()){
            try {
            addToLog( super.getMessageBuffer().take() );
            wait();
            } catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }

}
