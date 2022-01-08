package test.dmsfinal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class JavaPostgres {


    public static void writeUserToDatabase(String userName, String userPassword, String projectName) {
        String url = "jdbc:postgresql://localhost:5432/TaskManager";
        String user = "postgres";
        String pgPassword = "cantremembershit88";

        String query2 = "INSERT INTO users.users(username, pass, project_name) VALUES('" + userName + "', '" +
                userPassword + "', '" + projectName + "')";
        String query1 = "INSERT INTO users.projects(project_name) VALUES('" + projectName + "')";

        try (Connection con = DriverManager.getConnection(url, user, pgPassword);
             PreparedStatement pst = con.prepareStatement(query1);
             PreparedStatement pst2 = con.prepareStatement(query2)

        ) {

            pst.executeUpdate();
            pst2.executeUpdate();
            System.out.println("User account successfully created.");

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgres.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }


    public static Boolean validateLogIn(String userName, String userPassword, String projectName) {

        String query = "SELECT count(1) FROM users.users WHERE username = '"
                + userName + "' AND pass = '" + userPassword + "' AND project_name = '" + projectName + "'";

        boolean isSignedUp = true;

        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TaskManager",
                "postgres", "cantremembershit88");
             PreparedStatement pst = con.prepareStatement(query)) {

            ResultSet res = pst.executeQuery();

            while (res.next()) {
                isSignedUp = res.getInt(1) == 1;
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgres.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return isSignedUp;
    }


    public static void writeTaskToDatabase(String taskName, String taskDeadline, boolean isUrgent,
                                           String taskStatus, String projectName) {

        String query = "INSERT INTO users.tasks (task, due_to, is_urgent, status, project_name) VALUES ('" +
                taskName + "', '" + taskDeadline + "', '" + isUrgent + "', '" + taskStatus + "', '"
                + projectName + "')";

        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TaskManager",
                "postgres", "cantremembershit88");
             PreparedStatement pst = con.prepareStatement(query);
        ) {
            pst.executeUpdate();
            System.out.println("Task successfully created");

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgres.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public static Object retrieveTasksFromDatabase(String project_name, Integer column_idx) {

        String query = "SELECT * FROM users.tasks WHERE project_name = '" + project_name + "'";

        List<String> taskProp = new ArrayList<String>();

        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TaskManager",
                "postgres", "cantremembershit88");
             PreparedStatement pst = con.prepareStatement(query);
        ) {

            ResultSet rsData = pst.executeQuery();
            while (rsData.next()) {
                taskProp.add(rsData.getString(column_idx));
            }
            return taskProp;


        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgres.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return taskProp;
    }

    public static void deleteTaskFromDatabase(String taskName, String taskDeadline, String projectName) {

        String query = "DELETE FROM users.tasks WHERE task = '" + taskName + "' AND due_to = '" +
                taskDeadline + "' AND project_name = '" + projectName + "'";

        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TaskManager",
                "postgres", "cantremembershit88");
             PreparedStatement pst = con.prepareStatement(query);
        ) {
            pst.executeUpdate();
            System.out.println("Task successfully deleted");

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgres.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}


