package test.dmsfinal.model;

public class User {
    public String userName;
    public String projectName;

    public User(String userName, String projectName) {
        this.userName = userName;
        this.projectName = projectName;
    }

    public void setUserInfo(String userName, String projectName) {

        this.userName = userName;
        this.projectName = projectName;
    }


    public String getProjectName() {
        return projectName;
    }
}
