package test.dmsfinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class TaskManager extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        stage.setTitle("Task Manager 1.0");
        stage.setScene(new Scene(root, 800, 500));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/alatoologo.png")));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}