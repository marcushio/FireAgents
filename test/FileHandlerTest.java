import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FileHandlerTest {

    @Test
    public void getContentsAsString() {
        FileHandler handler = new FileHandler();
        List<String> lineList = handler.getLines("C:\\config\\config.txt");
        String lines = "";

        for(String line : lineList)lines+= line + "\n";
        assertEquals(
                "node 0 0\n" +
                        "node 3 0\n" +
                        "node 5 0\n" +
                        "node 7 0\n" +
                        "node 8 1\n" +
                        "node 1 1\n" +
                        "node 2 2\n" +
                        "node 4 1\n" +
                        "node 6 1\n" +
                        "node 7 2\n" +
                        "edge 0 0 3 0\n" +
                        "edge 1 1 0 0\n" +
                        "edge 1 1 2 2\n" +
                        "edge 2 2 3 0\n" +
                        "edge 2 2 4 1\n" +
                        "edge 3 0 5 0\n" +
                        "edge 5 0 4 1\n" +
                        "edge 5 0 6 1\n" +
                        "edge 5 0 7 0\n" +
                        "edge 4 1 6 1\n" +
                        "edge 6 1 7 2\n" +
                        "edge 7 2 7 0\n" +
                        "edge 7 0 8 1\n" +
                        "edge 8 1 7 2\n" +
                        "station 0 0\n" +
                        "fire 8 1\n", lines);
        assertEquals(26, lineList.size());
    }

}