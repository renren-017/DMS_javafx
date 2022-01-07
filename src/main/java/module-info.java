module test.dmsfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens test.dmsfinal to javafx.fxml;
    exports test.dmsfinal;
}