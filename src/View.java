import javafx.application.Application;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * @author: Marcus Trujillo
 * @version: 10/22/19
 * The view class is our GUI that visually represents the state of our simulation
 */
public class View implements Observer {
    private HBox root;
    private GridPane nodeMap;
    private ScrollPane logDisplay;
    private List<String> configuration;

    /**
     * Constructor
     * @param primaryStage
     */
    public View(Stage primaryStage, List<String> configuration){
        root = new HBox();
        nodeMap = new GridPane();
        //setup nodeMap area - maybe just have it return a gridpane...
        initializeMap(configuration);
        logDisplay = new ScrollPane();

    }

    /**
     *
     */
    @Override
    public void update(Observable model, Object arg){
        //if instanceof Node change the node

        //if instanceof agent change the object
    }

    /**
     *
     */
    private void initializeMap(List<String> configuration){//might return something

    }
}
