import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Translate;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.*;
import java.util.List;
//TODO create code to change colors and also to stroke f
/**
 * @author: Marcus Trujillo
 * @version: 10/22/19
 * The view class is our GUI that visually represents the state of our simulation. It has a log that can scroll on the right
 * and a display of the nodes to the left that can also scroll. Nodes will be placed at given locations and no scaling will be
 * done. Binds the changing components to their counterparts in the Network instance.
 */
public class View{
    private int X_FRONTIER = 1000;
    private int Y_FRONTIER = 1000;
    private int NODE_RADIUS = 10;
    private double SCALE_FACTOR = NODE_RADIUS *3;
    private HBox root;
    private Pane modelDisplay = new Pane();
    private ScrollPane modelScroll = new ScrollPane(modelDisplay);
    private TextArea log;
    private ScrollPane logDisplay;
    private Network network;
    private List<Circle> nodeViews = new ArrayList<>();
    private Group networkShapes = new Group();
    /**
     * Constructor
     * @param primaryStage
     */
    public View(Stage primaryStage, Network network){
        this.network = network;
        Scene scene = new Scene(makeRoot(),primaryStage.getMaxWidth(), primaryStage.getMaxWidth());
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
        makeNodes();
    }

    private BorderPane makeRoot(){
        BorderPane root = new BorderPane();
        root.setRight(makeLog());
        root.setCenter(modelScroll);
        return root;
    }

    private void makeNodes(){
        Map<Coordinate,Node> coordinateNodeMap = network.getCoordinateNodeMap();
        Set<Coordinate> coordinates = coordinateNodeMap.keySet();
        int i = 0; int j = 0;
        for(Coordinate coordinate : coordinates){
            Node ithNode = coordinateNodeMap.get(coordinate);
            Circle newCircle = new Circle(coordinate.getX()*SCALE_FACTOR,coordinate.getY()*SCALE_FACTOR, NODE_RADIUS, Paint.valueOf("blue"));
            newCircle.fillProperty().bind(ithNode.getColor());
            newCircle.strokeProperty().bind(ithNode.getColor());
            nodeViews.add(newCircle);
            networkShapes.getChildren().add(newCircle);
            addOutgoingLines(ithNode);
        }
        Translate translate = new Translate();
        translate.setY(NODE_RADIUS+ networkShapes.getTranslateY());
        networkShapes.getTransforms().add(translate);
        modelDisplay.getChildren().add(networkShapes);

    }

    private double scaleLineCoordinate(double coordinate){
        return (coordinate * SCALE_FACTOR);
    }
    private void addOutgoingLines(Node node) {
        for(Node neighbor : node.getNeighbors()){
            Coordinate neighborCoordinate = neighbor.getCoordinate();
            networkShapes.getChildren().add(new Line(
                    scaleLineCoordinate(node.getCoordinate().getX()),
                    scaleLineCoordinate(node.getCoordinate().getY()),
                    scaleLineCoordinate(neighborCoordinate.getX()),
                    scaleLineCoordinate(neighborCoordinate.getY()))
            );
        }
    }

    private void makeEdges(){}
    private ScrollPane makeLog(){
        TextArea log = new TextArea();
        log.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth()/4);
        log.setPrefHeight(Screen.getPrimary().getVisualBounds().getMaxY());
        log.setWrapText(true);
        ScrollPane logContainer = new ScrollPane(log);
        return logContainer;
    }
    private void bindNodes(){}
    private void bindLog(){}
    private void bindModel(){}
}
