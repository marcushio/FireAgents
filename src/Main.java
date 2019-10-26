import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;

public class Main {//extends Application {

    /**
     * Entry point to this javafx application.
     *
     * @param args A complete path to the configuration txt file that specifies initial conditions for the simulation.
     *             The format will vary depending upon the user's OS and which directory they have the file in.
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.run(args);
    }

    //@Override
    public void start(Stage primaryStage) {

    }

    private void run(String[] args){
        NetworkFactory factory = new NetworkFactory(args[0]);
        Network network = factory.build();
       // launch(args);
    }
}
