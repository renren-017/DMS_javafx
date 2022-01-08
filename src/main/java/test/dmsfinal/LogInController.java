package test.dmsfinal;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import test.dmsfinal.model.User;
import java.net.URL;
import java.util.Locale;
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

    @FXML
    private Button logOutBtn;

    @FXML
    public Button addTaskBtn;

    @FXML
    private TextField setTaskField;

    @FXML
    private TextField setDeadlineField;

    @FXML
    private ToggleGroup isUrgentRadioBtns;

    @FXML
    private ToggleGroup setStatusRadioBtns;

    @FXML
    private RadioButton isUrgentRadioBtn;

    @FXML
    private RadioButton setStatusUnsortedRadioBtn;

    @FXML
    private RadioButton setStatusInProgressRadioBtn;

    @FXML
    private Label taskWarningLabel;

    @FXML
    private Label projectNameLabel;


    static User user = new User("null", "null");

    public void toApp(ActionEvent actionEvent) throws Exception {

        if (checkUsernameField.getText().isBlank() || checkPasswordField.getText().isBlank() ||
                checkProjectNameField.getText().isBlank()) {
            logWarningLabel.setText("Please fill in all fields");

        } else {
            logWarningLabel.setText("");
            if (JavaPostgres.validateLogIn(checkUsernameField.getText(), checkPasswordField.getText(),
                    checkProjectNameField.getText())) {
                user.setUserInfo(checkUsernameField.getText(), checkProjectNameField.getText());

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


    public void logOut(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        Stage stage = (Stage) logOutBtn.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 500));
    }


    public void addTask(ActionEvent actionEvent) {

        if (isUrgentRadioBtns.getSelectedToggle() == null || setStatusRadioBtns.getSelectedToggle() == null ||
                setTaskField.getText().isBlank() || setDeadlineField.getText().isBlank()) {
            taskWarningLabel.setText("Please fill in all fields");

        } else {
            boolean taskUrgency = (isUrgentRadioBtns.getSelectedToggle() == isUrgentRadioBtn ? true : false);

            String taskStatus = "";
            if (setStatusRadioBtns.getSelectedToggle() == setStatusUnsortedRadioBtn) {
                taskStatus = "unsorted";
            } else if (setStatusRadioBtns.getSelectedToggle() == setStatusInProgressRadioBtn) {
                taskStatus = "in_progress";
            } else {
                taskStatus = "finished";
            }

            JavaPostgres.writeTaskToDatabase(
                    setTaskField.getText(),
                    setDeadlineField.getText(),
                    taskUrgency,
                    taskStatus,
                    user.getProjectName());

            setTaskField.clear();
            setDeadlineField.clear();
            isUrgentRadioBtns.getSelectedToggle().setSelected(false);
            setStatusRadioBtns.getSelectedToggle().setSelected(false);
        }
    }


    public void startApp(ActionEvent actionEvent) {
        projectNameLabel.setText(user.projectName.toUpperCase(Locale.ROOT));

        // retrieve tasks
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
