package test.dmsfinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    @FXML
    private Button toCreateAccountBtn;

    @FXML
    private Button toApp;

    public void toApp() {
        System.out.println("App is compiling");
    }

    public void toSignUp(ActionEvent actionEvent) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("createAccount.fxml"));

        Stage stage = (Stage) toCreateAccountBtn.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 500));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
