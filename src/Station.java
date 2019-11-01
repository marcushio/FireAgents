import javafx.beans.property.ListProperty;

public class Station {
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

    public ListProperty<String> getEntriesProperty(){
        return log.getEntriesProperty();
    }
}
