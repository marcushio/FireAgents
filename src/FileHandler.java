import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    /**
     * Read all lines of file, adding all lines with more than just white-space
     * to a list and returning the list.
     * @return List where each entry is a non-whitespace line from the file
     */
    public List<String> getLines(String filePath){
        List<String> lines = new ArrayList<>();
        String  content = "";
        String line;
        try(BufferedReader reader = new BufferedReader((new FileReader(filePath)))){
            while((line = reader.readLine())!=null){
                if(line.matches(".*\\S.*")) lines.add(line);
            }
        }
        catch(IOException e){
            System.out.println("The path "+filePath+"could not be read");
        }
        return lines;
    }
}
