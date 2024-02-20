package Assignments.A1_8Puzzles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main Scene that is ran by GUILauncher to fix VM argument requirements.
 *
 * @author Jonathan Turner
 * @version Spring 2024
 */
public class MainScene extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/A1/view/BoardView.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/A1/view/styles.css").toExternalForm());

        stage.setTitle("Jonathan Turner - CS3642 - Assignment 1");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
