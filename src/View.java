import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextAreaBuilder;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
import java.util.*;
import java.util.List;

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
    private int NODE_SIZE = 20;
    private HBox root;
    private Canvas nodeMap;
    private TextArea log;
    private ScrollPane logDisplay;
    private Network network;
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
        root.setCenter(makeNodeMap());
        return root;
    }
    private ScrollPane makeNodeMap(){
        nodeMap = new Canvas();
        nodeMap.setHeight(X_FRONTIER);
        nodeMap.setWidth(Y_FRONTIER);
        ScrollPane mapContainer = new ScrollPane(nodeMap);
        return mapContainer;
    }
    private void makeNodes(){
        Map<Coordinate,Node> coordinateNodeMap = network.getCoordinateNodeMap();
        Set<Coordinate> coordinates = coordinateNodeMap.keySet();
        int i = 0; int j = 0;
        for(Coordinate coordinate : coordinates){
            GraphicsContext gc = nodeMap.getGraphicsContext2D();
            gc.setFill(Paint.valueOf("Blue"));
            gc.fillOval(coordinate.getX()*NODE_SIZE,coordinate.getY()*NODE_SIZE, NODE_SIZE, NODE_SIZE);
            drawLinesToNeighbors(gc, coordinate, coordinateNodeMap.get(coordinate).getNeighbors());
        }

    }

    private void drawLinesToNeighbors(GraphicsContext gc, Coordinate startCoordinate, List<Node> neighbors) {
        for(Node neighbor : neighbors){
            Coordinate neighborCoordinate = neighbor.getCoordinate();
            gc.setStroke(Paint.valueOf("black"));
            gc.strokeLine(startCoordinate.getX(),startCoordinate.getY(), neighborCoordinate.getX(), neighborCoordinate.getY());
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
