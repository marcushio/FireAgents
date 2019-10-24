public class Station {
    private Log log = new Log();
    private Coordinate coordinate;
    public void addToLog(LogEntry entry){
        log.addEntry(entry);
    }
}
