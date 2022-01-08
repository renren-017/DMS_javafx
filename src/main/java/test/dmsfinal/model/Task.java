package test.dmsfinal.model;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Locale;

public class Task {
    private String taskName;
    private String taskDeadline;
    private Boolean isUrgent;
    private VBox vBox;

    public Task(String taskName, String taskDeadline, Boolean isUrgent, VBox vBox) {
        this.taskName = taskName;
        this.taskDeadline = taskDeadline;
        this.isUrgent = isUrgent;
        this.vBox = vBox;
    }

    public void draw() {

        VBox taskBar = new VBox(180);
        taskBar.setBackground(new Background(new BackgroundFill(Color.color(0.7, 0.7, 0.7),
                new CornerRadii(5), Insets.EMPTY)));
        taskBar.setPadding(new Insets(2));
        taskBar.setSpacing(3);

        Label task = new Label(taskName);
        task.setFont(Font.font("System", FontWeight.BOLD, 13));

        Label deadline = new Label(taskDeadline);
        deadline.setFont(Font.font("System", FontWeight.NORMAL, 12));

        vBox.getChildren().add(taskBar);
        taskBar.getChildren().add(task);
        taskBar.getChildren().add(deadline);

        if (isUrgent) {
            Label urgent = new Label("urgent".toUpperCase(Locale.ROOT));
            urgent.setTextFill(Color.color(0.568, 0.121, 0.188));
            urgent.setFont(Font.font("System", FontWeight.BOLD, 12));
            taskBar.getChildren().add(urgent);
        }

    }
}
