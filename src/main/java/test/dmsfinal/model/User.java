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


    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {

        this.projectName = projectName;
    }
}
