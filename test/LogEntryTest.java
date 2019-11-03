import org.junit.Test;
import sun.rmi.runtime.Log;

/**
 * @author: Marcus Trujillo
 * @version:
 * Test the functionality of our Log Entry Class
 */
public class LogEntryTest {

    @Test
    public void toStringTest(){
        LogEntry entry = new LogEntry("123ABC", new Coordinate(8,7));
        assert(entry.id.equals("123ABC"));
        assert(entry.location.equals("8,7"));
        assert( entry.toString().equals("Agent ID: 123ABC\nAgent Location: 8,7\n") );
    }

}
