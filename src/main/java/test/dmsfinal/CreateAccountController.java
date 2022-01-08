package test.dmsfinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {

    @FXML
    private TextField projectNameField;

    @FXML
    public TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label warningLabel;

    @FXML
    private Button toSignInBtn;

    @FXML
    private Button toCreateAccountBtn;


    public void toSignIn(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        Stage stage = (Stage) toSignInBtn.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 500));
    }


    public void setUserData(ActionEvent actionEvent) throws Exception {

        if (usernameField.getText().isBlank() || passwordField.getText().isBlank() ||
                projectNameField.getText().isBlank()) {
            warningLabel.setText("Please fill in all fields");
        } else {
            warningLabel.setText("");

//            JavaPostgres.writeUserToDatabase(usernameField.getText(),
//                    passwordField.getText(), projectNameField.getText());

            System.out.println("User " + usernameField.getText() + " successfully created");

            Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
            Stage stage = (Stage) toCreateAccountBtn.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 500));
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}