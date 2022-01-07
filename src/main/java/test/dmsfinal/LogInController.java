package test.dmsfinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    @FXML
    private Button toCreateAccountBtn;

    @FXML
    private Button toAppBtn;

    @FXML
    private TextField checkUsernameField;

    @FXML
    private TextField checkProjectNameField;

    @FXML
    private PasswordField checkPasswordField;

    @FXML
    private Label logWarningLabel;

    public void toApp(ActionEvent actionEvent) throws Exception {

        if (checkUsernameField.getText().isBlank() || checkPasswordField.getText().isBlank() ||
                checkProjectNameField.getText().isBlank()) {

            logWarningLabel.setText("Please fill in all fields");

        } else {

            logWarningLabel.setText("");
            if (JavaPostgres.validateLogIn(checkUsernameField.getText(), checkPasswordField.getText(),
                    checkProjectNameField.getText())) {

                Parent root = FXMLLoader.load(getClass().getResource("TaskManager.fxml"));
                Stage stage = (Stage) toAppBtn.getScene().getWindow();
                stage.setScene(new Scene(root, 800, 500));

            } else {

                logWarningLabel.setText("You entered wrong data, try again");

            }
        }
    }

    public void toSignUp(ActionEvent actionEvent) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("CreateAccount.fxml"));

        Stage stage = (Stage) toCreateAccountBtn.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 500));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
