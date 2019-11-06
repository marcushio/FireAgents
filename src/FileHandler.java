import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Colton Trujillo
 * @version 10/29/2019
 *
 * Handles the parsing of the configuration file.
 */

public class FileHandler {
    private List<String> nodeSpecs = new ArrayList<>();
    private List<String> edgeSpecs = new ArrayList<>();;
    private String stationSpec = "";
    private String fireSpec = "";
    /**
     * Read all lines of file, adding all lines with more than just white-space
     * to a list and returning the list.
     * @return List where each entry is a non-whitespace line from the file
     */
    public void setSpecs(String filePath){
        List<String> lines = new ArrayList<>();
        String  content = "";
        String line;
        try(BufferedReader reader = new BufferedReader((new FileReader(filePath)))){
            while((line = reader.readLine())!=null){
                if(line.matches("node.*")) nodeSpecs.add(line);
                if(line.matches("edge.*")) edgeSpecs.add(line);
                if(line.matches("fire.*")) fireSpec = line;
                if(line.matches("station.*")) stationSpec = line;
            }
        }
        catch(IOException e){
            System.out.println("The path "+filePath+"could not be read");
        }

    }

    /**
     * @return specs about the nodes
     */
    public List<String> getNodeSpecs() {
        return nodeSpecs;
    }

    /**
     * @return the specs about the edges
     */
    public List<String> getEdgeSpecs() {
        return edgeSpecs;
    }

    /**
     *
     * @return specs about the station
     */
    public String getStationSpec() {
        return stationSpec;
    }

    /**
     *
     * @return the specs about the fire
     */
    public String getFireSpec() {
        return fireSpec;
    }
}
