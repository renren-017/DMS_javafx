package test.dmsfinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import test.dmsfinal.model.Task;
import test.dmsfinal.model.User;
import java.net.URL;
import java.util.*;

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

    @FXML
    private VBox unsorted;

    @FXML
    private VBox in_progress;

    @FXML
    private VBox finished;


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
            boolean taskUrgency = (isUrgentRadioBtns.getSelectedToggle() == isUrgentRadioBtn);

            String taskStatus;
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

            vBoxesUpdate();
        }
    }

    private void vBoxesUpdate() {
        unsorted.getChildren().clear();
        in_progress.getChildren().clear();
        finished.getChildren().clear();

        int listLength = ((List<String>) JavaPostgres.retrieveTasksFromDatabase(user.projectName,
                1)).size();

        for (int j = 0; j < listLength; j++) {
            List<String> taskProps = new ArrayList<>();
            for (int i = 2; i < 6; i++) {
                taskProps.add(((List<String>) JavaPostgres.retrieveTasksFromDatabase(user.projectName,
                        i)).get(j));
            }
            String taskName = taskProps.get(0);
            String taskDeadline = taskProps.get(1);
            boolean taskUrgency = (taskProps.get(2).equals("t"));
            String taskStatus = taskProps.get(3);

            Task task = new Task(taskName, taskDeadline, taskStatus, taskUrgency, unsorted, unsorted, in_progress,
                    finished, user.projectName);

            Task.vBoxFill(taskStatus, task, unsorted, in_progress, finished);
        }
    }


    public void startApp(ActionEvent actionEvent) {
        projectNameLabel.setText(user.projectName.toUpperCase(Locale.ROOT));

        vBoxesUpdate();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
