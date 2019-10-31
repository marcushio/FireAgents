import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main extends Application {
    Network network;
    /**
     * Entry point to this javafx application.
     *
     * @param args A complete path to the configuration txt file that specifies initial conditions for the simulation.
     *             The format will vary depending upon the user's OS and which directory they have the file in.
     */
    public static void main(String[] args) {
        Main main = new Main();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        NetworkFactory factory = new NetworkFactory(getParameters().getUnnamed().get(0));
        network = factory.build();
        View view = new View(primaryStage,network);
        network.startNetwork();
        network.startFire();
    }

}
