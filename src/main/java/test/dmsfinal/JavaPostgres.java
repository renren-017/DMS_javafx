package test.dmsfinal;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class JavaPostgres {

    public static void writeUserToDatabase(String userName, String userPassword, String projectName) {
        String url = "jdbc:postgresql://localhost:5432/TaskManager";
        String user = "postgres";
        String pgPassword = "cantremembershit88";

        String query = "INSERT INTO users.users(username, password, project_name) VALUES(?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, pgPassword);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, userName);
            pst.setString(2, userPassword);
            pst.setString(3, projectName);
            pst.executeUpdate();
            System.out.println("User account successfully created.");

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgres.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public static Boolean validateLogIn(String userName, String userPassword, String projectName) {
        String url = "jdbc:postgresql://localhost:5432/TaskManager";
        String user = "postgres";
        String pgPassword = "cantremembershit88";

        String query = "SELECT count(1) FROM users.users WHERE username = '"
                + userName + "' AND password = '" + userPassword + "' AND project_name = '" + projectName + "'";

        boolean isSignedUp = true;

        try (Connection con = DriverManager.getConnection(url, user, pgPassword);
             PreparedStatement pst = con.prepareStatement(query)) {

            ResultSet res = pst.executeQuery();

            while (res.next()) {
                if (res.getInt(1) == 1) {
                    isSignedUp = true;
                } else isSignedUp = false;
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgres.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return isSignedUp;
    }
}
