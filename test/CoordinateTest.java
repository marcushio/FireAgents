import org.junit.Test;
import static org.junit.Assert.*;

public class CoordinateTest {
    @Test
    public void equals(){
    Coordinate same1 = new Coordinate(1,2);
    Coordinate same2 = new Coordinate (1,2);
    Coordinate diff1 = new Coordinate (5,8);
    assertTrue(same1.equals(same2));
    assertFalse(same2.equals(diff1));
    }
}
