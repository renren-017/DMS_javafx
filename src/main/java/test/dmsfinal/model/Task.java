package test.dmsfinal.model;

public class Task {
    String taskName;
    String taskDeadline;
    Boolean isUrgent;
    String taskStatus;

    public Task(String taskName, String taskDeadline, Boolean isUrgent, String taskStatus) {
        this.taskName = taskName;
        this.taskDeadline = taskDeadline;
        this.isUrgent = isUrgent;
        this.taskStatus = taskStatus;
    }
}
