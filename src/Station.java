public class Station {
    private Log log = new Log();
    private Coordinate coordinate;

    /**
     * Add entry to this Station's log
     * @param entry LogEntry instance containing log info
     */
    public void addToLog(LogEntry entry){
        log.addEntry(entry);
    }

    /**
     * Set this instance's coordinate
     * @param coordinate
     */
    public void setCoordinate(Coordinate coordinate){
        this.coordinate = coordinate;
    }
}
