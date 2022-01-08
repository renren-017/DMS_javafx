package test.dmsfinal.model;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import test.dmsfinal.JavaPostgres;
import test.dmsfinal.LogInController;

import java.util.Locale;

public class Task {
    private String taskName;
    private String taskDeadline;
    private String taskStatus;
    private Boolean isUrgent;
    private String projectName;
    public Button deleteBtn;
    private VBox unsorted;
    private VBox inProcess;
    private VBox finished;
    private VBox taskVBox;


    public void setTaskVBox(VBox vBox) {
        this.taskVBox = vBox;
    }

    public Task(String taskName, String taskDeadline, String taskStatus, Boolean isUrgent, VBox taskVBox, VBox unsorted,
                VBox inProcess, VBox finished, String projectName) {
        this.taskName = taskName;
        this.taskDeadline = taskDeadline;
        this.isUrgent = isUrgent;
        this.unsorted = unsorted;
        this.inProcess = inProcess;
        this.finished = finished;
        this.taskVBox = taskVBox;
        this.projectName = projectName;
        this.taskStatus = taskStatus;
    }


    public void draw(Boolean makeUnsorted, Boolean makeInProcess, Boolean makeFinished) {

        VBox taskBar = new VBox(180);
        taskBar.setBackground(new Background(new BackgroundFill(Color.color(0.7, 0.7, 0.7),
                new CornerRadii(5), Insets.EMPTY)));
        taskBar.setPadding(new Insets(2));
        taskBar.setSpacing(3);

        HBox btns = new HBox(180);
        btns.setBackground(new Background(new BackgroundFill(Color.color(0.7, 0.7, 0.7),
                CornerRadii.EMPTY, Insets.EMPTY)));
        btns.setPadding(new Insets(2));
        btns.setSpacing(2);


        Label task = new Label(taskName);
        task.setFont(Font.font("System", FontWeight.BOLD, 13));

        Label deadline = new Label(taskDeadline);
        deadline.setFont(Font.font("System", FontWeight.NORMAL, 12));

        taskVBox.getChildren().add(taskBar);
        taskBar.getChildren().add(task);
        taskBar.getChildren().add(deadline);

        if (isUrgent) {
            Label urgent = new Label("urgent".toUpperCase(Locale.ROOT));
            urgent.setTextFill(Color.color(0.568, 0.121, 0.188));
            urgent.setFont(Font.font("System", FontWeight.BOLD, 12));
            taskBar.getChildren().add(urgent);
        }


        deleteBtn = new Button("Delete Task");
        deleteBtn.setBackground(new Background (new BackgroundFill(Color.color(0.88, 0.88, 0.88),
                new CornerRadii(0), new Insets(0,4, 0, 0))));
        deleteBtn.setFont(Font.font("System", FontWeight.NORMAL, 10));
        deleteBtn.setOnAction( e -> {
            JavaPostgres.deleteTaskFromDatabase(taskName, taskDeadline, projectName);
        });
        taskBar.getChildren().add(btns);
        btns.getChildren().add(deleteBtn);

        if (makeUnsorted) {
            Button makeUnsortedBtn = new Button("");
            if (taskVBox == finished) {
                makeUnsortedBtn.setText("<-");
            } else { makeUnsortedBtn.setText("<<-"); }
            btnDesign(makeUnsortedBtn, unsorted);
            btns.getChildren().add(makeUnsortedBtn);
        }

        if (makeInProcess) {
            Button makeInProcessBtn = new Button("");
            if (taskVBox == unsorted) {
                makeInProcessBtn.setText("->");
            } else { makeInProcessBtn.setText("<-"); }
            btnDesign(makeInProcessBtn, unsorted);
            btns.getChildren().add(makeInProcessBtn);
        }

        if (makeFinished) {
            Button makeFinishedBtn = new Button("");
            if (taskVBox == unsorted) {
                makeFinishedBtn.setText("->>");
            } else { makeFinishedBtn.setText("->"); }
            btnDesign(makeFinishedBtn, finished);
            btns.getChildren().add(makeFinishedBtn);
        }


    }

    private void btnDesign(Button selectedBtn, VBox selectedVbox) {
        selectedBtn.setBackground(new Background(new BackgroundFill(Color.color(0.88, 0.88, 0.88),
                new CornerRadii(0), new Insets(0, 4, 0, 0))));
        selectedBtn.setFont(Font.font("System", FontWeight.NORMAL, 10));
        selectedBtn.setOnAction(e -> {
            setTaskVBox(selectedVbox);
            if (selectedVbox == unsorted) {
                draw(false, true, true);
            } else if (selectedVbox == inProcess) {
                draw(true, false, true);
            } else draw(true, true, false);
        });
    }
}
